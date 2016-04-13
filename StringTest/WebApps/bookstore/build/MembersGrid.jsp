<%@ include file="Common.jsp" %><%!
//
//   Filename: MembersGrid.jsp
//   Generated with CodeCharge  v.1.2.0
//   JSP.ccp build 05/21/2001
//

static final String sFileName = "MembersGrid.jsp";
              
%><%

String cSec = checkSecurity(2, session, response, request);
if ("sendRedirect".equals(cSec) ) return;
                
boolean bDebug = false;

String sAction = getParam( request, "FormAction");
String sForm = getParam( request, "FormName");
String sSearchErr = "";
String sMembersErr = "";

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


      String sSQL="";
      String transitParams = "";
      String sQueryString = "";
      String sPage = "";
      

      out.println("    <table style=\"\">");
      
      out.println("     <form method=\"get\" action=\"MembersGrid.jsp\" name=\"Search\">\n     <tr>");
      // Set variables with search parameters
      
      fldname = getParam( request, "name");

      // Show fields
      

      out.println("      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Name</font></td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"name\" maxlength=\"10\" value=\""+toHTML(fldname)+"\" size=\"10\">");
 out.println("</td>");
      
      out.println("      <td ><input type=\"submit\" value=\"Search\"/></td>");
      out.println("     </tr>\n     </form>\n    </table>");
      out.println("");
    }
    catch (Exception e) { out.println(e.toString()); }
  }


  void Members_Show (javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sMembersErr, String sForm, String sAction, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException  {
  
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

      String pname="";


    transitParams = "name=" + toURL(getParam( request, "name")) + "&";
    formParams = "name=" + toURL(getParam( request, "name")) + "&"; 
    // Build WHERE statement
    // Check Members parameter and create a valid sql for where clause 
   
    pname = getParam( request, "name");


    if ( ! pname.equals("") ) { 
      hasParam = true;
      sWhere = "m.member_login like '%" + replace(pname, "'", "''") + "%'" + " or " + "m.first_name like '%" + replace(pname, "'", "''") + "%'" + " or " + "m.last_name like '%" + replace(pname, "'", "''") + "%'";}
    if (hasParam) { sWhere = " WHERE (" + sWhere + ")"; }
    // Build ORDER statement
    sOrder = " order by m.member_login Asc";
    String sSort = getParam( request, "FormMembers_Sorting");
    String sSorted = getParam( request, "FormMembers_Sorted");
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
        sSortParams = "FormMembers_Sorting=" + sSort + "&FormMembers_Sorted=" + sSort + "&";
      }
      else {
        sSorted=sSort;
        sForm_Sorting = sSort;
        sDirection = " ASC";
        sSortParams = "FormMembers_Sorting=" + sSort + "&FormMembers_Sorted=" + "&";
      }
    
      if ( iSort == 1) { sOrder = " order by m.member_login" + sDirection; }
      if ( iSort == 2) { sOrder = " order by m.first_name" + sDirection; }
      if ( iSort == 3) { sOrder = " order by m.last_name" + sDirection; }
      if ( iSort == 4) { sOrder = " order by m.member_level" + sDirection; }
    }
  

  // Build full SQL statement
  
  sSQL = "select m.first_name as m_first_name, " +
    "m.last_name as m_last_name, " +
    "m.member_id as m_member_id, " +
    "m.member_level as m_member_level, " +
    "m.member_login as m_member_login " +
    " from members m ";
  
  sSQL = sSQL + sWhere + sOrder;

  String sNoRecords = "     <tr>\n      <td colspan=\"4\" style=\"background-color: #FFFFFF; border-width: 1\"><font style=\"font-size: 10pt; color: #000000\">No records</font></td>\n     </tr>";


  String tableHeader = "";
      tableHeader = "     <tr>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><a href=\""+sFileName+"?"+formParams+"FormMembers_Sorting=1&FormMembers_Sorted="+sSorted+"&\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Login</font></a></td>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><a href=\""+sFileName+"?"+formParams+"FormMembers_Sorting=2&FormMembers_Sorted="+sSorted+"&\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">First Name</font></a></td>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><a href=\""+sFileName+"?"+formParams+"FormMembers_Sorting=3&FormMembers_Sorted="+sSorted+"&\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Last Name</font></a></td>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><a href=\""+sFileName+"?"+formParams+"FormMembers_Sorting=4&FormMembers_Sorted="+sSorted+"&\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Level</font></a></td>\n     </tr>";
  
  
  try {
    out.println("    <table style=\"\">");
    out.println("     <tr>\n      <td style=\"background-color: #336699; text-align: Center; border-style: outset; border-width: 1\" colspan=\"4\"><a name=\"Members\"><font style=\"font-size: 12pt; color: #FFFFFF; font-weight: bold\">Members</font></a></td>\n     </tr>");
    out.println(tableHeader);

  }
  catch (Exception e) {}

  
  try {
    // Select current page
    iPage = Integer.parseInt(getParam( request, "FormMembers_Page"));
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
      String fldname = (String) rsHash.get("m_first_name");
      String fldlast_name = (String) rsHash.get("m_last_name");
      String fldmember_level = (String) rsHash.get("m_member_level");
      String fldmember_login = (String) rsHash.get("m_member_login");

      out.println("     <tr>");
      
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<a href=\"MembersInfo.jsp?"+transitParams+"member_id="+toURL((String) rsHash.get("m_member_id"))+"&\"><font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldmember_login)+"</font></a>");

      out.println("</td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldname)+"&nbsp;</font>");
      out.println("</td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldlast_name)+"&nbsp;</font>");
      out.println("</td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); 
        fldmember_level = getValFromLOV(fldmember_level, "1;Member;2;Administrator");out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldmember_level)+"&nbsp;</font>");
      out.println("</td>");
      out.println("     </tr>");
    
      iCounter++;
    }
    if (iCounter == 0) {
      // Recordset is empty
      out.println(sNoRecords);
    
      out.print("     <tr>\n      <td colspan=\"4\" style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">");
      out.print("<a href=\"MembersRecord.jsp?"+formParams+"\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Insert</font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
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
      out.print("\n        <a href=\"MembersRecord.jsp?"+formParams+"\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Insert</font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
      out.println("\n      </td>\n     </tr>");
    
    }
    else {
      out.print("     <tr>\n      <td colspan=\"4\" style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">");
    
      out.print("\n       <a href=\"MembersRecord.jsp?"+formParams+"\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Insert</font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
      bInsert = true;
    
      if ( iPage == 1 ) {
        out.print("\n       <a href_=\"#\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Previous</font></a>");
      }
      else {
        out.print("\n       <a href=\""+sFileName+"?"+formParams+sSortParams+"FormMembers_Page="+(iPage - 1)+"#Form\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Previous</font></a>");
      }
  
      out.print("\n       [ "+iPage+" ]");
  
      if (!bNext) { 
        out.print("\n       <a href_=\"#\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Next</font></a><br>");
      }
      else {
        out.print("\n       <a href=\""+sFileName+"?"+formParams+sSortParams+"FormMembers_Page="+(iPage + 1)+"#Form\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Next</font></a><br>");
      }
    
      if ( ! bInsert ) {
        out.print("     <tr>\n      <td colspan=\"4\" style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">");
        out.print("\n        <a href=\"MembersRecord.jsp?"+formParams+"\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Insert</font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
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
<meta name="GENERATOR" content="YesSoftware CodeCharge v.1.2.0 / JSP.ccp build 05/21/2001"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body style="background-color: #FFFFFF; color: #000000; font-family: Arial, Tahoma, Verdana, Helveticabackground-color: #FFFFFF; color: #000000; font-family: Arial, Tahoma, Verdana, Helvetica">
<jsp:include page="Header.jsp" flush="true"/>
 <table>
  <tr>
   
   <td valign="top"><font face=arial size=2> Enter full or partial login, first or last name</font>
<% Search_Show(request, response, session, out, sSearchErr, sForm, sAction, conn, stat); %>
    
   </td>
  </tr>
 </table>
 <table>
  <tr>
   <td valign="top">
<% Members_Show(request, response, session, out, sMembersErr, sForm, sAction, conn, stat); %>
    
   </td>
  </tr>
 </table>

<jsp:include page="Footer.jsp" flush="true"/>
<center><font face="Arial"><small>This dynamic site was generated with <a href="http://www.codecharge.com">CodeCharge</a></small></font></center>
</body>
</html>
<%%>
<%
if ( stat != null ) stat.close();
if ( conn != null ) conn.close();
%>
