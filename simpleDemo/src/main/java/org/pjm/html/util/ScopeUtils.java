package org.pjm.html.util;

import javax.servlet.jsp.PageContext;

/**
 * Simple utility functions for mapping scope identifier strings to
 * PageContext's integer IDs.
 *
 *
 */
public class ScopeUtils
{
	public static final String SCOPE_PAGE = "page";
	public static final String SCOPE_REQUEST = "request";
	public static final String SCOPE_SESSION = "session";
	public static final String SCOPE_APPLICATION = "application";

	/**
	 * Converts a scope string to an integer ID.
	 *
	 * @param scope scope string; must be "page", "request", "session",
	 * or "application"
	 * @return corresponding scope ID
	 * @throws IllegalArgumentException if scope was invalid
	 */
	public static int getScope(String scope)
	{
		if (SCOPE_PAGE.equals(scope))
			return PageContext.PAGE_SCOPE;
		if (SCOPE_REQUEST.equals(scope))
			return PageContext.REQUEST_SCOPE;
		if (SCOPE_SESSION.equals(scope))
			return PageContext.SESSION_SCOPE;
		if (SCOPE_APPLICATION.equals(scope))
			return PageContext.APPLICATION_SCOPE;
		throw new IllegalArgumentException(scope + " is not a valid scope."
				+ " Must be page, request, session, or application.");
	}
}
