/*package com.example.gcompany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.gcompany.domain.Employe;

import com.example.gcompany.service.EmployeService;


@Controller
public class EmployeController {
	
	 @Autowired
	    private EmployeService service;

	    @GetMapping("/")
	    public String viewHomePage(Model model) {
	        List<Employe> listemploye = service.listAll();
	        model.addAttribute("listemploye", listemploye);
	        System.out.println("aaaaaaaaijeroughe" + listemploye.toString());
	        System.out.print("Get / ");	
	        return "index";
	    }

	    @GetMapping("/new")
	    public String add(Model model) {
	        model.addAttribute("employe", new Employe());
	        return "new";
	    }

	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String saveEmployee(@ModelAttribute("employe") Employe emp) {
	        service.save(emp);
	        return "redirect:/";
	    }

	    @RequestMapping("/edit/{cin}")
	    public ModelAndView showEditEmployePage(@PathVariable(name = "cin") String cin) {
	        ModelAndView mav = new ModelAndView("new");
	        Employe emp = service.getEmploye(cin);
	        mav.addObject("employe", emp);
	        return mav;
	        
	    }
	    @RequestMapping("/delete/{cin}")
	    public String deleteEmployeePage(@PathVariable(name = "cin") String cin) {
	        service.delete(cin);
	        return "redirect:/";
	    }

}*/