package interfaces;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class logFilter
 */
@WebFilter("/*")
public class LogFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LogFilter() {
        // TODO Auto-generated constructor stub
    }

    
	/**
	 * @see Filter#destroy()
	 */
    @Override
	public void destroy() {
		System.out.println("LogFilter destroy!");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		 HttpServletRequest req = (HttpServletRequest) request;
		 
		 String servletPath = req.getServletPath();
 
		 System.out.println("#INFO " + new Date() + " - ServletPath :" + servletPath //
                + ", URL =" + req.getRequestURL());
		 
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
    @Override
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("LogFilter init!");
	}

}
