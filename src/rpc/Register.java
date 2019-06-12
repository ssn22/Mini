package rpc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import db.DBConnection;
import db.DBConnectionFactory;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DBConnection conn = DBConnectionFactory.getDBConnection();   
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			JSONObject msg = new JSONObject();
			//get request parameters for userID and password
			String user = request.getParameter("user_id");
			String pwd = request.getParameter("password");
			String fname = request.getParameter("firstname");
			String lname = request.getParameter("lastname");
			if (!conn.contains(user)) {			
				conn.addUser(user, pwd, fname, lname);
				msg.put("status", "OK");

			} else {
				msg.put("status", "Invalid");
				msg.put("message", "User already exists.");
			}
			RpcHelper.writeJsonObject(response, msg);
		} catch (JSONException e){
			e.printStackTrace();
		}
	}

}
