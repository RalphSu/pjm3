package org.ttjhome.html.struts;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.struts.action.ActionServlet;

import servletunit.HttpServletRequestSimulator;
import servletunit.HttpServletResponseSimulator;
import servletunit.RequestDispatcherSimulator;
import servletunit.ServletConfigSimulator;
import servletunit.ServletContextSimulator;
import servletunit.struts.MockStrutsTestCase;

public class TestCandidateAction extends MockStrutsTestCase {

	private String moduleName = "candidate";
 
	public void setUp()  throws Exception{
        if (actionServlet == null)
            actionServlet = new ActionServlet();
        config = new ServletConfigSimulatorWrapper();
        request = new HttpServletRequestSimulator(config.getServletContext());
        response = new HttpServletResponseSimulator();
        context = (ServletContextSimulatorWrapper) config.getServletContext();
        requestWrapper = null;
        responseWrapper = null;
        isInitialized = true;
	}


	public void testList() throws Exception {
		setServletConfigFile("/WEB-INF/struts-config.xml");
	    setConfigFile(moduleName,"/WEB-INF/struts-candidates-config.xml");
	    
		setRequestPathInfo(this.moduleName, "/candidates/list.do");
		actionPerform();
		verifyNoActionErrors();
		verifyTilesForward("success", ".candidates.list");
	}

	 private class ServletContextSimulatorWrapper extends
			ServletContextSimulator implements ServletContext {
  	
  	   public Object getAttribute(String name)
  	    {
  	        Object obj= super.getAttribute(name);
  	        if(obj==null&&name.contains("org.apache.struts.tiles.DEFINITIONS_FACTORY")){
  	        	obj = super.getAttribute("org.apache.struts.tiles.DEFINITIONS_FACTORY");
  	        }
  	        return obj;
  	    }
  	   
  	   
      public RequestDispatcher getRequestDispatcher(String urlpath)
      {
   	   RequestDispatcher dispatcher =  new ReRequestDispatcherSimulator(urlpath);
          return dispatcher;
      }
 
  	   
  	   
  	
	}
	 
	 
		private final class ReRequestDispatcherSimulator extends
		RequestDispatcherSimulator implements RequestDispatcher {

			private Object dispatchedResource;
			public ReRequestDispatcherSimulator(Object dispatchedResource) {
				super(dispatchedResource);
				this.dispatchedResource = dispatchedResource;
			}

		public void forward(ServletRequest request, ServletResponse response)
				throws ServletException, IOException {
		
			super.forward(request, response);
			
			
		}
			
			
		}
	 
	private class ServletConfigSimulatorWrapper extends ServletConfigSimulator {
      private Hashtable parameters;
      private ServletContext context;

      public ServletConfigSimulatorWrapper()
      {
          parameters=new Hashtable();
          context = new ServletContextSimulatorWrapper();
      }
      
      public ServletContext getServletContext()
      {
          return context;
      }
	}
}
