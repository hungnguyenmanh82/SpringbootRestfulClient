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
public class App3_PUT {
    static final String URL_UPDATE_EMPLOYEE = "http://localhost:8080/employee";
    static final String URL_EMPLOYEE_PREFIX = "http://localhost:8080/employee";
 
    public static void main(String[] args) {
 
        String empNo = "E01";
 
        Employee updateInfo = new Employee(empNo, "Tom", "Cleck");
 
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
 
        RestTemplate restTemplate = new RestTemplate();
 
        // Data attached to the request.
        HttpEntity<Employee> requestBody = new HttpEntity<>(updateInfo, headers);
 
        // Send request with PUT method.
        // ResponseEntity<Employee>: là response, ko liên quan tới request
        ResponseEntity<Employee> response = restTemplate.exchange(URL_UPDATE_EMPLOYEE, HttpMethod.PUT, requestBody, Employee.class);
        
        HttpStatus statusCode = response.getStatusCode();
		System.out.println("Response Satus Code: " + statusCode);
		
        //==============================================================================
        // đây là GET dùng với mục đích kiểm tra giá trị vừa thay đổi
        String resourceUrl = URL_EMPLOYEE_PREFIX + "/" + empNo;
        Employee e = restTemplate.getForObject(resourceUrl, Employee.class);
 
        if (e != null) {
            System.out.println("(Client side) Employee after update: ");
            System.out.println("Employee: " + e.getEmpNo() + " - " + e.getEmpName());
        }
    }
}
