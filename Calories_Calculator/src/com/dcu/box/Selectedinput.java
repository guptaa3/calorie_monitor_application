package com.dcu.box;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class Selectedinput extends HttpServlet{

	public static Map<Integer,String> listOfItems = new HashMap<Integer, String>();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			
			String[] checkedid=req.getParameterValues("id");
			
			for(int i=0;i<checkedid.length;i++) {
				String query = "Select product_name from nutri_data where id ='" + checkedid[i] +"';";
				System.out.println(query);
				jdbc_connect_cloud jdbc_connect_cloud = new jdbc_connect_cloud();
				ResultSet result = jdbc_connect_cloud.connectToCloudJDBC(query);
				while(result.next())
					listOfItems.put(new Integer(checkedid[i]), result.getString(1));
				System.out.println(checkedid[i]);
				jdbc_connect_cloud.closeConnection();
			}
			
			System.out.println(listOfItems.toString());
	
			
			out.print("<html>");
			out.print("<head><link rel=\"stylesheet\" href=\"style.css\"></head>");
			out.print("<body");
			out.println("<div class=\"nutrition-form\">");
			out.print("<form  ACTION=\"Check.jsp\" METHOD=\"POST\">");
			out.print("<center> <h2>");
			out.print("Good Job! You have selected " + listOfItems.size() + " Items till now");
			out.print("</h2></center>");
			out.print("<center><input type=\"submit\" value=\"Select More Foods!\">");
			out.println("<input type=\"button\" onclick=\"location.href='graph.jsp'\";\" value=\"Show me Analytics\" />");
			out.println("</center>");
			out.print("</form>");
			out.println("</div>");
			out.print("</body>");
			out.print("</html>");	
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			
		}
				
				finally {
					try {
						if(con!=null) {
						con.close(); 
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
