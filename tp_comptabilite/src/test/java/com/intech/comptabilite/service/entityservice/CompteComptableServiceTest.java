package com.intech.comptabilite.service.entityservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.intech.comptabilite.model.CompteComptable;
import com.intech.comptabilite.repositories.CompteComptableRepository;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class CompteComptableServiceTest {
	
	@Autowired
	private CompteComptableRepository repo;
	
	@Autowired
	private CompteComptableService compteComptableService;
	
	@BeforeAll
    public void cleanUp(){
        repo.deleteAll();
    }
	
	@Test
	public void getByNumero() {
		
		//Arrange
		List<CompteComptable> vList = new ArrayList<CompteComptable>();
		vList.add(new CompteComptable(0, "test1"));
		vList.add(new CompteComptable(1, "test2"));
		vList.add(new CompteComptable(2, "test3"));
		
		int search = 1;
		String expected = "test2";
		
		//Act
		CompteComptable result = compteComptableService.getByNumero(vList, search);
		
		//Assert
		assertEquals(expected, result.getLibelle());
	}
	
	@Test
	public void getByNumeroWithRepo() {
		
		//Arrange
		repo.save(new CompteComptable(0, "test1"));
		repo.save(new CompteComptable(1, "test2"));
		repo.save(new CompteComptable(2, "test3"));
		
		int search = 1;
		String expected = "test2";
		
		//Act
		CompteComptable result = compteComptableService.getByNumero(compteComptableService.getListCompteComptable(), search);
		
		//Assert
		assertEquals(expected, result.getLibelle());
	}
	
}


	
	
		
		
