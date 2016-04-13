<%@ include file="Common.jsp"%>
<%!
//
//   Filename: EditorialCatRecord.jsp
//   Generated with CodeCharge  v.1.2.0
//   JSP.ccp build 05/21/2001
//

static final String sFileName = "EditorialCatRecord.jsp";
              
%>
<%

String cSec = checkSecurity(2, session, response, request);
if ("sendRedirect".equals(cSec) ) return;
                
boolean bDebug = false;

String sAction = getParam( request, "FormAction");
String sForm = getParam( request, "FormName");
String seditorial_categoriesErr = "";

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
if ( sForm.equals("editorial_categories") ) {
  seditorial_categoriesErr = editorial_categoriesAction(request, response, session, out, sAction, sForm, conn, stat);
  if ( "sendRedirect".equals(seditorial_categoriesErr)) return;
}

%>
<%!


  String editorial_categoriesAction(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sAction, String sForm, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
  
    String seditorial_categoriesErr ="";
    try {

      if (sAction.equals("")) return "";

      String sSQL="";
      String transitParams = "";
      String primaryKeyParams = "";
      String sQueryString = "";
      String sPage = "";
      String sParams = "";
      String sActionFileName = "EditorialCatGrid.jsp";
      String sWhere = " ";
      boolean bErr = false;
      long iCount = 0;

  
      String pPKeditorial_cat_id = "";
      if (sAction.equalsIgnoreCase("cancel") ) {
        try {
          if ( stat != null ) stat.close();
          if ( conn != null ) conn.close();
        }
        catch ( java.sql.SQLException ignore ) {}
        response.sendRedirect (sActionFileName);
        return "sendRedirect";
      }

      final int iinsertAction = 1;
      final int iupdateAction = 2;
      final int ideleteAction = 3;
      int iAction = 0;

      if ( sAction.equalsIgnoreCase("insert") ) { iAction = iinsertAction; }
      if ( sAction.equalsIgnoreCase("update") ) { iAction = iupdateAction; }
      if ( sAction.equalsIgnoreCase("delete") ) { iAction = ideleteAction; }

      // Create WHERE statement

      if ( iAction == iupdateAction || iAction == ideleteAction ) { 
        pPKeditorial_cat_id = getParam( request, "PK_editorial_cat_id");
        if ( isEmpty(pPKeditorial_cat_id)) return seditorial_categoriesErr;
        sWhere = "editorial_cat_id=" + toSQL(pPKeditorial_cat_id, adNumber);
      }


      String fldeditorial_cat_name="";
      String fldeditorial_cat_id="";

      // Load all form fields into variables
    
      fldeditorial_cat_name = getParam(request, "editorial_cat_name");
      // Validate fields
      if ( iAction == iinsertAction || iAction == iupdateAction ) {
        if (seditorial_categoriesErr.length() > 0 ) {
          return (seditorial_categoriesErr);
        }
      }


      sSQL = "";
      // Create SQL statement

      switch (iAction) {
  
        case iinsertAction :
          
            sSQL = "insert into editorial_categories (" + 
                "editorial_cat_name)" +

                " values (" + 
                toSQL(fldeditorial_cat_name, adText) + ")";
          break;
  
      case iupdateAction:
        
          sSQL = "update editorial_categories set " +
                "editorial_cat_name=" + toSQL(fldeditorial_cat_name, adText);
          sSQL = sSQL + " where " + sWhere;
        break;
      
      case ideleteAction:
           sSQL = "delete from editorial_categories where " + sWhere;
          
        break;
  
      }

      if ( seditorial_categoriesErr.length() > 0 ) return seditorial_categoriesErr;
      try {
        // Execute SQL statement
        stat.executeUpdate(sSQL);
      }
      catch(java.sql.SQLException e) {
        seditorial_categoriesErr = e.toString(); return (seditorial_categoriesErr);
      }
  
      try {
        if ( stat != null ) stat.close();
        if ( conn != null ) conn.close();
      }
      catch ( java.sql.SQLException ignore ) {}
      response.sendRedirect (sActionFileName);

      return "sendRedirect";
    }
    catch (Exception e) {out.println(e.toString()); }
    return (seditorial_categoriesErr);
  }

  


  void editorial_categories_Show(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String seditorial_categoriesErr, String sForm, String sAction, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
    try {

      String sSQL="";
      String sQueryString = "";
      String sPage = "";
      String sWhere = "";
      String transitParams = "";
      String transitParamsHidden = "";
      String requiredParams = "";
      String primaryKeyParams ="";
      java.util.Hashtable rsHash = new java.util.Hashtable();
      
      String peditorial_cat_id = "";

      String fldeditorial_cat_id="";
      String fldeditorial_cat_name="";


      boolean bPK = true;

      if ( "".equals(seditorial_categoriesErr)) {
        // Load primary key and form parameters
        fldeditorial_cat_id = getParam( request, "editorial_cat_id");
        peditorial_cat_id = getParam( request, "editorial_cat_id");
      }
      else {
        // Load primary key, form parameters and form fields
        fldeditorial_cat_id = getParam( request, "editorial_cat_id");
        fldeditorial_cat_name = getParam( request, "editorial_cat_name");
        peditorial_cat_id = getParam( request, "PK_editorial_cat_id");
      }

      
      if ( isEmpty(peditorial_cat_id)) { bPK = false; }
      
      sWhere += "editorial_cat_id=" + toSQL(peditorial_cat_id, adNumber);
      primaryKeyParams += "<input type=\"hidden\" name=\"PK_editorial_cat_id\" value=\""+peditorial_cat_id+"\"/>";

      sSQL = "select * from editorial_categories where " + sWhere;


      out.println("    <table style=\"\">");
      out.println("     <tr>\n      <td style=\"background-color: #336699; text-align: Center; border-style: outset; border-width: 1\" colspan=\"2\"><font style=\"font-size: 12pt; color: #FFFFFF; font-weight: bold\">Editorial Categories</font></td>\n     </tr>");
      if ( ! seditorial_categoriesErr.equals("")) {
        out.println("     <tr>\n      <td style=\"background-color: #FFFFFF; border-width: 1\" colspan=\"2\"><font style=\"font-size: 10pt; color: #000000\">"+seditorial_categoriesErr+"</font></td>\n     </tr>");
      }
      seditorial_categoriesErr="";
      out.println("     <form method=\"get\" action=\""+sFileName+"\" name=\"editorial_categories\">");

      java.sql.ResultSet rs = null;

      if ( bPK &&  ! (sAction.equals("insert") && "editorial_categories".equals(sForm))) {

        // Open recordset
        rs = openrs( stat, sSQL);
        rs.next();
        String[] aFields = getFieldsName( rs );
        getRecordToHash( rs, rsHash, aFields );
        rs.close();
        fldeditorial_cat_id = (String) rsHash.get("editorial_cat_id");
        if ( "".equals(seditorial_categoriesErr)) {
          // Load data from recordset when form displayed first time
          fldeditorial_cat_name = (String) rsHash.get("editorial_cat_name");
        }

        if (sAction.equals("") || ! "editorial_categories".equals(sForm)) {
      
          fldeditorial_cat_id = (String) rsHash.get("editorial_cat_id");
          fldeditorial_cat_name = (String) rsHash.get("editorial_cat_name");
        }
        
      }
      else {
        if ( "".equals(seditorial_categoriesErr)) {
          fldeditorial_cat_id = toHTML(getParam(request,"editorial_cat_id"));
        }
      }
      


      // Show form field
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Name</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"editorial_cat_name\" maxlength=\"50\" value=\""+toHTML(fldeditorial_cat_name)+"\" size=\"50\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td colspan=\"2\" align=\"right\">");
      

      if ( bPK && ! (sAction.equals("insert") && "editorial_categories".equals(sForm))) {
        out.print("<input type=\"submit\" value=\"Update\" onclick=\"document.editorial_categories.FormAction.value = 'update';\">");out.print("<input type=\"submit\" value=\"Delete\" onclick=\"document.editorial_categories.FormAction.value = 'delete';\">");out.print("<input type=\"submit\" value=\"Cancel\" onclick=\"document.editorial_categories.FormAction.value = 'cancel';\">");
        out.print("<input type=\"hidden\" name=\"FormName\" value=\"editorial_categories\"><input type=\"hidden\" value=\"update\" name=\"FormAction\">");
      }
      
      else {
        out.print("<input type=\"submit\" value=\"Insert\" onclick=\"document.editorial_categories.FormAction.value = 'insert';\">");out.print("<input type=\"submit\" value=\"Cancel\" onclick=\"document.editorial_categories.FormAction.value = 'cancel';\">");
        out.print("<input type=\"hidden\" name=\"FormName\" value=\"editorial_categories\"><input type=\"hidden\" value=\"insert\" name=\"FormAction\">");
      }out.print("<input type=\"hidden\" name=\"editorial_cat_id\" value=\""+toHTML(fldeditorial_cat_id)+"\">");
      out.print(transitParamsHidden+requiredParams+primaryKeyParams);
      out.println("</td>\n     </tr>\n     </form>\n    </table>");
      



    }
    catch (Exception e) { out.println(e.toString()); }
  } %>

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
		<% editorial_categories_Show(request, response, session, out, seditorial_categoriesErr, sForm, sAction, conn, stat); %>

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
