/*package com.example.gcompany.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gcompany.domain.Employe;

import com.example.gcompany.repository.EmployeRepository;
import com.fasterxml.jackson.annotation.JacksonInject.Value;


@Service

	public class EmployeService {
	
	
	@Autowired
	private EmployeRepository repo;
	
	

	public List<Employe> listAll() {

		return repo.findAll();

	}
	public void save(Employe e){


		repo.save(e);
	}
	public Employe getEmploye(String cin ){
		return repo.findById(cin).get();
	}

	public void delete(String cin ){
		repo.deleteById(cin);
		

	}
}
*/

