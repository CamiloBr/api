package filtros;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class Encabezado implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpServletRequest = ((HttpServletRequest)request);
        
        /*
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();

        if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                        System.out.println("Header: " + httpServletRequest.getHeader(headerNames.nextElement()));
                }
        }*/
        
        System.out.println("Token = "+httpServletRequest.getHeader("Authorization")); 
       
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
    
}
