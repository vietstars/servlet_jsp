package controller;

import java.io.IOException;
import java.util.Map;

import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import validates.InputValidatiorHandle;
import validates.InputValidator;
import model.UserAcc;
/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_USER_COOKIE = "COOKIE_USER";
	
	@InputValidator(name="email", min=6, max=35, msg="The length limit your account should be 6 -36 characters")
    public String userEmail; 

	@InputValidator(name="password", min=6, max=20, msg="The length limit your password should be 6 -20 characters")
    public String password;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }
    
    public String getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_USER_COOKIE.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("view", this.getClass().getSimpleName().toLowerCase() + ".jsp");
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/_layout.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.userEmail = request.getParameter("email");
		this.password = request.getParameter("password");  
        String remember = request.getParameter("remember");
        boolean rememberMe = "Y".equals(remember);
		UserAcc user = null;
		Map<String, String> error = null;

        if(rememberMe) {
    		Cookie cookieUserEmail = new Cookie(ATT_USER_COOKIE, userEmail);
            cookieUserEmail.setMaxAge(24 * 60 * 60);
            response.addCookie(cookieUserEmail);
    	} else {
    		Cookie cookieUserEmail = new Cookie(ATT_USER_COOKIE, null);
    		cookieUserEmail.setMaxAge(0);
            response.addCookie(cookieUserEmail);
    	}
        String rememberAcc = getCookie(request);
        user = new UserAcc(userEmail,password);
        InputValidatiorHandle validate = new InputValidatiorHandle();
        try {
        	error = validate.check(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
        if( error.get("email") != null || error.get("password") != null ) {
        	if(rememberAcc != null)request.setAttribute("user",rememberAcc);
        	request.setAttribute("error",error);
        	doGet(request,response);
        }else{
        	Gson gson = new Gson();
        	String getAcc = user.getAccount();
        	UserAcc Account = gson.fromJson(getAcc, UserAcc.class);
        	if((Account.email).equals(this.userEmail) && (Account.password).equals(this.password)) {
        		HttpSession session = request.getSession();
        		session.setAttribute("isLogin", true);
        		session.setAttribute("loggedId", Account.email);
        		session.setAttribute("loggedGender", Account.gender);
        		response.sendRedirect(request.getContextPath() + "/home");
        	}else{
        		if(!(Account.email).equals(this.userEmail)) {
            		error.put("email","Email not found!");
            	}
            	if(!(Account.password).equals(this.password)) {
            		error.put("password","Invaild password!");
            	}
            	if( error.get("email") != null || error.get("password") != null ) {
                	if(rememberAcc != null)request.setAttribute("user",rememberAcc);
                	request.setAttribute("error",error);
                	doGet(request,response);
            	}
        	}
        }
        
	}

}