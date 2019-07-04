package com.dcu.box;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.jdbc.Driver;

@SuppressWarnings("serial")
public class Withcheckbox extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int count=0;
		int id = 0;
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();

		Connection con=null;

		Statement stmt=null;
		ResultSet rs=null;
		ResultSet rs1=null;

		try {
			Driver driverRef=new Driver();
			DriverManager.registerDriver(driverRef);	
			String foodname=req.getParameter("FOODNAME");

			String query="SELECT id,product_name,brands,energy_100g FROM nutri_data where product_name like '%"+foodname+"%';";
			jdbc_connect_cloud jdbc_connect_cloud = new jdbc_connect_cloud();
			rs = jdbc_connect_cloud.connectToCloudJDBC(query);
			rs1 = jdbc_connect_cloud.connectToCloudJDBC(query);
			
			while(rs.next()) {
				Reader s1=rs.getCharacterStream("product_name");
				if(s1!=null)
				{
					count++;	
				}
			}
			if(count!=0) {
				out.print("<html>");
				out.print("<head><link rel=\"stylesheet\" href=\"style.css\"></head>");
				out.print("<body");
				out.println("<div class=\"table-title\">");
				out.print("<Form ACTION=\"selectedinput\" METHOD=\"POST\">");
				out.print("<table lass=\"table-fill\" border=\"1\">");
				out.println("<tr>");
				out.print("<font  color=\"white\">");
				out.print("<center><h2><center>Select Food Items </h2> </center>");
				
				out.print("<center><input type=\"submit\" value=\"Submit\"></center>");
				out.print("<p></p>");
				out.println("<thead>");
				out.print("<th class=\"text-left\">Select</th>"); 
				out.print("<th class=\"text-left\">PRODUCT NAME</th>"); 
				out.print("<th class=\"text-left\">BRAND</th>");
				out.print("<th class=\"text-left\">CALORIES</th>");
				out.println("</thead>");
				out.println("<tbody class=\"table-hover\">");
				while(rs1.next()) {
					out.println("<tr>");
					out.print("<td class=\"text-left\"><input type=\"checkbox\" name=\"id\" value=\""+rs1.getInt(1)+"\"></td>");
					out.print("<td class=\"text-left\">"+rs1.getString(2)+"</td>"); 
					out.print("<td class=\"text-left\">"+rs1.getString(3)+"</td>");
					out.print("<td class=\"text-left\">"+rs1.getInt(4)+"</td>");
					out.print("</tr>");
				}
				out.println("</tbody>");
				out.print("</tr>");
				out.print("</table>");
				out.print("</form>");
				out.print("</body>");
				out.print("</html>");
			}
			else if(count==0) {
				out.println("<!doctype html>\r\n" + 
						"<html lang=\"en\">\r\n" + 
						"<head>\r\n" + 
						"    <meta charset=\"UTF-8\">\r\n" + 
						"    <title>No Data Found</title>\r\n" + 
						"    <style>\r\n" + 
						"    @import url('https://fonts.googleapis.com/css?family=Open+Sans');\r\n" + 
						"        body{\r\n" + 
						"            margin: 0;\r\n" + 
						"            padding: 0;\r\n" + 
						"        }\r\n" + 
						"        body:before{\r\n" + 
						"            content: '';\r\n" + 
						"            position: fixed;\r\n" + 
						"            width: 100vw;\r\n" + 
						"            height: 100vh;\r\n" + 
						"            background-image: url(\"bgimage.jpg\");\r\n" + 
						"            background-position: center center;\r\n" + 
						"            background-repeat: no-repeat;\r\n" + 
						"            background-attachment: fixed;\r\n" + 
						"            background-size: cover;\r\n" + 
						"            -webkit-filter: blur(3px);\r\n" + 
						"            -moz-filter: blur(3px);\r\n" + 
						"            -o-filter: blur(3px);\r\n" + 
						"            -ms-filter: blur(3px);\r\n" + 
						"            filter: blur(3px);\r\n" + 
						"        }\r\n" + 
						"        .nutrition-form\r\n" + 
						"        {\r\n" + 
						"            position: absolute;\r\n" + 
						"            top: 50%;\r\n" + 
						"            left: 50%;\r\n" + 
						"            transform: translate(-50%,-50%);\r\n" + 
						"            width: 400px;\r\n" + 
						"            height: 500px;\r\n" + 
						"            padding: 80px 40px;\r\n" + 
						"            box-sizing: border-box;\r\n" + 
						"            background: rgba(0,0,0,.5);\r\n" + 
						"        }\r\n" + 
						"        .nutrition-form h2 {\r\n" + 
						"            margin: 0;\r\n" + 
						"            padding: 0 0 20px;\r\n" + 
						"            font-family: 'Open Sans',serif;\r\n" + 
						"            color: white;\r\n" + 
						"            text-align: center;\r\n" + 
						"            text-transform: uppercase;\r\n" + 
						"        }\r\n" + 
						"        .nutrition-form p\r\n" + 
						"        {\r\n" + 
						"            margin: 0;\r\n" + 
						"            padding: 0;\r\n" + 
						"            font-weight: bold;\r\n" + 
						"            font-family: 'Open Sans',serif;\r\n" + 
						"            color: white;\r\n" + 
						"        }\r\n" + 
						"        \r\n" + 
						"    </style>\r\n" + 
						"</head>\r\n" + 
						"<body>\r\n" + 
						"    <div class=\"nutrition-form\">\r\n" + 
						"       \r\n" + 
						"        <h2>Sorry, entered name is incorrect or not available</h2>\r\n" + 
						"        <Form>\r\n" + 
						"            <p>Click <a href=\"Check.jsp\">here</a> To Search again</p>\r\n" + 
						"			\r\n" + 
						"            \r\n" + 
						"        </form>\r\n" + 
						"    </div>\r\n" + 
						"</body>\r\n" + 
						"</html>");
			}
			jdbc_connect_cloud.closeConnection();
		}
		catch(SQLException ex) {
			ex.printStackTrace();

		}

		finally {
			try {
				if(con!=null) {
					con.close(); 
					System.out.println(count);
					System.out.println(id);
					System.out.println("Connection closed");

				}

				if(stmt!=null) {
					stmt.close(); 
					System.out.println("Statement closed");
				}
				if(rs!=null) {
					rs.close(); 
					System.out.println("ResultSet closed");
				}

			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

