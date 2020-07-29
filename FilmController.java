package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FilmController {

	@Autowired
	FilmRepo filmRepo;

	@RequestMapping("/showmov")
	public ModelAndView showmov(@ModelAttribute("form") Film filmpar) {
		
		ModelAndView mw = new ModelAndView("showrating");
		Boolean var = false;
		String message = "Film kaydedildi";
		filmpar.setImdbid(filmpar.getImdbid().substring(2));
		
		for (Film flm : filmRepo.findByimdbid(filmpar.getImdbid())) {
			if (flm.getImdbid().equals(filmpar.getImdbid())) {
				var = true;
				break; 
			}
		}

		if (!var) 
			filmRepo.save(filmpar);			
		else
			message = "Film zaten mevcut!";
		
		mw.addObject("message", message);
		return mw;
	}
	
	@RequestMapping("/filmsec")
	public ModelAndView filmbul(String title) {
		ModelAndView mw = new ModelAndView();
		List<Film> filmler = filmRepo.findBytitle(title);
		mw.addObject("filmler", filmler);
		return mw;
	}

}
