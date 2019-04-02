package com.restservice.example.ApplicationRestService;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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