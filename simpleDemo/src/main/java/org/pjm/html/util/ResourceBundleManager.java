package org.pjm.html.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UTFDataFormatException;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle; 
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;



import org.pjm.html.util.PropertiesEncodingInputStream;






/**
 * ResourceBundleManager provides an alternative to
 * {@link ResourceBundle#getBundleImpl(String, Locale, ClassLoader)}. It
 * exists for several reasons:
 * <ol>
 * <li>We want to load our resources from the docroot, which is not in our
 * classpath. ResourceBundle can only load from the classpath.</li>
 * <li>We want to allow for new bundle .properties files to be hot-deployed.
 *  ResourceBundle has caching, but no API to expire entries from its
 * cache.</li>
 * <li>We want our ResourceBundles to be UTF-8 encoded .properties files.
 * ResourceBundle can only handle Latin1-encoded .properties files.</li>
 * </ol>
 *
 * <p>This class is abstract and has no knowledge of how to open any
 * InputStreams.
 *
 */
public abstract class ResourceBundleManager
{
	/** base path of all of our bundles */
	private String mRoot;
	/** map of BundleCacheKeys to ResourceBundles */
	private Map<BundleCacheKey, ResourceBundle> mBundles;
	/** timestamp of when the bundle cache should be emptied */
	private long mNextLoadTime;
	/** should we even use the bundle cache? */
	private boolean mCacheEnabled;
	/** configured cache refresh interval */
	private long mReloadInterval;

	/** standard bundle file suffix */
	private static final String SUFFIX = ".properties";

	/** environment property which enables bundle caching */
	protected static final String CONFIG_ENABLE_CACHE = "resourcebundle.cache";

	/** environment property which controls cache refresh interval */
	protected static final String CONFIG_REFRESH_INTERVAL = "resourcebundle.refresh";

	/** default reload interval -- 15 minutes */
	protected static final long DEFAULT_RELOAD_INTERVAL = 60 * 15;

	private static final Logger log = Logger.getLogger(ResourceBundleManager.class
			.getName());

	protected ResourceBundleManager(String root)
	{
		mRoot = root;

		// Use a ConcurrentHashMap, to allow for multiple threads to read
		// AND write to the cache at the same time. In the worst case, we may
		// occasionally have several threads reloading the same bundle, but
		// it's worth it to avoid having this be a bottleneck across the
		// entire JVM.
		// One possible future enhancement is to use WeakReferences
		// (or SoftReferences?) to our ResourceBundles, to protect against
		// unbounded cache growth. That should wait until it proves to
		// be a problem, however.
		mBundles = new ConcurrentHashMap<BundleCacheKey, ResourceBundle>();
	}

	protected void init(boolean cacheEnabled, long reloadInterval)
	{
		mCacheEnabled = cacheEnabled;
		mReloadInterval = reloadInterval;
	}

	protected ResourceBundle getBundle(BundleRequest request,
                                       String baseName, Locale locale, boolean fallbackToDefault)
	{
		expireCache();

		// Check the cache first.
		BundleCacheKey key = new BundleCacheKey(baseName, locale);
		ResourceBundle bundle = mBundles.get(key);
		if (bundle == null)
		{
			// Not in the cache, so load it.
			bundle = recursivelyGetBundle(request, baseName, locale, fallbackToDefault);
			if (bundle == null)
			{
				// this is likely due to new DO partition that has been introduced without having localized descriptions
				// of its DOs stored in resource bundle, neither it was marked as one of partitions to load from DB
				// via props/env/def.properties#item.localized.resourcebundle.exception.partitions
				throw new MissingResourceException("can't find resources for " + baseName,
						getClass().getName(), baseName + " " + locale);
			}
			// Cache it.
			if (mCacheEnabled)
			{
				mBundles.put(key, bundle);
			}
		}
		return bundle;
	}

	/**
	 * See if it's time to empty out the cache. The cache is emptied
	 * periodically to allow for bundles to be hot-deployed.
	 * <p>Note the intentional lack of synchronization here. It's OK if two
	 * threads decide to empty the cache at the same time, just as it's OK
	 * if two threads try to cache the same entry. In either case, the last
	 * thread wins.
	 */
	private void expireCache()
	{
		if (mCacheEnabled)
		{
			long now = System.currentTimeMillis();
			if (now > mNextLoadTime)
			{
				mNextLoadTime = now + mReloadInterval;
				mBundles.clear();
			}
		}
	}

