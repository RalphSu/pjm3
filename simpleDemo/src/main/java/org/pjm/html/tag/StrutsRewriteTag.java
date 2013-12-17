package org.pjm.html.tag;

import java.net.MalformedURLException;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.RewriteTag;
import org.pjm.html.util.ScopeUtils;


public class StrutsRewriteTag extends RewriteTag
{
	protected String mVar;
	protected String mScope = ScopeUtils.SCOPE_PAGE;
	
	public String getVar()
	{
		return mVar;
	}

	public void setVar(String value)
	{
		mVar = value;
	}

	public String getScope()
	{
		return mScope;
	}

	public void setScope(String value)
	{
		mScope = value;
	}
	


	// This is copied & pasted from org.apache.struts.taglib.html.RewriteTag.
	// Unfortunately, there was no clean way to extend RewriteTag's functionality.
	public int doStartTag() throws JspException
	{
		   // Generate the hyperlink URL
        Map params = TagUtils.getInstance().computeParameters
            (pageContext, paramId, paramName, paramProperty, paramScope,
             name, property, scope, transaction);
             
        String url = null;
        try {
            // Note that we're encoding the & character to &amp; in XHTML mode only, 
            // otherwise the & is written as is to work in javascripts. 
			url =
				TagUtils.getInstance().computeURLWithCharEncoding(
					pageContext,
					forward,
					href,
					page,
					action,
					module,
					params,
					anchor,
					false,
                    this.isXhtml(),
                    useLocalEncoding);
                    
        } catch (MalformedURLException e) {
            TagUtils.getInstance().saveException(pageContext, e);
            throw new JspException
                (messages.getMessage("rewrite.url", e.toString()));
        }

		int scopeInt = ScopeUtils.getScope(mScope);
		if (mVar != null)
		{
			pageContext.setAttribute(mVar, url, scopeInt);
		}
		else
		{
			TagUtils.getInstance().write(pageContext, url);
		}

		return (SKIP_BODY);
	}


}
