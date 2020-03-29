package com.twentythree.people.apirest.courses.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.twentythree.people.apirest.courses.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {
	
	public  List<Cliente> findAll();

}
