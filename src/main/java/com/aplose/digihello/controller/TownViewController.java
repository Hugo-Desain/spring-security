package com.aplose.digihello.controller;

//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.aplose.digihello.exception.DigiHelloException;
import com.aplose.digihello.model.Town;
import com.aplose.digihello.service.TownService;

@Controller
public class TownViewController {
	@Autowired
	private TownService townService;

	@GetMapping("/townList")
	public String getTowns(Model model, Authentication authentication) {
		model.addAttribute("towns", townService.getAllTowns());
		model.addAttribute("authentication", authentication);
		return "town/townList";
	}
	
	@GetMapping("/deleteTown/{id}")
	public String deleteTown(@PathVariable Long id) {
		townService.deleteTown(id);
		return "redirect:/townList";
	}
	
	
	@PostMapping("/addTown")
	public String addTown(Model model) {
		Town  t = (Town)model.getAttribute("town");
		//todo : traiter la création de la ville
		try {
			townService.addTown(t);
		} catch (DigiHelloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}
	

}
