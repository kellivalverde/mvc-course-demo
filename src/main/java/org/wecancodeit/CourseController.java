package org.wecancodeit;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourseController {

	@Resource
	private CourseRepository courseRepo;

	@GetMapping("/show-courses") // matches test  //grabs end point
	public String findAllCourses(Model model) {

		model.addAttribute("coursesModel", courseRepo.findAllCourses()); // add attribute here after @Test shouldAddAllCoursesToTheModel -- > grabs the info --> places into the template
		return "courses-template"; // html file -- > Spring knows to go to my src/main/resources/templates + template name + .html
	}

	@GetMapping("/show-single-course")
	public String findOneCourse(@RequestParam Long id, Model model) throws CourseNotFoundException{
		
		if(courseRepo.findOneCourse(id) == null) {
			throw new CourseNotFoundException();
		}
		
		
		model.addAttribute("courseModel", courseRepo.findOneCourse(id));
		return "course-template";
	}

}
