/*
* To change this license header, choose License Headers in Project 
Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
*
* @author siyac
*/
public class TransactionServlet extends HttpServlet {
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
 throws ServletException, IOException {
 response.setContentType("text/html;charset=UTF-8");
 try (PrintWriter out = response.getWriter()) {
 
 int am=Integer.parseInt(request.getParameter("t1"));
 String un=request.getParameter("t2");
 
 out.println("<!DOCTYPE html>");
 out.println("<html>");
 out.println("<head>");
 out.println("<title>Servlet TransferServlet</title>"); 
 out.println("</head>");
 out.println("<body>");
 HttpSession hs=request.getSession();
 
 
 try
 {
 Class.forName("com.mysql.jdbc.Driver");
 java.sql.Connection 
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Bankdb",
"root","schoudhari@2003");
 out.println("Connection Established.......");
 
 PreparedStatement stat4=con.prepareStatement("select * from customerdetails where Userid=?");
 stat4.setString(1,hs.getValue("uid").toString());
 
 ResultSet rs1=stat4.executeQuery();
 
 if(rs1.next())
 {
 if(rs1.getInt(6)>am)
 {
 
 
 PreparedStatement stat1=con.prepareStatement("select * from customerdetails where Amount=?");
 stat1.setInt(1,am);
 
 ResultSet rs=stat1.executeQuery();
 
 if(rs.next())
 {
 response.sendRedirect("Welcome.html");
 }
 else
 out.println("Sorry you cannot transfer beacuse your account balanace is low.");
 
 PreparedStatement pstat=con.prepareStatement("insert into transaction values(?,?,?,?)");
 pstat.setString(1,hs.getValue("uid").toString());
 pstat.setInt(2,am);
 pstat.setString(3,"Transfer out");
 pstat.setString(4,new java.sql.Date(hs.getCreationTime()).toString());
 pstat.executeUpdate();
 
 
 PreparedStatement pstat1=con.prepareStatement("update customerdetails set Amount=Amount-? where Userid=?");
 pstat1.setInt(1,am);
 pstat1.setString(2,hs.getValue("uid").toString());
 pstat1.executeUpdate();
 
 
 
 PreparedStatement pstat2=con.prepareStatement("insert into transaction value(?,?,?,?)");
 pstat2.setString(1,un);
 pstat2.setInt(2,am);
 pstat2.setString(3,"Transfer In");
 pstat2.setString(4,new 
java.sql.Date(hs.getCreationTime()).toString());
 pstat2.executeUpdate();
 
 
 PreparedStatement pstat3=con.prepareStatement("update customerdetails set Amount=Amount+? where Userid=?");
 pstat3.setInt(1,am);
 pstat3.setString(2,un);
 pstat3.executeUpdate();
 
 out.println("Transfer Successfully.......");
 }
 else
 out.println("Can not transfer money beacuse your account balance is low.....");
 }
 }
 catch(ClassNotFoundException | SQLException e)
 {
 out.println(e);
 }
 
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
 processRequest(request, response);
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
 processRequest(request, response);
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
