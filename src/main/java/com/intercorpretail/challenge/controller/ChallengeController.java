package com.intercorpretail.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.intercorpretail.challenge.model.Cliente;
import com.intercorpretail.challenge.repository.DaoCliente;

@Controller
public class ChallengeController {
	
	@Autowired
	private DaoCliente daoCliente;

	@PostMapping(path="/creaCliente")
	private ResponseEntity<Object> crearCliente(@RequestBody Cliente client) {
		daoCliente.save(client);
		return ResponseEntity.ok().body(client);
	}
}
