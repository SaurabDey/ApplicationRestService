package com.restservice.example.ApplicationRestService;

import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//https://dzone.com/articles/spring-boot-restful-web-service-complete-example

@SpringBootApplication
public class ApplicationRestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationRestServiceApplication.class, args);
	}
}
@RestController
class MessageRestController {
 
    @RequestMapping(value= {"/hello"}, method= {RequestMethod.GET}, produces= {MediaType.APPLICATION_JSON_VALUE})
    String getMessage(@RequestParam(value = "name",defaultValue="World") String name) {
        String rsp = "Hi " + name + " : responded on - " + new Date();
        System.out.println(rsp);
        return rsp;
    }
}

@RestController
class StudentRetrieveController {
  @RequestMapping(method= {RequestMethod.GET}, produces= {MediaType.APPLICATION_JSON_VALUE}, value= {"/student/allstudent"})
  @ResponseBody
  public List<Student> getAllStudents() {
  return StudentRegistration.getInstance().getStudentRecords();
  }
}

@RestController
class StudentRegistrationController {
  @RequestMapping(method= {RequestMethod.POST}, produces= {MediaType.APPLICATION_JSON_VALUE}, value= {"/register/student"})
  @ResponseBody
  public StudentRegistrationReply registerStudent(@RequestBody Student student) {
  System.out.println("In registerStudent");
        StudentRegistrationReply stdregreply = new StudentRegistrationReply();           
        StudentRegistration.getInstance().add(student);
        //We are setting the below value just to reply a message back to the caller
        stdregreply.setName(student.getName());
        stdregreply.setAge(student.getAge());
        stdregreply.setRegistrationNumber(student.getRegistrationNumber());
        stdregreply.setRegistrationStatus("Successful");
        return stdregreply;
}
}

@RestController
class StudentUpdateController {
@RequestMapping(method= {RequestMethod.PUT}, produces= {MediaType.APPLICATION_JSON_VALUE}, value= {"/update/student"})
@ResponseBody
public String updateStudentRecord(@RequestBody Student stdn) {
System.out.println("In updateStudentRecord");   
    return StudentRegistration.getInstance().upDateStudent(stdn);
}
}

@RestController
class StudentDeleteController {
@RequestMapping(method= {RequestMethod.DELETE}, produces= {MediaType.APPLICATION_JSON_VALUE}, value= {"/delete/student/{regdNum}"})
@ResponseBody
public String deleteStudentRecord(@PathVariable("regdNum") String regdNum) {
System.out.println("In deleteStudentRecord");   
    return StudentRegistration.getInstance().deleteStudent(regdNum);
}
}

