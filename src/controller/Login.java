package controller;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import validates.InputValidator;
/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@InputValidator(name="Email", min=6, max=35, msg="Email error!")
    public String userName; 

	@InputValidator(name="password", min=6, max=20, msg="password error!")
    public String password;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		

//			String userName = request.getParameter("email");
//			
//			String password = request.getParameter("password");
        
//        String rememberMeStr = request.getParameter("remember");
//        boolean remember = "Y".equals(rememberMeStr);
//
//        boolean hasError = false;
//        String errorString = null;
        try {
        	Field[] fields = Login.class.getFields();
        	for(Field field:fields){
        		 for(Annotation ann:field.getAnnotations()){
                     if (ann instanceof InputValidator){
                    	 InputValidator check = (InputValidator)ann;
                         System.out.println(check.name());
                         System.out.println(check.min());
                         System.out.println(check.max());
                         System.out.println(check.msg());
                     }
                 }
        	}
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        doGet(request,response);
	}

}
