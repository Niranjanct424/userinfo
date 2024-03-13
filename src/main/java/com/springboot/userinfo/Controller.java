package com.springboot.userinfo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
    @GetMapping("/talk")
    public String simplseAPI() {
    	return "Hi";
    }
    
}
