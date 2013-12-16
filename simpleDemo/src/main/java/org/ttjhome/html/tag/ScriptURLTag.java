package org.ttjhome.html.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


public class ScriptURLTag extends TagSupport {

	/** uri to the script location */
	private String mSrc;
	
	public void setSrc(String src) {
		mSrc = src;
	}

	public int doEndTag() throws JspException {
		try {
			JspWriter versionURLTag = pageContext.getOut();
			versionURLTag.append("<script ")
			        .append("src=\"").append(mSrc).append("\" ")
			        .append("type=\"text/javascript\" ")
			        .append("charset=\"utf-8\"")
			        .append("></script>");
		} catch (IOException ioexception) {
			throw new JspTagException(ioexception);
		}

		return EVAL_PAGE; // continue parsing the page
	}

}
