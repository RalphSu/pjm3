package org.ttjhome.html.util;

import java.util.HashMap;
import java.util.Map;

/**
 * WebResourceBundles holds a few instances of {@link WebResourceBundleManager},
 * organized into "resource groups". The default resource group (identified
 * by {@link #GROUP_DEFAULT} is rooted at /WEB-INF/resources. This resource
 * group is specific to a single web application. There is also a common
 * resource group (identified by {@link #GROUP_COMMON}, which is shared
 * by all web applications.
 *
 * 
 */
public class WebResourceBundles
{
	/** identifies the default resource group */
	public static final String GROUP_DEFAULT = "default";
	/** identifies the shared resource group */
	public static final String GROUP_COMMON = "common";
	public static final String GROUP_CONFIG = "config";
	private static final String DEFAULT_BASE = "/WEB-INF/resources";
	private static final String COMMON_BASE = "/common/protect/resources";
	private static final String CONF_BASE = "/WEB-INF/configs";
	
	private static final Map mManagers;

	static
	{
		mManagers = new HashMap();
		mManagers.put(GROUP_DEFAULT, new WebResourceBundleManager(DEFAULT_BASE));
		mManagers.put(GROUP_COMMON, new WebResourceBundleManager(COMMON_BASE));
		mManagers.put(GROUP_CONFIG, new WebResourceBundleManager(CONF_BASE));
	}

	/**
	 * Return the bundle manager for the default resource bundle group.
	 *
	 * @return a WebResourceBundleManager for /WEB-INF/resources
	 */
	public static WebResourceBundleManager instance()
	{
		return instance(GROUP_DEFAULT);
	}

	public static WebResourceBundleManager instance(String group)
	{
	
		WebResourceBundleManager mgr;
		synchronized (mManagers)
		{
			mgr = (WebResourceBundleManager)mManagers.get(group);
		}
		return mgr;
	}

}

