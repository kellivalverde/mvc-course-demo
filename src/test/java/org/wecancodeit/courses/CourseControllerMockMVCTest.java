package org.wecancodeit.courses;

import static org.hamcrest.CoreMatchers.is;
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
import org.wecancodeit.courses.controllers.CourseController;
import org.wecancodeit.courses.models.Course;
import org.wecancodeit.courses.repositories.CourseRepository;

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
	public void shouldGetStatusOfOKWhenNavigatingToCourseOnePage() throws Exception {
		when(courseRepo.findOneCourse(1L)).thenReturn(courseOne);
		this.mockMvc.perform(get("/show-single-course?id=1")).andExpect(status().isOk())
		.andExpect(view().name("course-template"));
	}
	
	@Test
	public void shouldAddAllCoursesToTheModel() throws Exception {
		when(courseRepo.findAllCourses()).thenReturn(Arrays.asList(courseOne, courseTwo)); //use a when-then return to mock database -- common for mocking information
				this.mockMvc.perform(get("/show-courses"))
				.andExpect(model().attribute("coursesModel", hasSize(2)));
	}

	@Test
	public void shouldAddSingleCoursesToTheModel() throws Exception {
		when(courseRepo.findOneCourse(1L)).thenReturn(courseOne); 
				this.mockMvc.perform(get("/show-single-course?id=1"))
				.andExpect(model().attribute("courseModel", is(courseOne)));
	}

	@Test
	public void shouldReturnNotFoundForBadRequest() throws Exception {
		Long badId = 5L;
		when(courseRepo.findOneCourse(badId)).thenReturn(null);
		this.mockMvc.perform(get("/show-single-course?id=5"))
		.andExpect(status().isNotFound());
	}











}
