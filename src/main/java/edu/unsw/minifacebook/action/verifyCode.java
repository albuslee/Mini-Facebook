package edu.unsw.minifacebook.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import edu.unsw.minifacebook.service.UserService;

/**
 * Servlet implementation class verifyCode
 */
public class verifyCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public verifyCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String code=request.getParameter("code");
		String usr=request.getParameter("username");
        boolean result = userService.verification(usr,code);
        if (result){
        	request.getRequestDispatcher("login.jsp").forward(request, response);}
        else{
        	request.getRequestDispatcher("fail.jsp").forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
