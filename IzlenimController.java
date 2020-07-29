package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IzlenimController {

	@Autowired
	IzlenimRepo izRepo;
	@Autowired
	FilmRepo filmRepo;
	@Autowired
	UserService userService;

	@RequestMapping("/filmdetay")
	public ModelAndView filmdetay(String imdbid) {

		ModelAndView mw = new ModelAndView("filmdetay");
		List<Film> filmler = filmRepo.findByimdbid(imdbid);	
		mw.addObject("filmler", filmler);
		return mw;
	}

	@RequestMapping("/izlenimekle")
	public ModelAndView izlenimekle(@ModelAttribute Izlenim izlenim, Authentication aut) {

		ModelAndView mw = new ModelAndView("filmdetay");
		Izlenim iz = new Izlenim();
		String mesaj = "İzlediniz";
		Kullanici kul = userService.findByUsername(aut.getName());

		iz.setKulid(kul.getKulid());
		iz.setImdbid(izlenim.getImdbid());
		iz.setKulrating(izlenim.getKulrating());
		iz.setIzlenimtarihi(izlenim.getIzlenimtarihi());

		List<Izlenim> izler = izRepo.findBykulid(kul.getKulid());
		Boolean var = false;
		
		
		for (Izlenim izl : izler) {
			if (izl.getImdbid().equals(iz.getImdbid())) {
				var = true;
				break;
			}
		}

		if (var) {
			izRepo.setIzlenimBykulid(iz.getKulid(), iz.getKulrating(), iz.getIzlenimtarihi());
			mesaj = "İzlenim güncellendi.";
		} else
			izRepo.save(iz);
		
		
		mw.addObject("mesaj", mesaj);
		return mw;
	}

}
