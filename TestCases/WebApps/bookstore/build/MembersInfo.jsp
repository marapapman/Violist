<%@ include file="Common.jsp"%>
<%!
//
//   Filename: MembersInfo.jsp
//   Generated with CodeCharge  v.1.2.0
//   JSP.ccp build 05/21/2001
//

static final String sFileName = "MembersInfo.jsp";
              
%>
<%

String cSec = checkSecurity(2, session, response, request);
if ("sendRedirect".equals(cSec) ) return;
                
boolean bDebug = false;

String sAction = getParam( request, "FormAction");
String sForm = getParam( request, "FormName");
String sRecordErr = "";
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
if ( sForm.equals("Record") ) {
  sRecordErr = RecordAction(request, response, session, out, sAction, sForm, conn, stat);
  if ( "sendRedirect".equals(sRecordErr)) return;
}

%>
<%!


  String RecordAction(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sAction, String sForm, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
  
    String sRecordErr ="";
    try {

      if (sAction.equals("")) return "";

      String sSQL="";
      String transitParams = "";
      String primaryKeyParams = "";
      String sQueryString = "";
      String sPage = "";
      String sParams = "";
      String sActionFileName = "AdminMenu.jsp";
      String sWhere = " ";
      boolean bErr = false;
      long iCount = 0;

  
      String pPKmember_id = "";

      final int iinsertAction = 1;
      final int iupdateAction = 2;
      final int ideleteAction = 3;
      int iAction = 0;

      if ( sAction.equalsIgnoreCase("insert") ) { iAction = iinsertAction; }
      if ( sAction.equalsIgnoreCase("update") ) { iAction = iupdateAction; }
      if ( sAction.equalsIgnoreCase("delete") ) { iAction = ideleteAction; }

      // Create WHERE statement


      String fldmember_id="";
      String fldmember_level="";

      // Load all form fields into variables
    

      sSQL = "";
      // Create SQL statement

      if ( sRecordErr.length() > 0 ) return sRecordErr;
      try {
        // Execute SQL statement
        stat.executeUpdate(sSQL);
      }
      catch(java.sql.SQLException e) {
        sRecordErr = e.toString(); return (sRecordErr);
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
    return (sRecordErr);
  }

  


  void Record_Show(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sRecordErr, String sForm, String sAction, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
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
      
      String pmember_id = "";

      String fldmember_id="";
      String fldmember_login="";
      String fldmember_level="";
      String fldname="";
      String fldlast_name="";
      String fldemail="";
      String fldphone="";
      String fldaddress="";
      String fldnotes="";


      boolean bPK = true;

      if ( "".equals(sRecordErr)) {
        // Load primary key and form parameters
        pmember_id = getParam( request, "member_id");
      }
      else {
        // Load primary key, form parameters and form fields
        fldmember_id = getParam( request, "member_id");
        pmember_id = getParam( request, "PK_member_id");
      }

      
      if ( isEmpty(pmember_id)) { bPK = false; }
      
      sWhere += "member_id=" + toSQL(pmember_id, adNumber);
      primaryKeyParams += "<input type=\"hidden\" name=\"PK_member_id\" value=\""+pmember_id+"\"/>";

      sSQL = "select * from members where " + sWhere;


      out.println("    <table style=\"\">");
      out.println("     <tr>\n      <td style=\"background-color: #336699; text-align: Center; border-style: outset; border-width: 1\" colspan=\"2\"><font style=\"font-size: 12pt; color: #FFFFFF; font-weight: bold\">Member Info</font></td>\n     </tr>");
      if ( ! sRecordErr.equals("")) {
        out.println("     <tr>\n      <td style=\"background-color: #FFFFFF; border-width: 1\" colspan=\"2\"><font style=\"font-size: 10pt; color: #000000\">"+sRecordErr+"</font></td>\n     </tr>");
      }
      sRecordErr="";
      out.println("     <form method=\"get\" action=\""+sFileName+"\" name=\"Record\">");

      java.sql.ResultSet rs = null;

      if ( bPK &&  ! (sAction.equals("insert") && "Record".equals(sForm))) {

        // Open recordset
        rs = openrs( stat, sSQL);
        rs.next();
        String[] aFields = getFieldsName( rs );
        getRecordToHash( rs, rsHash, aFields );
        rs.close();
        fldmember_id = (String) rsHash.get("member_id");
        fldmember_login = (String) rsHash.get("member_login");
        fldmember_level = (String) rsHash.get("member_level");
        fldname = (String) rsHash.get("first_name");
        fldlast_name = (String) rsHash.get("last_name");
        fldemail = (String) rsHash.get("email");
        fldphone = (String) rsHash.get("phone");
        fldaddress = (String) rsHash.get("address");
        fldnotes = (String) rsHash.get("notes");

        if (sAction.equals("") || ! "Record".equals(sForm)) {
      
          fldmember_id = (String) rsHash.get("member_id");
          fldmember_login = (String) rsHash.get("member_login");
          fldmember_level = (String) rsHash.get("member_level");
          fldname = (String) rsHash.get("first_name");
          fldlast_name = (String) rsHash.get("last_name");
          fldemail = (String) rsHash.get("email");
          fldphone = (String) rsHash.get("phone");
          fldaddress = (String) rsHash.get("address");
          fldnotes = (String) rsHash.get("notes");
        }
        else {
          fldmember_id = (String) rsHash.get("member_id");
          fldmember_login = (String) rsHash.get("member_login");
          fldmember_level = (String) rsHash.get("member_level");
          fldname = (String) rsHash.get("first_name");
          fldlast_name = (String) rsHash.get("last_name");
          fldemail = (String) rsHash.get("email");
          fldphone = (String) rsHash.get("phone");
          fldaddress = (String) rsHash.get("address");
          fldnotes = (String) rsHash.get("notes");
        }
        
      }
      else {
      }
      


      // Show form field
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Login</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<a href=\"MembersRecord.jsp?"+transitParams+"member_id="+toURL((String) rsHash.get("member_id"))+"&\"><font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldmember_login)+"</font></a>");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Level</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldmember_level)+"&nbsp;</font>");
      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">First Name</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldname)+"&nbsp;</font>");
      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Last Name</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldlast_name)+"&nbsp;</font>");
      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Email</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldemail)+"&nbsp;</font>");
      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Phone</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldphone)+"&nbsp;</font>");
      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Address</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldaddress)+"&nbsp;</font>");
      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Notes</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldnotes)+"&nbsp;</font>");
      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td colspan=\"2\" align=\"right\">");
      

      if ( bPK && ! (sAction.equals("insert") && "Record".equals(sForm))) {
        
        out.print("<input type=\"hidden\" name=\"FormName\" value=\"Record\"><input type=\"hidden\" value=\"\" name=\"FormAction\">");
      }
      out.print("<input type=\"hidden\" name=\"member_id\" value=\""+toHTML(fldmember_id)+"\">");
      out.print(transitParamsHidden+requiredParams+primaryKeyParams);
      out.println("</td>\n     </tr>\n     </form>\n    </table>");
      



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

      String pmember_id="";


    transitParams = "member_id=" + toURL(getParam( request, "member_id")) + "&";
    formParams = "member_id=" + toURL(getParam( request, "member_id")) + "&";boolean bReq = true; 
    // Build WHERE statement
        
    //-- Check member_id parameter and create a valid sql for where clause
  
    pmember_id = getParam( request, "member_id");
    if ( ! isNumber (pmember_id)) {
      pmember_id = "";
    }
    
    if (pmember_id != null && ! pmember_id.equals("")) {
            
        hasParam = true;
        sWhere += "o.member_id=" + pmember_id;
    }
    
    else bReq = false;
    if (hasParam) { sWhere = " AND (" + sWhere + ")"; }
    // Build ORDER statement
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
    
      if ( iSort == 1) { sOrder = " order by o.order_id" + sDirection; }
      if ( iSort == 2) { sOrder = " order by i.name" + sDirection; }
      if ( iSort == 3) { sOrder = " order by o.quantity" + sDirection; }
    }
  

  // Build full SQL statement
  
  sSQL = "select o.item_id as o_item_id, " +
    "o.member_id as o_member_id, " +
    "o.order_id as o_order_id, " +
    "o.quantity as o_quantity, " +
    "i.item_id as i_item_id, " +
    "i.name as i_name " +
    " from orders o, items i" +
    " where i.item_id=o.item_id  ";
  
  sSQL = sSQL + sWhere + sOrder;

  String sNoRecords = "     <tr>\n      <td colspan=\"3\" style=\"background-color: #FFFFFF; border-width: 1\"><font style=\"font-size: 10pt; color: #000000\">No records</font></td>\n     </tr>";


  String tableHeader = "";
      tableHeader = "     <tr>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><a href=\""+sFileName+"?"+formParams+"FormOrders_Sorting=1&FormOrders_Sorted="+sSorted+"&\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Order</font></a></td>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><a href=\""+sFileName+"?"+formParams+"FormOrders_Sorting=2&FormOrders_Sorted="+sSorted+"&\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Item</font></a></td>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><a href=\""+sFileName+"?"+formParams+"FormOrders_Sorting=3&FormOrders_Sorted="+sSorted+"&\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Quantity</font></a></td>\n     </tr>";
  
  
  try {
    out.println("    <table style=\"\">");
    out.println("     <tr>\n      <td style=\"background-color: #336699; text-align: Center; border-style: outset; border-width: 1\" colspan=\"3\"><a name=\"Orders\"><font style=\"font-size: 12pt; color: #FFFFFF; font-weight: bold\">Shopping Cart</font></a></td>\n     </tr>");
    out.println(tableHeader);

    if ( ! bReq ) {
      out.println(sNoRecords);
      out.println("    </table>");
      return;
    }

  }
  catch (Exception e) {}

  
  try {
    java.sql.ResultSet rs = null;
    // Open recordset
    rs = openrs( stat, sSQL);
    iCounter = 0;
    
    java.util.Hashtable rsHash = new java.util.Hashtable();
    String[] aFields = getFieldsName( rs );

    // Show main table based on recordset
    while ( rs.next() ) {

      getRecordToHash( rs, rsHash, aFields );
      String flditem_id = (String) rsHash.get("i_name");
      String fldorder_id = (String) rsHash.get("o_order_id");
      String fldquantity = (String) rsHash.get("o_quantity");

      out.println("     <tr>");
      
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<a href=\"OrdersRecord.jsp?"+transitParams+"order_id="+toURL((String) rsHash.get("o_order_id"))+"&\"><font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldorder_id)+"</font></a>");

      out.println("</td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(flditem_id)+"&nbsp;</font>");
      out.println("</td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldquantity)+"&nbsp;</font>");
      out.println("</td>");
      out.println("     </tr>");
    
      iCounter++;
    }
    if (iCounter == 0) {
      // Recordset is empty
      out.println(sNoRecords);
    
      iCounter = RecordsPerPage+1;
      bIsScroll = false;
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
		<% Record_Show(request, response, session, out, sRecordErr, sForm, sAction, conn, stat); %>

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
