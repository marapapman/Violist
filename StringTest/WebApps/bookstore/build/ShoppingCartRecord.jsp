<%@ include file="Common.jsp"%>
<%!
//
//   Filename: ShoppingCartRecord.jsp
//   Generated with CodeCharge  v.1.2.0
//   JSP.ccp build 05/21/2001
//

static final String sFileName = "ShoppingCartRecord.jsp";
              
%>
<%

String cSec = checkSecurity(1, session, response, request);
if ("sendRedirect".equals(cSec) ) return;
                
boolean bDebug = false;

String sAction = getParam( request, "FormAction");
String sForm = getParam( request, "FormName");
String sShoppingCartRecordErr = "";

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
if ( sForm.equals("ShoppingCartRecord") ) {
  sShoppingCartRecordErr = ShoppingCartRecordAction(request, response, session, out, sAction, sForm, conn, stat);
  if ( "sendRedirect".equals(sShoppingCartRecordErr)) return;
}

%>
<%!


  String ShoppingCartRecordAction(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sAction, String sForm, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
  
    String sShoppingCartRecordErr ="";
    try {

      if (sAction.equals("")) return "";

      String sSQL="";
      String transitParams = "";
      String primaryKeyParams = "";
      String sQueryString = "";
      String sPage = "";
      String sParams = "";
      String sActionFileName = "ShoppingCart.jsp";
      String sWhere = " ";
      boolean bErr = false;
      long iCount = 0;

  
      String pPKorder_id = "";
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
        pPKorder_id = getParam( request, "PK_order_id");
        if ( isEmpty(pPKorder_id)) return sShoppingCartRecordErr;
        sWhere = "order_id=" + toSQL(pPKorder_id, adNumber);
      }


      String fldUserID="";
      String fldmember_id="";
      String fldquantity="";
      String fldorder_id="";
      String flditem_id="";

      // Load all form fields into variables
    
      fldUserID = (String) session.getAttribute("UserID");
      fldmember_id = getParam(request, "member_id");
      fldquantity = getParam(request, "quantity");
      // Validate fields
      if ( iAction == iinsertAction || iAction == iupdateAction ) {
        if ( isEmpty(fldquantity) ) {
          sShoppingCartRecordErr = sShoppingCartRecordErr + "The value in field Quantity is required.<br>";
        }
        if ( ! isNumber(fldmember_id)) {
          sShoppingCartRecordErr = sShoppingCartRecordErr + "The value in field member_id is incorrect.<br>";
        }
        if ( ! isNumber(fldquantity)) {
          sShoppingCartRecordErr = sShoppingCartRecordErr + "The value in field Quantity is incorrect.<br>";
        }
        if (sShoppingCartRecordErr.length() > 0 ) {
          return (sShoppingCartRecordErr);
        }
      }


      sSQL = "";
      // Create SQL statement

      switch (iAction) {
  
      case iupdateAction:
        
          sSQL = "update orders set " +
                "member_id=" + toSQL(fldmember_id, adNumber) +
                ",quantity=" + toSQL(fldquantity, adNumber);
          sSQL = sSQL + " where " + sWhere;
        break;
      
      case ideleteAction:
           sSQL = "delete from orders where " + sWhere;
          
        break;
  
      }

      if ( sShoppingCartRecordErr.length() > 0 ) return sShoppingCartRecordErr;
      try {
        // Execute SQL statement
        stat.executeUpdate(sSQL);
      }
      catch(java.sql.SQLException e) {
        sShoppingCartRecordErr = e.toString(); return (sShoppingCartRecordErr);
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
    return (sShoppingCartRecordErr);
  }

  


  void ShoppingCartRecord_Show(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sShoppingCartRecordErr, String sForm, String sAction, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
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
      
      String porder_id = "";

      String flditem_id="";
      String fldUserID="";
      String fldorder_id="";
      String fldmember_id="";
      String fldquantity="";


      boolean bPK = true;

      if ( "".equals(sShoppingCartRecordErr)) {
        // Load primary key and form parameters
        porder_id = getParam( request, "order_id");
      }
      else {
        // Load primary key, form parameters and form fields
        fldorder_id = getParam( request, "order_id");
        fldmember_id = getParam( request, "member_id");
        fldquantity = getParam( request, "quantity");
        porder_id = getParam( request, "PK_order_id");
      }

      
      if ( isEmpty(porder_id)) { bPK = false; }
      
      sWhere += "order_id=" + toSQL(porder_id, adNumber);
      primaryKeyParams += "<input type=\"hidden\" name=\"PK_order_id\" value=\""+porder_id+"\"/>";

      sSQL = "select * from orders where " + sWhere;


      out.println("    <table style=\"\">");
      out.println("     <tr>\n      <td style=\"background-color: #336699; text-align: Center; border-style: outset; border-width: 1\" colspan=\"2\"><font style=\"font-size: 12pt; color: #FFFFFF; font-weight: bold\">ShoppingCart</font></td>\n     </tr>");
      if ( ! sShoppingCartRecordErr.equals("")) {
        out.println("     <tr>\n      <td style=\"background-color: #FFFFFF; border-width: 1\" colspan=\"2\"><font style=\"font-size: 10pt; color: #000000\">"+sShoppingCartRecordErr+"</font></td>\n     </tr>");
      }
      sShoppingCartRecordErr="";
      out.println("     <form method=\"get\" action=\""+sFileName+"\" name=\"ShoppingCartRecord\">");

      java.sql.ResultSet rs = null;

      if ( bPK &&  ! (sAction.equals("insert") && "ShoppingCartRecord".equals(sForm))) {

        // Open recordset
        rs = openrs( stat, sSQL);
        rs.next();
        String[] aFields = getFieldsName( rs );
        getRecordToHash( rs, rsHash, aFields );
        rs.close();
        fldorder_id = (String) rsHash.get("order_id");
        fldmember_id = (String) rsHash.get("member_id");
        flditem_id = (String) rsHash.get("item_id");
        if ( "".equals(sShoppingCartRecordErr)) {
          // Load data from recordset when form displayed first time
          fldquantity = (String) rsHash.get("quantity");
        }

        if (sAction.equals("") || ! "ShoppingCartRecord".equals(sForm)) {
      
          fldorder_id = (String) rsHash.get("order_id");
          fldmember_id = (String) rsHash.get("member_id");
          flditem_id = (String) rsHash.get("item_id");
          fldquantity = (String) rsHash.get("quantity");
        }
        else {
          fldorder_id = (String) rsHash.get("order_id");
          fldmember_id = (String) rsHash.get("member_id");
          flditem_id = (String) rsHash.get("item_id");
        }
        
      }
      else {
        if ( "".equals(sShoppingCartRecordErr)) {
          fldmember_id = toHTML((String) session.getAttribute("UserID"));
        }
      }
      
      // Set lookup fields
          flditem_id = dLookUp( stat, "items", "name", "item_id=" + toSQL(flditem_id, adNumber));


      // Show form field
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Item</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(flditem_id)+"&nbsp;</font>");
      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Quantity</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"quantity\" maxlength=\"5\" value=\""+toHTML(fldquantity)+"\" size=\"5\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td colspan=\"2\" align=\"right\">");
      

      if ( bPK && ! (sAction.equals("insert") && "ShoppingCartRecord".equals(sForm))) {
        out.print("<input type=\"submit\" value=\"Update\" onclick=\"document.ShoppingCartRecord.FormAction.value = 'update';\">");out.print("<input type=\"submit\" value=\"Delete\" onclick=\"document.ShoppingCartRecord.FormAction.value = 'delete';\">");out.print("<input type=\"submit\" value=\"Cancel\" onclick=\"document.ShoppingCartRecord.FormAction.value = 'cancel';\">");
        out.print("<input type=\"hidden\" name=\"FormName\" value=\"ShoppingCartRecord\"><input type=\"hidden\" value=\"update\" name=\"FormAction\">");
      }
      
      else {
        out.print("<input type=\"submit\" value=\"Cancel\" onclick=\"document.ShoppingCartRecord.FormAction.value = 'cancel';\">");
        out.print("<input type=\"hidden\" name=\"FormName\" value=\"ShoppingCartRecord\"><input type=\"hidden\" value=\"insert\" name=\"FormAction\">");
      }out.print("<input type=\"hidden\" name=\"order_id\" value=\""+toHTML(fldorder_id)+"\">");out.print("<input type=\"hidden\" name=\"member_id\" value=\""+toHTML(fldmember_id)+"\">");
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
		<% ShoppingCartRecord_Show(request, response, session, out, sShoppingCartRecordErr, sForm, sAction, conn, stat); %>
		<script language="JavaScript" type="text/javascript">
if (document.forms["ShoppingCartRecord"])
document.ShoppingCartRecord.onsubmit=delconf;
function delconf() {
if (document.ShoppingCartRecord.FormAction.value == 'delete')
  return confirm('Delete record?');
}
</script></td>
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
