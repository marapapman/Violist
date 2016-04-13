<%@ include file="Common.jsp"%>
<%!
//
//   Filename: AdvSearch.jsp
//   Generated with CodeCharge  v.1.2.0
//   JSP.ccp build 05/21/2001
//

static final String sFileName = "AdvSearch.jsp";
              
%>
<%

boolean bDebug = false;

String sAction = getParam( request, "FormAction");
String sForm = getParam( request, "FormName");
String sSearchErr = "";

java.sql.Connection conn = null;
java.sql.Statement stat = null;
String sErr = loadDriver();
conn = cn();
stat = conn.createStatement();
if ( ! sErr.equals("") ) {
 try {
   out.println(sErr);
 }
 catch (Exception e) {}
}

%>

<%!

  void Search_Show (javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sSearchErr, String sForm, String sAction, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
    try {
      

      String fldname="";
      String fldauthor="";
      String fldcategory_id="";
      String fldpricemin="";
      String fldpricemax="";


      String sSQL="";
      String transitParams = "";
      String sQueryString = "";
      String sPage = "";
      

      out.println("    <table style=\"\">");
      out.println("     <tr>\n      <td style=\"background-color: #336699; text-align: Center; border-style: outset; border-width: 1\" colspan=\"11\"><a name=\"Search\"><font style=\"font-size: 12pt; color: #FFFFFF; font-weight: bold\">Advanced Search</font></a></td>\n     </tr>");
      out.println("     <form method=\"get\" action=\"Books.jsp\" name=\"Search\">\n     <tr>");
      // Set variables with search parameters
      
      fldname = getParam( request, "name");
      fldauthor = getParam( request, "author");
      fldcategory_id = getParam( request, "category_id");
      fldpricemin = getParam( request, "pricemin");
      fldpricemax = getParam( request, "pricemax");

      // Show fields
      

      out.println("      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Title</font></td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"name\" maxlength=\"20\" value=\""+toHTML(fldname)+"\" size=\"20\">");
 out.println("</td>\n     </tr>\n     <tr>");
      
      out.println("      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Author</font></td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"author\" maxlength=\"100\" value=\""+toHTML(fldauthor)+"\" size=\"20\">");
 out.println("</td>\n     </tr>\n     <tr>");
      
      out.println("      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Category</font></td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); 
      out.print("<select name=\"category_id\">"+getOptions( conn, "select category_id, name from categories order by 2",true,false,fldcategory_id)+"</select>");
       out.println("</td>\n     </tr>\n     <tr>");
      
      out.println("      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Price more then</font></td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"pricemin\" maxlength=\"10\" value=\""+toHTML(fldpricemin)+"\" size=\"10\">");
 out.println("</td>\n     </tr>\n     <tr>");
      
      out.println("      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Price less then</font></td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"pricemax\" maxlength=\"10\" value=\""+toHTML(fldpricemax)+"\" size=\"10\">");
 out.println("</td>\n     </tr>\n     <tr>");
      
      out.println("      <td align=\"right\" colspan=\"3\"><input type=\"submit\" value=\"Search\"/></td>");
      out.println("     </tr>\n     </form>\n    </table>");
      out.println("");
    }
    catch (Exception e) { out.println(e.toString()); }
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
<table>
	<tr>

		<td valign="top">
		<% Search_Show(request, response, session, out, sSearchErr, sForm, sAction, conn, stat); %>

		</td>
	</tr>
</table>

<jsp:include page="Footer.jsp" flush="true" />
<center><font face="Arial"><small>This dynamic
site was generated with <a href="http://www.codecharge.com">CodeCharge</a></small></font></center>
</body>
</html>
<%%>
<%
if ( stat != null ) stat.close();
if ( conn != null ) conn.close();
%>
