package controller;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserAcc;
import validates.InputValidator;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@InputValidator(name="email", min=6, max=35, msg="The length limit your account should be 6 -36 characters")
    public String userName; 

	@InputValidator(name="password", min=6, max=20, msg="The length limit your password should be 6 -20 characters")
    public String password;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
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
        String gender = request.getParameter("gender");        
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
        user = new UserAcc(userEmail,password);
        if( error.get("email") && error.get("password") ) {
        	request.setAttribute("user",user);
        	request.setAttribute("errorString",errorString);
        	request.setAttribute("error",error);
        	doGet(request,response);
        }else{     
        	Gson gson = new Gson();
        	user.setGender(gender);
        	user.setAccount(gson.toJson(user));
        	response.sendRedirect(request.getContextPath());
        }
	}

}
