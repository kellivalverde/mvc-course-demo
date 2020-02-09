package org.wecancodeit;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays; //BE CAREFUL WITH THIS IMPORT

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(CourseController.class)

class CourseControllerMockMVCTest {

	@Autowired
	private MockMvc mockMvc; //fake user object that grabs end points in our application
	
	//need to mock-up some info so we don't get a return null
	@Mock
	private Course courseOne;
	
	@Mock
	private Course courseTwo;
	
	@MockBean
	private CourseRepository courseRepo; //allows us to inject the mocks(above)
	
	

	@Test
	public void shouldGetStatusOfOKWhenNavigatingToAllCourses() throws Exception {
		this.mockMvc.perform(get("/show-courses")).andExpect(status().isOk())  //"user" tries this end point 
		.andExpect(view().name("courses-template"));

	}

	@Test
	public void shouldGetStatusOfOKWhenNavigatingToSingleCoursePage() throws Exception {
		this.mockMvc.perform(get("/show-single-course")).andExpect(status().isOk())
		.andExpect(view().name("course-template"));
	}
	
	@Test
	public void shouldAddAllCoursesToTheModel() throws Exception {
		when(courseRepo.findAllCourses()).thenReturn(Arrays.asList(courseOne, courseTwo)); //use a when-then return to mock database -- common for mocking information
				this.mockMvc.perform(get("/show-courses"))
				.andExpect(model().attribute("coursesModel", hasSize(2)));
				//failed --> build in controller
	}
}
