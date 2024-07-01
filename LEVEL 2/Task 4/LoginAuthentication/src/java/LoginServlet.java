/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
*
* @author siyac
*/
public class LoginServlet extends HttpServlet {
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
 try (PrintWriter out = response.getWriter()) 
 {
 
 out.println("<!DOCTYPE html>");
 out.println("<html>");
 out.println("<head>");
 out.println("<title>Servlet LoginServlet1</title>"); 
 out.println("</head>");
 out.println("<body>");
 
 String id=request.getParameter("t1");
 String ps=request.getParameter("t2");
// out.println("Welcome To Our Service....");
// out.println("<script type=\"text/javascript\">"); 
// out.println("alert('Welcome To Our Service....');"); 
// out.println("location='index.html';"); 
// out.println("</script>");
 
 try
 {
 
 Class.forName("com.mysql.jdbc.Driver");
 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Bankdb","root","schoudhari@2003");
 System.out.println("Connection Established.....");
 
 PreparedStatement pstat=con.prepareStatement("select * from customerdetails where Userid=? and Password=?");
 pstat.setString(1,id);
 pstat.setString(2,ps);
 ResultSet rs=pstat.executeQuery();
 
 if(rs.next())
 {
 HttpSession hs=request.getSession(true);
 hs.putValue("uid",id);
 response.sendRedirect("Welcome.html");
 }
 else
// out.println("Record is not found");
 out.println("<script type=\"text/javascript\">"); 
 out.println("alert('Record is not found');"); 
 out.println("location='index.html';"); 
 out.println("</script>");
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