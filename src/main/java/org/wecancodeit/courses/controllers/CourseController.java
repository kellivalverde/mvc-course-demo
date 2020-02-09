package org.wecancodeit.courses.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wecancodeit.courses.exceptions.CourseNotFoundException;
import org.wecancodeit.courses.repositories.CourseRepository;

@RequestMapping("/show-courses")
@Controller
public class CourseController {

	@Resource
	private CourseRepository courseRepo;

	@GetMapping("") 
	public String findAllCourses(Model model) {

		model.addAttribute("coursesModel", courseRepo.findAllCourses()); 
		return "courses-template"; // html file -- > Spring knows to go to my src/main/resources/templates + template name + .html
	}

	//new way with Path Variable - better for APIs
	@GetMapping("/{id}")
	public String findOneCourse(@PathVariable(value = "id") Long id, Model model) throws CourseNotFoundException{
		
		if(courseRepo.findOneCourse(id) == null) {
			throw new CourseNotFoundException();
		}
		model.addAttribute("courseModel", courseRepo.findOneCourse(id));
		return "course-template";
	}

	
	
	
	
	
	
	
	
// Request Parameter style
//	
//	@GetMapping("/show-single-course")
//	public String findOneCourse(@RequestParam Long id, Model model) throws CourseNotFoundException{
//		
//		if(courseRepo.findOneCourse(id) == null) {
//			throw new CourseNotFoundException();
//		}
//		model.addAttribute("courseModel", courseRepo.findOneCourse(id));
//		return "course-template";
//	}
	
	
}
