/*
* To change this license header, choose License Headers in Project 
Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
*
* @author siyac
*/
public class StatementServlet extends HttpServlet {
 /**
 * Processes requests for both HTTP <code>GET</code> and 
<code>POST</code>
 * methods.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
 protected void processRequest(HttpServletRequest request, 
HttpServletResponse response)
 throws ServletException, IOException, ClassNotFoundException, 
SQLException {
 response.setContentType("text/html;charset=UTF-8");
 try (PrintWriter out = response.getWriter()) {
 /* TODO output your page here. You may use following sample code. 
*/
 
 
 out.println("<!DOCTYPE html>");
 out.println("<html>");
 out.println("<head>");
 out.println("<title>Servlet statement</title>"); 
 out.println("</head>");
 out.println("<body>");
 HttpSession hs=request.getSession();
 
 try
 {
 Class.forName("com.mysql.jdbc.Driver");
 Connection 
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Bankdb",
"root","schoudhari@2003");
 System.out.println("Connection Established.....");
 
 
 
 out.println("<center>");
 out.println("<h1>Account Statement</h1>");
 
 
 out.println("<h1>User Id:"+hs.getValue("uid").toString()+"</h1>");
 PreparedStatement pstat1=con.prepareStatement("select * from transaction where Userid=?");
 pstat1.setString(1,hs.getValue("uid").toString());
 ResultSet rs=pstat1.executeQuery();
 
 out.println("<table border=2>");
 out.println("<tr>");
 
out.println("<td>Amount</td><td>Transactiontype</td><td>Date</td>");
 out.println("</tr>");
 while(rs.next())
 {
 out.println("<tr>");
 out.println("<td>"+rs.getString(2)+"</td>");
 out.println("<td>"+rs.getString(3)+"</td>");
 out.println("<td>"+rs.getString(4)+"</td>");
 out.println("</tr>");
 
 
 }
 out.println("</table>");
 
 PreparedStatement stat=con.prepareStatement("select Amount from customerdetails where Userid=?"); 
 stat.setString(1,hs.getValue("uid").toString());
 ResultSet rs1=stat.executeQuery();
 if(rs1.next())
 {
 out.println("<h1>Balance : "+rs1.getInt(1)+"</h1>");
 }
 out.println("</body>");
 out.println("</html>");
 
 }
 catch(Exception e)
 {
 out.println(e);
 }
 out.println("<a href=Welcome.html>Go Back</a>");
 out.println("</body>");
 out.println("</html>");
 
 }
 }
 
 // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
 /**
 * Handles the HTTP <code>GET</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
 @Override
 protected void doGet(HttpServletRequest request, HttpServletResponse 
response)
 throws ServletException, IOException {
 try {
 processRequest(request, response);
 } catch (ClassNotFoundException ex) {
 Logger.getLogger(StatementServlet.class.getName()).log(Level.SEVERE, null, 
ex);
 } catch (SQLException ex) {
 Logger.getLogger(StatementServlet.class.getName()).log(Level.SEVERE, null, 
ex);
 }
 }
 /**
 * Handles the HTTP <code>POST</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse 
response)
 throws ServletException, IOException {
 try {
 processRequest(request, response);
 } catch (ClassNotFoundException ex) {
 Logger.getLogger(StatementServlet.class.getName()).log(Level.SEVERE, null, 
ex);
 } catch (SQLException ex) {
 Logger.getLogger(StatementServlet.class.getName()).log(Level.SEVERE, null, 
ex);
 }
 }
 /**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */
 @Override
 public String getServletInfo() {
 return "Short description";
 }// </editor-fold>

}