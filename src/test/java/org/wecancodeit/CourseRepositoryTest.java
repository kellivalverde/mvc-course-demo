package org.wecancodeit;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;

class CourseRepositoryTest {

	@Resource
	private CourseRepository underTest;
	private Course courseOne = new Course(1L, "course name", "description");
	private Course courseTwo = new Course(2L, "course name", "description");

	@Test
	public void shouldReturnCourseOneById() {
		underTest = new CourseRepository(courseOne);
		Course foundCourse = underTest.findOneCourse(1L);
		assertEquals(foundCourse, courseOne);
	}

	@Test
	public void shouldReturnCourseTwoById() {
		underTest = new CourseRepository(courseTwo);
		Course foundCourse = underTest.findOneCourse(2L);
		assertEquals(foundCourse, courseTwo);

	}

	@Test
	public void shouldFindAllCourses() {
		underTest = new CourseRepository(courseOne, courseTwo);
		Collection<Course> foundCourses = underTest.findAllCourses();
		// assertEquals(foundCourses.size(), 2);
		assertTrue(foundCourses.contains(courseOne));
		assertTrue(foundCourses.contains(courseTwo));


	}

}
