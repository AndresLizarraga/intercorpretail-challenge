package com.intercorpretail.challenge.repository;

import org.springframework.data.repository.CrudRepository;

import com.intercorpretail.challenge.model.Cliente;

public interface DaoCliente extends CrudRepository <Cliente,Long>{

}
