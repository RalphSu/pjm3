package org.ttjhome.html.util;

import javax.servlet.ServletContext;
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.MessageResources;




public class WebMessageResourcesFactory extends MessageResourcesFactory
{
	private String mGroup;
	private ServletContext mServletContext;

	public WebMessageResourcesFactory(ServletContext servletContext,
			String group)
	{
		setReturnNull(false);
		mGroup = group;
		mServletContext = servletContext;
	}

	public MessageResources createResources(String config)
	{
		return new WebMessageResources(this, config, getReturnNull(), mGroup,
				mServletContext);
	}

}
