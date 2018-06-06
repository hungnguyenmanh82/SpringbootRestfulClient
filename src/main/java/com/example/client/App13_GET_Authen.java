package com.example.client;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

/**
 * Phải run với Springboot mới đc. run java app sẽ ko chạy đc (mặc dù bản chất nó là thư viện java)
 *
 */
public class App13_GET_Authen {
	public static final String USER_NAME = "tom";
	public static final String PASSWORD = "123";

	static final String URL_EMPLOYEES = "http://localhost:8080/employees";

	public static void main(String[] args) {

		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();

		//================================== Authentication ========

		String auth = USER_NAME + ":" + PASSWORD;
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodedAuth);
		headers.set("Authorization", authHeader);

		//========================================================= 
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		// Request to return JSON format
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("my_other_key", "my_other_value");

		// HttpEntity<String>: To get result as String.
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		// RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		// Send request with GET method, and Headers.
		// ResponseEntity<Employee>: là response, ko liên quan tới request
		ResponseEntity<String> response = restTemplate.exchange(URL_EMPLOYEES, //
				HttpMethod.GET, entity, String.class);

		HttpStatus statusCode = response.getStatusCode();
		System.out.println("Response Satus Code: " + statusCode);

		String result = response.getBody();

		System.out.println(result);
	}
}
