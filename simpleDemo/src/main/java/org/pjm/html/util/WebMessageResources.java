package org.pjm.html.util;

import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.pjm.html.util.WebResourceBundleManager;
import org.pjm.html.util.WebResourceBundles;

import javax.servlet.ServletContext;
import java.util.Locale;
import java.util.MissingResourceException; 
import java.util.ResourceBundle;

/**
 * Simple glue between our web localization mechanism
 * ({@link WebResourceBundleManager}) and Struts.
 *
 * 
 */
public class WebMessageResources extends MessageResources
{
	private String mGroup;
	private ServletContext mServletContext;

	public WebMessageResources(MessageResourcesFactory factory,
			String config, boolean returnNull, String group,
			ServletContext context)
	{
		super(factory, config, returnNull);
		mGroup = group;
		mServletContext = context;
	}

	public String getMessage(Locale locale, String key)
	{
		if (mServletContext == null)
		{
			return null;
		}
		
		WebResourceBundleManager manager = WebResourceBundles.instance(mGroup);
		ResourceBundle bundle = manager.getBundle(mServletContext,
				getConfig(), locale);
		try
		{
			return bundle.getString(key);
		}
		catch (MissingResourceException e)
		{
			return getReturnNull() ? null : ("???" + key + "???");
		}
	}
}
