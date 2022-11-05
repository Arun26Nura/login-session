package com.besant.app.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.besant.app.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName= request.getParameter("username");
		String password= request.getParameter("password");
		
		LoginService service= new LoginService();
		Boolean isValidUser= service.isValidUser(userName, password);
		
		if(isValidUser) {
			HttpSession session= request.getSession();
			session.setAttribute("userId", userName);
			
			response.sendRedirect("profile");
			//request.getRequestDispatcher("profile.jsp").forward(request, response);
		}else {
			response.sendRedirect("error");
		}
		
		
		
		
		
		
	}

}
