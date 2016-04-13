<%@ include file="Common.jsp"%>
<%!
//
//   Filename: CardTypesRecord.jsp
//   Generated with CodeCharge  v.1.2.0
//   JSP.ccp build 05/21/2001
//

static final String sFileName = "CardTypesRecord.jsp";
              
%>
<%

String cSec = checkSecurity(2, session, response, request);
if ("sendRedirect".equals(cSec) ) return;
                
boolean bDebug = false;

String sAction = getParam( request, "FormAction");
String sForm = getParam( request, "FormName");
String sCardTypesErr = "";

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
if ( sForm.equals("CardTypes") ) {
  sCardTypesErr = CardTypesAction(request, response, session, out, sAction, sForm, conn, stat);
  if ( "sendRedirect".equals(sCardTypesErr)) return;
}

%>
<%!


  String CardTypesAction(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sAction, String sForm, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
  
    String sCardTypesErr ="";
    try {

      if (sAction.equals("")) return "";

      String sSQL="";
      String transitParams = "";
      String primaryKeyParams = "";
      String sQueryString = "";
      String sPage = "";
      String sParams = "";
      String sActionFileName = "CardTypesGrid.jsp";
      String sWhere = " ";
      boolean bErr = false;
      long iCount = 0;

  
      String pPKcard_type_id = "";
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
        pPKcard_type_id = getParam( request, "PK_card_type_id");
        if ( isEmpty(pPKcard_type_id)) return sCardTypesErr;
        sWhere = "card_type_id=" + toSQL(pPKcard_type_id, adNumber);
      }


      String fldname="";
      String fldcard_type_id="";

      // Load all form fields into variables
    
      fldname = getParam(request, "name");
      // Validate fields
      if ( iAction == iinsertAction || iAction == iupdateAction ) {
        if ( isEmpty(fldname) ) {
          sCardTypesErr = sCardTypesErr + "The value in field Name is required.<br>";
        }
        if (sCardTypesErr.length() > 0 ) {
          return (sCardTypesErr);
        }
      }


      sSQL = "";
      // Create SQL statement

      switch (iAction) {
  
        case iinsertAction :
          
            sSQL = "insert into card_types (" + 
                "name)" +

                " values (" + 
                toSQL(fldname, adText) + ")";
          break;
  
      case iupdateAction:
        
          sSQL = "update card_types set " +
                "name=" + toSQL(fldname, adText);
          sSQL = sSQL + " where " + sWhere;
        break;
      
      case ideleteAction:
           sSQL = "delete from card_types where " + sWhere;
          
        break;
  
      }

      if ( sCardTypesErr.length() > 0 ) return sCardTypesErr;
      try {
        // Execute SQL statement
        stat.executeUpdate(sSQL);
      }
      catch(java.sql.SQLException e) {
        sCardTypesErr = e.toString(); return (sCardTypesErr);
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
    return (sCardTypesErr);
  }

  


  void CardTypes_Show(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sCardTypesErr, String sForm, String sAction, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
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
      
      String pcard_type_id = "";

      String fldcard_type_id="";
      String fldname="";


      boolean bPK = true;

      if ( "".equals(sCardTypesErr)) {
        // Load primary key and form parameters
        fldcard_type_id = getParam( request, "card_type_id");
        pcard_type_id = getParam( request, "card_type_id");
      }
      else {
        // Load primary key, form parameters and form fields
        fldcard_type_id = getParam( request, "card_type_id");
        fldname = getParam( request, "name");
        pcard_type_id = getParam( request, "PK_card_type_id");
      }

      
      if ( isEmpty(pcard_type_id)) { bPK = false; }
      
      sWhere += "card_type_id=" + toSQL(pcard_type_id, adNumber);
      primaryKeyParams += "<input type=\"hidden\" name=\"PK_card_type_id\" value=\""+pcard_type_id+"\"/>";

      sSQL = "select * from card_types where " + sWhere;


      out.println("    <table style=\"\">");
      out.println("     <tr>\n      <td style=\"background-color: #336699; text-align: Center; border-style: outset; border-width: 1\" colspan=\"2\"><font style=\"font-size: 12pt; color: #FFFFFF; font-weight: bold\">Card Type</font></td>\n     </tr>");
      if ( ! sCardTypesErr.equals("")) {
        out.println("     <tr>\n      <td style=\"background-color: #FFFFFF; border-width: 1\" colspan=\"2\"><font style=\"font-size: 10pt; color: #000000\">"+sCardTypesErr+"</font></td>\n     </tr>");
      }
      sCardTypesErr="";
      out.println("     <form method=\"get\" action=\""+sFileName+"\" name=\"CardTypes\">");

      java.sql.ResultSet rs = null;

      if ( bPK &&  ! (sAction.equals("insert") && "CardTypes".equals(sForm))) {

        // Open recordset
        rs = openrs( stat, sSQL);
        rs.next();
        String[] aFields = getFieldsName( rs );
        getRecordToHash( rs, rsHash, aFields );
        rs.close();
        fldcard_type_id = (String) rsHash.get("card_type_id");
        if ( "".equals(sCardTypesErr)) {
          // Load data from recordset when form displayed first time
          fldname = (String) rsHash.get("name");
        }

        if (sAction.equals("") || ! "CardTypes".equals(sForm)) {
      
          fldcard_type_id = (String) rsHash.get("card_type_id");
          fldname = (String) rsHash.get("name");
        }
        
      }
      else {
        if ( "".equals(sCardTypesErr)) {
          fldcard_type_id = toHTML(getParam(request,"card_type_id"));
        }
      }
      


      // Show form field
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Name</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"name\" maxlength=\"50\" value=\""+toHTML(fldname)+"\" size=\"50\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td colspan=\"2\" align=\"right\">");
      

      if ( bPK && ! (sAction.equals("insert") && "CardTypes".equals(sForm))) {
        out.print("<input type=\"submit\" value=\"Update\" onclick=\"document.CardTypes.FormAction.value = 'update';\">");out.print("<input type=\"submit\" value=\"Delete\" onclick=\"document.CardTypes.FormAction.value = 'delete';\">");out.print("<input type=\"submit\" value=\"Cancel\" onclick=\"document.CardTypes.FormAction.value = 'cancel';\">");
        out.print("<input type=\"hidden\" name=\"FormName\" value=\"CardTypes\"><input type=\"hidden\" value=\"update\" name=\"FormAction\">");
      }
      
      else {
        out.print("<input type=\"submit\" value=\"Insert\" onclick=\"document.CardTypes.FormAction.value = 'insert';\">");out.print("<input type=\"submit\" value=\"Cancel\" onclick=\"document.CardTypes.FormAction.value = 'cancel';\">");
        out.print("<input type=\"hidden\" name=\"FormName\" value=\"CardTypes\"><input type=\"hidden\" value=\"insert\" name=\"FormAction\">");
      }out.print("<input type=\"hidden\" name=\"card_type_id\" value=\""+toHTML(fldcard_type_id)+"\">");
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
		<% CardTypes_Show(request, response, session, out, sCardTypesErr, sForm, sAction, conn, stat); %>

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
