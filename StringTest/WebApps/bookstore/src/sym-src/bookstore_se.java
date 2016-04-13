import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
//import org.apache.jasper.runtime.*;
import org.apache.jsp.*;
import java.io.*;
import gov.nasa.jpf.symbolic.integer.Expression;
import gov.nasa.jpf.jvm.*;

public class bookstore_se {
private static final boolean Login = true;
private static final boolean ShoppingCart = true;
private static final boolean AdminBooks = true;
private static final boolean Header = true;
private static final boolean Footer = true;
private static final boolean BookDetail = true;
private static final boolean Books = true;
private static final boolean BookMaint = true;
private static final boolean AdvSearch = true;
private static final boolean CategoriesGrid = true;
private static final boolean CardTypesGrid = true;
private static final boolean OrdersRecord = true;
private static final boolean MembersInfo = true;
private static final boolean CardTypesRecord = true;
private static final boolean EditorialCatGrid = true;
private static final boolean EditorialsGrid = true;
private static final boolean ShoppingCartRecord = true;
private static final boolean Registration = true;
private static final boolean CategoriesRecord = true;
private static final boolean EditorialsRecord = true;
private static final boolean EditorialCatRecord = true;
private static final boolean MembersRecord = true;
private static final boolean MyInfo = true;
private static final boolean OrdersGrid = true;
private static final boolean AdminMenu = true;
private static final boolean MembersGrid = true;
private static final boolean Default = true;
public static void main(String[] args) throws java.io.IOException, ServletException {
StringWriter sw = new StringWriter();
PrintWriter pw = new PrintWriter(sw);
HttpServletRequest request = new HttpServletRequestWrapper();
HttpServletResponse response = new HttpServletResponseWrapper();
int i = Verify.random(27);
switch (i) {
case 0:
if (Login) {
pw.println("-----------------------------------------------------");
pw.println("%name::Login.jsp");
(new Login_jsp())._jspService(new HttpServletRequestWrapper(), new HttpServletResponseWrapper());
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 1:
if (ShoppingCart) {
pw.println("-----------------------------------------------------");
pw.println("%name::ShoppingCart.jsp");
(new ShoppingCart_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 2:
if (AdminBooks) {
pw.println("-----------------------------------------------------");
pw.println("%name::AdminBooks.jsp");
(new AdminBooks_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 3:
if (Header) {
pw.println("-----------------------------------------------------");
pw.println("%name::Header.jsp");
(new Header_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 4:
if (Footer) {
pw.println("-----------------------------------------------------");
pw.println("%name::Footer.jsp");
(new Footer_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 5:
if (BookDetail) {
pw.println("-----------------------------------------------------");
pw.println("%name::BookDetail.jsp");
(new BookDetail_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 6:
if (Books) {
pw.println("-----------------------------------------------------");
pw.println("%name::Books.jsp");
(new Books_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 7:
if (BookMaint) {
pw.println("-----------------------------------------------------");
pw.println("%name::BookMaint.jsp");
(new BookMaint_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 8:
if (AdvSearch) {
pw.println("-----------------------------------------------------");
pw.println("%name::AdvSearch.jsp");
(new AdvSearch_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 9:
if (CategoriesGrid) {
pw.println("-----------------------------------------------------");
pw.println("%name::CategoriesGrid.jsp");
(new CategoriesGrid_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 10:
if (CardTypesGrid) {
pw.println("-----------------------------------------------------");
pw.println("%name::CardTypesGrid.jsp");
(new CardTypesGrid_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 11:
if (OrdersRecord) {
pw.println("-----------------------------------------------------");
pw.println("%name::OrdersRecord.jsp");
(new OrdersRecord_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 12:
if (MembersInfo) {
pw.println("-----------------------------------------------------");
pw.println("%name::MembersInfo.jsp");
(new MembersInfo_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 13:
if (CardTypesRecord) {
pw.println("-----------------------------------------------------");
pw.println("%name::CardTypesRecord.jsp");
(new CardTypesRecord_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 14:
if (EditorialCatGrid) {
pw.println("-----------------------------------------------------");
pw.println("%name::EditorialCatGrid.jsp");
(new EditorialCatGrid_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 15:
if (EditorialsGrid) {
pw.println("-----------------------------------------------------");
pw.println("%name::EditorialsGrid.jsp");
(new EditorialsGrid_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 16:
if (ShoppingCartRecord) {
pw.println("-----------------------------------------------------");
pw.println("%name::ShoppingCartRecord.jsp");
(new ShoppingCartRecord_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 17:
if (Registration) {
pw.println("-----------------------------------------------------");
pw.println("%name::Registration.jsp");
(new Registration_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 18:
if (CategoriesRecord) {
pw.println("-----------------------------------------------------");
pw.println("%name::CategoriesRecord.jsp");
(new CategoriesRecord_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 19:
if (EditorialsRecord) {
pw.println("-----------------------------------------------------");
pw.println("%name::EditorialsRecord.jsp");
(new EditorialsRecord_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 20:
if (EditorialCatRecord) {
pw.println("-----------------------------------------------------");
pw.println("%name::EditorialCatRecord.jsp");
(new EditorialCatRecord_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 21:
if (MembersRecord) {
pw.println("-----------------------------------------------------");
pw.println("%name::MembersRecord.jsp");
(new MembersRecord_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 22:
if (MyInfo) {
pw.println("-----------------------------------------------------");
pw.println("%name::MyInfo.jsp");
(new MyInfo_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 23:
if (OrdersGrid) {
pw.println("-----------------------------------------------------");
pw.println("%name::OrdersGrid.jsp");
(new OrdersGrid_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 24:
if (AdminMenu) {
pw.println("-----------------------------------------------------");
pw.println("%name::AdminMenu.jsp");
(new AdminMenu_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 25:
if (MembersGrid) {
pw.println("-----------------------------------------------------");
pw.println("%name::MembersGrid.jsp");
(new MembersGrid_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
case 26:
if (Default) {
pw.println("-----------------------------------------------------");
pw.println("%name::Default.jsp");
(new Default_jsp())._jspService(request, response);
pw.println(SymString.toStringPC());
pw.println(Expression.pc);
System.out.println(sw.toString());
}
break;
}
pw.close();
}
}
