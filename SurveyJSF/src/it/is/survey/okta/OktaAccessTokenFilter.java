package it.is.survey.okta;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okta.jwt.JoseException;
import com.okta.jwt.JwtHelper;
import com.okta.jwt.JwtVerifier;

//@WebFilter(urlPatterns = "/api/*")
@WebFilter(urlPatterns = "/rest/*")
public  class OktaAccessTokenFilter implements Filter {

    private JwtVerifier jwtVerifier;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    try {	
    JwtHelper jwtHelper= new JwtHelper();
    jwtHelper.setIssuerUrl("https://dev-315516.oktapreview.com/oauth2/default");
    jwtHelper.setAudience("api://default");
    
        
            this.jwtVerifier = jwtHelper.build();

        } catch (Exception e) {
            throw new ServletException("Failed to create JWT Verifier", e);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                                                 throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        Enumeration<String> es= httpRequest.getHeaderNames();
        String nameHeader="";
        String valueHeader="";
        System.out.println("2 In filtro per verifica autenticazione");
       
        HttpServletRequest httprequest = (HttpServletRequest) request;
        
        Cookie[] cookieList=httprequest.getCookies();
        
        
        
        while (es.hasMoreElements()){
        	nameHeader=es.nextElement();
        	valueHeader=httpRequest.getHeader(nameHeader);
        	
        }
        String authHeader = httpRequest.getHeader("Authorization");
        
        
        System.out.println("In doFilter 1: authHeader");
        if (authHeader != null
                && !authHeader.isEmpty()
                && authHeader.startsWith("Bearer ")) {
        	
            String jwtString = authHeader.replaceFirst("^Bearer ", "");

            try {
            	
                jwtVerifier.decodeAccessToken(jwtString);
                chain.doFilter(request, response);
                return;

            } catch (JoseException e) {
                httpRequest.getServletContext().log("Failed to decode Access Token", e);
            }
        }
    
        httpResponse.setHeader("WWW-Authenticate", "Bearer realm=\"Okta-Servlet-Example\"");
        httpResponse.sendError(401, "Unauthorized");
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}