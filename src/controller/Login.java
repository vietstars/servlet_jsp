package controller;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.HashMap;

import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    public String userName; 

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
		String userEmail = request.getParameter("email");
		String password = request.getParameter("password");  
        String remember = request.getParameter("remember");
        boolean rememberMe = "Y".equals(remember);
		UserAcc user = null;
        Map<String,String> errorString = new HashMap<String,String>();
        Map<String, Boolean> error = new HashMap<String, Boolean>();
        try {
        	Field[] fields = Login.class.getFields();
        	for(Field field:fields){
        		 for(Annotation ann:field.getAnnotations()){
                     if (ann instanceof InputValidator){
                    	 InputValidator check = (InputValidator)ann;                    	 
                    	 if("email".equals(check.name())){
                    		 if( userEmail.length()<check.min() || userEmail.length()>check.max() ) {
                    			 error.put("email",true);
                    			 errorString.put("email",check.msg());
                    		 } else {
                    			 error.put("email",false);
                    		 }
                    	 }                   	 
                    	 if("password".equals(check.name())){
                    		 if( password.length()<check.min() || password.length()>check.max() ) {
                    			 error.put("password",true);
                    			 errorString.put("password",check.msg());
                    		 } else {
                    			 error.put("password",false);
                    		 }
                    	 }
                     }
                 }
        	}
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
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
        if( error.get("email") || error.get("password") ) {
        	if(rememberAcc != null)request.setAttribute("user",rememberAcc);
        	request.setAttribute("errorString",errorString);
        	request.setAttribute("error",error);
        	doGet(request,response);
        }else{
        	Gson gson = new Gson();
        	String getAcc = user.getAccount();
        	UserAcc Account = gson.fromJson(getAcc, UserAcc.class);
        	if((Account.email).equals(userEmail) && (Account.password).equals(password)) {
        		HttpSession session = request.getSession();
        		session.setAttribute("isLogin", true);
        		session.setAttribute("loggedId", Account.email);
        		session.setAttribute("loggedGender", Account.gender);
        		response.sendRedirect(request.getContextPath() + "/home");
        	}else{
        		if(!(Account.email).equals(userEmail)) {
            		error.put("email",true);
       			 	errorString.put("email","Invaild email!");
            	}
            	if(!(Account.password).equals(password)) {
            		error.put("password",true);
       			 	errorString.put("password","Invaild password!");
            	}
            	if( error.get("email") || error.get("password") ) {
                	if(rememberAcc != null)request.setAttribute("user",rememberAcc);
                	request.setAttribute("errorString",errorString);
                	request.setAttribute("error",error);
                	doGet(request,response);
            	}
        	}
        }
        
	}

}