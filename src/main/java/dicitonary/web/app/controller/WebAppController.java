package dicitonary.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebAppController {

	
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
}
