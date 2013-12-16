package org.ttjhome.html.struts.candidate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CandidatesAction extends Action{

	private static final Logger logger = LoggerFactory.getLogger(CandidatesAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("Entering execute of CandidatesAction with form"+form);
		ActionForward actionForward = mapping.findForward("success");
		return actionForward;
		
		
	}
}
