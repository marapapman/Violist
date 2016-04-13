<%@ include file="Common.jsp"%>
<%!
//
//   Filename: OrdersRecord.jsp
//   Generated with CodeCharge  v.1.2.0
//   JSP.ccp build 05/21/2001
//

static final String sFileName = "OrdersRecord.jsp";
              
%>
<%

String cSec = checkSecurity(2, session, response, request);
if ("sendRedirect".equals(cSec) ) return;
                
boolean bDebug = false;

String sAction = getParam( request, "FormAction");
String sForm = getParam( request, "FormName");
String sOrdersErr = "";

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
if ( sForm.equals("Orders") ) {
  sOrdersErr = OrdersAction(request, response, session, out, sAction, sForm, conn, stat);
  if ( "sendRedirect".equals(sOrdersErr)) return;
}

%>
<%!


  String OrdersAction(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sAction, String sForm, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
  
    String sOrdersErr ="";
    try {

      if (sAction.equals("")) return "";

      String sSQL="";
      String transitParams = "";
      String primaryKeyParams = "";
      String sQueryString = "";
      String sPage = "";
      String sParams = "";
      String sActionFileName = "OrdersGrid.jsp";
      String sWhere = " ";
      boolean bErr = false;
      long iCount = 0;

  
      sParams = "?";
      sParams += "item_id=" + toURL(getParam( request, "Trn_item_id")) + "&";
      sParams += "member_id=" + toURL(getParam( request, "Trn_member_id"));
      String pPKorder_id = "";
      if (sAction.equalsIgnoreCase("cancel") ) {
        try {
          if ( stat != null ) stat.close();
          if ( conn != null ) conn.close();
        }
        catch ( java.sql.SQLException ignore ) {}
        response.sendRedirect (sActionFileName + sParams);
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
        if ( isEmpty(pPKorder_id)) return sOrdersErr;
        sWhere = "order_id=" + toSQL(pPKorder_id, adNumber);
      }


      String fldmember_id="";
      String flditem_id="";
      String fldquantity="";
      String fldorder_id="";

      // Load all form fields into variables
    
      fldmember_id = getParam(request, "member_id");
      flditem_id = getParam(request, "item_id");
      fldquantity = getParam(request, "quantity");
      // Validate fields
      if ( iAction == iinsertAction || iAction == iupdateAction ) {
        if ( isEmpty(fldmember_id) ) {
          sOrdersErr = sOrdersErr + "The value in field Member is required.<br>";
        }
        if ( isEmpty(flditem_id) ) {
          sOrdersErr = sOrdersErr + "The value in field Item is required.<br>";
        }
        if ( isEmpty(fldquantity) ) {
          sOrdersErr = sOrdersErr + "The value in field Quantity is required.<br>";
        }
        if ( ! isNumber(fldmember_id)) {
          sOrdersErr = sOrdersErr + "The value in field Member is incorrect.<br>";
        }
        if ( ! isNumber(flditem_id)) {
          sOrdersErr = sOrdersErr + "The value in field Item is incorrect.<br>";
        }
        if ( ! isNumber(fldquantity)) {
          sOrdersErr = sOrdersErr + "The value in field Quantity is incorrect.<br>";
        }
        if (sOrdersErr.length() > 0 ) {
          return (sOrdersErr);
        }
      }


      sSQL = "";
      // Create SQL statement

      switch (iAction) {
  
        case iinsertAction :
          
            sSQL = "insert into orders (" + 
                "member_id," +
                "item_id," +
                "quantity)" +

                " values (" + 
                toSQL(fldmember_id, adNumber) + "," +
                toSQL(flditem_id, adNumber) + "," +
                toSQL(fldquantity, adNumber) + ")";
          break;
  
      case iupdateAction:
        
          sSQL = "update orders set " +
                "member_id=" + toSQL(fldmember_id, adNumber) +
                ",item_id=" + toSQL(flditem_id, adNumber) +
                ",quantity=" + toSQL(fldquantity, adNumber);
          sSQL = sSQL + " where " + sWhere;
        break;
      
      case ideleteAction:
           sSQL = "delete from orders where " + sWhere;
          
        break;
  
      }

      if ( sOrdersErr.length() > 0 ) return sOrdersErr;
      try {
        // Execute SQL statement
        stat.executeUpdate(sSQL);
      }
      catch(java.sql.SQLException e) {
        sOrdersErr = e.toString(); return (sOrdersErr);
      }
  
      try {
        if ( stat != null ) stat.close();
        if ( conn != null ) conn.close();
      }
      catch ( java.sql.SQLException ignore ) {}
      response.sendRedirect (sActionFileName + sParams);

      return "sendRedirect";
    }
    catch (Exception e) {out.println(e.toString()); }
    return (sOrdersErr);
  }

  


  void Orders_Show(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sOrdersErr, String sForm, String sAction, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
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
      String fldmember_id="";
      String fldorder_id="";
      String fldquantity="";


      boolean bPK = true;

      if ( "".equals(sOrdersErr)) {
        // Load primary key and form parameters
        flditem_id = getParam( request, "item_id");
        fldmember_id = getParam( request, "member_id");
        fldorder_id = getParam( request, "order_id");
        transitParamsHidden += "<input type=\"hidden\" name=\"Trn_item_id\" value=\""+getParam( request, "item_id")+"\">";
        transitParams += "Trn_item_id="+getParam( request, "item_id")+"&";
        transitParamsHidden += "<input type=\"hidden\" name=\"Trn_member_id\" value=\""+getParam( request, "member_id")+"\">";
        transitParams += "Trn_member_id="+getParam( request, "member_id")+"&";
        porder_id = getParam( request, "order_id");
      }
      else {
        // Load primary key, form parameters and form fields
        fldmember_id = getParam( request, "member_id");
        flditem_id = getParam( request, "item_id");
        fldquantity = getParam( request, "quantity");
        transitParamsHidden += "<input type=\"hidden\" name=\"Trn_item_id\" value=\""+getParam( request, "Trn_item_id")+"\">";
        transitParams += "Trn_item_id="+getParam( request, "Trn_item_id")+"&";
        
        transitParamsHidden += "<input type=\"hidden\" name=\"Trn_member_id\" value=\""+getParam( request, "Trn_member_id")+"\">";
        transitParams += "Trn_member_id="+getParam( request, "Trn_member_id")+"&";
        
        porder_id = getParam( request, "PK_order_id");
      }

      
      if ( isEmpty(porder_id)) { bPK = false; }
      
      sWhere += "order_id=" + toSQL(porder_id, adNumber);
      primaryKeyParams += "<input type=\"hidden\" name=\"PK_order_id\" value=\""+porder_id+"\"/>";

      sSQL = "select * from orders where " + sWhere;


      out.println("    <table style=\"\">");
      out.println("     <tr>\n      <td style=\"background-color: #336699; text-align: Center; border-style: outset; border-width: 1\" colspan=\"2\"><font style=\"font-size: 12pt; color: #FFFFFF; font-weight: bold\">Orders</font></td>\n     </tr>");
      if ( ! sOrdersErr.equals("")) {
        out.println("     <tr>\n      <td style=\"background-color: #FFFFFF; border-width: 1\" colspan=\"2\"><font style=\"font-size: 10pt; color: #000000\">"+sOrdersErr+"</font></td>\n     </tr>");
      }
      sOrdersErr="";
      out.println("     <form method=\"get\" action=\""+sFileName+"\" name=\"Orders\">");

      java.sql.ResultSet rs = null;

      if ( bPK &&  ! (sAction.equals("insert") && "Orders".equals(sForm))) {

        // Open recordset
        rs = openrs( stat, sSQL);
        rs.next();
        String[] aFields = getFieldsName( rs );
        getRecordToHash( rs, rsHash, aFields );
        rs.close();
        fldorder_id = (String) rsHash.get("order_id");
        if ( "".equals(sOrdersErr)) {
          // Load data from recordset when form displayed first time
          fldmember_id = (String) rsHash.get("member_id");
          flditem_id = (String) rsHash.get("item_id");
          fldquantity = (String) rsHash.get("quantity");
        }

        if (sAction.equals("") || ! "Orders".equals(sForm)) {
      
          fldorder_id = (String) rsHash.get("order_id");
          fldmember_id = (String) rsHash.get("member_id");
          flditem_id = (String) rsHash.get("item_id");
          fldquantity = (String) rsHash.get("quantity");
        }
        else {
          fldorder_id = (String) rsHash.get("order_id");
        }
        
      }
      else {
        if ( "".equals(sOrdersErr)) {
          fldorder_id = toHTML(getParam(request,"order_id"));
          fldmember_id = toHTML(getParam(request,"member_id"));
          flditem_id = toHTML(getParam(request,"item_id"));
        }
      }
      


      // Show form field
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Order</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldorder_id)+"&nbsp;</font>");
      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Member</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); 
      out.print("<select name=\"member_id\">"+getOptions( conn, "select member_id, member_login from members order by 2",false,true,fldmember_id)+"</select>");
      
      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Item</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); 
      out.print("<select name=\"item_id\">"+getOptions( conn, "select item_id, name from items order by 2",false,true,flditem_id)+"</select>");
      
      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Quantity</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"quantity\" maxlength=\"10\" value=\""+toHTML(fldquantity)+"\" size=\"10\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td colspan=\"2\" align=\"right\">");
      

      if ( bPK && ! (sAction.equals("insert") && "Orders".equals(sForm))) {
        out.print("<input type=\"submit\" value=\"Update\" onclick=\"document.Orders.FormAction.value = 'update';\">");out.print("<input type=\"submit\" value=\"Delete\" onclick=\"document.Orders.FormAction.value = 'delete';\">");out.print("<input type=\"submit\" value=\"Cancel\" onclick=\"document.Orders.FormAction.value = 'cancel';\">");
        out.print("<input type=\"hidden\" name=\"FormName\" value=\"Orders\"><input type=\"hidden\" value=\"update\" name=\"FormAction\">");
      }
      
      else {
        out.print("<input type=\"submit\" value=\"Insert\" onclick=\"document.Orders.FormAction.value = 'insert';\">");out.print("<input type=\"submit\" value=\"Cancel\" onclick=\"document.Orders.FormAction.value = 'cancel';\">");
        out.print("<input type=\"hidden\" name=\"FormName\" value=\"Orders\"><input type=\"hidden\" value=\"insert\" name=\"FormAction\">");
      }
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
		<% Orders_Show(request, response, session, out, sOrdersErr, sForm, sAction, conn, stat); %>

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
