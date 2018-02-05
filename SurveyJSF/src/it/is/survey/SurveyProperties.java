package it.is.survey;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SurveyProperties {

	private static Properties properties = null;
	private static SurveyProperties surveyProperties;

	private SurveyProperties() {

		InputStream input = this.getClass().getClassLoader().getResourceAsStream("/survey.properties");
		try {
			properties.load(input);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static synchronized Properties getSurveyProperties() {

		if (surveyProperties == null) {
			properties = new Properties();
			surveyProperties = new SurveyProperties();
		}
		return properties;
	}

}