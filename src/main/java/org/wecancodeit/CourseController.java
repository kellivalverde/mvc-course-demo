package org.wecancodeit;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {

	@Resource
	private CourseRepository courseRepo;

	@GetMapping("/show-courses") // matches test  //grabs end point
	public String findAllCourses(Model model) {

		// add attribute here after @Test shouldAddAllCoursesToTheModel -- > grabs the info --> places into the template
		model.addAttribute("coursesModel", courseRepo.findAllCourses());
		return "courses-template"; // html file -- > Spring knows to go to my src/main/resources/templates + template name + .html
	}

	@GetMapping("/show-single-course")

	public String findOneCourse() {
		return "course-template";
	}

}
