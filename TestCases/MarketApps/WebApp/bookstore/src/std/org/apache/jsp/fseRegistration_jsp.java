package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class fseRegistration_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

//
	//   Filename: Common.jsp
	//   Generated with CodeCharge  v.1.2.0
	//   JSP.ccp build 05/21/2001
	//

	static final String CRLF = "\r\n";

	static final int UNDEFINT = Integer.MIN_VALUE;

	static final int adText = 1;

	static final int adDate = 2;

	static final int adNumber = 3;

	static final int adSearch_ = 4;

	static final int ad_Search_ = 5;

	static final String appPath = "/";

	//Database connection string

	static final String DBDriver = "com.mysql.jdbc.Driver";

	static final String strConn = "jdbc:mysql://localhost/bookstore";

	static final String DBusername = "bkUser";

	static final String DBpassword = "bkPass";

	public static String loadDriver() {
		String sErr = "";
		try {
			java.sql.DriverManager.registerDriver((java.sql.Driver) (Class
					.forName(DBDriver).newInstance()));
		} catch (Exception e) {
			sErr = e.toString();
		}
		return (sErr);
	}

	public static void absolute(java.sql.ResultSet rs, int row)
			throws java.sql.SQLException {
		for (int x = 1; x < row; x++)
			rs.next();
	}

	java.sql.ResultSet openrs(java.sql.Statement stat, String sql)
			throws java.sql.SQLException {
		java.sql.ResultSet rs = stat.executeQuery(sql);
		return (rs);
	}

	String dLookUp(java.sql.Statement stat, String table, String fName,
			String where) {
		java.sql.Connection conn1 = null;
		java.sql.Statement stat1 = null;
		try {
			conn1 = cn();
			stat1 = conn1.createStatement();
			java.sql.ResultSet rsLookUp = openrs(stat1, "SELECT " + fName
					+ " FROM " + table + " WHERE " + where);
			if (!rsLookUp.next()) {
				rsLookUp.close();
				stat1.close();
				conn1.close();
				return "";
			}
			String res = rsLookUp.getString(1);
			rsLookUp.close();
			stat1.close();
			conn1.close();
			return (res == null ? "" : res);
		} catch (Exception e) {
			return "";
		}
	}

	long dCountRec(java.sql.Statement stat, String table, String sWhere) {
		long lNumRecs = 0;
		try {
			java.sql.ResultSet rs = stat.executeQuery("select count(*) from "
					+ table + " where " + sWhere);
			if (rs != null && rs.next()) {
				lNumRecs = rs.getLong(1);
			}
			rs.close();
		} catch (Exception e) {
		}
		;
		return lNumRecs;
	}

	String proceedError(javax.servlet.http.HttpServletResponse response,
			Exception e) {
		return e.toString();
	}

	String[] getFieldsName(java.sql.ResultSet rs) throws java.sql.SQLException {
		java.sql.ResultSetMetaData metaData = rs.getMetaData();
		int count = metaData.getColumnCount();
		String[] aFields = new String[count];
		for (int j = 0; j < count; j++) {
			aFields[j] = metaData.getColumnLabel(j + 1);
		}
		return aFields;
	}

	java.util.Hashtable getRecordToHash(java.sql.ResultSet rs,
			java.util.Hashtable rsHash, String[] aFields)
			throws java.sql.SQLException {
		for (int iF = 0; iF < aFields.length; iF++) {
			rsHash.put(aFields[iF], getValue(rs, aFields[iF]));
		}
		return rsHash;
	}

	java.sql.Connection cn() throws java.sql.SQLException {
		//return java.sql.DriverManager.getConnection(strConn, DBusername, DBpassword);
		String url = strConn + "?" + "user=" + DBusername + "&" + "password=" + DBpassword + "&" + "connectTimeout=10000" + "&" + "socketTimeout=10000" + "&" + "allowMultiQueries=true";
		return java.sql.DriverManager.getConnection(url);
	}

	String toURL(String strValue) {
		if (strValue == null)
			return "";
		if (strValue.compareTo("") == 0)
			return "";
		return java.net.URLEncoder.encode(strValue);
	}

	String toHTML(String value) {
		if (value == null)
			return "";
		value = replace(value, "&", "&amp;");
		value = replace(value, "<", "&lt;");
		value = replace(value, ">", "&gt;");
		value = replace(value, "\"", "&" + "quot;");
		return value;
	}

	String getValueHTML(java.sql.ResultSet rs, String fieldName) {
		try {
			String value = rs.getString(fieldName);
			if (value != null) {
				return toHTML(value);
			}
		} catch (java.sql.SQLException sqle) {
		}
		return "";
	}

	String getValue(java.sql.ResultSet rs, String strFieldName) {
		if ((rs == null) || (isEmpty(strFieldName))
				|| ("".equals(strFieldName)))
			return "";
		try {
			String sValue = rs.getString(strFieldName);
			if (sValue == null)
				sValue = "";
			return sValue;
		} catch (Exception e) {
			return "";
		}
	}

	String getParam(javax.servlet.http.HttpServletRequest req, String paramName) {
		String param = req.getParameter(paramName);
		if (param == null) return "";
		param = replace(param, "&amp;", "&");
		param = replace(param, "&lt;", "<");
		param = replace(param, "&gt;", ">");
		param = replace(param, "&amp;lt;", "<");
		param = replace(param, "&amp;gt;", ">");
		return param;
	}

	boolean isNumber (String param) {
	    boolean result;
	    if ( param == null || param.equals("")) return true;
	    param=param.replace('d','_').replace('f','_');
	    try {
	      Double dbl = new Double(param);
	      result = true;
	    }
	    catch (NumberFormatException nfe) {
	      result = false;
	    }
	    return result;
	  }

	boolean isEmpty(int val) {
		return val == UNDEFINT;
	}

	boolean isEmpty(String val) {
		return val.equals("");
	}

	String getCheckBoxValue(String val, String checkVal, String uncheckVal,
			int ctype) {
		if (val == null || val.equals(""))
			return toSQL(uncheckVal, ctype);
		else
			return toSQL(checkVal, ctype);
	}

	String toWhereSQL(String fieldName, String fieldVal, int type) {
		String res = "";
		switch (type) {
		case adText:
			if (!"".equals(fieldVal)) {
				res = " " + fieldName + " like '%" + fieldVal + "%'";
			}
		case adNumber:
			res = " " + fieldName + " = " + fieldVal + " ";
		case adDate:
			res = " " + fieldName + " = '" + fieldVal + "' ";
		default:
			res = " " + fieldName + " = '" + fieldVal + "' ";
		}
		return res;
	}

	String toSQL(String value, int type) {

		if (value == null)
			return "null";

		String param = value;
		if ("".equals(param) && (type == adText || type == adDate)) {
			return "null";
		}
		switch (type) {
		case adText: {
			param = replace(param, "'", "''");
			param = replace(param, "&amp;", "&");
			param = "'" + param + "'";
			break;
		}
		case adSearch_:
		case ad_Search_: {
			param = replace(param, "'", "''");
			break;
		}
		case adNumber: {
			try {
				if (!isNumber(value) || "".equals(param))
					param = "null";
				else
					param = value;
			} catch (NumberFormatException nfe) {
				param = "null";
			}
			break;
		}
		case adDate: {
			param = "'" + param + "'";
			break;
		}
		}
		return param;
	}

	private String replace(String str, String pattern, String replace) {

		return str;
		//    if (replace == null) {
		//      replace = "";
		//    }
		//    int s = 0, e = 0;
		//    StringBuffer result = new StringBuffer((int) str.length()*2);
		//    while ((e = str.indexOf(pattern, s)) >= 0) {
		//      result.append(str.substring(s, e));
		//      result.append(replace);
		//      s = e + pattern.length();
		//    }
		//    result.append(str.substring(s));
		//    return result.toString();
	}

	String getOptions(java.sql.Connection conn, String sql, boolean isSearch,
			boolean isRequired, String selectedValue) {

		String sOptions = "";
		String sSel = "";

		if (isSearch) {
			sOptions += "<option value=\"\">All</option>";
		} else {
			if (!isRequired) {
				sOptions += "<option value=\"\"></option>";
			}
		}
		try {
			java.sql.Statement stat = conn.createStatement();
			java.sql.ResultSet rs = null;
			rs = openrs(stat, sql);
			while (rs.next()) {
				String id = toHTML(rs.getString(1));
				String val = toHTML(rs.getString(2));
				if (id.compareTo(selectedValue) == 0) {
					sSel = "SELECTED";
				} else {
					sSel = "";
				}
				sOptions += "<option value=\"" + id + "\" " + sSel + ">" + val
						+ "</option>";
			}
			rs.close();
			stat.close();
		} catch (Exception e) {
		}
		return sOptions;
	}

	String getOptionsLOV(String sLOV, boolean isSearch, boolean isRequired,
			String selectedValue) {
		String sSel = "";
		String slOptions = "";
		String sOptions = "";
		String id = "";
		String val = "";
		java.util.StringTokenizer LOV = new java.util.StringTokenizer(sLOV,
				";", true);
		int i = 0;
		String old = ";";
		while (LOV.hasMoreTokens()) {
			id = LOV.nextToken();
			if (!old.equals(";") && (id.equals(";"))) {
				id = LOV.nextToken();
			} else {
				if (old.equals(";") && (id.equals(";"))) {
					id = "";
				}
			}
			if (!id.equals("")) {
				old = id;
			}

			i++;

			if (LOV.hasMoreTokens()) {
				val = LOV.nextToken();
				if (!old.equals(";") && (val.equals(";"))) {
					val = LOV.nextToken();
				} else {
					if (old.equals(";") && (val.equals(";"))) {
						val = "";
					}
				}
				if (val.equals(";")) {
					val = "";
				}
				if (!val.equals("")) {
					old = val;
				}
				i++;
			}

			if (id.compareTo(selectedValue) == 0) {
				sSel = "SELECTED";
			} else {
				sSel = "";
			}
			slOptions += "<option value=\"" + id + "\" " + sSel + ">" + val
					+ "</option>";
		}
		if ((i % 2) == 0)
			sOptions += slOptions;
		return sOptions;
	}

	String getValFromLOV(String selectedValue, String sLOV) {
		String sRes = "";
		String id = "";
		String val = "";
		java.util.StringTokenizer LOV = new java.util.StringTokenizer(sLOV,
				";", true);
		int i = 0;
		String old = ";";
		while (LOV.hasMoreTokens()) {
			id = LOV.nextToken();
			if (!old.equals(";") && (id.equals(";"))) {
				id = LOV.nextToken();
			} else {
				if (old.equals(";") && (id.equals(";"))) {
					id = "";
				}
			}
			if (!id.equals("")) {
				old = id;
			}

			i++;

			if (LOV.hasMoreTokens()) {
				val = LOV.nextToken();
				if (!old.equals(";") && (val.equals(";"))) {
					val = LOV.nextToken();
				} else {
					if (old.equals(";") && (val.equals(";"))) {
						val = "";
					}
				}
				if (val.equals(";")) {
					val = "";
				}
				if (!val.equals("")) {
					old = val;
				}
				i++;
			}

			if (id.compareTo(selectedValue) == 0) {
				sRes = val;
			}
		}
		return sRes;
	}

	String checkSecurityNONE(int iLevel, javax.servlet.http.HttpSession session,
			javax.servlet.http.HttpServletResponse response,
			javax.servlet.http.HttpServletRequest request) {
		return "";
	}
	
	String checkSecurity(int iLevel, javax.servlet.http.HttpSession session,
			javax.servlet.http.HttpServletResponse response,
			javax.servlet.http.HttpServletRequest request) {
		try {
			Object o1 = session.getAttribute("UserID");
			Object o2 = session.getAttribute("UserRights");
			boolean bRedirect = false;
			if (o1 == null || o2 == null) {
				bRedirect = true;
			}
			if (!bRedirect) {
				if ((o1.toString()).equals("")) {
					bRedirect = true;
				} else if ((new Integer(o2.toString())).intValue() < iLevel) {
					bRedirect = true;
				}
			}

			if (bRedirect) {
				response.sendRedirect("Login.jsp?querystring="
						+ toURL(request.getQueryString()) + "&ret_page="
						+ toURL(request.getRequestURI()));
				return "sendRedirect";
			}
		} catch (Exception e) {
		}
		return "";
	}

