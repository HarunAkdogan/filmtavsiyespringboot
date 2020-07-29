package com.example.demo;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private IzlenimRepo izRepo;

	@Autowired
	private FilmRepo filmRepo;
	
	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new Kullanici());

		return "registration";
	}

	@PostMapping("/registration")
	public String registration(@ModelAttribute("userForm") Kullanici userForm, BindingResult bindingResult) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		userService.save(userForm);

		securityService.autoLogin(userForm.getUsername(), userForm.getPasswordconfirm());

		return "redirect:/index";
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@GetMapping({ "/", "/index" })
	public String welcome(Model model,Authentication authentication) {
		Kullanici kul = userService.findByUsername(authentication.getName());
		model.addAttribute("user",kul);
		List<Film> izlenimler = filmRepo.trendGetir();
		List<Film> rastfilm = filmRepo.rastfilm();
		model.addAttribute("izlenimler", izlenimler);
		model.addAttribute("rastfilm", rastfilm);
		
		return "index";
	}
	
	@GetMapping({ "/profil" })
	public String profil(Model model,Authentication authentication) {
		Kullanici kul = userService.findByUsername(authentication.getName());
		List<Izlenim> izlenenler = izRepo.findBykulid(kul.getKulid());
		model.addAttribute("izlenenler", izlenenler);
		model.addAttribute("user",kul);
		return "profil";
	}
	
	
	@RequestMapping("/filmekle")
	public String filmekleyin() {
		return "filmekle";
	}
}