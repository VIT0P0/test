package com.example.gcompany.controller;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.gcompany.domain.Employee;
import com.example.gcompany.domain.Mois_state;
import com.example.gcompany.domain.PresenceList;
import com.example.gcompany.service.EmployeeService;
import com.example.gcompany.service.MoisService;
import com.example.gcompany.service.PresenceListService;



@Controller
@RequestMapping("/")
public class EmployeeController {
	
	 @Autowired
	   private EmployeeService service;
	 @Autowired
	 private PresenceListService presenceListservice;
	 @Autowired
	 MoisService monthService;
	/* SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
	 SimpleDateFormat monthformat = new SimpleDateFormat("MM/yyyy");
 	String date = formatter1.format(new Date()); 	
 	String month = monthformat.format(new Date());*/
	
	   @GetMapping("/")
	    public String viewHomePage(Model model) {
		   
		   String date = service.getCurrentDate();	
		 	String month = service.getCurrentMonth();
		 	
	        List<Employee> listemployee = service.listAll();
	        model.addAttribute("listemployee", listemployee);
	        System.out.print("Get / ");	
	        
	        
	        System.out.println("the moooooooonthhhhh isss  "+month);
	        System.out.println("the moooooooonthhhhh isss  "+month);
	        System.out.println("the moooooooonthhhhh isss  "+month);
	    
	    
	    	//System.out.println("hhhhhhhhhhhhhh "+date);
	    	System.out.println(listemployee.toString());
	    	
	    	 List<PresenceList> presenceList = presenceListservice.getList(date);
	    	 List<Mois_state> monthList = monthService.getThisMonthList(month); 
	    	
	    	 if( presenceList.isEmpty()) {
	    		 //System.out.println("oierjgoejgojergeogoegoegoe");
	    	 	 presenceListservice.insertAllAbsence(date);
	    	 }
	    	 if(monthList.isEmpty()) {
	    		 monthService.insertAll(month);
	    	 }
	    	
	    	 
	    	 
	        return "index";
	    }

	    @GetMapping("/new")
	    public String add(Model model) {
	        model.addAttribute("employee", new Employee());
	        return "new";
	    }

	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String saveEmployee(@ModelAttribute("employee") Employee emp) {
	    	 String date = service.getCurrentDate();	
			 	String month = service.getCurrentMonth();
	        service.save(emp);
	        PresenceList p = presenceListservice.verifPresence(emp.getCin(), date);
	    	

	        if(p==null) 
	        presenceListservice.insertOneAbsence(emp.getCin(),emp.getNom(),date);
	        
	        Mois_state m = monthService.verifMonth(emp.getCin(), month);
	        
	        if(m==null)
	        	monthService.insertOneMonth(emp.getCin(),emp.getNom(), month);
	        
	        return "redirect:/";
	    }
	   
	    @RequestMapping(value="/try",method=RequestMethod.GET)
	    public String trying(@RequestParam String name ) throws Exception {
	    	
	    	//System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA"+name);
	    	name = name.substring(1,name.length()-1);
	    	String [] table = name.split(",");
	    	/*for(int i=1;i<table.length;i+=2) {
	    		System.out.println(table[i-1]+" : "+table[i]);
	    	}*/
	    	for(String v : table ) {
	    		System.out.println(v);
	    		
	    		int c = v.indexOf(":");
	    		System.out.println(v.substring(1,c-1));
	    		System.out.println(v.substring(c+2));
	    		presenceListservice.edit(Float.valueOf(v.substring(c+2,v.length()-1))
	    		,Long.valueOf(v.substring(1,c-1)));
	    	}
	    	
	    	
	    	
	    	return "prJournalière";
	    }
	    @RequestMapping(value="/updateAbsence",method=RequestMethod.GET)
	    public @ResponseBody List<PresenceList> updateTables(@RequestParam String name ) throws Exception {
	    	
	    	 String date = service.getCurrentDate();	
			 	String month = service.getCurrentMonth();
	    	System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA"+name);
	    	name = name.substring(1,name.length()-1);
	    	String [] table = name.split(",");
	    	/*for(int i=1;i<table.length;i+=2) {
	    		System.out.println(table[i-1]+" : "+table[i]);
	    	}*/
	    	for(String v : table ) {
	    		System.out.println(v);
	    		
	    		int c = v.indexOf(":");
	    		System.out.println(v.substring(1,c-1));
	    		System.out.println(v.substring(c+2));
	    		Long id = Long.valueOf(v.substring(1,c-1));
	    		float presence = Float.valueOf(v.substring(c+2,v.length()-1));
	    		PresenceList pr = presenceListservice.getPresenceList(id);
	    		float oldPresence = pr.getPresence();
	    		String cin = pr.getCin();
	    		presenceListservice.edit(presence,id);
	    		
	    	
	    			monthService.updateMonth(presence-oldPresence, cin, month);
	    		
	    	}
	    	List<PresenceList> absenceList = presenceListservice.getAbsenceList(date);
	    	
	    
	    	return absenceList;
	    }
	    	@RequestMapping(value="/updatePresence",method=RequestMethod.GET)
		    public @ResponseBody List<PresenceList> updatePresence( ) throws Exception {
	    		 String date = service.getCurrentDate();	
	 		 
	    		List<PresenceList> presenceList = presenceListservice.getPresenceList(date);
	    		
	    		if(!presenceList.isEmpty()) {
	    			
	    		}
	    		System.out.println(presenceList);
	    		return presenceList;
	    	}
	    	
	    
	    @RequestMapping("/edit/{cin}")
	    public ModelAndView showEditEmployeePage(@PathVariable(name = "cin")String  cin) {
	        ModelAndView mav = new ModelAndView("new");
	        Employee emp = service.getEmployee(cin);
	        mav.addObject("employee", emp);
	        return mav;
	        
	    }
	    @RequestMapping("/delete/{cin}")
	    public String deleteEmployeePage(@PathVariable(name = "cin") String cin) {
	        service.delete(cin);
	        return "redirect:/";
	    }
	    @GetMapping("/prJournalière")
	    public String showPresence(Model model) {
	    	 String date = service.getCurrentDate();	
			 
	    	
	    	 List<PresenceList> absenceList = presenceListservice.getAbsenceList(date);
	    	 model.addAttribute("absenceList", absenceList );
	    	 List<PresenceList> presenceList = presenceListservice.getPresenceList(date);
	    	 model.addAttribute("presenceList",presenceList);
	    	return "prJournalière";
	    	
	    }
	    @GetMapping("/prMensuelle")
	    public String showMonth(Model model) {
	    	List<Mois_state> monthList = monthService.getAll();
	    	model.addAttribute("monthList", monthList);
	    	return "prMensuelle";
	    	
	    
	    }

}