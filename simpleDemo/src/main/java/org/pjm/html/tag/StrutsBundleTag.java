package org.pjm.html.tag;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.pjm.html.util.LangCodeUtil;
import org.pjm.html.util.ScopeUtils;
import org.pjm.html.util.WebResourceBundles;


/**
 * Custom tag which loads our own UTF-8 ResourceBundles from a special
 * location under our docroot. Once a bundle is loaded, it can be used in
 * &lt;fmt:message&gt; tags. This tag is an alternative to the
 * &lt;fmt:bundle&gt; tag, which can only load Latin1 bundles from the classpath.
 * The tag can also take a secondary bundle path which can be used to locate a
 * resource if the primary (basename) bundle does not contain the key.
 *
 */
public class StrutsBundleTag extends TagSupport
{
	private static final String WEB_BUNDLE = "web";
	private static final String PLATFORM_BUNDLE = "classpath";
	/** bundle path*/
	private String mBasename;
	/** resource group to use */
	private String mGroup;
	/** optional context variable to output to */
	private String mVar;
	/** scope of output variable */
	private String mScope;
	/** site config path */
	private String mSiteconf;

	public StrutsBundleTag()
	{
		mScope = ScopeUtils.SCOPE_PAGE;
		mGroup = WebResourceBundles.GROUP_DEFAULT;
	}

	public void setVar(String var)
	{
		mVar = var;
	}

	public String getVar()
	{
		return mVar;
	}

	public String getGroup()
	{
		return mGroup;
	}

	public void setGroup(String group)
	{
		mGroup = group;
	}
	public String getSiteconf()
	{
		return mSiteconf;
	}

	public void setSiteconf(String siteconf)
	{
		mSiteconf = siteconf;
	}
	
	public String getBasename()
	{
		return mBasename;
	}

	public void setBasename(String basename)
	{
		mBasename = basename;
	}

	
	public String getScope()
	{
		return mScope;
	}

	public void setScope(String scope)
	{
		mScope = scope;
	}


	
	public int doEndTag() throws JspException
	{
		LocalizationContext context = getMappedLocalizationContext();

        int scope = ScopeUtils.getScope(mScope);

		// Store the l10n context in a context variable, if one was specified.
		// Otherwise, store it in the JSTL default l10n context variable.
		if (mVar != null)
		{
			pageContext.setAttribute(mVar, context, scope);
		}
		else
		{
			Config.set(pageContext, Config.FMT_LOCALIZATION_CONTEXT, context, scope);
		}

		return EVAL_PAGE;
	}


	private LocalizationContext getMappedLocalizationContext() throws JspException 
	{
		ResourceBundle primary = getBundle(mBasename);
		
		return new LocalizationContext(primary);
		
	}

	private ResourceBundle getBundle(String bundleName) throws JspException
	{
		ResourceBundle bundle = getRawBundle(bundleName);

		return bundle;
	}

	private ResourceBundle getRawBundle(String bundleName) throws JspException {
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		Locale locale = LangCodeUtil.makeLocale(request.getParameter("locale"));
	
		try {
			if (bundleName == null) {
				return WebResourceBundles.instance(mGroup).getBundle(
						pageContext.getServletContext(), mSiteconf+"/default", locale);
			}
			else{
				return WebResourceBundles.instance(mGroup).getBundle(
						pageContext.getServletContext(), bundleName, locale);
			}
		} catch (MissingResourceException e) {
			throw new JspException("can't load resources for " + bundleName
					+ " " + locale, e);
		}
	}
}
