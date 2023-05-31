package com.intech.comptabilite.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.intech.comptabilite.model.CompteComptable;
import com.intech.comptabilite.service.entityservice.CompteComptableService;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class CompteComptableControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CompteComptableService compteComptableService;
	
	@WithMockUser
	@Test
	public void getCompteComptables() throws Exception {
		
		//Arrange
		List<CompteComptable> compteComptables = new ArrayList<CompteComptable>();
		CompteComptable compteComptable = new CompteComptable(0, "test1");
		compteComptables.add(compteComptable);
		
		Mockito.when(compteComptableService.getListCompteComptable()).thenReturn(compteComptables);
		RequestBuilder rBuilder = MockMvcRequestBuilders.get("/CompteComptable");
		
		//Act
		ResultActions rActions = mockMvc.perform(rBuilder);
		
		//Assert
		rActions.andExpect(MockMvcResultMatchers.status().isOk());
		rActions.andExpect(MockMvcResultMatchers.jsonPath("$[0].libelle").value("test1"));	
	}
	
	@WithMockUser
	@Test
	public void getCompteComptableNotFound() throws Exception {
		
		//Arrange
		Mockito.when(compteComptableService.getByNumero(compteComptableService.getListCompteComptable(), 0));
		RequestBuilder rBuilder = MockMvcRequestBuilders.get("/CompteComptable/0");
		
		//Act
		ResultActions rActions = mockMvc.perform(rBuilder);
		
		//Assert
		rActions.andExpect(MockMvcResultMatchers.status().is(404));
	}
}