//
//   Filename: Registration.jsp
//   Generated with CodeCharge  v.1.2.0
//   JSP.ccp build 05/21/2001
//

static final String sFileName = "fseRegistration.jsp";
              



  void Reg_Show(HttpServletRequest request, HttpServletResponse response, HttpSession session, JspWriter out, String sRegErr, String sForm, String sAction) throws java.io.IOException {
    try {
	String fldmember_login = getParam( request, "member_login");
        String fldmember_pin1 = getParam( request, "member_pin1");      
	String fldmember_pin2 = getParam(request,"member_pin2");

	String fldfull_name = getParam( request, "full_name");      
	String fldzip_code = getParam(request,"zip_code");

	if (!isNumber(fldmember_pin1)) {
		sRegErr = "Pin must be numeric";
	
	}

	if (sForm.equals("") || !sRegErr.equals("")) {
		out.println("    <table style=\"\">");
		out.println("     <tr>\n      <td style=\"background-color: #336699; text-align: Center; border-style: outset; border-width: 1\" colspan=\"2\"><font style=\"font-size: 12pt; color: #FFFFFF; font-weight: bold\">Registration</font></td>\n     </tr>");
		if ( ! sRegErr.equals("")) {
			out.println("     <tr>\n      <td style=\"background-color: #FFFFFF; border-width: 1\" colspan=\"2\"><font style=\"font-size: 10pt; color: red\">"+sRegErr+"</font></td>\n     </tr>");
		}
		sRegErr="";
		
		out.println("     <form method=\"get\" action=\""+sFileName+"\" name=\"Reg\">");

	      	out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Choose Login</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"member_login\" maxlength=\"20\" value=\""+toHTML(fldmember_login)+"\" size=\"20\">");

	      	out.println("</td>\n     </tr>");
	      
	      	out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Choose Pin</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"member_pin1\" maxlength=\"20\" value=\""+toHTML(fldmember_pin1)+"\" size=\"20\">");

	      	out.println("</td>\n     </tr>");
	      
	      	out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Confirm Pin</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"member_pin2\" maxlength=\"20\" value=\""+toHTML(fldmember_pin2)+"\" size=\"20\">");

	      	out.println("</td>\n     </tr>");
	      
	      	out.print("     <tr>\n      <td colspan=\"2\" align=\"right\">");
	      
		out.print("<input type=\"submit\" value=\"Register\" onclick=\"document.Reg.FormAction.value = 'insert';\">");out.print("<input type=\"submit\" value=\"Cancel\" onclick=\"document.Reg.FormAction.value = 'cancel';\">");
	
		out.print("<input type=\"hidden\" name=\"FormName\" value=\"Register\"><input type=\"hidden\" value=\"insert\" name=\"FormAction\">");
	      
		out.println("</td>\n     </tr>\n     </form>\n    </table>");  
	} else if (sForm.equals("Register")) {

		out.println("    <table style=\"\">");
		out.println("     <tr>\n      <td style=\"background-color: #336699; text-align: Center; border-style: outset; border-width: 1\" colspan=\"2\"><font style=\"font-size: 12pt; color: #FFFFFF; font-weight: bold\">Registration</font></td>\n     </tr>");
		if ( ! sRegErr.equals("")) {
			out.println("     <tr>\n      <td style=\"background-color: #FFFFFF; border-width: 1\" colspan=\"2\"><font style=\"font-size: 10pt; color: #000000\">"+sRegErr+"</font></td>\n     </tr>");
		}
		sRegErr="";
		
		out.println("     <form method=\"get\" action=\""+sFileName+"\" name=\"Complete\">");

	      	out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Full name</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"full_name\" maxlength=\"20\" value=\""+toHTML(fldfull_name)+"\" size=\"20\">");

	      	out.println("</td>\n     </tr>");
	      
	      	out.print("     <tr>\n      <td style=\"background-color: #FFEAC5; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #000000\">Zip Code</font></td><td style=\"background-color: #FFFFFF; border-width: 1\">"); out.print("<input type=\"text\"  name=\"zip_code\" maxlength=\"20\" value=\""+toHTML(fldzip_code)+"\" size=\"20\">");

	      	out.println("</td>\n     </tr>");
	      
	      	out.print("     <tr>\n      <td colspan=\"2\" align=\"right\">");
	      
		out.print("<input type=\"submit\" value=\"Complete Registration\" onclick=\"document.Reg.FormAction.value = 'insert';\">");out.print("<input type=\"submit\" value=\"Cancel\" onclick=\"document.Reg.FormAction.value = 'cancel';\">");
	
		out.print("<input type=\"hidden\" name=\"FormName\" value=\"Complete\"><input type=\"hidden\" value=\"insert\" name=\"FormAction\">");
	      
		out.println("</td>\n     </tr>\n     </form>\n    </table>");  
	} else if (sForm.equals("Complete")) {
		out.println("<table style=\"\">");
		out.println("     <tr><td align=center style=\"background-color: #FFFFFF; border-width: 1\" colspan=\"2\"><font style=\"font-size: 20pt; color: #000000\">Welcome whalfond, registration is complete.</font></td></tr>");
		out.println("     <tr><td align=center style=\"background-color: #FFFFFF; border-width: 1\" colspan=\"2\"><font style=\"font-size: 10pt; color: #000000\"><href=\"Default.jsp\">Proceed to Online Bookstore.</a></font></td></tr>");
		out.println("</table>");  		
	}
   } catch (Exception e) { out.println(e.toString()); }
  } 






  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/Common.jsp");
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\n');
      out.write('\n');
      out.write('\n');


