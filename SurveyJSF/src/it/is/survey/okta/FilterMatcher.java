package it.is.survey.okta;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterMatcher {

	public static boolean match(String uri) {

		boolean railCrossing = false;

		if (uri.indexOf(".jsf") != -1) {
			railCrossing = true;
		}
		if (uri.indexOf(".xhtml") != -1) {
			railCrossing = true;
		}
		if (uri.indexOf(".faces") != -1) {
			railCrossing = true;
		}

		return railCrossing;
	}

}
