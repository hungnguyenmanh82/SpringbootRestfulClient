package com.example.client;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import com.example.client.model.Employee;

/**
 * Phải run với Springboot mới đc. run java app sẽ ko chạy đc (mặc dù bản chất nó là thư viện java)
 *
 */
public class App12_GET_Object {
	static final String URL_EMPLOYEES = "http://localhost:8080/employees";

	public static void main(String[] args) {

		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_XML }));
		// Request to return XML format
		headers.setContentType(MediaType.APPLICATION_XML);
		headers.set("my_other_key", "my_other_value");

		// HttpEntity<Employee[]>: To get result as Employee[].
		HttpEntity<Employee[]> entity = new HttpEntity<Employee[]>(headers);

		// RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		// Send request with GET method, and Headers.
		// ResponseEntity<Employee>: là response, ko liên quan tới request
		ResponseEntity<Employee[]> response = restTemplate.exchange(URL_EMPLOYEES, //
				HttpMethod.GET, entity, Employee[].class);

		HttpStatus statusCode = response.getStatusCode();
		System.out.println("Response Satus Code: " + statusCode);

		// Status Code: 200
		if (statusCode == HttpStatus.OK) {
			// Response Body Data
			Employee[] list = response.getBody();

			if (list != null) {
				for (Employee e : list) {
					System.out.println("Employee: " + e.getEmpNo() + " - " + e.getEmpName());
				}
			}
		}

	}
}
