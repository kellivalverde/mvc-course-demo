package org.wecancodeit.courses.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Bad Request! Course not found!")

public class CourseNotFoundException extends Exception {

}
