package home;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpSession; 

/**
 * Servlet implementation class Home
 */
@WebServlet(description = "Home page", urlPatterns = { "/home" })
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Home() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		ServletOutputStream out = response.getOutputStream();
//		String user = request.getParameter("user");
//		out.println("<h1>Home page</h1>");
//		out.println("<h2>=="+this.getClass().getName()+"==</h2>");
//		out.println("<br><span>getParameter get User:</span>");
//		out.println(user);
		request.setAttribute("listBook", "master Ben prau");
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/layout.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		ServletOutputStream out = response.getOutputStream();
		String user = request.getParameter("account");
		out.println("<br><span>getParameter post Account:</span>");
		out.println(user);

		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
			jb.append(line);
		} catch (Exception e) { /*report an error*/ }

		out.println(jb.toString());  
	}

}
