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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
*
* @author siyac
*/
public class WithdrawServlet extends HttpServlet {
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
 
 out.println("<!DOCTYPE html>");
 out.println("<html>");
 out.println("<head>");
 out.println("<title>Servlet DepositServlet</title>"); 
 out.println("</head>");
 out.println("<body>");
 HttpSession hs=request.getSession();
 
 try
 {
 Class.forName("com.mysql.jdbc.Driver");
 java.sql.Connection 
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Bankdb","root","schoudhari@2003");
 out.println("Connection Established.......");
 
 PreparedStatement pstat=con.prepareStatement("insert into transaction values(?,?,?,?)");
 pstat.setString(1,hs.getValue("uid").toString());
 pstat.setInt(2,am);
 pstat.setString(3,"Withdraw");
 pstat.setString(4,new 
java.sql.Date(hs.getCreationTime()).toString());
 pstat.executeUpdate();
 
 
 PreparedStatement pstat1=con.prepareStatement("update customerdetails set Amount=Amount-? where Userid=?");
 pstat1.setInt(1,am);
 pstat1.setString(2,hs.getValue("uid").toString());
 
 pstat1.executeUpdate();
 out.println("<b>Withdraw Successfully......</b>");
 
 
 }
 
 catch(Exception e)
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
