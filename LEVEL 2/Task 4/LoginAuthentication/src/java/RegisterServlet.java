/*
* To change this license header, choose License Headers in Project Properties.
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
/**
*
* @author siyac
*/
public class RegisterServlet extends HttpServlet {
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
 
 String id=request.getParameter("t1");
 String ps=request.getParameter("t2");
 String nm=request.getParameter("t3");
 String ad=request.getParameter("t4");
 String ct=request.getParameter("cb");
 String am=request.getParameter("t5");
 
 out.println("<!DOCTYPE html>");
 out.println("<html>");
 out.println("<head>");
 out.println("<title>Servlet RegisterServlet</title>"); 
 out.println("</head>");
 out.println("<body>");
 try{
 Class.forName("com.mysql.jdbc.Driver");
 java.sql.Connection 
 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Bankdb","root","schoudhari@2003");
 System.out.println("Connection Established.......");
 PreparedStatement pstat=con.prepareStatement("insert into customerdetails values(?,?,?,?,?,?)");
 pstat.setString(1,id);
 pstat.setString(2,ps);
 pstat.setString(3,nm);
 pstat.setString(4,ad);
 pstat.setString(5,ct);
 pstat.setString(6,am);
 pstat.executeUpdate();
 out.println("<script type=\"text/javascript\">"); 
 out.println("alert('Registration Successfull........');"); 
 out.println("location='Registration.html';"); 
 out.println("</script>"); 
 }
 catch(Exception e)
 {
 out.println(e);
 }
 
 
 out.println("<br/>");
 out.println("<a href=index.html>Go Back</a>");
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