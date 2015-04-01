<%@ include file="Common.jsp"%>
<%!
//
//   Filename: MembersRecord.jsp
//   Generated with CodeCharge  v.1.2.0
//   JSP.ccp build 05/21/2001
//

static final String sFileName = "MembersRecord.jsp";
              
%>
<%

String cSec = checkSecurity(2, session, response, request);
if ("sendRedirect".equals(cSec) ) return;
                
boolean bDebug = false;

String sAction = getParam( request, "FormAction");
String sForm = getParam( request, "FormName");
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
if ( sForm.equals("Members") ) {
  sMembersErr = MembersAction(request, response, session, out, sAction, sForm, conn, stat);
  if ( "sendRedirect".equals(sMembersErr)) return;
}

%>
<%!


  String MembersAction(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sAction, String sForm, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
  
    String sMembersErr ="";
    try {

      if (sAction.equals("")) return "";

      String sSQL="";
      String transitParams = "";
      String primaryKeyParams = "";
      String sQueryString = "";
      String sPage = "";
      String sParams = "";
      String sActionFileName = "MembersGrid.jsp";
      String sWhere = " ";
      boolean bErr = false;
      long iCount = 0;

  
      sParams = "?";
      sParams += "member_login=" + toURL(getParam( request, "Trn_member_login"));
      String pPKmember_id = "";
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
        pPKmember_id = getParam( request, "PK_member_id");
        if ( isEmpty(pPKmember_id)) return sMembersErr;
        sWhere = "member_id=" + toSQL(pPKmember_id, adNumber);
      }


      String fldmember_login="";
      String fldmember_password="";
      String fldmember_level="";
      String fldname="";
      String fldlast_name="";
      String fldemail="";
      String fldphone="";
      String fldaddress="";
      String fldnotes="";
      String fldcard_type_id="";
      String fldcard_number="";
      String fldmember_id="";

      // Load all form fields into variables
    
      fldmember_login = getParam(request, "member_login");
      fldmember_password = getParam(request, "member_password");
      fldmember_level = getParam(request, "member_level");
      fldname = getParam(request, "name");
      fldlast_name = getParam(request, "last_name");
      fldemail = getParam(request, "email");
      fldphone = getParam(request, "phone");
      fldaddress = getParam(request, "address");
      fldnotes = getParam(request, "notes");
      fldcard_type_id = getParam(request, "card_type_id");
      fldcard_number = getParam(request, "card_number");
      // Validate fields
      if ( iAction == iinsertAction || iAction == iupdateAction ) {
        if ( isEmpty(fldmember_login) ) {
          sMembersErr = sMembersErr + "The value in field Login* is required.<br>";
        }
        if ( isEmpty(fldmember_password) ) {
          sMembersErr = sMembersErr + "The value in field Password* is required.<br>";
        }
        if ( isEmpty(fldmember_level) ) {
          sMembersErr = sMembersErr + "The value in field Level* is required.<br>";
        }
        if ( isEmpty(fldname) ) {
          sMembersErr = sMembersErr + "The value in field First Name* is required.<br>";
        }
        if ( isEmpty(fldlast_name) ) {
          sMembersErr = sMembersErr + "The value in field Last Name* is required.<br>";
        }
        if ( isEmpty(fldemail) ) {
          sMembersErr = sMembersErr + "The value in field Email* is required.<br>";
        }
        if ( ! isNumber(fldmember_level)) {
          sMembersErr = sMembersErr + "The value in field Level* is incorrect.<br>";
        }
        if ( ! isNumber(fldcard_type_id)) {
          sMembersErr = sMembersErr + "The value in field Credit Card Type is incorrect.<br>";
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
            sMembersErr = sMembersErr + "The value in field Login* is already in database.<br>";
          }
        }
        if (sMembersErr.length() > 0 ) {
          return (sMembersErr);
        }
      }


      sSQL = "";
      // Create SQL statement

      switch (iAction) {
  
        case iinsertAction :
          
            sSQL = "insert into members (" + 
                "member_login," +
                "member_password," +
                "member_level," +
                "first_name," +
                "last_name," +
                "email," +
                "phone," +
                "address," +
                "notes," +
                "card_type_id," +
                "card_number)" +

                " values (" + 
                toSQL(fldmember_login, adText) + "," +
                toSQL(fldmember_password, adText) + "," +
                toSQL(fldmember_level, adNumber) + "," +
                toSQL(fldname, adText) + "," +
                toSQL(fldlast_name, adText) + "," +
                toSQL(fldemail, adText) + "," +
                toSQL(fldphone, adText) + "," +
                toSQL(fldaddress, adText) + "," +
                toSQL(fldnotes, adText) + "," +
                toSQL(fldcard_type_id, adNumber) + "," +
                toSQL(fldcard_number, adText) + ")";
          break;
  
      case iupdateAction:
        
          sSQL = "update members set " +
                "member_login=" + toSQL(fldmember_login, adText) +
                ",member_password=" + toSQL(fldmember_password, adText) +
                ",member_level=" + toSQL(fldmember_level, adNumber) +
                ",first_name=" + toSQL(fldname, adText) +
                ",last_name=" + toSQL(fldlast_name, adText) +
                ",email=" + toSQL(fldemail, adText) +
                ",phone=" + toSQL(fldphone, adText) +
                ",address=" + toSQL(fldaddress, adText) +
                ",notes=" + toSQL(fldnotes, adText) +
                ",card_type_id=" + toSQL(fldcard_type_id, adNumber) +
                ",card_number=" + toSQL(fldcard_number, adText);
          sSQL = sSQL + " where " + sWhere;
        break;
      
      case ideleteAction:
           sSQL = "delete from members where " + sWhere;
          
        break;
  
      }

      if ( sMembersErr.length() > 0 ) return sMembersErr;
      try {
        // Execute SQL statement
        stat.executeUpdate(sSQL);
      }
      catch(java.sql.SQLException e) {
        sMembersErr = e.toString(); return (sMembersErr);
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
    return (sMembersErr);
  }

  


  void Members_Show(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpSession session, javax.servlet.jsp.JspWriter out, String sMembersErr, String sForm, String sAction, java.sql.Connection conn, java.sql.Statement stat) throws java.io.IOException {
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

      String fldmember_login="";
      String fldmember_id="";
      String fldmember_password="";
      String fldmember_level="";
      String fldname="";
      String fldlast_name="";
      String fldemail="";
      String fldphone="";
      String fldaddress="";
      String fldnotes="";
      String fldcard_type_id="";
      String fldcard_number="";


      boolean bPK = true;

      if ( "".equals(sMembersErr)) {
        // Load primary key and form parameters
        fldmember_login = getParam( request, "member_login");
        fldmember_id = getParam( request, "member_id");
        transitParamsHidden += "<input type=\"hidden\" name=\"Trn_member_login\" value=\""+getParam( request, "member_login")+"\">";
        transitParams += "Trn_member_login="+getParam( request, "member_login")+"&";
        pmember_id = getParam( request, "member_id");
      }
      else {
        // Load primary key, form parameters and form fields
        fldmember_id = getParam( request, "member_id");
        fldmember_login = getParam( request, "member_login");
        fldmember_password = getParam( request, "member_password");
        fldmember_level = getParam( request, "member_level");
        fldname = getParam( request, "name");
        fldlast_name = getParam( request, "last_name");
        fldemail = getParam( request, "email");
        fldphone = getParam( request, "phone");
        fldaddress = getParam( request, "address");
        fldnotes = getParam( request, "notes");
        fldcard_type_id = getParam( request, "card_type_id");
        fldcard_number = getParam( request, "card_number");
        transitParamsHidden += "<input type=\"hidden\" name=\"Trn_member_login\" value=\""+getParam( request, "Trn_member_login")+"\">";
        transitParams += "Trn_member_login="+getParam( request, "Trn_member_login")+"&";
        
        pmember_id = getParam( request, "PK_member_id");
      }

      
      if ( isEmpty(pmember_id)) { bPK = false; }
      
      sWhere += "member_id=" + toSQL(pmember_id, adNumber);
      primaryKeyParams += "<input type=\"hidden\" name=\"PK_member_id\" value=\""+pmember_id+"\"/>";

      sSQL = "select * from members where " + sWhere;


      out.println("    <table style=\"\">");
      out.println("     <tr>\n      <td style=\"background-color: #336699; text-align: Center; border-style: outset; border-width: 1\" colspan=\"2\"><font style=\"font-size: 12pt; color: #FFFFFF; font-weight: bold\">Members</font></td>\n     </tr>");
      if ( ! sMembersErr.equals("")) {
        out.println("     <tr>\n      <td style=\"background-color: #FFFFFF; border-width: 1\" colspan=\"2\"><font style=\"font-size: 10pt; color: #000000\">"+sMembersErr+"</font></td>\n     </tr>");
      }
      sMembersErr="";
      out.println("     <form method=\"get\" action=\""+sFileName+"\" name=\"Members\">");

      java.sql.ResultSet rs = null;

      if ( bPK &&  ! (sAction.equals("insert") && "Members".equals(sForm))) {

        // Open recordset
        rs = openrs( stat, sSQL);
        rs.next();
        String[] aFields = getFieldsName( rs );
        getRecordToHash( rs, rsHash, aFields );
        rs.close();
        fldmember_id = (String) rsHash.get("member_id");
        if ( "".equals(sMembersErr)) {
          // Load data from recordset when form displayed first time
          fldmember_login = (String) rsHash.get("member_login");
          fldmember_password = (String) rsHash.get("member_password");
          fldmember_level = (String) rsHash.get("member_level");
          fldname = (String) rsHash.get("first_name");
          fldlast_name = (String) rsHash.get("last_name");
          fldemail = (String) rsHash.get("email");
          fldphone = (String) rsHash.get("phone");
          fldaddress = (String) rsHash.get("address");
          fldnotes = (String) rsHash.get("notes");
          fldcard_type_id = (String) rsHash.get("card_type_id");
          fldcard_number = (String) rsHash.get("card_number");
        }

        if (sAction.equals("") || ! "Members".equals(sForm)) {
      
          fldmember_id = (String) rsHash.get("member_id");
          fldmember_login = (String) rsHash.get("member_login");
          fldmember_password = (String) rsHash.get("member_password");
          fldmember_level = (String) rsHash.get("member_level");
          fldname = (String) rsHash.get("first_name");
          fldlast_name = (String) rsHash.get("last_name");
          fldemail = (String) rsHash.get("email");
          fldphone = (String) rsHash.get("phone");
          fldaddress = (String) rsHash.get("address");
          fldnotes = (String) rsHash.get("notes");
          fldcard_type_id = (String) rsHash.get("card_type_id");
          fldcard_number = (String) rsHash.get("card_number");
        }
        
      }
      else {
        if ( "".equals(sMembersErr)) {
          fldmember_id = toHTML(getParam(request,"member_id"));
          fldmember_login = toHTML(getParam(request,"member_login"));
        }
      }
      


      // Show form field
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Login*</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"member_login\" maxlength=\"20\" value=\""+toHTML(fldmember_login)+"\" size=\"20\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Password*</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"password\"  name=\"member_password\" maxlength=\"20\" value=\""+toHTML(fldmember_password)+"\" size=\"20\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Level*</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); 
      out.print("<select name=\"member_level\">"+getOptionsLOV("1;Member;2;Administrator",false,true,fldmember_level)+"</select>");
      
      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">First Name*</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"name\" maxlength=\"50\" value=\""+toHTML(fldname)+"\" size=\"50\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Last Name*</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"last_name\" maxlength=\"50\" value=\""+toHTML(fldlast_name)+"\" size=\"50\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Email*</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"email\" maxlength=\"50\" value=\""+toHTML(fldemail)+"\" size=\"50\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Phone</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"phone\" maxlength=\"50\" value=\""+toHTML(fldphone)+"\" size=\"50\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Address</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"address\" maxlength=\"50\" value=\""+toHTML(fldaddress)+"\" size=\"50\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Notes</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<textarea name=\"notes\" cols=\"50\" rows=\"5\">"+toHTML(fldnotes)+"</textarea>");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Credit Card Type</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); 
      out.print("<select name=\"card_type_id\">"+getOptions( conn, "select card_type_id, name from card_types order by 2",false,false,fldcard_type_id)+"</select>");
      
      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Credit Card Number</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"card_number\" maxlength=\"50\" value=\""+toHTML(fldcard_number)+"\" size=\"50\">");

      out.println("</td>\n     </tr>");
      
      out.print("     <tr>\n      <td colspan=\"2\" align=\"right\">");
      

      if ( bPK && ! (sAction.equals("insert") && "Members".equals(sForm))) {
        out.print("<input type=\"submit\" value=\"Update\" onclick=\"document.Members.FormAction.value = 'update';\">");out.print("<input type=\"submit\" value=\"Delete\" onclick=\"document.Members.FormAction.value = 'delete';\">");out.print("<input type=\"submit\" value=\"Cancel\" onclick=\"document.Members.FormAction.value = 'cancel';\">");
        out.print("<input type=\"hidden\" name=\"FormName\" value=\"Members\"><input type=\"hidden\" value=\"update\" name=\"FormAction\">");
      }
      
      else {
        out.print("<input type=\"submit\" value=\"Insert\" onclick=\"document.Members.FormAction.value = 'insert';\">");out.print("<input type=\"submit\" value=\"Cancel\" onclick=\"document.Members.FormAction.value = 'cancel';\">");
        out.print("<input type=\"hidden\" name=\"FormName\" value=\"Members\"><input type=\"hidden\" value=\"insert\" name=\"FormAction\">");
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
<table>
	<tr>

		<td valign="top">
		<% Members_Show(request, response, session, out, sMembersErr, sForm, sAction, conn, stat); %>
		<script language="JavaScript" type="text/javascript">
if (document.forms["Members"])
document.Members.onsubmit=delconf;
function delconf() {
if (document.Members.FormAction.value == 'delete')
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