boolean bDebug = false;

String sAction = getParam( request, "FormAction");
String sForm = getParam( request, "FormName");
String sRegErr = "";


      out.write('\n');
      out.write("\n\n<html>\n<head>\n<title>Book Store</title>\n<meta name=\"GENERATOR\"\n\tcontent=\"YesSoftware CodeCharge v.1.2.0 / JSP.ccp build 05/21/2001\" />\n<meta http-equiv=\"pragma\" content=\"no-cache\" />\n<meta http-equiv=\"expires\" content=\"0\" />\n<meta http-equiv=\"cache-control\" content=\"no-cache\" />\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\n</head>\n<body\n\tstyle=\"background-color: #FFFFFF; color: #000000; font-family: Arial, Tahoma, Verdana, Helveticabackground-color: #FFFFFF; color: #000000; font-family: Arial, Tahoma, Verdana, Helvetica\">\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "Header.jsp", out, true);
      out.write("\n<center>\n<table>\n\t<tr>\n\n\t\t<td valign=\"top\">\n\t\t");
 Reg_Show(request, response, session, out, sRegErr, sForm, sAction); 
      out.write("\n\n\t\t</td>\n\t</tr>\n</table>\n\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "Footer.jsp", out, true);
      out.write("\n<center><font face=\"Arial\"><small>This dynamic\nsite was generated with <a href=\"http://www.codecharge.com\">CodeCharge</a></small></font></center>\n</center>\n</body>\n</html>\n\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
