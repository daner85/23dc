package com.twentythree.people.apirest.courses.models.services;

import java.util.List;

import com.twentythree.people.apirest.courses.models.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();
	
	public Cliente save(Cliente cliente);
	
	public void delete(Long id);
	
	public Cliente findById(Long id);

}
