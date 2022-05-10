package com.intercorpretail.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.intercorpretail.challenge.model.Client;
import com.intercorpretail.challenge.repository.DaoClient;

@Controller
public class ChallengeController {
	
	@Autowired
	private DaoClient daoClient;

	@PostMapping(path="/creaCliente")
	private ResponseEntity<Object> crearCliente(@RequestBody Client client) {
		daoClient.save(client);
		return ResponseEntity.ok().body(client);
	}
}
