<%@ include file="Common.jsp"%>
<%!
//
//   Filename: Registration.jsp
//   Generated with CodeCharge  v.1.2.0
//   JSP.ccp build 05/21/2001
//

static final String sFileName = "fseRegistration.jsp";
              
%>
<%

boolean bDebug = false;

String sAction = getParam( request, "FormAction");
String sForm = getParam( request, "FormName");
String sRegErr = "";

%>
<%!

  void Reg_Show(HttpServletRequest request, HttpServletResponse response, HttpSession session, JspWriter out, String sRegErr, String sForm, String sAction) throws java.io.IOException {
    try {
	String fldmember_login = getParam( request, "member_login");
        String fldmember_pin1 = getParam( request, "member_pin1");      
	String fldmember_pin2 = getParam(request,"member_pin2");

	String fldfull_name = getParam( request, "full_name");      
	String fldzip_code = getParam(request,"zip_code");

	if (!isNumber(fldmember_pin1)) {
		sRegErr = "Pin must be numeric";
	
	}

	if (sForm.equals("") || !sRegErr.equals("")) {
		out.println("    <table style=\"\">");
		out.println("     <tr>\n      <td style=\"background-color: #336699; text-align: Center; border-style: outset; border-width: 1\" colspan=\"2\"><font style=\"font-size: 12pt; color: #FFFFFF; font-weight: bold\">Registration</font></td>\n     </tr>");
		if ( ! sRegErr.equals("")) {
			out.println("     <tr>\n      <td style=\"background-color: #FFFFFF; border-width: 1\" colspan=\"2\"><font style=\"font-size: 10pt; color: red\">"+sRegErr+"</font></td>\n     </tr>");
		}
		sRegErr="";
		
		out.println("     <form method=\"get\" action=\""+sFileName+"\" name=\"Reg\">");

	      	out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Choose Login</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"member_login\" maxlength=\"20\" value=\""+toHTML(fldmember_login)+"\" size=\"20\">");

	      	out.println("</td>\n     </tr>");
	      
	      	out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Choose Pin</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"member_pin1\" maxlength=\"20\" value=\""+toHTML(fldmember_pin1)+"\" size=\"20\">");

	      	out.println("</td>\n     </tr>");
	      
	      	out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Confirm Pin</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"member_pin2\" maxlength=\"20\" value=\""+toHTML(fldmember_pin2)+"\" size=\"20\">");

	      	out.println("</td>\n     </tr>");
	      
	      	out.print("     <tr>\n      <td colspan=\"2\" align=\"right\">");
	      
		out.print("<input type=\"submit\" value=\"Register\" onclick=\"document.Reg.FormAction.value = 'insert';\">");out.print("<input type=\"submit\" value=\"Cancel\" onclick=\"document.Reg.FormAction.value = 'cancel';\">");
	
		out.print("<input type=\"hidden\" name=\"FormName\" value=\"Register\"><input type=\"hidden\" value=\"insert\" name=\"FormAction\">");
	      
		out.println("</td>\n     </tr>\n     </form>\n    </table>");  
	} else if (sForm.equals("Register")) {

		out.println("    <table style=\"\">");
		out.println("     <tr>\n      <td style=\"background-color: #336699; text-align: Center; border-style: outset; border-width: 1\" colspan=\"2\"><font style=\"font-size: 12pt; color: #FFFFFF; font-weight: bold\">Registration</font></td>\n     </tr>");
		if ( ! sRegErr.equals("")) {
			out.println("     <tr>\n      <td style=\"background-color: #FFFFFF; border-width: 1\" colspan=\"2\"><font style=\"font-size: 10pt; color: #000000\">"+sRegErr+"</font></td>\n     </tr>");
		}
		sRegErr="";
		
		out.println("     <form method=\"get\" action=\""+sFileName+"\" name=\"Complete\">");

	      	out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Full name</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"full_name\" maxlength=\"20\" value=\""+toHTML(fldfull_name)+"\" size=\"20\">");

	      	out.println("</td>\n     </tr>");
	      
	      	out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Zip Code</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"zip_code\" maxlength=\"20\" value=\""+toHTML(fldzip_code)+"\" size=\"20\">");

	      	out.println("</td>\n     </tr>");
	      
	      	out.print("     <tr>\n      <td colspan=\"2\" align=\"right\">");
	      
		out.print("<input type=\"submit\" value=\"Complete Registration\" onclick=\"document.Reg.FormAction.value = 'insert';\">");out.print("<input type=\"submit\" value=\"Cancel\" onclick=\"document.Reg.FormAction.value = 'cancel';\">");
	
		out.print("<input type=\"hidden\" name=\"FormName\" value=\"Complete\"><input type=\"hidden\" value=\"insert\" name=\"FormAction\">");
	      
		out.println("</td>\n     </tr>\n     </form>\n    </table>");  
	} else if (sForm.equals("Complete")) {
		out.println("<table style=\"\">");
		out.println("     <tr><td align=center style=\"background-color: #FFFFFF; border-width: 1\" colspan=\"2\"><font style=\"font-size: 20pt; color: #000000\">Welcome whalfond, registration is complete.</font></td></tr>");
		out.println("     <tr><td align=center style=\"background-color: #FFFFFF; border-width: 1\" colspan=\"2\"><font style=\"font-size: 10pt; color: #000000\"><href=\"Default.jsp\">Proceed to Online Bookstore.</a></font></td></tr>");
		out.println("</table>");  		
	}
   } catch (Exception e) { out.println(e.toString()); }
  } 





%>

<html>
<head>
<title>Book Store</title>
<meta name="GENERATOR"
	content="YesSoftware CodeCharge v.1.2.0 / JSP.ccp build 05/21/2001" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body
	style="background-color: #FFFFFF; color: #000000; font-family: Arial, Tahoma, Verdana, Helveticabackground-color: #FFFFFF; color: #000000; font-family: Arial, Tahoma, Verdana, Helvetica">
<jsp:include page="Header.jsp" flush="true" />
<center>
<table>
	<tr>

		<td valign="top">
		<% Reg_Show(request, response, session, out, sRegErr, sForm, sAction); %>

		</td>
	</tr>
</table>

<jsp:include page="Footer.jsp" flush="true" />
<center><font face="Arial"><small>This dynamic
site was generated with <a href="http://www.codecharge.com">CodeCharge</a></small></font></center>
</center>
</body>
</html>

