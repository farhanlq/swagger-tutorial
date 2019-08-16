package com.demo.swagger.controller;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.demo.swagger.model.Employee;
import com.demo.swagger.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
public class EmployeeControllerTest {

	private MockMvc mockMvc;
	
	private static List<Employee> employeeList;
	
	@InjectMocks
	EmployeeController employeeController;
	
	@Mock
	EmployeeRepository employeeRepository;
	
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
		 employeeList = Arrays.asList(new Employee(3, "abc", "cdfd", "abcs@ymail.com"),new Employee(4, "lmn", "jkl", "lmn@gmail.com"));
	}

	@Test
	public void testGetAllEmployees() throws Exception {
		
		Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);
		assertNotNull(employeeController.getAllEmployees());
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	public void testGetEmployeeById() throws Exception {
		Optional<Employee> employee = Optional.of(new Employee(3, "abc", "cdfd", "abcs@ymail.com"));
		Mockito.when(employeeRepository.findById(employee.get().getId())).thenReturn(employee);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/3")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("abc")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("cdfd")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.emailId", Matchers.is("abcs@ymail.com")));
		Mockito.verify(employeeRepository).findById(3l);
	}
	
	
	
	

	
	
}
