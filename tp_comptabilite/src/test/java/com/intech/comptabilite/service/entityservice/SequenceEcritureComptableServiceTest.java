package com.intech.comptabilite.service.entityservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.intech.comptabilite.model.SequenceEcritureComptable;
import com.intech.comptabilite.repositories.SequenceEcritureComptableRepository;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class SequenceEcritureComptableServiceTest {

	@Autowired
	private SequenceEcritureComptableRepository repo;
	
	@Autowired
	private SequenceEcritureComptableService sequenceEcritureComptableService;

	@BeforeAll
    public void cleanUp(){
        repo.deleteAll();
    }
	
	@Test
	public void getDernierValeurByCodeAndAnnee() {

		// Arrange
		repo.deleteAll();
		repo.save(new SequenceEcritureComptable("00001", 2023, 15));
		repo.save(new SequenceEcritureComptable("00002", 2023, 30));
		repo.save(new SequenceEcritureComptable("00003", 2023, 45));
		
		String code = "00002";
		int annee = 2023;

		int expected = 30;
		
		// Act
		int result = 0;
		try {
			result = sequenceEcritureComptableService.getDernierValeurByCodeAndAnnee(code, annee);
		} catch(Exception e) {
			fail("No result found exception : " + e);
		}
		
		// Assert
		assertEquals(expected, result);
	}

}
