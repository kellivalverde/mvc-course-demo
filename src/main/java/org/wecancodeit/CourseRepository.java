package org.wecancodeit;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class CourseRepository {

	private Map<Long, Course> courseList = new HashMap<>();
	private Course courseOne = new Course(1L, "course one", "description of course one");
	private Course courseTwo = new Course(2L, "course two", "description of course two");

	//real database
	public CourseRepository() {
		courseList.put(courseOne.getId(), courseOne);
		courseList.put(courseTwo.getId(), courseTwo);
	}
	
	// uses varargs (variable argument)for testing purposes to take on the necessary courses
	public CourseRepository(Course... courses) {
		// was this.courseOne = courseOne;
		for (Course course : courses) {
			// create a Map for collecting courses
			courseList.put(course.getId(), course); // grabs each singular class and adds it to the database
		}
	}

	public Course findOneCourse(long id) {
		 //return courseOne;  needs to be more flexible
		return courseList.get(id);
	}

	public Collection<Course> findAllCourses() {
		return courseList.values();
	}

}