	/**
	 * Actually loads a resource bundle, using the specified locale. This
	 * method calls itself recursively to find the "parent" bundles with
	 * increasingly less specific locales.
	 *
	 * @param request BundleRequest providing extra information about how
	 * to load the bundle
	 * @param baseName base resource name
	 * @param locale desired locale
     * @param fallbackToDefault
	 * @return loaded ResourceBundle
	 * @throws MissingResourceException if the resource could not be found
	 */
	private ResourceBundle recursivelyGetBundle(BundleRequest request,
                                                String baseName, Locale locale, boolean fallbackToDefault)
	{
		try
		{
			// Try to find the parent resource bundle. The parent of this
			// bundle is the one with a more "general" locale.
			Locale generalizedLocale = LangCodeUtil.generalizeLocale(locale, fallbackToDefault);
			ResourceBundle parent = (generalizedLocale != null) ?
				recursivelyGetBundle(request, baseName, generalizedLocale, fallbackToDefault) : null;

			ResourceBundle bundle = loadLocaleBundle(request, parent,
					baseName, locale);
			return (bundle != null) ? bundle : parent;

		}
		catch (UTFDataFormatException e)
		{
			// Malformed .properties file
			log.warning("error loading resources"+baseName + " " + locale+e);
			return null;
		}
		catch (IOException e)
		{
			throw new MissingResourceException(
					"error loading resources for " + baseName,
					getClass().getName(), baseName + " " + locale);
		}
	}

	/**
	 * Loads a resource bundle.
	 *
	 * @param request BundleRequest providing extra information about how
	 * to load the bundle
	 * @param parent ResourceBundle which should be this bundle's parent
	 * @param key base path of resource
	 * @param locale desired Locale
	 * @return loaded resource bundle, or null if not found
	 * @throws IOException if there was an error reading the file
	 */
	private ResourceBundle loadLocaleBundle(BundleRequest request,
			ResourceBundle parent, String key, Locale locale)
			throws IOException
	{
		String lang = locale.getLanguage();
		String country = locale.getCountry();
		String variant = locale.getVariant();

		if (variant.length() > 0)
		{
			return loadBundle(request, parent,
					key + "_" + lang + "_" + country + "_" + variant, locale);
		}

		if (country.length() > 0)
		{
			return loadBundle(request, parent,
					key + "_" + lang + "_" + country, locale);
		}

		if (lang.length() > 0)
		{
			return loadBundle(request, parent, key + "_" + lang, locale);
		}
		
		return loadBundle(request, parent, key, locale);
	}

	/**
	 * Attempts to load a resource bundle.
	 *
	 * @param request BundleRequest providing extra information about how
	 * to load the bundle
	 * @param parent ResourceBundle which should be this bundle's parent
	 * @param key path of resource, including locale suffix
	 * @param locale desired Locale
	 * @return loaded resource bundle, or null if not found
	 * @throws IOException if there was an error reading the file
	 */
	private ResourceBundle loadBundle(BundleRequest request,
			ResourceBundle parent, String key, Locale locale)
		throws IOException
	{
		String path = getFullPath(key + SUFFIX);
		InputStream in = request.openResource(path);
		if (in == null)
		{
			return null;
		}

		log.log(Level.FINEST, "ResourceBundleManager: loading " + key);

		ResourceBundle bundle = new UTF8PropertiesResourceBundle(path, in,
				parent, locale);
		in.close();
		return bundle;
	}

	private String getFullPath(String resourceName)
	{
		if (!resourceName.startsWith("/"))
		{
			resourceName = "/" + resourceName;
		}

		return mRoot + resourceName;
	}

	private static class BundleCacheKey
	{
		private String mBaseName;
		private Locale mLocale;

		public BundleCacheKey(String baseName, Locale locale)
		{
			mBaseName = baseName;
			mLocale = locale;
		}

		public boolean equals(Object obj)
		{
			BundleCacheKey other = (BundleCacheKey)obj;
			return mBaseName.equals(other.mBaseName) &&
					mLocale.equals(other.mLocale);
		}

		public int hashCode()
		{
			return mBaseName.hashCode() ^ mLocale.hashCode();
		}
	}

	/**
	 * Helper class to do the dirty work of actually opening streams.
	 */
	public abstract static class BundleRequest
	{
		public abstract InputStream openResource(String path);
	}

	/**
	 * The {@link ResourceBundle} class doesn't provide a public way to
	 * set the parent or locale fields. This version does.
	 */
	public static class UTF8PropertiesResourceBundle
			extends PropertyResourceBundle
	{
		private String mSource;
		private Locale mLocale;

		public UTF8PropertiesResourceBundle(String source, InputStream in,
				ResourceBundle parent, Locale locale)
			throws IOException
		{
			super(new PropertiesEncodingInputStream(in, "UTF8"));
			setParent(parent);
			mSource = source;
			mLocale = locale;
		}

		public String getSource()
		{
			return mSource;
		}

		public Locale getLocale()
		{
			return mLocale;
		}
	}
}
