<%@ include file="Common.jsp"%>
<%!
//
//   Filename: Books.jsp
//   Generated with CodeCharge  v.1.2.0
//   JSP.ccp build 05/21/2001
//

static final String sFileName = "Books.jsp";
              
%>
<%

boolean bDebug = false;

String sAction = getParam( request, "FormAction");
String sForm = getParam( request, "FormName");
String sResultsErr = "";
String sSearchErr = "";
String sAdvMenuErr = "";
String sTotalErr = "";

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

  void Results_Show (javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sResultsErr, String sForm, String sAction, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException  {
  
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

      String pauthor="";
      String pcategory_id="";
      String pname="";
      String ppricemax="";
      String ppricemin="";


    transitParams = "category_id=" + toURL(getParam( request, "category_id")) + "&name=" + toURL(getParam( request, "name")) + "&pricemin=" + toURL(getParam( request, "pricemin")) + "&pricemax=" + toURL(getParam( request, "pricemax")) + "&author=" + toURL(getParam( request, "author")) + "&";
    formParams = "category_id=" + toURL(getParam( request, "category_id")) + "&name=" + toURL(getParam( request, "name")) + "&pricemin=" + toURL(getParam( request, "pricemin")) + "&pricemax=" + toURL(getParam( request, "pricemax")) + "&author=" + toURL(getParam( request, "author")) + "&"; 
    // Build WHERE statement
        
    //-- Check author parameter and create a valid sql for where clause
  
    pauthor = getParam( request, "author");
    if (pauthor != null && ! pauthor.equals("")) {
            
        hasParam = true;
        sWhere += "i.author like '%" + replace(pauthor, "'", "''") + "%'";
    }
    
    //-- Check category_id parameter and create a valid sql for where clause
  
    pcategory_id = getParam( request, "category_id");
    //if ( ! isNumber (pcategory_id)) {
    //  pcategory_id = "";
    //}
    
    if (pcategory_id != null && ! pcategory_id.equals("")) {
            
      if (! sWhere.equals("")) sWhere += " and ";
        hasParam = true;
        sWhere += "i.category_id=" + pcategory_id;
    }
    
    //-- Check name parameter and create a valid sql for where clause
  
    pname = getParam( request, "name");
    if (pname != null && ! pname.equals("")) {
            
      if (! sWhere.equals("")) sWhere += " and ";
        hasParam = true;
        sWhere += "i.name like '%" + replace(pname, "'", "''") + "%'";
    }
    
    //-- Check pricemax parameter and create a valid sql for where clause
  
    ppricemax = getParam( request, "pricemax");
    if ( ! isNumber (ppricemax)) {
      ppricemax = "";
    }
    
    if (ppricemax != null && ! ppricemax.equals("")) {
            
      if (! sWhere.equals("")) sWhere += " and ";
        hasParam = true;
        sWhere += "i.price<" + ppricemax;
    }
    
    //-- Check pricemin parameter and create a valid sql for where clause
  
    ppricemin = getParam( request, "pricemin");
    if ( ! isNumber (ppricemin)) {
      ppricemin = "";
    }
    
    if (ppricemin != null && ! ppricemin.equals("")) {
            
      if (! sWhere.equals("")) sWhere += " and ";
        hasParam = true;
        sWhere += "i.price>" + ppricemin;
    }
    
    if (hasParam) { sWhere = " AND (" + sWhere + ")"; }
    // Build ORDER statement
    sOrder = " order by i.name Asc";
    String sSort = getParam( request, "FormResults_Sorting");
    String sSorted = getParam( request, "FormResults_Sorted");
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
        sSortParams = "FormResults_Sorting=" + sSort + "&FormResults_Sorted=" + sSort + "&";
      }
      else {
        sSorted=sSort;
        sForm_Sorting = sSort;
        sDirection = " ASC";
        sSortParams = "FormResults_Sorting=" + sSort + "&FormResults_Sorted=" + "&";
      }
    
      if ( iSort == 1) { sOrder = " order by i.name" + sDirection; }
      if ( iSort == 2) { sOrder = " order by i.author" + sDirection; }
      if ( iSort == 3) { sOrder = " order by i.price" + sDirection; }
      if ( iSort == 4) { sOrder = " order by c.name" + sDirection; }
      if ( iSort == 5) { sOrder = " order by i.image_url" + sDirection; }
    }
  

  // Build full SQL statement
  
  sSQL = "select i.author as i_author, " +
    "i.category_id as i_category_id, " +
    "i.image_url as i_image_url, " +
    "i.item_id as i_item_id, " +
    "i.name as i_name, " +
    "i.price as i_price, " +
    "c.category_id as c_category_id, " +
    "c.name as c_name " +
    " from items i, categories c" +
    " where c.category_id=i.category_id  ";
  
  sSQL = sSQL + sWhere + sOrder;

  String sNoRecords = "     <tr>\n      <td colspan=\"2\" style=\"background-color: #FFFFFF; border-width: 1\"><font style=\"font-size: 10pt; color: #000000\">No records</font></td>\n     </tr>";


  String tableHeader = "";
    
  
  try {
    out.println("    <table style=\"\">");
    out.println("     <tr>\n      <td style=\"background-color: #336699; text-align: Center; border-style: outset; border-width: 1\" colspan=\"2\"><a name=\"Results\"><font style=\"font-size: 12pt; color: #FFFFFF; font-weight: bold\">Search Results</font></a></td>\n     </tr>");
    out.println(tableHeader);

  }
  catch (Exception e) {}

  
  try {
    // Select current page
    iPage = Integer.parseInt(getParam( request, "FormResults_Page"));
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
      String fldimage_url = (String) rsHash.get("i_image_url");
      String fldname = (String) rsHash.get("i_name");
      String fldprice = (String) rsHash.get("i_price");
fldimage_url="<img border=0 src=" + fldimage_url + ">";

      out.print("     <tr>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Title</font> </td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<a href=\"BookDetail.jsp?"+transitParams+"item_id="+toURL((String) rsHash.get("i_item_id"))+"&\"><font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldname)+"</font></a>");

      out.println("</td>\n     </tr>");
      out.print("     <tr>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Author</font> </td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldauthor)+"&nbsp;</font>");
      out.println("</td>\n     </tr>");
      out.print("     <tr>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Price</font> </td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldprice)+"&nbsp;</font>");
      out.println("</td>\n     </tr>");
      out.print("     <tr>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Category</font> </td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldcategory_id)+"&nbsp;</font>");
      out.println("</td>\n     </tr>");
      out.print("     <tr>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Image URL</font> </td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<a href=\"BookDetail.jsp?"+transitParams+"item_id="+toURL((String) rsHash.get("i_item_id"))+"&\"><font style=\"font-size: 10pt; color: #000000\">"+fldimage_url+"</font></a>");

      out.println("</td>\n     </tr>");
      out.println("     <tr>\n      <td colspan=\"2\" style=\"background-color: #FFFFFF; border-width: 1\">&nbsp;</td>\n     </tr>");
    
      iCounter++;
    }
    if (iCounter == 0) {
      // Recordset is empty
      out.println(sNoRecords);
    
      iCounter = RecordsPerPage+1;
      bIsScroll = false;
    }

    else {

  
    // Parse scroller
    boolean bInsert = false;
    boolean bNext = rs.next();
    if ( !bNext && iPage == 1 ) {
    
    }
    else {
      out.print("     <tr>\n      <td colspan=\"2\" style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">");
    
      if ( iPage == 1 ) {
        out.print("\n       <a href_=\"#\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Previous</font></a>");
      }
      else {
        out.print("\n       <a href=\""+sFileName+"?"+formParams+sSortParams+"FormResults_Page="+(iPage - 1)+"#Form\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Previous</font></a>");
      }
  
      out.print("\n       [ "+iPage+" ]");
  
      if (!bNext) { 
        out.print("\n       <a href_=\"#\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Next</font></a><br>");
      }
      else {
        out.print("\n       <a href=\""+sFileName+"?"+formParams+sSortParams+"FormResults_Page="+(iPage + 1)+"#Form\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Next</font></a><br>");
      }
    
      out.println("</td>\n     </tr>");
    }
  
    }

    if ( rs != null ) rs.close();
    out.println("    </table>");
    
  }
  catch (Exception e) { out.println(e.toString()); }
}


  void Search_Show (javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sSearchErr, String sForm, String sAction, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
    try {
      

      String fldcategory_id="";
      String fldname="";


      String sSQL="";
      String transitParams = "";
      String sQueryString = "";
      String sPage = "";
      

      out.println("    <table style=\"\">");
      
      out.println("     <form method=\"get\" action=\"Books.jsp\" name=\"Search\">\n     <tr>");
      // Set variables with search parameters
      
      fldcategory_id = getParam( request, "category_id");
      fldname = getParam( request, "name");

      // Show fields
      

      out.println("      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Category</font></td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); 
      out.print("<select name=\"category_id\">"+getOptions( conn, "select category_id, name from categories order by 2",true,false,fldcategory_id)+"</select>");
       out.println("</td>\n     </tr>\n     <tr>");
      
      out.println("      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Title</font></td>");
      out.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"name\" maxlength=\"10\" value=\""+toHTML(fldname)+"\" size=\"10\">");
 out.println("</td>\n     </tr>\n     <tr>");
      
      out.println("      <td align=\"right\" colspan=\"3\"><input type=\"submit\" value=\"Search\"/></td>");
      out.println("     </tr>\n     </form>\n    </table>");
      out.println("");
    }
    catch (Exception e) { out.println(e.toString()); }
  }


  void AdvMenu_Show (javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sAdvMenuErr, String sForm, String sAction, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
    try {
  
      out.println("    <table style=\"\">");
      
      out.print("     <tr>");
      // Set URLs
      
      String fldField1 = "AdvSearch.jsp";
      // Show fields
      
      out.print("\n      <td style=\"background-color: #FFFFFF; border-width: 1\"><a href=\""+fldField1+"\"><font style=\"font-size: 10pt; color: #000000\">Advanced Search</font></a></td>");

      out.println("\n     </tr>\n    </table>");
  
    }
    catch (Exception e) { out.println(e.toString()); }
  }


  void Total_Show (javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sTotalErr, String sForm, String sAction, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException  {
  
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

      String pauthor="";
      String pcategory_id="";
      String pname="";
      String ppricemax="";
      String ppricemin="";


    formParams = "category_id=" + toURL(getParam( request, "category_id")) + "&name=" + toURL(getParam( request, "name")) + "&author=" + toURL(getParam( request, "author")) + "&pricemin=" + toURL(getParam( request, "pricemin")) + "&pricemax=" + toURL(getParam( request, "pricemax")) + "&"; 
    // Build WHERE statement
        
    //-- Check author parameter and create a valid sql for where clause
  
    pauthor = getParam( request, "author");
    if (pauthor != null && ! pauthor.equals("")) {
            
        hasParam = true;
        sWhere += "i.author like '%" + replace(pauthor, "'", "''") + "%'";
    }
    
    //-- Check category_id parameter and create a valid sql for where clause
  
    pcategory_id = getParam( request, "category_id");
    if ( ! isNumber (pcategory_id)) {
      pcategory_id = "";
    }
    
    if (pcategory_id != null && ! pcategory_id.equals("")) {
            
      if (! sWhere.equals("")) sWhere += " and ";
        hasParam = true;
        sWhere += "i.category_id=" + pcategory_id;
    }
    
    //-- Check name parameter and create a valid sql for where clause
  
    pname = getParam( request, "name");
    if (pname != null && ! pname.equals("")) {
            
      if (! sWhere.equals("")) sWhere += " and ";
        hasParam = true;
        sWhere += "i.name like '%" + replace(pname, "'", "''") + "%'";
    }
    
    //-- Check pricemax parameter and create a valid sql for where clause
  
    ppricemax = getParam( request, "pricemax");
    if ( ! isNumber (ppricemax)) {
      ppricemax = "";
    }
    
    if (ppricemax != null && ! ppricemax.equals("")) {
            
      if (! sWhere.equals("")) sWhere += " and ";
        hasParam = true;
        sWhere += "i.price<=" + ppricemax;
    }
    
    //-- Check pricemin parameter and create a valid sql for where clause
  
    ppricemin = getParam( request, "pricemin");
    if ( ! isNumber (ppricemin)) {
      ppricemin = "";
    }
    
    if (ppricemin != null && ! ppricemin.equals("")) {
            
      if (! sWhere.equals("")) sWhere += " and ";
        hasParam = true;
        sWhere += "i.price>=" + ppricemin;
    }
    
    if (hasParam) { sWhere = " WHERE (" + sWhere + ")"; }

  // Build full SQL statement
  
  sSQL = "select i.author as i_author, " +
    "i.category_id as i_category_id, " +
    "i.item_id as i_item_id, " +
    "i.name as i_name, " +
    "i.price as i_price " +
    " from items i ";
  
sSQL="select count(item_id) as i_item_id from items as i";
  sSQL = sSQL + sWhere + sOrder;

  String sNoRecords = "     <tr>\n      <td colspan=\"2\" style=\"background-color: #FFFFFF; border-width: 1\"><font style=\"font-size: 10pt; color: #000000\">No records</font></td>\n     </tr>";


  String tableHeader = "";
    
  
  try {
    out.println("    <table style=\"\">");
    
    out.println(tableHeader);

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
      String flditem_id = (String) rsHash.get("i_item_id");

      out.print("     <tr>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Items found:</font> </td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(flditem_id)+"&nbsp;</font>");
      out.println("</td>\n     </tr>");
      out.println("     <tr>\n      <td colspan=\"2\" style=\"background-color: #FFFFFF; border-width: 1\">&nbsp;</td>\n     </tr>");
    
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
		<% Search_Show(request, response, session, out, sSearchErr, sForm, sAction, conn, stat); %>

		</td>
		<td valign="top">
		<% AdvMenu_Show(request, response, session, out, sAdvMenuErr, sForm, sAction, conn, stat); %>

		</td>
	</tr>
</table>
<table>
	<tr>
		<td valign="top">
		<% Total_Show(request, response, session, out, sTotalErr, sForm, sAction, conn, stat); %>

		</td>
	</tr>
</table>
<table>
	<tr>
		<td valign="top">
		<% Results_Show(request, response, session, out, sResultsErr, sForm, sAction, conn, stat); %>

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
