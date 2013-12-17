package org.pjm.html.tag;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.MessageResources;
import org.pjm.html.util.ResourceBundleManager;
import org.pjm.html.util.ScopeUtils;
import org.pjm.html.util.WebMessageResourcesFactory;
import org.pjm.html.util.WebResourceBundles;

/**
 * Custom tag which helps interface between our localization framework
 * ({@link ResourceBundleManager}) and the Struts localization framework
 * ({@link ActionMessage}). An instance of this tag must be used on any
 * JSP on which you use the Struts &lt;bean:message&gt; tag.
 *
 * @see ResourceBundleManager
 * @see ActionMessage
 * @see WebMessageResourcesFactory
 * 
 */
public class StrutsResourceTag extends TagSupport
{
    /** resource bundle group to use */
    private String mGroup;
    /** bundle path*/
    private String mBasename;
    /** variable to associate the bundle with */
    private String mBundle;
    /** scope for mBundle */
    private String mScope;

 

    public StrutsResourceTag()
    {
        mScope = ScopeUtils.SCOPE_REQUEST;
        mGroup = WebResourceBundles.GROUP_DEFAULT;
    }

    public String getGroup()
    {
        return mGroup;
    }

    public void setGroup(String group)
    {
        mGroup = group;
    }

    public void setBundle(String bundle)
    {
        mBundle = bundle;
    }

    public String getBundle()
    {
        return mBundle;
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
		MessageResources resources = null;

		// This is the glue between our resource bundles and Struts.
		WebMessageResourcesFactory factory = new WebMessageResourcesFactory(
				pageContext.getServletContext(), mGroup);
		resources = factory.createResources(mBasename);

		int scope = ScopeUtils.getScope(mScope);
		pageContext.setAttribute(mBundle, resources, scope);

		return EVAL_PAGE;
	}
}
