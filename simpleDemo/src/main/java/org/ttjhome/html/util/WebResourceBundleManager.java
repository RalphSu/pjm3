package org.ttjhome.html.util;

import javax.servlet.ServletContext;
import java.io.InputStream;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;


/**
 * Subclass of ResourceBundleManager which loads its contents from /WEB-INF,
 * with the help of a {@link ServletContext}.
 *
 * <p>Note that this class lacks a public constructor. It is not intended
 * that clients should instantiate this, as that would prevent the built-in
 * cache from working optimally.
 */
public class WebResourceBundleManager extends ResourceBundleManager
{
	/**
	 * Creates a new ResourceBundleManager, pointing at the specified
	 * content root.
	 *
	 * @param root base path to prefix on all names when calling
	 *             {@link #getBundle}.
	 */
	// note that the constructor is intentionally non-public.
	// only WebResourceBundles should instantiate this class.
	WebResourceBundleManager(String root)
	{
		super(root);

		Properties props = new Properties();
		boolean cacheEnabled = Boolean.getBoolean(props
				.getProperty(CONFIG_ENABLE_CACHE));
		long interval = props.getProperty(CONFIG_REFRESH_INTERVAL) != null ? Long
				.parseLong(props.getProperty(CONFIG_REFRESH_INTERVAL))
				: DEFAULT_RELOAD_INTERVAL;
		long reloadInterval = 1000 * interval;
		init(cacheEnabled, reloadInterval);
	}

	/**
	 * Loads a ResourceBundle using the specified ServletContext, base name,
	 * and Locale. The search algorithm is basically the same one used by
	 * {@link ResourceBundle#getBundleImpl(String, Locale, ClassLoader)}.
	 * This ResourceBundleManager's root is used as a prefix when searching.
	 * If the search was successful, the result will be cached.
	 *
	 * @param context ServletContext which can load the resources for us
	 * @param baseName "/"-delimited path of the bundle, with no
	 * locale or .properties suffix
	 * @param locale Locale for which a ResourceBundle is desired
	 * @return a ResourceBundle given the specified baseName and locale
	 * @throws MissingResourceException if no bundle could be found, or
	 * if there was an error loading the bundle
	 */
	public ResourceBundle getBundle(ServletContext context, String baseName,
			Locale locale)
		throws MissingResourceException
	{
		BundleRequest request = new WebBundleRequest(context);
		return getBundle(request, baseName, locale, true);
	}

	class WebBundleRequest extends BundleRequest
	{
		private ServletContext mContext;

		WebBundleRequest(ServletContext context)
		{
			mContext = context;
		}

		public InputStream openResource(String path)
		{
			return mContext.getResourceAsStream(path);
		}
	}
}
