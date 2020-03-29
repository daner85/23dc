package com.twentythree.people.apirest.courses.models.entity;

import java.io.Serializable;


public class JwtEntity implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwt;

	public JwtEntity(String jwt) {
		this.jwt = jwt;
	}

	public String getToken() {
		return this.jwt;
	}
}
