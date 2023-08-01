package com.employeeMangementServices;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.employeeMangementServices.model.Employee;
import com.employeeMangementServices.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@WebMvcTest
class employeeRestControllerTest {

	private static final String URI = "/api/v1/employees";

	private static final String LASTNAME = "Sondur";

	private static final String FIRSTNAME = "Sagar";

	private static final String MAIL = "sagarsakri@gmail.com";
	
	
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private EmployeeRepository repo;

	@Test
	public void getAllEmployees() throws Exception {
		Employee emp = build();

		List<Employee> l = Arrays.asList(emp);
		when(repo.findAll()).thenReturn(l);
//
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(URI)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//		String content = mvcResult.getResponse().getContentAsString();
//		assertTrue(content.length()>0);
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		mvc.perform(get(URI)).andExpect(status().isOk()).andExpect(
				content().json(objectWriter.writeValueAsString(l)));

	}
	
	@Test
	public void testCreateEmployee() throws Exception {
		Employee employee = build();
		when(repo.save(any())).thenReturn(employee);
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		mvc.perform(post(URI).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectWriter.writeValueAsString(employee))).andExpect(status().isOk())
				.andExpect(content().json(objectWriter.writeValueAsString(employee)));
				
	}
	@Test
	public void testUpdateEmployee() throws Exception {
		Employee employee = build();
		when(repo.save(any())).thenReturn(employee);
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		mvc.perform(put(URI).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectWriter.writeValueAsString(employee))).andExpect(status().isOk())
				.andExpect(content().json(objectWriter.writeValueAsString(employee)));
				
	}
	
	@Test
	public void testDeleteEmployee() throws Exception {
		
		doNothing().when(repo).delete(build());
		mvc.perform(delete(URI)).andExpect(status().isOk());
	}
	

	public Employee build() {
		Employee emp = new Employee();

		emp.setEmailId(MAIL);
		emp.setFirstName(FIRSTNAME);
		emp.setLastName(LASTNAME);
		return emp;
	}

}
