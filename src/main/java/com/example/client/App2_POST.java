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
public class App2_POST {
	static final String URL_CREATE_EMPLOYEE = "http://localhost:8080/employee";

	public static void main(String[] args) {

		String empNo = "E11";

		Employee newEmployee = new Employee(empNo, "Tom", "Cleck");

		//================================== Header ========================
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_XML_VALUE);
		headers.setContentType(MediaType.APPLICATION_XML);

		RestTemplate restTemplate = new RestTemplate();

		// Data attached to the request.
		HttpEntity<Employee> requestBody = new HttpEntity<>(newEmployee, headers);

		// Send request with POST method.
		// ResponseEntity<Employee>: là response, ko liên quan tới request
		ResponseEntity<Employee> result = restTemplate.postForEntity(URL_CREATE_EMPLOYEE, requestBody, Employee.class);

		System.out.println("Status code:" + result.getStatusCode());

		// Code = 200.
		if (result.getStatusCode() == HttpStatus.OK) {
			Employee e = result.getBody();
			System.out.println("(Client Side) Employee Created: "+ e.getEmpNo());
		}

	}
}
