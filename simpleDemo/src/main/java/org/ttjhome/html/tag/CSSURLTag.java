package org.ttjhome.html.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class CSSURLTag extends TagSupport {

	/** uri to the css location */
	private String mHref;
	
	public void setHref(String href) {
		mHref = href;
	}

	public int doEndTag() throws JspException {
		try {
			JspWriter versionURLTag = pageContext.getOut();
			versionURLTag.append("<link ")
			        .append("rel=\"StyleSheet\" ")
			        .append("href=\"").append(mHref).append("\"")
			        .append("/>");
		} catch (IOException ioexception) {
			throw new JspTagException(ioexception);
		}

		return EVAL_PAGE; // continue parsing the page
	}

}
