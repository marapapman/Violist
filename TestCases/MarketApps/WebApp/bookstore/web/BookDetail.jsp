<%@ include file="Common.jsp"%>
<%!
//
//   Filename: BookDetail.jsp
//   Generated with CodeCharge  v.1.2.0
//   JSP.ccp build 05/21/2001
//

static final String sFileName = "BookDetail.jsp";
              
%>
<%

String cSec = checkSecurity(1, session, response, request);
if ("sendRedirect".equals(cSec) ) return;
                
boolean bDebug = false;

String sAction = getParam( request, "FormAction");
String sForm = getParam( request, "FormName");
String sDetailErr = "";
String sOrderErr = "";
String sRatingErr = "";

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
if ( sForm.equals("Detail") ) {
  sDetailErr = DetailAction(request, response, session, out, sAction, sForm, conn, stat);
  if ( "sendRedirect".equals(sDetailErr)) return;
}
if ( sForm.equals("Order") ) {
  sOrderErr = OrderAction(request, response, session, out, sAction, sForm, conn, stat);
  if ( "sendRedirect".equals(sOrderErr)) return;
}
if ( sForm.equals("Rating") ) {
  sRatingErr = RatingAction(request, response, session, out, sAction, sForm, conn, stat);
  if ( "sendRedirect".equals(sRatingErr)) return;
}

