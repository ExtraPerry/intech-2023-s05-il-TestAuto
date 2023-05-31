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

import com.intech.comptabilite.model.JournalComptable;
import com.intech.comptabilite.repositories.JournalComptableRepository;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class JournalComptableServiceTest {
	
	@Autowired
	private JournalComptableRepository repo;
	
	@Autowired
	private JournalComptableService journalComptableService;
	
	@BeforeAll
    public void cleanUp(){
        repo.deleteAll();
    }
	
	@Test
	public void getByCode() {
		
		//Arrange
		List<JournalComptable> vList = new ArrayList<JournalComptable>();
		vList.add(new JournalComptable("BQ-2023/00001", "test1"));
		vList.add(new JournalComptable("BQ-2023/00002", "test2"));
		vList.add(new JournalComptable("BQ-2023/00003", "test3"));
		
		String search = "BQ-2023/00002";
		String expected = "test2";
		
		//Act
		JournalComptable result = journalComptableService.getByCode(vList, search);
		
		//Assert
		assertEquals(expected, result.getLibelle());
	}
	
	@Test
	public void getByCodeWithRepo() {
		
		//Arrange
		repo.save(new JournalComptable("BQ-2023/00001", "test1"));
		repo.save(new JournalComptable("BQ-2023/00002", "test2"));
		repo.save(new JournalComptable("BQ-2023/00003", "test3"));
		
		String search = "BQ-2023/00002";
		String expected = "test2";
		
		//Act
		JournalComptable result = journalComptableService.getByCode(journalComptableService.getListJournalComptable(), search);
		
		//Assert
		assertEquals(expected, result.getLibelle());
	}

}
