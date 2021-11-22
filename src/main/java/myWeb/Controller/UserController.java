package myWeb.Controller;

import myWeb.Model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

	@RequestMapping(value = "/user/hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("Welcome to my web page.");
		messages.add("Below are your available actions:");
		model.addAttribute("messages", messages);
		return "user/hello";
	}

	@GetMapping("/user/showUser")
	public ModelAndView showUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/showUser");
		modelAndView.addObject("user", user);
		return modelAndView;
	}

}