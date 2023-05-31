package com.intech.comptabilite.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intech.comptabilite.model.CompteComptable;
import com.intech.comptabilite.service.entityservice.CompteComptableService;

@RestController
@RequestMapping("/CompteComptable")
public class CompteComptableController {

	private CompteComptableService compteComptableService;
	
	@GetMapping
	public List<CompteComptable> getCompteComptables() {
		return compteComptableService.getListCompteComptable();
	}
	
	@GetMapping("/{num}")
	public ResponseEntity<CompteComptable> getCompteComptable(@PathVariable(name = "num") Integer num){
		CompteComptable compteComptable = compteComptableService.getByNumero(compteComptableService.getListCompteComptable(), num);
		if (compteComptable == null) {
			return ResponseEntity.ok(compteComptable);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
