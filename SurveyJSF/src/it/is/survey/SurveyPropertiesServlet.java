package it.is.survey;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SurveyPropertiesServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		Properties properties = SurveyProperties.getSurveyProperties();

		Enumeration e = properties.propertyNames();

		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			out.println(key + " -- " + properties.getProperty(key));
		}

	}

}
