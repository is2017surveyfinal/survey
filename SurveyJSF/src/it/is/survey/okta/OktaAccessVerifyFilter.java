package it.is.survey.okta;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/*" })
public class OktaAccessVerifyFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
        System.out.println("1 In filtro per verifica pagina");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String requestedUri = httpRequest.getRequestURI();

		if (!FilterMatcher.match(requestedUri)) {

			chain.doFilter(request, response);
			return;

		} else {
			if (FilterMatcher.match(requestedUri)) {

				try {


					String group = ((User) (httpRequest.getSession().getAttribute("userSurvey"))).getGroups();
					if (group.indexOf("Administrator") != -1) {
						chain.doFilter(request, response);
					} else {
						httpResponse.setHeader("WWW-Authenticate", "Bearer realm=\"Okta-Servlet-Example\"");
						httpResponse.sendError(401, "Unauthorized");

					}

				} catch (Exception e) {
					httpResponse.setHeader("WWW-Authenticate", "Bearer realm=\"Okta-Servlet-Example\"");
					httpResponse.sendError(401, "Unauthorized");
				}
				return;
			}

		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
}
