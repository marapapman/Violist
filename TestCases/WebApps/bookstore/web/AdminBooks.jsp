<%@ include file="Common.jsp"%>

<%!
//
//   Filename: AdminBooks.jsp
//   Generated with CodeCharge  v.1.2.0
//   JSP.ccp build 05/21/2001
//

static final String sFileName = "AdminBooks.jsp";
              
%>
<%

String cSec = checkSecurity(2, session, response, request);
if ("sendRedirect".equals(cSec) ) return;
                
boolean bDebug = false;

String sAction = getParam( request, "FormAction");
String sForm = getParam( request, "FormName");
String sSearchErr = "";
String sItemsErr = "";

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
      

      String fldcategory_id="";
      String fldis_recommended="";


      String sSQL="";
      String transitParams = "";
      String sQueryString = "";
      String sPage = "";
      

      out.println("    <table style=\"\">");
      
      out.println("     <form method=\"get\" action=\"AdminBooks.jsp\" name=\"Search\">\n     <tr>");
      // Set variables with search parameters
      
      fldcategory_id = getParam( request, "category_id");
      fldis_recommended = getParam( request, "is_recommended");

      // Show fields
      

      out.println("      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Category</font></td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); 
      out.print("<select name=\"category_id\">"+getOptions( conn, "select category_id, name from categories order by 2",true,false,fldcategory_id)+"</select>");
       out.println("</td>");
      
      out.println("      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Recommended</font></td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); 
      out.print("<select name=\"is_recommended\">"+getOptionsLOV(";All;0;No;1;Yes",true,false,fldis_recommended)+"</select>");
       out.println("</td>");
      
      out.println("      <td ><input type=\"submit\" value=\"Search\"/></td>");
      out.println("     </tr>\n     </form>\n    </table>");
      out.println("");
    }
    catch (Exception e) { out.println(e.toString()); }
  }


  void Items_Show (javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sItemsErr, String sForm, String sAction, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException  {
  
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

      String pcategory_id="";
      String pis_recommended="";


    transitParams = "category_id=" + toURL(getParam( request, "category_id")) + "&is_recommended=" + toURL(getParam( request, "is_recommended")) + "&";
    formParams = "category_id=" + toURL(getParam( request, "category_id")) + "&is_recommended=" + toURL(getParam( request, "is_recommended")) + "&"; 
    // Build WHERE statement
        
    //-- Check category_id parameter and create a valid sql for where clause
  
    pcategory_id = getParam( request, "category_id");
    if ( ! isNumber (pcategory_id)) {
      pcategory_id = "";
    }
    
    if (pcategory_id != null && ! pcategory_id.equals("")) {
            
        hasParam = true;
        sWhere += "i.category_id=" + pcategory_id;
    }
    
    //-- Check is_recommended parameter and create a valid sql for where clause
  
    pis_recommended = getParam( request, "is_recommended");
    if ( ! isNumber (pis_recommended)) {
      pis_recommended = "";
    }
    
    if (pis_recommended != null && ! pis_recommended.equals("")) {
            
      if (! sWhere.equals("")) sWhere += " and ";
        hasParam = true;
        sWhere += "i.is_recommended=" + pis_recommended;
    }
    
    if (hasParam) { sWhere = " AND (" + sWhere + ")"; }
    // Build ORDER statement
    String sSort = getParam( request, "FormItems_Sorting");
    String sSorted = getParam( request, "FormItems_Sorted");
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
        sSortParams = "FormItems_Sorting=" + sSort + "&FormItems_Sorted=" + sSort + "&";
      }
      else {
        sSorted=sSort;
        sForm_Sorting = sSort;
        sDirection = " ASC";
        sSortParams = "FormItems_Sorting=" + sSort + "&FormItems_Sorted=" + "&";
      }
    
      if ( iSort == 1) { sOrder = " order by i.name" + sDirection; }
      if ( iSort == 2) { sOrder = " order by i.author" + sDirection; }
      if ( iSort == 3) { sOrder = " order by i.price" + sDirection; }
      if ( iSort == 4) { sOrder = " order by c.name" + sDirection; }
      if ( iSort == 5) { sOrder = " order by i.is_recommended" + sDirection; }
    }
  

  // Build full SQL statement
  
  sSQL = "select i.author as i_author, " +
    "i.category_id as i_category_id, " +
    "i.is_recommended as i_is_recommended, " +
    "i.item_id as i_item_id, " +
    "i.name as i_name, " +
    "i.price as i_price, " +
    "c.category_id as c_category_id, " +
    "c.name as c_name " +
    " from items i, categories c" +
    " where c.category_id=i.category_id  ";
  
  sSQL = sSQL + sWhere + sOrder;

  String sNoRecords = "     <tr>\n      <td colspan=\"6\" style=\"background-color: #FFFFFF; border-width: 1\"><font style=\"font-size: 10pt; color: #000000\">No records</font></td>\n     </tr>";


  String tableHeader = "";
      tableHeader = "     <tr>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><a &\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Edit</font></a></td>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><a href=\""+sFileName+"?"+formParams+"FormItems_Sorting=1&FormItems_Sorted="+sSorted+"&\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Title</font></a></td>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><a href=\""+sFileName+"?"+formParams+"FormItems_Sorting=2&FormItems_Sorted="+sSorted+"&\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Author</font></a></td>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><a href=\""+sFileName+"?"+formParams+"FormItems_Sorting=3&FormItems_Sorted="+sSorted+"&\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Price</font></a></td>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><a href=\""+sFileName+"?"+formParams+"FormItems_Sorting=4&FormItems_Sorted="+sSorted+"&\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Category</font></a></td>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><a href=\""+sFileName+"?"+formParams+"FormItems_Sorting=5&FormItems_Sorted="+sSorted+"&\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Recommended</font></a></td>\n     </tr>";
  
  
  try {
    out.println("    <table style=\"\">");
    out.println("     <tr>\n      <td style=\"background-color: #336699; text-align: Center; border-style: outset; border-width: 1\" colspan=\"6\"><a name=\"Items\"><font style=\"font-size: 12pt; color: #FFFFFF; font-weight: bold\">Books</font></a></td>\n     </tr>");
    out.println(tableHeader);

  }
  catch (Exception e) {}

  
  try {
    // Select current page
    iPage = Integer.parseInt(getParam( request, "FormItems_Page"));
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
      String fldauthor = (String) rsHash.get("i_author");
      String fldcategory_id = (String) rsHash.get("c_name");
      String fldis_recommended = (String) rsHash.get("i_is_recommended");
      String fldname = (String) rsHash.get("i_name");
      String fldprice = (String) rsHash.get("i_price");
      String fldField1= "Edit";

      out.println("     <tr>");
      
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<a href=\"BookMaint.jsp?"+transitParams+"item_id="+toURL((String) rsHash.get("i_item_id"))+"&\"><font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldField1)+"</font></a>");

      out.println("</td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldname)+"&nbsp;</font>");
      out.println("</td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldauthor)+"&nbsp;</font>");
      out.println("</td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldprice)+"&nbsp;</font>");
      out.println("</td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldcategory_id)+"&nbsp;</font>");
      out.println("</td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); 
        fldis_recommended = getValFromLOV(fldis_recommended, "0;No;1;Yes");out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldis_recommended)+"&nbsp;</font>");
      out.println("</td>");
      out.println("     </tr>");
    
      iCounter++;
    }
    if (iCounter == 0) {
      // Recordset is empty
      out.println(sNoRecords);
    
      out.print("     <tr>\n      <td colspan=\"6\" style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">");
      out.print("<a href=\"BookMaint.jsp?"+formParams+"\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Add New</font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
      out.println("</td>\n     </tr>");
    
      iCounter = RecordsPerPage+1;
      bIsScroll = false;
    }

    else {

  
    // Parse scroller
    boolean bInsert = false;
    boolean bNext = rs.next();
    if ( !bNext && iPage == 1 ) {
    
      out.print("     <tr>\n      <td colspan=\"6\" style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\">\n       <font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">");
      out.print("\n        <a href=\"BookMaint.jsp?"+formParams+"\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Add New</font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
      out.println("\n      </td>\n     </tr>");
    
    }
    else {
      out.print("     <tr>\n      <td colspan=\"6\" style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">");
    
      out.print("\n       <a href=\"BookMaint.jsp?"+formParams+"\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Add New</font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
      bInsert = true;
    
      if ( iPage == 1 ) {
        out.print("\n       <a href_=\"#\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Previous</font></a>");
      }
      else {
        out.print("\n       <a href=\""+sFileName+"?"+formParams+sSortParams+"FormItems_Page="+(iPage - 1)+"#Form\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Previous</font></a>");
      }
  
      out.print("\n       [ "+iPage+" ]");
  
      if (!bNext) { 
        out.print("\n       <a href_=\"#\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Next</font></a><br>");
      }
      else {
        out.print("\n       <a href=\""+sFileName+"?"+formParams+sSortParams+"FormItems_Page="+(iPage + 1)+"#Form\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Next</font></a><br>");
      }
    
      if ( ! bInsert ) {
        out.print("     <tr>\n      <td colspan=\"6\" style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">");
        out.print("\n        <a href=\"BookMaint.jsp?"+formParams+"\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Add New</font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
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
		<% Items_Show(request, response, session, out, sItemsErr, sForm, sAction, conn, stat); %>

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
