package com.css862.resources;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/k8s")
public class HelloWorld {

	@RequestMapping(method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, value = "/helloworld")
	@ResponseBody
	public String sayHelloWorld() {
		System.out.println("Hello world example...");
		return "Hello World!!!";
	}


}
