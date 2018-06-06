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
public class App4_DELETE {
	static final String URL_UPDATE_EMPLOYEE = "http://localhost:8080/employee";
	static final String URL_EMPLOYEE_PREFIX = "http://localhost:8080/employee";

	public static void main(String[] args) {

		
		RestTemplate restTemplate = new RestTemplate();

		//================================ DELETE ===========================================
		// URL with URI-variable
		String resourceUrl = "http://localhost:8080/employee/{empNo}";

		Object[] uriValues = new Object[] { "E01" };

		// Send request with DELETE method.
		restTemplate.delete(resourceUrl, uriValues);

		//==============================================================================
		// đây là GET dùng với mục đích kiểm tra giá trị vừa thay đổi
		Employee e = restTemplate.getForObject(resourceUrl, Employee.class);

		if (e != null) {
			System.out.println("(Client side) Employee after delete: ");
			System.out.println("Employee: " + e.getEmpNo() + " - " + e.getEmpName());
		} else {
			System.out.println("Employee not found!");
		}
	}
}
