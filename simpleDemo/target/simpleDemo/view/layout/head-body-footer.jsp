<%@ include file="/WEB-INF/jspf/tag-libraries.jspf" %>
<tiles:importAttribute scope="request" />
<ttjhome:bundle siteconf="${siteconf}" var="siteconfig" group="config" scope="request"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:fb="http://www.facebook.com/2008/fbml" xmlns:ie>
<head>
  <!-- title>Simple Demo</title -->
  <title><fmt:message key="${pagekey}.pagetitle" bundle="${siteconfig}"/></title>
<!-- START:stylesheet -->
  <!-- link rel="stylesheet" href="../include/css/layout.css" type="text/css" /-->
	<fmt:message key="${pagekey}.cssfiles" bundle="${siteconfig}" var="cssfiles"/>
		<c:forTokens items="${cssfiles}" delims="," var="cssFile">
			<ttjhome:cssURL href="${cssFile}"/>
		</c:forTokens>
	 <fmt:message key="${pagekey}.jsfiles" bundle="${siteconfig}" var="jsfiles"  />
			<c:if test="${!empty jsfiles}">
			<c:forTokens items="${jsfiles}" delims="," var="jsFile">
				<ttjhome:scriptURL src="${jsFile}"/>
			</c:forTokens>
			</c:if>
<!-- END:stylesheet -->

</head>

<body >


 <div id="banner">
  	<div style="float:left;"><img src="../include/img/logo.png" width="50" height="64" alt="" /></div>
	  <%= "Simple Demo" %>
  </div>
  <table id="contents">
  	<tr>
  
    <td id="main">
      		<tiles:insert attribute="body"/>
    </td>
	<td id="side">
		<fieldset>
		 	<legend>Quick Search</legend>

		</fieldset>
		<tiles:insert attribute="side"/>

  	</td>
    </tr>
	</table>




</body>
</html>
