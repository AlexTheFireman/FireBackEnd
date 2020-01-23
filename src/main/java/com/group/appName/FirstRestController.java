package com.group.appName;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class FirstRestController {
	
	//@GetMapping(value = "/1", produces = "Content-type=application/json")
	@RequestMapping(value = "/1", method = RequestMethod.GET, produces = "application/json")
	public String stub() throws IOException {
		Convert convert = new Convert();

		String s = convert.start();
		return s;
	}
}
