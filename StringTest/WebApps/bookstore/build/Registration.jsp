<%@ include file="Common.jsp"%>
<%!
//
//   Filename: Registration.jsp
//   Generated with CodeCharge  v.1.2.0
//   JSP.ccp build 05/21/2001
//

static final String sFileName = "Registration.jsp";
              
%>
<%

boolean bDebug = false;

String sAction = getParam( request, "FormAction");
String sForm = getParam( request, "FormName");
String sRegErr = "";

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
if ( sForm.equals("Reg") ) {
  sRegErr = RegAction(request, response, session, out, sAction, sForm, conn, stat);
  if ( "sendRedirect".equals(sRegErr)) return;
}

%>
<%!


  String RegAction(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sAction, String sForm, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
  
    String sRegErr ="";
    try {

      if (sAction.equals("")) return "";

      String sSQL="";
      String transitParams = "";
      String primaryKeyParams = "";
      String sQueryString = "";
      String sPage = "";
      String sParams = "";
      String sActionFileName = "Default.jsp";
      String sWhere = " ";
      boolean bErr = false;
      long iCount = 0;

  
      String pPKmember_id = "";
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


      String fldmember_login="";
      String fldmember_password="";
      String fldfirst_name="";
      String fldlast_name="";
      String fldemail="";
      String fldaddress="";
      String fldphone="";
      String fldcard_type_id="";
      String fldcard_number="";
      String fldmember_password2="";
      String fldmember_id="";

      // Load all form fields into variables
    
      fldmember_login = getParam(request, "member_login");
      fldmember_password = getParam(request, "member_password");
      fldmember_password2 = getParam(request, "member_password2");
      fldfirst_name = getParam(request, "first_name");
      fldlast_name = getParam(request, "last_name");
      fldemail = getParam(request, "email");
      fldaddress = getParam(request, "address");
      fldphone = getParam(request, "phone");
      fldcard_type_id = getParam(request, "card_type_id");
      fldcard_number = getParam(request, "card_number");
      // Validate fields
      if ( iAction == iinsertAction || iAction == iupdateAction ) {
        if ( isEmpty(fldmember_login) ) {
          sRegErr = sRegErr + "The value in field Login* is required.<br>";
        }
        if ( isEmpty(fldmember_password) ) {
          sRegErr = sRegErr + "The value in field Password* is required.<br>";
        }
        if ( isEmpty(fldmember_password2) ) {
          sRegErr = sRegErr + "The value in field Confirm Password* is required.<br>";
        }
        if ( isEmpty(fldfirst_name) ) {
          sRegErr = sRegErr + "The value in field First Name* is required.<br>";
        }
        if ( isEmpty(fldlast_name) ) {
          sRegErr = sRegErr + "The value in field Last Name* is required.<br>";
        }
        if ( isEmpty(fldemail) ) {
          sRegErr = sRegErr + "The value in field Email* is required.<br>";
        }
        if ( ! isNumber(fldcard_type_id)) {
          sRegErr = sRegErr + "The value in field Credit Card Type is incorrect.<br>";
        }
        if ( ! isEmpty(fldmember_login)) {
          iCount = 0;
          if ( iAction == iinsertAction ) {
            iCount = dCountRec(stat, "members", "member_login=" + toSQL(fldmember_login, adText));
          }
          else {
            if ( iAction == iupdateAction ) {
              iCount = dCountRec( stat, "members", "member_login=" + toSQL(fldmember_login, adText) + " and not(" + sWhere + ")");
            }
          }
          if (iCount > 0) {
            sRegErr = sRegErr + "The value in field Login* is already in database.<br>";
          }
        }
if ( ! getParam( request, "member_password" ).equals(getParam( request,"member_password2"))) {
  sRegErr += "\nPassword and Confirm Password fields don't match";
}
        if (sRegErr.length() > 0 ) {
          return (sRegErr);
        }
      }


      sSQL = "";
      // Create SQL statement

      switch (iAction) {
  
        case iinsertAction :
          
            sSQL = "insert into members (" + 
                "member_login," +
                "member_password," +
                "first_name," +
                "last_name," +
                "email," +
                "address," +
                "phone," +
                "card_type_id," +
                "card_number)" +

                " values (" + 
                toSQL(fldmember_login, adText) + "," +
                toSQL(fldmember_password, adText) + "," +
                toSQL(fldfirst_name, adText) + "," +
                toSQL(fldlast_name, adText) + "," +
                toSQL(fldemail, adText) + "," +
                toSQL(fldaddress, adText) + "," +
                toSQL(fldphone, adText) + "," +
                toSQL(fldcard_type_id, adNumber) + "," +
                toSQL(fldcard_number, adText) + ")";
          break;
  
      }

      if ( sRegErr.length() > 0 ) return sRegErr;
      try {
        // Execute SQL statement
        stat.executeUpdate(sSQL);
      }
      catch(java.sql.SQLException e) {
        sRegErr = e.toString(); return (sRegErr);
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
    return (sRegErr);
  }

  


  void Reg_Show(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sRegErr, String sForm, String sAction, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
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
      String fldmember_password="";
      String fldfirst_name="";
      String fldlast_name="";
      String fldemail="";
      String fldaddress="";
      String fldphone="";
      String fldcard_type_id="";
      String fldcard_number="";
      String fldmember_password2="";


      boolean bPK = true;

      if ( "".equals(sRegErr)) {
        // Load primary key and form parameters
        pmember_id = getParam( request, "member_id");
      }
      else {
        // Load primary key, form parameters and form fields
        fldmember_id = getParam( request, "member_id");
        fldmember_login = getParam( request, "member_login");
        fldmember_password = getParam( request, "member_password");
        fldfirst_name = getParam( request, "first_name");
        fldlast_name = getParam( request, "last_name");
        fldemail = getParam( request, "email");
        fldaddress = getParam( request, "address");
        fldphone = getParam( request, "phone");
        fldcard_type_id = getParam( request, "card_type_id");
        fldcard_number = getParam( request, "card_number");
        pmember_id = getParam( request, "PK_member_id");
      }

      
      fldmember_password2 = getParam(request,"member_password2");
      if ( isEmpty(pmember_id)) { bPK = false; }
      
      sWhere += "member_id=" + toSQL(pmember_id, adNumber);
      primaryKeyParams += "<input type=\"hidden\" name=\"PK_member_id\" value=\""+pmember_id+"\"/>";

      sSQL = "select * from members where " + sWhere;


      out.println("    <table style=\"\">");
      out.println("     <tr>\n      <td style=\"background-color: #336699; text-align: Center; border-style: outset; border-width: 1\" colspan=\"2\"><font style=\"font-size: 12pt; color: #FFFFFF; font-weight: bold\">Registration</font></td>\n     </tr>");
      if ( ! sRegErr.equals("")) {
        out.println("     <tr>\n      <td style=\"background-color: #FFFFFF; border-width: 1\" colspan=\"2\"><font style=\"font-size: 10pt; color: #000000\">"+sRegErr+"</font></td>\n     </tr>");
      }
      sRegErr="";
      out.println("     <form method=\"get\" action=\""+sFileName+"\" name=\"Reg\">");

      java.sql.ResultSet rs = null;

      if ( bPK &&  ! (sAction.equals("insert") && "Reg".equals(sForm))) {

        // Open recordset
        rs = openrs( stat, sSQL);
        rs.next();
        String[] aFields = getFieldsName( rs );
        getRecordToHash( rs, rsHash, aFields );
        rs.close();
        fldmember_id = (String) rsHash.get("member_id");
        if ( "".equals(sRegErr)) {
          // Load data from recordset when form displayed first time
          fldmember_login = (String) rsHash.get("member_login");
          fldmember_password = (String) rsHash.get("member_password");
          fldfirst_name = (String) rsHash.get("first_name");
          fldlast_name = (String) rsHash.get("last_name");
          fldemail = (String) rsHash.get("email");
          fldaddress = (String) rsHash.get("address");
          fldphone = (String) rsHash.get("phone");
          fldcard_type_id = (String) rsHash.get("card_type_id");
          fldcard_number = (String) rsHash.get("card_number");
        }

        if (sAction.equals("") || ! "Reg".equals(sForm)) {
      
          fldmember_id = (String) rsHash.get("member_id");
          fldmember_login = (String) rsHash.get("member_login");
          fldmember_password = (String) rsHash.get("member_password");
          fldfirst_name = (String) rsHash.get("first_name");
          fldlast_name = (String) rsHash.get("last_name");
          fldemail = (String) rsHash.get("email");
          fldaddress = (String) rsHash.get("address");
          fldphone = (String) rsHash.get("phone");
          fldcard_type_id = (String) rsHash.get("card_type_id");
          fldcard_number = (String) rsHash.get("card_number");
        }
        
      }
      else {
      }
      


      // Show form field
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Login*</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"member_login\" maxlength=\"20\" value=\""+toHTML(fldmember_login)+"\" size=\"20\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Password*</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"password\"  name=\"member_password\" maxlength=\"20\" value=\""+toHTML(fldmember_password)+"\" size=\"20\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Confirm Password*</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"password\"  name=\"member_password2\" maxlength=\"20\" value=\""+toHTML(fldmember_password2)+"\" size=\"20\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">First Name*</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"first_name\" maxlength=\"50\" value=\""+toHTML(fldfirst_name)+"\" size=\"50\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Last Name*</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"last_name\" maxlength=\"50\" value=\""+toHTML(fldlast_name)+"\" size=\"50\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Email*</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"email\" maxlength=\"50\" value=\""+toHTML(fldemail)+"\" size=\"50\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Address</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"address\" maxlength=\"50\" value=\""+toHTML(fldaddress)+"\" size=\"50\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Phone</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"phone\" maxlength=\"50\" value=\""+toHTML(fldphone)+"\" size=\"50\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Credit Card Type</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); 
      out.print("<select name=\"card_type_id\">"+getOptions( conn, "select card_type_id, name from card_types order by 2",false,false,fldcard_type_id)+"</select>");
      
      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Credit Card Number</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"card_number\" maxlength=\"20\" value=\""+toHTML(fldcard_number)+"\" size=\"20\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td colspan=\"2\" align=\"right\">");
      

      if ( bPK && ! (sAction.equals("insert") && "Reg".equals(sForm))) {
        out.print("<input type=\"submit\" value=\"Cancel\" onclick=\"document.Reg.FormAction.value = 'cancel';\">");
        out.print("<input type=\"hidden\" name=\"FormName\" value=\"Reg\"><input type=\"hidden\" value=\"\" name=\"FormAction\">");
      }
      
      else {
        out.print("<input type=\"submit\" value=\"Register\" onclick=\"document.Reg.FormAction.value = 'insert';\">");out.print("<input type=\"submit\" value=\"Cancel\" onclick=\"document.Reg.FormAction.value = 'cancel';\">");
        out.print("<input type=\"hidden\" name=\"FormName\" value=\"Reg\"><input type=\"hidden\" value=\"insert\" name=\"FormAction\">");
      }out.print("<input type=\"hidden\" name=\"member_id\" value=\""+toHTML(fldmember_id)+"\">");
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
<center>
<table>
	<tr>

		<td valign="top">
		<% Reg_Show(request, response, session, out, sRegErr, sForm, sAction, conn, stat); %>

		</td>
	</tr>
</table>

<jsp:include page="Footer.jsp" flush="true" />
<center><font face="Arial"><small>This dynamic
site was generated with <a href="http://www.codecharge.com">CodeCharge</a></small></font></center>
</center>
</body>
</html>
<%%>
<%
if ( stat != null ) stat.close();
if ( conn != null ) conn.close();
%>
