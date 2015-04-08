<%@ include file="Common.jsp"%>
<%!
//
//   Filename: EditorialCatGrid.jsp
//   Generated with CodeCharge  v.1.2.0
//   JSP.ccp build 05/21/2001
//

static final String sFileName = "EditorialCatGrid.jsp";
              
%>
<%
	String cSec = checkSecurity(2, session, response, request);
	if ("sendRedirect".equals(cSec))
		return;

	boolean bDebug = false;

	String sAction = getParam(request, "FormAction");
	String sForm = getParam(request, "FormName");
	String seditorial_categoriesErr = "";

	java.sql.Connection conn = null;
	java.sql.Statement stat = null;
	String sErr = loadDriver();
	conn = cn();
	stat = conn.createStatement();
	if (!sErr.equals("")) {
		try {
			out.println(sErr);
		} catch (Exception e) {
		}
	}
%>
<%!void editorial_categories_Show(
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response,
			javax.servlet.http.HttpSession session,
			javax.servlet.jsp.JspWriter out, String seditorial_categoriesErr,
			String sForm, String sAction, java.sql.Connection conn,
			java.sql.Statement stat) throws java.io.IOException {

		String sWhere = "";
		int iCounter = 0;
		int iPage = 0;
		boolean bIsScroll = true;
		boolean hasParam = false;
		String sOrder = "";
		String sSQL = "";
		String transitParams = "";
		String sQueryString = "";
		String sPage = "";
		int RecordsPerPage = 20;
		String sSortParams = "";
		String formParams = "";

		// Build WHERE statement

		// Build ORDER statement
		sOrder = " order by e.editorial_cat_name Asc";
		String sSort = getParam(request, "Formeditorial_categories_Sorting");
		String sSorted = getParam(request, "Formeditorial_categories_Sorted");
		String sDirection = "";
		String sForm_Sorting = "";
		int iSort = 0;
		try {
			iSort = Integer.parseInt(sSort);
		} catch (NumberFormatException e) {
			sSort = "";
		}
		if (iSort == 0) {
			sForm_Sorting = "";
		} else {
			if (sSort.equals(sSorted)) {
				sSorted = "0";
				sForm_Sorting = "";
				sDirection = " DESC";
				sSortParams = "Formeditorial_categories_Sorting=" + sSort
						+ "&Formeditorial_categories_Sorted=" + sSort + "&";
			} else {
				sSorted = sSort;
				sForm_Sorting = sSort;
				sDirection = " ASC";
				sSortParams = "Formeditorial_categories_Sorting=" + sSort
						+ "&Formeditorial_categories_Sorted=" + "&";
			}

			if (iSort == 1) {
				sOrder = " order by e.editorial_cat_name" + sDirection;
			}
		}

		// Build full SQL statement

		sSQL = "select e.editorial_cat_id as e_editorial_cat_id, "
				+ "e.editorial_cat_name as e_editorial_cat_name "
				+ " from editorial_categories e ";

		sSQL = sSQL + sWhere + sOrder;

		String sNoRecords = "     <tr>\n      <td colspan=\"1\" style=\"background-color: #FFFFFF; border-width: 1\"><font style=\"font-size: 10pt; color: #000000\">No records</font></td>\n     </tr>";

		String tableHeader = "";
		tableHeader = "     <tr>\n      <td style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><a href=\""
				+ sFileName
				+ "?"
				+ formParams
				+ "Formeditorial_categories_Sorting=1&Formeditorial_categories_Sorted="
				+ sSorted
				+ "&\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Name</font></a></td>\n     </tr>";

		try {
			out.println("    <table style=\"\">");
			out
					.println("     <tr>\n      <td style=\"background-color: #336699; text-align: Center; border-style: outset; border-width: 1\" colspan=\"1\"><a name=\"editorial_categories\"><font style=\"font-size: 12pt; color: #FFFFFF; font-weight: bold\">Editorial Category</font></a></td>\n     </tr>");
			out.println(tableHeader);

		} catch (Exception e) {
		}

		try {
			// Select current page
			iPage = Integer.parseInt(getParam(request,
					"Formeditorial_categories_Page"));
		} catch (NumberFormatException e) {
			iPage = 0;
		}

		if (iPage == 0) {
			iPage = 1;
		}
		RecordsPerPage = 20;
		try {
			java.sql.ResultSet rs = null;
			// Open recordset
			rs = openrs(stat, sSQL);
			iCounter = 0;
			absolute(rs, (iPage - 1) * RecordsPerPage + 1);
			java.util.Hashtable rsHash = new java.util.Hashtable();
			String[] aFields = getFieldsName(rs);

			// Show main table based on recordset
			while ((iCounter < RecordsPerPage) && rs.next()) {

				getRecordToHash(rs, rsHash, aFields);
				String fldeditorial_cat_id = (String) rsHash
						.get("e_editorial_cat_id");
				String fldeditorial_cat_name = (String) rsHash
						.get("e_editorial_cat_name");

				out.println("     <tr>");

				out
						.print("      <td style=\"background-color: #FFFFFF; border-width: 1\">");
				out
						.print("<a href=\"EditorialCatRecord.jsp?"
								+ transitParams
								+ "editorial_cat_id="
								+ toURL((String) rsHash
										.get("e_editorial_cat_id"))
								+ "&\"><font style=\"font-size: 10pt; color: #000000\">"
								+ toHTML(fldeditorial_cat_name) + "</font></a>");

				out.println("</td>");
				out.println("     </tr>");

				iCounter++;
			}
			if (iCounter == 0) {
				// Recordset is empty
				out.println(sNoRecords);

				out
						.print("     <tr>\n      <td colspan=\"1\" style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">");
				out
						.print("<a href=\"EditorialCatRecord.jsp?"
								+ formParams
								+ "\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Insert</font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				out.println("</td>\n     </tr>");

				iCounter = RecordsPerPage + 1;
				bIsScroll = false;
			}

			else {

				// Parse scroller
				boolean bInsert = false;
				boolean bNext = rs.next();
				if (!bNext && iPage == 1) {

					out
							.print("     <tr>\n      <td colspan=\"1\" style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\">\n       <font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">");
					out
							.print("\n        <a href=\"EditorialCatRecord.jsp?"
									+ formParams
									+ "\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Insert</font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					out.println("\n      </td>\n     </tr>");

				} else {
					out
							.print("     <tr>\n      <td colspan=\"1\" style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">");

					out
							.print("\n       <a href=\"EditorialCatRecord.jsp?"
									+ formParams
									+ "\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Insert</font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					bInsert = true;

					if (iPage == 1) {
						out
								.print("\n       <a href_=\"#\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Previous</font></a>");
					} else {
						out
								.print("\n       <a href=\""
										+ sFileName
										+ "?"
										+ formParams
										+ sSortParams
										+ "Formeditorial_categories_Page="
										+ (iPage - 1)
										+ "#Form\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Previous</font></a>");
					}

					out.print("\n       [ " + iPage + " ]");

					if (!bNext) {
						out
								.print("\n       <a href_=\"#\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Next</font></a><br>");
					} else {
						out
								.print("\n       <a href=\""
										+ sFileName
										+ "?"
										+ formParams
										+ sSortParams
										+ "Formeditorial_categories_Page="
										+ (iPage + 1)
										+ "#Form\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Next</font></a><br>");
					}

					if (!bInsert) {
						out
								.print("     <tr>\n      <td colspan=\"1\" style=\"background-color: #FFFFFF; border-style: inset; border-width: 0\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">");
						out
								.print("\n        <a href=\"EditorialCatRecord.jsp?"
										+ formParams
										+ "\"><font style=\"font-size: 10pt; color: #CE7E00; font-weight: bold\">Insert</font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					}

					out.println("</td>\n     </tr>");
				}

			}

			if (rs != null)
				rs.close();
			out.println("    </table>");

		} catch (Exception e) {
			out.println(e.toString());
		}
	}%>

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
		<%
					editorial_categories_Show(request, response, session, out,
					seditorial_categoriesErr, sForm, sAction, conn, stat);
		%>

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
