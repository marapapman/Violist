<%@ include file="Common.jsp"%>
<%!
//
//   Filename: OrdersGrid.jsp
//   Generated with CodeCharge  v.1.2.0
//   JSP.ccp build 05/21/2001
//

static final String sFileName = "OrdersGrid.jsp";
              
%>
<%

String cSec = checkSecurity(2, session, response, request);
if ("sendRedirect".equals(cSec) ) return;
                
boolean bDebug = false;

String sAction = getParam( request, "FormAction");
String sForm = getParam( request, "FormName");
String sSearchErr = "";
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

%>
<%!

  void Search_Show (javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sSearchErr, String sForm, String sAction, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
    try {
      

      String flditem_id="";
      String fldmember_id="";


      String sSQL="";
      String transitParams = "";
      String sQueryString = "";
      String sPage = "";
      

      out.println("    <table style=\"\">");
      
      out.println("     <form method=\"get\" action=\"OrdersGrid.jsp\" name=\"Search\">\n     <tr>");
      // Set variables with search parameters
      
      flditem_id = getParam( request, "item_id");
      fldmember_id = getParam( request, "member_id");

      // Show fields
      

      out.println("      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Item</font></td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); 
      out.print("<select name=\"item_id\">"+getOptions( conn, "select item_id, name from items order by 2",true,false,flditem_id)+"</select>");
       out.println("</td>");
      
      out.println("      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Member</font></td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); 
      out.print("<select name=\"member_id\">"+getOptions( conn, "select member_id, member_login from members order by 2",true,false,fldmember_id)+"</select>");
       out.println("</td>");
      
      out.println("      <td ><input type=\"submit\" value=\"Search\"/></td>");
      out.println("     </tr>\n     </form>\n    </table>");
      out.println("");
    }
    catch (Exception e) { out.println(e.toString()); }
  }


  void Orders_Show (javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sOrdersErr, String sForm, String sAction, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException  {
  
    String sWhere = "";
    int iCounter=0;
    int iPage = 0;
    boolean bIsScroll = true;
    boolean hasParam = false;
    String sOrder = "";
    String sSQL="";
    String transitParams = "";
    String sQueryString = "";
    String sPage = "";
    int RecordsPerPage = 20;
    String sSortParams = "";
    String formParams = "";

      String pitem_id="";
      String pmember_id="";


    transitParams = "item_id=" + toURL(getParam( request, "item_id")) + "&member_id=" + toURL(getParam( request, "member_id")) + "&";
    formParams = "item_id=" + toURL(getParam( request, "item_id")) + "&member_id=" + toURL(getParam( request, "member_id")) + "&"; 
    // Build WHERE statement
        
    //-- Check item_id parameter and create a valid sql for where clause
  
    pitem_id = getParam( request, "item_id");
    if ( ! isNumber (pitem_id)) {
      pitem_id = "";
    }
    
    if (pitem_id != null && ! pitem_id.equals("")) {
            
        hasParam = true;
        sWhere += "o.item_id=" + pitem_id;
    }
    
    //-- Check member_id parameter and create a valid sql for where clause
  
    pmember_id = getParam( request, "member_id");
    if ( ! isNumber (pmember_id)) {
      pmember_id = "";
    }
    
    if (pmember_id != null && ! pmember_id.equals("")) {
            
      if (! sWhere.equals("")) sWhere += " and ";
        hasParam = true;
        sWhere += "o.member_id=" + pmember_id;
    }
    
    if (hasParam) { sWhere = " AND (" + sWhere + ")"; }
    // Build ORDER statement
    sOrder = " order by o.order_id Asc";
    String sSort = getParam( request, "FormOrders_Sorting");
    String sSorted = getParam( request, "FormOrders_Sorted");
    String sDirection = "";
    String sForm_Sorting = "";
    int iSort = 0;
    try {
      iSort = Integer.parseInt(sSort);
    }
    catch (NumberFormatException e ) {
      sSort = "";
    }
    if ( iSort == 0 ) {
      sForm_Sorting = "";
    }
    else {
      if ( sSort.equals(sSorted)) { 
        sSorted="0";
        sForm_Sorting = "";
        sDirection = " DESC";
        sSortParams = "FormOrders_Sorting=" + sSort + "&FormOrders_Sorted=" + sSort + "&";
      }
      else {
        sSorted=sSort;
        sForm_Sorting = sSort;
        sDirection = " ASC";
        sSortParams = "FormOrders_Sorting=" + sSort + "&FormOrders_Sorted=" + "&";
      }
    
      if ( iSort == 1) { sOrder = " order by i.name" + sDirection; }
      if ( iSort == 2) { sOrder = " order by m.member_login" + sDirection; }
      if ( iSort == 3) { sOrder = " order by o.quantity" + sDirection; }
    }
  

  // Build full SQL statement
  
  sSQL = "select o.item_id as o_item_id, " +
    "o.member_id as o_member_id, " +
    "o.order_id as o_order_id, " +
    "o.quantity as o_quantity, " +
    "i.item_id as i_item_id, " +
    "i.name as i_name, " +
    "m.member_id as m_member_id, " +
    "m.member_login as m_member_login " +
    " from orders o, items i, members m" +
    " where i.item_id=o.item_id and m.member_id=o.member_id  ";
  
  sSQL = sSQL + sWhere + sOrder;

  String sNoRecords = "     <tr>\n      <td colspan=\"4\" style=\"background-color: #FFFFFF; border-width: 1\"><font style=\"font-size: 10pt; color: #000000\">No records</font></td>\n     </tr>";


  String tableHeader = "";
      tableHeader = "     <tr>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><a &\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Edit</font></a></td>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><a href=\""+sFileName+"?"+formParams+"FormOrders_Sorting=1&FormOrders_Sorted="+sSorted+"&\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Item</font></a></td>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><a href=\""+sFileName+"?"+formParams+"FormOrders_Sorting=2&FormOrders_Sorted="+sSorted+"&\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Member</font></a></td>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><a href=\""+sFileName+"?"+formParams+"FormOrders_Sorting=3&FormOrders_Sorted="+sSorted+"&\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Quantity</font></a></td>\n     </tr>";
  
  
  try {
    out.println("    <table style=\"\">");
    out.println("     <tr>\n      <td style=\"background-color: #336699; text-align: Center; border-style: outset; border-width: 1\" colspan=\"4\"><a name=\"Orders\"><font style=\"font-size: 12pt; color: #FFFFFF; font-weight: bold\">Orders</font></a></td>\n     </tr>");
    out.println(tableHeader);

  }
  catch (Exception e) {}

  
  try {
    // Select current page
    iPage = Integer.parseInt(getParam( request, "FormOrders_Page"));
  }
  catch (NumberFormatException e ) {
    iPage = 0;
  }

  if (iPage == 0) { iPage = 1; }
  RecordsPerPage = 20;
  try {
    java.sql.ResultSet rs = null;
    // Open recordset
    rs = openrs( stat, sSQL);
    iCounter = 0;
    absolute (rs, (iPage-1)*RecordsPerPage+1);
    java.util.Hashtable rsHash = new java.util.Hashtable();
    String[] aFields = getFieldsName( rs );

    // Show main table based on recordset
    while ( (iCounter < RecordsPerPage) && rs.next() ) {

      getRecordToHash( rs, rsHash, aFields );
      String flditem_id = (String) rsHash.get("i_name");
      String fldmember_id = (String) rsHash.get("m_member_login");
      String fldorder_id = (String) rsHash.get("o_order_id");
      String fldquantity = (String) rsHash.get("o_quantity");
      String fldField1= "Edit";

      out.println("     <tr>");
      
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<a href=\"OrdersRecord.jsp?"+transitParams+"order_id="+toURL((String) rsHash.get("o_order_id"))+"&\"><font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldField1)+"</font></a>");

      out.println("</td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(flditem_id)+"&nbsp;</font>");
      out.println("</td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldmember_id)+"&nbsp;</font>");
      out.println("</td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldquantity)+"&nbsp;</font>");
      out.println("</td>");
      out.println("     </tr>");
    
      iCounter++;
    }
    if (iCounter == 0) {
      // Recordset is empty
      out.println(sNoRecords);
    
      out.print("     <tr>\n      <td colspan=\"4\" style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">");
      out.print("<a href=\"OrdersRecord.jsp?"+formParams+"\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Insert</font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
      out.println("</td>\n     </tr>");
    
      iCounter = RecordsPerPage+1;
      bIsScroll = false;
    }

    else {

  
    // Parse scroller
    boolean bInsert = false;
    boolean bNext = rs.next();
    if ( !bNext && iPage == 1 ) {
    
      out.print("     <tr>\n      <td colspan=\"4\" style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\">\n       <font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">");
      out.print("\n        <a href=\"OrdersRecord.jsp?"+formParams+"\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Insert</font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
      out.println("\n      </td>\n     </tr>");
    
    }
    else {
      out.print("     <tr>\n      <td colspan=\"4\" style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">");
    
      out.print("\n       <a href=\"OrdersRecord.jsp?"+formParams+"\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Insert</font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
      bInsert = true;
    
      if ( iPage == 1 ) {
        out.print("\n       <a href_=\"#\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Previous</font></a>");
      }
      else {
        out.print("\n       <a href=\""+sFileName+"?"+formParams+sSortParams+"FormOrders_Page="+(iPage - 1)+"#Form\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Previous</font></a>");
      }
  
      out.print("\n       [ "+iPage+" ]");
  
      if (!bNext) { 
        out.print("\n       <a href_=\"#\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Next</font></a><br>");
      }
      else {
        out.print("\n       <a href=\""+sFileName+"?"+formParams+sSortParams+"FormOrders_Page="+(iPage + 1)+"#Form\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Next</font></a><br>");
      }
    
      if ( ! bInsert ) {
        out.print("     <tr>\n      <td colspan=\"4\" style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">");
        out.print("\n        <a href=\"OrdersRecord.jsp?"+formParams+"\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Insert</font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
      }
    
      out.println("</td>\n     </tr>");
    }
  
    }

    if ( rs != null ) rs.close();
    out.println("    </table>");
    
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
