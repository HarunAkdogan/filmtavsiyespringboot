package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RatingController {

	@Autowired
	RatingRepo ratRepo;
	@Autowired
	UserService userService;
	@Autowired
	IzlenimRepo izRepo;
	@Autowired
	LinkRepo linkRepo;
	@Autowired
	TavsiyeRepo tavRepo;
	@Autowired
	FilmRepo flmRepo;
	@Autowired
	MovieRepo movieRepo;

	@RequestMapping("/getrating")
	public ModelAndView getrating(int ratingid) {

		ModelAndView mw = new ModelAndView("showrating");
		Rating rt = ratRepo.findById(ratingid).orElse(new Rating());

		System.out.println(rt.movieid);

		mw.addObject(rt);
		return mw;
	}

	@RequestMapping("/index")
	public String goindex() {
		return "index";
	}

	@RequestMapping("/home")
	public String gohome() {
		return "home";
	}

	@RequestMapping("/filmi_izleyenler")
	public ModelAndView filmi_izleyenler(int movie1, int movie2, int rat1, int rat2) {

		// ModelAndView mw = new ModelAndView("showRating");

		List<Rating> users1 = ratRepo.findBymovieid(movie1);
		List<Rating> users2 = ratRepo.findBymovieid(movie2);
		List<Integer> userIds = new ArrayList<>();
		HashMap<Integer, Integer> moviesRatings = new HashMap<Integer, Integer>();

		for (Rating rating1 : users1) {
			for (Rating rating2 : users2) {
				if (rating2.userid == rating1.userid && Math.abs(rating1.rating - rat1) <= 1
						&& Math.abs(rating2.rating - rat2) <= 1)

					userIds.add(rating2.userid);
			}
		}

		for (Integer userId : userIds) {
			List<Rating> rt = ratRepo.findByuserid(userId);
			for (Rating r : rt) {
				if (r.rating > 3) {
					if (moviesRatings.containsKey(r.movieid))
						moviesRatings.put(r.movieid, moviesRatings.get(r.movieid) + 1);
					else
						moviesRatings.put(r.movieid, 1);
				}
			}

		}

		System.out.println("Kullanıcılar:");
		for (Integer rating : userIds) {
			System.out.println(rating);
		}
		System.out.println("--------------------------------");

		System.out.println("Öneri Filmler:");

		Set<Entry<Integer, Integer>> set = moviesRatings.entrySet();
		List<Entry<Integer, Integer>> list = new ArrayList<Entry<Integer, Integer>>(set);
		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		System.out.println(list.size());

		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getValue() < ((float) userIds.size() * 0.1f)) {
				list.remove(i);
				i--;
			}

		}

		for (Entry<Integer, Integer> entry : list) {

			System.out.println(entry.getKey() + " , " + entry.getValue());

		}
		System.out.println("--------------------------------");

		System.out.println(list.size());

		return new ModelAndView("showrating", "list", list);
	}

	@RequestMapping("/tavsiyeler")
	public ModelAndView tavsiyeler(Authentication aut) {

		ModelAndView mv = new ModelAndView("tavsiyeler");
		Kullanici kul = userService.findByUsername(aut.getName());
		List<Izlenim> rast2 = null;
		List<Link> linkler1 = null;
		List<Link> linkler2 = null;

		for (int i = 0; i < 20; i++) {
			rast2 = izRepo.rastgeleIki(kul.getKulid());
			linkler1 = linkRepo.findByimdbid(rast2.get(0).getImdbid());
			linkler2 = linkRepo.findByimdbid(rast2.get(1).getImdbid());

			if (!linkler1.isEmpty() && !linkler2.isEmpty())
				break;
		}

		System.out.println(rast2.get(0).getImdbid());
		System.out.println(rast2.get(1).getImdbid());

		if (!linkler1.isEmpty() && !linkler2.isEmpty()) {
			// rast2.get(1).getImdbid()
			// linkler1.get(0).getMovieid()
			List<Rating> users1 = ratRepo.findBymovieid(linkler1.get(0).getMovieid());
			List<Rating> users2 = ratRepo.findBymovieid(linkler2.get(0).getMovieid());

			List<Integer> userIds = new ArrayList<>();
			HashMap<Integer, Integer> moviesRatings = new HashMap<Integer, Integer>();

			for (Rating rating1 : users1) {
				for (Rating rating2 : users2) {
					if (rating2.userid == rating1.userid && Math.abs(rating1.rating - rast2.get(0).getKulrating()) <= 1
							&& Math.abs(rating2.rating - rast2.get(1).getKulrating()) <= 1)

						userIds.add(rating2.userid);
				}
			}

			for (Integer userId : userIds) {
				List<Rating> rt = ratRepo.findByuserid(userId);
				for (Rating r : rt) {
					if (r.rating > 3) {
						if (moviesRatings.containsKey(r.movieid))
							moviesRatings.put(r.movieid, moviesRatings.get(r.movieid) + 1);
						else
							moviesRatings.put(r.movieid, 1);
					}
				}

			}

			System.out.println("Kullanıcılar:");
			for (Integer rating : userIds) {
				System.out.println(rating);
			}
			System.out.println("--------------------------------");

			System.out.println("Öneri Filmler:");

			Set<Entry<Integer, Integer>> set = moviesRatings.entrySet();
			List<Entry<Integer, Integer>> list = new ArrayList<Entry<Integer, Integer>>(set);
			Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
				public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
					return o2.getValue().compareTo(o1.getValue());
				}
			});

			for (int i = 0; i < list.size(); i++) {

				if (list.get(i).getValue() < ((float) userIds.size() * 0.1f)) {
					list.remove(i);
					i--;
				}

			}

			for (int i = 0; i < list.size(); i++) {

				if (i > 19) {
					list.remove(i);
					i--;
				}
			}

			System.out.println(list.size());

			for (Entry<Integer, Integer> entry : list) {

				System.out.println(entry.getKey() + " , " + entry.getValue());

				Link link = linkRepo.findById(entry.getKey()).orElse(new Link());
				List<Tavsiyefilm> tavsiye = tavRepo.tavBul(kul.getKulid(), link.getImdbid());

				Tavsiyefilm tf = new Tavsiyefilm();
				tf.setImdbid(link.getImdbid());
				tf.setKulid(kul.getKulid());
				tf.setTavmiktari(entry.getValue());
				tf.setMovieid(link.getMovieid());

				if (tavsiye.isEmpty()) {
					tavRepo.save(tf);
				} else if (tavsiye.get(0).getTavmiktari() > entry.getValue()) {
					tavRepo.delete(tavsiye.get(0));
					tavRepo.save(tf);
				}
			}

			System.out.println("--------------------------------");

			System.out.println(list.size());

			mv.addObject("list", list);
			
		}else
			mv.addObject("list", null);
		
		List<Tavsiyefilm> flms = tavRepo.findBykulid(kul.getKulid());
		
		//List<Movie> flms = new ArrayList<Movie>();
		//for(Tavsiyefilm t : tumtavsiyeler) {
		//	flms.add(movieRepo.findBymovieid(linkRepo.findByimdbid(t.getImdbid()).get(0).getMovieid()).get(0)); }
		
			
			mv.addObject("flms", flms);
		return mv;

	}

}
