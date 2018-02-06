package it.is.survey.okta;

//Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jose4j.jwk.HttpsJwks;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.nimbusds.oauth2.sdk.ParseException;
import com.okta.jwt.JoseException;
import com.okta.jwt.Jwt;
import com.okta.jwt.JwtHelper;
import com.okta.jwt.JwtVerifier;

import java.security.Key;

import java.util.Map;
import java.util.HashMap;

//Extend HttpServlet class
public class callback extends HttpServlet {

	private String message;
	private User user = new User();
	private Map<String, Key> CACHED_KEYS = new HashMap<String, Key>();

	public void init() throws ServletException {

		user.setEmail("Not initialized");
		user.setGroups("Not initialized");
		message = "Hello World";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cookieState = "";
		String cookieNonce = "";

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("message");
		Cookie[] cookieList = request.getCookies();

		for (int i = 0; i < cookieList.length; i++) {
			System.out.println("Name: " + cookieList[i].getName() + " value:" + cookieList[i].getValue());
			if (cookieList[i].getName().equals("okta-oauth-state"))
				cookieState = cookieList[i].getValue();
			if (cookieList[i].getName().equals("okta-oauth-nonce"))
				cookieNonce = cookieList[i].getValue();

		}

		String queryString = null;
		try {
			String code = request.getParameter("code");
			queryString = getTokenUri(code);

		} catch (UnsupportedEncodingException e) {
			e.getMessage();// System.out.println("Eccezione:"+e);
		}

		String clientId = "0oacrk32l7wo0Pgse0h7";
		String clientSecret = "CBuCvXYcPILMm2mANHKHDNWWfKOZYvGKNAKfG7er";
		String tokenEndpoint = "https://dev-315516.oktapreview.com/oauth2/default/v1/token?";
		byte[] encodedAuth = Base64.encodeBase64((clientId + ":" + clientSecret).getBytes());

		CloseableHttpClient httpClient = HttpClients.custom().disableCookieManagement().build();

		Unirest.setHttpClient(httpClient);
		HttpResponse<JsonNode> jsonResponse = null;
		try {
			System.out.println("*****prima di post");
			jsonResponse = Unirest.post(tokenEndpoint + queryString).header("user-agent", null)
					.header("content-type", "application/x-www-form-urlencoded")
					.header("authorization", "Basic " + new String(encodedAuth)).header("connection", "close")
					.header("accept", "application/json").asJson();
		} catch (Exception e) {
			e.getMessage();
		}

		JsonNode tokens = jsonResponse.getBody();

		String idToken = tokens.getObject().get("id_token").toString();
		out.println("idToken:" + idToken);
		String accessToken = tokens.getObject().get("access_token").toString();
		out.println("accessToken:" + accessToken);
		Map claims = null;
		try {
			claims = validateToken(idToken, cookieNonce);
		} catch (Exception e) {
			e.getMessage();
		}
		this.validateAccessToken(accessToken);
		request.getSession().setAttribute("userSurvey", user);
		user.setClaims(claims);

		HttpSession session = request.getSession();
		session.setAttribute("user", user.getEmail());

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/survey-list.jsf");
		dispatcher.forward(request, response);

	}

	private Map validateToken(String idToken, String nonce) throws Exception {
		Map decoded;
		Key key = fetchJwk(idToken);

		// Allow for 5 minute clock skew
		int clock_skew = 300;

		JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireExpirationTime()
				.setAllowedClockSkewInSeconds(clock_skew).setExpectedAudience("0oacrk32l7wo0Pgse0h7")
				.setExpectedIssuer("https://dev-315516.oktapreview.com/oauth2/default").setVerificationKey(key).build();

		// Validate the JWT and process it to the Claims
		JwtClaims jwtClaims = jwtConsumer.processToClaims(idToken);

		String claimsNonce = jwtClaims.getClaimsMap().get("nonce").toString();

		if (!claimsNonce.equals(nonce)) {
			throw new Exception("Claims nonce does not mach cookie nonce");
		}

		// Verify token was not issued in the future (accounting for clock skew)
		NumericDate current = NumericDate.now();
		current.addSeconds(clock_skew);

		if (jwtClaims.getIssuedAt().isAfter(current)) {
			throw new Exception("invalid iat claim");
		}

		decoded = jwtClaims.getClaimsMap();
		return decoded;
	}

	private String getTokenUri(String code) throws UnsupportedEncodingException {
		String redirectUri = "http://35.205.111.61/SurveyJSF/callback";

		code = convertToUTF8(code);
		redirectUri = convertToUTF8(redirectUri);

		return "grant_type=authorization_code&code=" + code + "&redirect_uri=" + redirectUri;

	}

	private void validateAccessToken(String accessToken) {

		JwtVerifier jwtVerifier;
		try {
			jwtVerifier = new JwtHelper().setIssuerUrl("https://dev-315516.oktapreview.com/oauth2/default")
					.setAudience("api://default").build();

			Jwt jwt = jwtVerifier.decodeAccessToken(accessToken);
			user.setEmail(jwt.getClaims().get("sub").toString());
			user.setGroups(jwt.getClaims().get("Groups").toString());

		} catch (ParseException | IOException e) {

			e.printStackTrace();
		} catch (JoseException e) {

			e.printStackTrace();
		}

	}

	public static String convertToUTF8(String s) {
		String out = null;
		try {
			out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
		} catch (java.io.UnsupportedEncodingException e) {
			return null;
		}
		return out;
	}

	private Key fetchJwk(String idToken) throws JoseException, IOException, Exception {
		JsonWebSignature jws = new JsonWebSignature();
		jws.setCompactSerialization(idToken);
		String keyID = jws.getKeyIdHeaderValue();
		String keyAlg = jws.getAlgorithmHeaderValue();

		if (CACHED_KEYS.get(keyID) != null) {
			return CACHED_KEYS.get(keyID);
		}

		String jwksUri = "https://dev-315516.oktapreview.com/oauth2/default/v1/keys";
		HttpsJwks httpJkws = new HttpsJwks(jwksUri);

		for (JsonWebKey key : httpJkws.getJsonWebKeys()) {
			if (!keyAlg.equals(key.getAlgorithm())) {
				throw new Exception("invalid algorithm");
			}
			CACHED_KEYS.put(key.getKeyId(), key.getKey());
		}

		if (CACHED_KEYS.get(keyID) == null) {
			return null; // No Key found
		}
		return CACHED_KEYS.get(keyID);
	}

	private String send401(HttpServletResponse resp, String message) {
		try {
			resp.sendError(401, message);
			return null;
		} catch (IOException e) {
			return e.getMessage();
		}
	}

	public void destroy() {

	}
}