%>
<%!


  String DetailAction(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sAction, String sForm, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
  
    String sDetailErr ="";
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

  
      sParams = "?";
      sParams += "item_id=" + toURL(getParam( request, "Trn_item_id"));
      String pPKitem_id = "";

      final int iinsertAction = 1;
      final int iupdateAction = 2;
      final int ideleteAction = 3;
      int iAction = 0;

      if ( sAction.equalsIgnoreCase("insert") ) { iAction = iinsertAction; }
      if ( sAction.equalsIgnoreCase("update") ) { iAction = iupdateAction; }
      if ( sAction.equalsIgnoreCase("delete") ) { iAction = ideleteAction; }

      // Create WHERE statement


      String flditem_id="";
      String fldcategory_id="";
      String fldprice="";

      // Load all form fields into variables
    

      sSQL = "";
      // Create SQL statement

      if ( sDetailErr.length() > 0 ) return sDetailErr;
      try {
        // Execute SQL statement
        stat.executeUpdate(sSQL);
      }
      catch(java.sql.SQLException e) {
        sDetailErr = e.toString(); return (sDetailErr);
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
    return (sDetailErr);
  }

  


  void Detail_Show(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sDetailErr, String sForm, String sAction, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
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
      
      String pitem_id = "";

      String fldcategory_id="";
      String flditem_id="";
      String fldname="";
      String fldauthor="";
      String fldprice="";
      String fldimage_url="";
      String fldnotes="";
      String fldproduct_url="";


      boolean bPK = true;

      if ( "".equals(sDetailErr)) {
        // Load primary key and form parameters
        flditem_id = getParam( request, "item_id");
        transitParamsHidden += "<input type=\"hidden\" name=\"Trn_item_id\" value=\""+getParam( request, "item_id")+"\">";
        transitParams += "Trn_item_id="+getParam( request, "item_id")+"&";
        pitem_id = getParam( request, "item_id");
      }
      else {
        // Load primary key, form parameters and form fields
        flditem_id = getParam( request, "item_id");
        transitParamsHidden += "<input type=\"hidden\" name=\"Trn_item_id\" value=\""+getParam( request, "Trn_item_id")+"\">";
        transitParams += "Trn_item_id="+getParam( request, "Trn_item_id")+"&";
        
        pitem_id = getParam( request, "PK_item_id");
      }

      
      if ( isEmpty(pitem_id)) { bPK = false; }
      
     // sWhere += "item_id=" + toSQL(pitem_id, adNumber);
	sWhere += "item_id=" + pitem_id;
      primaryKeyParams += "<input type=\"hidden\" name=\"PK_item_id\" value=\""+pitem_id+"\"/>";

      sSQL = "select * from items where " + sWhere;


      out.println("    <table style=\"\">");
      out.println("     <tr>\n      <td style=\"background-color: #336699; text-align: Center; border-style: outset; border-width: 1\" colspan=\"2\"><font style=\"font-size: 12pt; color: #FFFFFF; font-weight: bold\">Book Detail</font></td>\n     </tr>");
      if ( ! sDetailErr.equals("")) {
        out.println("     <tr>\n      <td style=\"background-color: #FFFFFF; border-width: 1\" colspan=\"2\"><font style=\"font-size: 10pt; color: #000000\">"+sDetailErr+"</font></td>\n     </tr>");
      }
      sDetailErr="";
      out.println("     <form method=\"get\" action=\""+sFileName+"\" name=\"Detail\">");

      java.sql.ResultSet rs = null;

      if ( bPK &&  ! (sAction.equals("insert") && "Detail".equals(sForm))) {

        // Open recordset
        rs = openrs( stat, sSQL);
        rs.next();
        String[] aFields = getFieldsName( rs );
        getRecordToHash( rs, rsHash, aFields );
        rs.close();
        flditem_id = (String) rsHash.get("item_id");
        fldname = (String) rsHash.get("name");
        fldauthor = (String) rsHash.get("author");
        fldcategory_id = (String) rsHash.get("category_id");
        fldprice = (String) rsHash.get("price");
        fldimage_url = (String) rsHash.get("image_url");
        fldnotes = (String) rsHash.get("notes");
        fldproduct_url = (String) rsHash.get("product_url");

        if (sAction.equals("") || ! "Detail".equals(sForm)) {
      
          flditem_id = (String) rsHash.get("item_id");
          fldname = (String) rsHash.get("name");
          fldauthor = (String) rsHash.get("author");
          fldcategory_id = (String) rsHash.get("category_id");
          fldprice = (String) rsHash.get("price");
          fldimage_url = (String) rsHash.get("image_url");
          fldnotes = (String) rsHash.get("notes");
          fldproduct_url = (String) rsHash.get("product_url");
        }
        else {
          flditem_id = (String) rsHash.get("item_id");
          fldname = (String) rsHash.get("name");
          fldauthor = (String) rsHash.get("author");
          fldcategory_id = (String) rsHash.get("category_id");
          fldprice = (String) rsHash.get("price");
          fldimage_url = (String) rsHash.get("image_url");
          fldnotes = (String) rsHash.get("notes");
          fldproduct_url = (String) rsHash.get("product_url");
        }
        
      }
      else {
        if ( "".equals(sDetailErr)) {
          flditem_id = toHTML(getParam(request,"item_id"));
        }
      }
      
      // Set lookup fields
          fldcategory_id = dLookUp( stat, "categories", "name", "category_id=" + toSQL(fldcategory_id, adNumber));
      if ( "".equals(sDetailErr)) {
      
fldimage_url="<img border=0 src=" + fldimage_url + ">";
fldproduct_url="Review this book on Amazon.com";
      }


      // Show form field
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Title</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldname)+"&nbsp;</font>");
      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Author</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldauthor)+"&nbsp;</font>");
      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Category</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldcategory_id)+"&nbsp;</font>");
      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Price</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldprice)+"&nbsp;</font>");
      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Picture</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<a href=\""+(String) rsHash.get("product_url")+"\"><font style=\"font-size: 10pt; color: #000000\">"+fldimage_url+"</font></a>");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Notes</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+fldnotes+"&nbsp;</font>");
      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\"></font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<a href=\""+(String) rsHash.get("product_url")+"\"><font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldproduct_url)+"</font></a>");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td colspan=\"2\" align=\"right\">");
      

      if ( bPK && ! (sAction.equals("insert") && "Detail".equals(sForm))) {
        
        out.print("<input type=\"hidden\" name=\"FormName\" value=\"Detail\"><input type=\"hidden\" value=\"\" name=\"FormAction\">");
      }
      out.print("<input type=\"hidden\" name=\"item_id\" value=\""+toHTML(flditem_id)+"\">");
      out.print(transitParamsHidden+requiredParams+primaryKeyParams);
      out.println("</td>\n     </tr>\n     </form>\n    </table>");
      



    }
    catch (Exception e) { out.println(e.toString()); }
  } 


  String OrderAction(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sAction, String sForm, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
  
    String sOrderErr ="";
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

      final int iinsertAction = 1;
      final int iupdateAction = 2;
      final int ideleteAction = 3;
      int iAction = 0;

      if ( sAction.equalsIgnoreCase("insert") ) { iAction = iinsertAction; }
      if ( sAction.equalsIgnoreCase("update") ) { iAction = iupdateAction; }
      if ( sAction.equalsIgnoreCase("delete") ) { iAction = ideleteAction; }

      // Create WHERE statement


      String fldUserID="";
      String fldquantity="";
      String flditem_id="";
      String fldorder_id="";

      // Load all form fields into variables
    
      fldUserID = (String) session.getAttribute("UserID");
      fldquantity = getParam(request, "quantity");
      flditem_id = getParam(request, "item_id");
      // Validate fields
      if ( iAction == iinsertAction || iAction == iupdateAction ) {
        if ( isEmpty(fldquantity) ) {
          sOrderErr = sOrderErr + "The value in field Quantity is required.<br>";
        }
        //if ( ! isNumber(fldquantity)) {
        //  sOrderErr = sOrderErr + "The value in field Quantity is incorrect.<br>";
        //}
        if ( ! isNumber(flditem_id)) {
          sOrderErr = sOrderErr + "The value in field item_id is incorrect.<br>";
        }
        if (sOrderErr.length() > 0 ) {
          return (sOrderErr);
        }
      }


      sSQL = "";
      // Create SQL statement

      switch (iAction) {
  
        case iinsertAction :
          
            sSQL = "insert into orders (" + 
                "member_id," + 
                "quantity," +
                "item_id)" +

                " values (" + 
                toSQL(fldUserID, adNumber) + "," +
                toSQL(fldquantity, adNumber) + "," +
                toSQL(flditem_id, adNumber) + ")";
          break;
  
      }

      if ( sOrderErr.length() > 0 ) return sOrderErr;
      try {
        // Execute SQL statement
        stat.executeUpdate(sSQL);
      }
      catch(java.sql.SQLException e) {
        sOrderErr = e.toString(); return (sOrderErr);
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
    return (sOrderErr);
  }

  


  void Order_Show(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sOrderErr, String sForm, String sAction, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
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
      String fldquantity="";


      boolean bPK = true;

      if ( "".equals(sOrderErr)) {
        // Load primary key and form parameters
        flditem_id = getParam( request, "item_id");
        porder_id = getParam( request, "order_id");
      }
      else {
        // Load primary key, form parameters and form fields
        fldorder_id = getParam( request, "order_id");
        fldquantity = getParam( request, "quantity");
        flditem_id = getParam( request, "item_id");
        porder_id = getParam( request, "PK_order_id");
      }

      
      if ( isEmpty(porder_id)) { bPK = false; }
      
      sWhere += "order_id=" + toSQL(porder_id, adNumber);
      primaryKeyParams += "<input type=\"hidden\" name=\"PK_order_id\" value=\""+porder_id+"\"/>";

      sSQL = "select * from orders where " + sWhere;


      out.println("    <table style=\"\">");
      
      if ( ! sOrderErr.equals("")) {
        out.println("     <tr>\n      <td style=\"background-color: #FFFFFF; border-width: 1\" colspan=\"2\"><font style=\"font-size: 10pt; color: #000000\">"+sOrderErr+"</font></td>\n     </tr>");
      }
      sOrderErr="";
      out.println("     <form method=\"get\" action=\""+sFileName+"\" name=\"Order\">");

      java.sql.ResultSet rs = null;

      if ( bPK &&  ! (sAction.equals("insert") && "Order".equals(sForm))) {

        // Open recordset
        rs = openrs( stat, sSQL);
        rs.next();
        String[] aFields = getFieldsName( rs );
        getRecordToHash( rs, rsHash, aFields );
        rs.close();
        fldorder_id = (String) rsHash.get("order_id");
        flditem_id = (String) rsHash.get("item_id");
        if ( "".equals(sOrderErr)) {
          // Load data from recordset when form displayed first time
          fldquantity = (String) rsHash.get("quantity");
        }

        if (sAction.equals("") || ! "Order".equals(sForm)) {
      
          fldorder_id = (String) rsHash.get("order_id");
          fldquantity = (String) rsHash.get("quantity");
          flditem_id = (String) rsHash.get("item_id");
        }
        
      }
      else {
        if ( "".equals(sOrderErr)) {
          flditem_id = toHTML(getParam(request,"item_id"));
          fldquantity= "1";
        }
      }
      


      // Show form field
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Quantity</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"quantity\" maxlength=\"100\" value=\""+toHTML(fldquantity)+"\" size=\"10\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td colspan=\"2\" align=\"right\">");
      

      if ( bPK && ! (sAction.equals("insert") && "Order".equals(sForm))) {
        
        out.print("<input type=\"hidden\" name=\"FormName\" value=\"Order\"><input type=\"hidden\" value=\"\" name=\"FormAction\">");
      }
      
      else {
        out.print("<input type=\"submit\" value=\"Add to Shopping Cart\" onclick=\"document.Order.FormAction.value = 'insert';\">");
        out.print("<input type=\"hidden\" name=\"FormName\" value=\"Order\"><input type=\"hidden\" value=\"insert\" name=\"FormAction\">");
      }out.print("<input type=\"hidden\" name=\"order_id\" value=\""+toHTML(fldorder_id)+"\">");out.print("<input type=\"hidden\" name=\"item_id\" value=\""+toHTML(flditem_id)+"\">");
      out.print(transitParamsHidden+requiredParams+primaryKeyParams);
      out.println("</td>\n     </tr>\n     </form>\n    </table>");
      



    }
    catch (Exception e) { out.println(e.toString()); }
  } 


  String RatingAction(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sAction, String sForm, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
  
    String sRatingErr ="";
    try {

      if (sAction.equals("")) return "";

      String sSQL="";
      String transitParams = "";
      String primaryKeyParams = "";
      String sQueryString = "";
      String sPage = "";
      String sParams = "";
      String sActionFileName = "BookDetail.jsp";
      String sWhere = " ";
      boolean bErr = false;
      long iCount = 0;

  
      sParams = "?";
      sParams += "item_id=" + toURL(getParam( request, "Trn_item_id"));
      String pPKitem_id = "";

      final int iinsertAction = 1;
      final int iupdateAction = 2;
      final int ideleteAction = 3;
      int iAction = 0;

      if ( sAction.equalsIgnoreCase("insert") ) { iAction = iinsertAction; }
      if ( sAction.equalsIgnoreCase("update") ) { iAction = iupdateAction; }
      if ( sAction.equalsIgnoreCase("delete") ) { iAction = ideleteAction; }

      // Create WHERE statement

      if ( iAction == iupdateAction || iAction == ideleteAction ) { 
        pPKitem_id = getParam( request, "PK_item_id");
        if ( isEmpty(pPKitem_id)) return sRatingErr;
        sWhere = "item_id=" + toSQL(pPKitem_id, adNumber);
      }


      String fldrating="";
      String fldrating_count="";
      String flditem_id="";
      String fldrating_view="";
      String fldrating_count_view="";

      // Load all form fields into variables
    
      fldrating = getParam(request, "rating");
      fldrating_count = getParam(request, "rating_count");
      // Validate fields
      if ( iAction == iinsertAction || iAction == iupdateAction ) {
        if ( isEmpty(fldrating) ) {
          sRatingErr = sRatingErr + "The value in field Your Rating is required.<br>";
        }
        if ( ! isNumber(fldrating)) {
          sRatingErr = sRatingErr + "The value in field Your Rating is incorrect.<br>";
        }
        if ( ! isNumber(fldrating_count)) {
          sRatingErr = sRatingErr + "The value in field rating_count is incorrect.<br>";
        }
        if (sRatingErr.length() > 0 ) {
          return (sRatingErr);
        }
      }


      sSQL = "";
      // Create SQL statement

      switch (iAction) {
  
      case iupdateAction:
        
sSQL = "update items set rating=rating+" + getParam(request, "rating") + ", rating_count=rating_count+1 where item_id=" + getParam(request, "item_id");
        if ("".equals(sSQL)) {
          sSQL = "update items set " +
                "rating=" + toSQL(fldrating, adNumber) +
                ",rating_count=" + toSQL(fldrating_count, adNumber);
          sSQL = sSQL + " where " + sWhere;
        }
        break;
      
      }

      if ( sRatingErr.length() > 0 ) return sRatingErr;
      try {
        // Execute SQL statement
        stat.executeUpdate(sSQL);
      }
      catch(java.sql.SQLException e) {
        sRatingErr = e.toString(); return (sRatingErr);
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
    return (sRatingErr);
  }

  


  void Rating_Show(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sRatingErr, String sForm, String sAction, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
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
      
      String pitem_id = "";

      String flditem_id="";
      String fldrating="";
      String fldrating_count="";
      String fldrating_view="";
      String fldrating_count_view="";


      boolean bPK = true;

      if ( "".equals(sRatingErr)) {
        // Load primary key and form parameters
        flditem_id = getParam( request, "item_id");
        transitParamsHidden += "<input type=\"hidden\" name=\"Trn_item_id\" value=\""+getParam( request, "item_id")+"\">";
        transitParams += "Trn_item_id="+getParam( request, "item_id")+"&";
        pitem_id = getParam( request, "item_id");
      }
      else {
        // Load primary key, form parameters and form fields
        flditem_id = getParam( request, "item_id");
        fldrating = getParam( request, "rating");
        fldrating_count = getParam( request, "rating_count");
        transitParamsHidden += "<input type=\"hidden\" name=\"Trn_item_id\" value=\""+getParam( request, "Trn_item_id")+"\">";
        transitParams += "Trn_item_id="+getParam( request, "Trn_item_id")+"&";
        
        pitem_id = getParam( request, "PK_item_id");
      }

      
      if ( isEmpty(pitem_id)) { bPK = false; }
      
      sWhere += "item_id=" + toSQL(pitem_id, adNumber);
      primaryKeyParams += "<input type=\"hidden\" name=\"PK_item_id\" value=\""+pitem_id+"\"/>";

      sSQL = "select * from items where " + sWhere;


      out.println("    <table style=\"\">");
      out.println("     <tr>\n      <td style=\"background-color: #336699; text-align: Center; border-style: outset; border-width: 1\" colspan=\"2\"><font style=\"font-size: 12pt; color: #FFFFFF; font-weight: bold\">Rating</font></td>\n     </tr>");
      if ( ! sRatingErr.equals("")) {
        out.println("     <tr>\n      <td style=\"background-color: #FFFFFF; border-width: 1\" colspan=\"2\"><font style=\"font-size: 10pt; color: #000000\">"+sRatingErr+"</font></td>\n     </tr>");
      }
      sRatingErr="";
      out.println("     <form method=\"get\" action=\""+sFileName+"\" name=\"Rating\">");

      java.sql.ResultSet rs = null;

      if ( bPK &&  ! (sAction.equals("insert") && "Rating".equals(sForm))) {

        // Open recordset
        rs = openrs( stat, sSQL);
        rs.next();
        String[] aFields = getFieldsName( rs );
        getRecordToHash( rs, rsHash, aFields );
        rs.close();
        flditem_id = (String) rsHash.get("item_id");
        fldrating_view = (String) rsHash.get("rating");
        fldrating_count_view = (String) rsHash.get("rating_count");
        fldrating_count = (String) rsHash.get("rating_count");
        if ( "".equals(sRatingErr)) {
          // Load data from recordset when form displayed first time
          fldrating = (String) rsHash.get("rating");
        }

        if (sAction.equals("") || ! "Rating".equals(sForm)) {
      
          flditem_id = (String) rsHash.get("item_id");
          fldrating_view = (String) rsHash.get("rating");
          fldrating_count_view = (String) rsHash.get("rating_count");
          fldrating = (String) rsHash.get("rating");
          fldrating_count = (String) rsHash.get("rating_count");
        }
        else {
          flditem_id = (String) rsHash.get("item_id");
          fldrating_view = (String) rsHash.get("rating");
          fldrating_count_view = (String) rsHash.get("rating_count");
          fldrating_count = (String) rsHash.get("rating_count");
        }
        
      }
      else {
        if ( "".equals(sRatingErr)) {
          flditem_id = toHTML(getParam(request,"item_id"));
        }
      }
      
      if ( "".equals(sRatingErr)) {
      
if (fldrating_view.equals("0"))
      {
        fldrating_view = "Not rated yet";
        fldrating_count_view = "";
      }
      else
      {
        fldrating_view = "<img src=\"images/" + java.lang.Math.round(java.lang.Integer.parseInt(fldrating)/java.lang.Integer.parseInt(fldrating_count)) + "stars.gif\">";
      }
      }


      // Show form field
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Current Rating</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+fldrating_view+"&nbsp;</font>");
      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Total Votes</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<font style=\"font-size: 10pt; color: #000000\">"+toHTML(fldrating_count_view)+"&nbsp;</font>");
      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Your Rating</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); 
      out.print("<select name=\"rating\">"+getOptionsLOV("1;Deficient;2;Regular;3;Good;4;Very Good;5;Excellent",false,true,fldrating)+"</select>");
      
      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td colspan=\"2\" align=\"right\">");
      

      if ( bPK && ! (sAction.equals("insert") && "Rating".equals(sForm))) {
        out.print("<input type=\"submit\" value=\"Vote\" onclick=\"document.Rating.FormAction.value = 'update';\">");
        out.print("<input type=\"hidden\" name=\"FormName\" value=\"Rating\"><input type=\"hidden\" value=\"update\" name=\"FormAction\">");
      }
      
      else {
        
        out.print("<input type=\"hidden\" name=\"FormName\" value=\"Rating\"><input type=\"hidden\" value=\"insert\" name=\"FormAction\">");
      }out.print("<input type=\"hidden\" name=\"item_id\" value=\""+toHTML(flditem_id)+"\">");out.print("<input type=\"hidden\" name=\"rating_count\" value=\""+toHTML(fldrating_count)+"\">");
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
		<% Detail_Show(request, response, session, out, sDetailErr, sForm, sAction, conn, stat); %>

		</td>
	</tr>
</table>
<table>
	<tr>
		<td valign="top">
		<% Order_Show(request, response, session, out, sOrderErr, sForm, sAction, conn, stat); %>

		</td>
	</tr>
</table>
<table>
	<tr>
		<td valign="top">
		<% Rating_Show(request, response, session, out, sRatingErr, sForm, sAction, conn, stat); %>

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
