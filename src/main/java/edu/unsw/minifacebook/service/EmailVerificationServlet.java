package edu.unsw.minifacebook.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.unsw.minifacebook.forms.UserForm;
import edu.unsw.minifacebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Servlet implementation class EmailVerificationServlet
 */
public class EmailVerificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserForm userform;

	@Autowired
	private UserService userService;

	public UserForm getUserform() {
		return userform;
	}

	public void setUserform(UserForm userform) {
		this.userform = userform;
	}


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmailVerificationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getParameter("username");
		request.getParameter("email");
		try {
			boolean result = userService.register(userform);
			if (result) {
				response.getWriter().append("asdf");
			} else
				response.getWriter().append("user already exist");
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
