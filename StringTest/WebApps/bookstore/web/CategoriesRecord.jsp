<%@ include file="Common.jsp"%>
<%!
//
//   Filename: CategoriesRecord.jsp
//   Generated with CodeCharge  v.1.2.0
//   JSP.ccp build 05/21/2001
//

static final String sFileName = "CategoriesRecord.jsp";
              
%>
<%

String cSec = checkSecurity(2, session, response, request);
if ("sendRedirect".equals(cSec) ) return;
                
boolean bDebug = false;

String sAction = getParam( request, "FormAction");
String sForm = getParam( request, "FormName");
String sCategoriesErr = "";

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
if ( sForm.equals("Categories") ) {
  sCategoriesErr = CategoriesAction(request, response, session, out, sAction, sForm, conn, stat);
  if ( "sendRedirect".equals(sCategoriesErr)) return;
}

%>
<%
if ( stat != null ) stat.close();
if ( conn != null ) conn.close();
%>
<%!


  String CategoriesAction(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sAction, String sForm, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
  
    String sCategoriesErr ="";
    try {

      if (sAction.equals("")) return "";

      String sSQL="";
      String transitParams = "";
      String primaryKeyParams = "";
      String sQueryString = "";
      String sPage = "";
      String sParams = "";
      String sActionFileName = "CategoriesGrid.jsp";
      String sWhere = " ";
      boolean bErr = false;
      long iCount = 0;

  
      String pPKcategory_id = "";
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
        pPKcategory_id = getParam( request, "PK_category_id");
        if ( isEmpty(pPKcategory_id)) return sCategoriesErr;
        sWhere = "category_id=" + toSQL(pPKcategory_id, adNumber);
      }


      String fldname="";
      String fldcategory_id="";

      // Load all form fields into variables
    
      fldname = getParam(request, "name");
      // Validate fields
      if ( iAction == iinsertAction || iAction == iupdateAction ) {
        if ( isEmpty(fldname) ) {
          sCategoriesErr = sCategoriesErr + "The value in field Name is required.<br>";
        }
        if (sCategoriesErr.length() > 0 ) {
          return (sCategoriesErr);
        }
      }


      sSQL = "";
      // Create SQL statement

      switch (iAction) {
  
        case iinsertAction :
          
            sSQL = "insert into categories (" + 
                "name)" +

                " values (" + 
                toSQL(fldname, adText) + ")";
          break;
  
      case iupdateAction:
        
          sSQL = "update categories set " +
                "name=" + toSQL(fldname, adText);
          sSQL = sSQL + " where " + sWhere;
        break;
      
      case ideleteAction:
           sSQL = "delete from categories where " + sWhere;
          
        break;
  
      }

      if ( sCategoriesErr.length() > 0 ) return sCategoriesErr;
      try {
        // Execute SQL statement
        stat.executeUpdate(sSQL);
      }
      catch(java.sql.SQLException e) {
        sCategoriesErr = e.toString(); return (sCategoriesErr);
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
    return (sCategoriesErr);
  }

  


  void Categories_Show(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sCategoriesErr, String sForm, String sAction, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
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
      
      String pcategory_id = "";

      String fldcategory_id="";
      String fldname="";


      boolean bPK = true;

      if ( "".equals(sCategoriesErr)) {
        // Load primary key and form parameters
        fldcategory_id = getParam( request, "category_id");
        pcategory_id = getParam( request, "category_id");
      }
      else {
        // Load primary key, form parameters and form fields
        fldcategory_id = getParam( request, "category_id");
        fldname = getParam( request, "name");
        pcategory_id = getParam( request, "PK_category_id");
      }

      
      if ( isEmpty(pcategory_id)) { bPK = false; }
      
      sWhere += "category_id=" + toSQL(pcategory_id, adNumber);
      primaryKeyParams += "<input type=\"hidden\" name=\"PK_category_id\" value=\""+pcategory_id+"\"/>";

      sSQL = "select * from categories where " + sWhere;


      out.println("    <table style=\"\">");
      out.println("     <tr>\n      <td style=\"background-color: #336699; text-align: Center; border-style: outset; border-width: 1\" colspan=\"2\"><font style=\"font-size: 12pt; color: #FFFFFF; font-weight: bold\">Categories</font></td>\n     </tr>");
      if ( ! sCategoriesErr.equals("")) {
        out.println("     <tr>\n      <td style=\"background-color: #FFFFFF; border-width: 1\" colspan=\"2\"><font style=\"font-size: 10pt; color: #000000\">"+sCategoriesErr+"</font></td>\n     </tr>");
      }
      sCategoriesErr="";
      out.println("     <form method=\"get\" action=\""+sFileName+"\" name=\"Categories\">");

      java.sql.ResultSet rs = null;

      if ( bPK &&  ! (sAction.equals("insert") && "Categories".equals(sForm))) {

        // Open recordset
        rs = openrs( stat, sSQL);
        rs.next();
        String[] aFields = getFieldsName( rs );
        getRecordToHash( rs, rsHash, aFields );
        rs.close();
        fldcategory_id = (String) rsHash.get("category_id");
        if ( "".equals(sCategoriesErr)) {
          // Load data from recordset when form displayed first time
          fldname = (String) rsHash.get("name");
        }

        if (sAction.equals("") || ! "Categories".equals(sForm)) {
      
          fldcategory_id = (String) rsHash.get("category_id");
          fldname = (String) rsHash.get("name");
        }
        
      }
      else {
        if ( "".equals(sCategoriesErr)) {
          fldcategory_id = toHTML(getParam(request,"category_id"));
        }
      }
      


      // Show form field
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Name</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"name\" maxlength=\"50\" value=\""+toHTML(fldname)+"\" size=\"50\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td colspan=\"2\" align=\"right\">");
      

      if ( bPK && ! (sAction.equals("insert") && "Categories".equals(sForm))) {
        out.print("<input type=\"submit\" value=\"Update\" onclick=\"document.Categories.FormAction.value = 'update';\">");out.print("<input type=\"submit\" value=\"Delete\" onclick=\"document.Categories.FormAction.value = 'delete';\">");out.print("<input type=\"submit\" value=\"Cancel\" onclick=\"document.Categories.FormAction.value = 'cancel';\">");
        out.print("<input type=\"hidden\" name=\"FormName\" value=\"Categories\"><input type=\"hidden\" value=\"update\" name=\"FormAction\">");
      }
      
      else {
        out.print("<input type=\"submit\" value=\"Insert\" onclick=\"document.Categories.FormAction.value = 'insert';\">");out.print("<input type=\"submit\" value=\"Cancel\" onclick=\"document.Categories.FormAction.value = 'cancel';\">");
        out.print("<input type=\"hidden\" name=\"FormName\" value=\"Categories\"><input type=\"hidden\" value=\"insert\" name=\"FormAction\">");
      }out.print("<input type=\"hidden\" name=\"category_id\" value=\""+toHTML(fldcategory_id)+"\">");
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
		<% Categories_Show(request, response, session, out, sCategoriesErr, sForm, sAction, conn, stat); %>

		</td>
	</tr>
</table>

<jsp:include page="Footer.jsp" flush="true" />
<center><font face="Arial"><small>This dynamic
site was generated with <a href="http://www.codecharge.com">CodeCharge</a></small></font></center>
</body>
</html>
<%%>
