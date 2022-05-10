package com.intercorpretail.challenge.repository;

import org.springframework.data.repository.CrudRepository;

import com.intercorpretail.challenge.model.Client;

public interface DaoClient extends CrudRepository <Client,Long>{

}
