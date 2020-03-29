package com.twentythree.people.apirest.courses.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="course")
public class Course implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@ApiModelProperty(notes = "course code")
	@Max(value = 9999, message = "Code should not be greater than 9999")
	private Long code;
	@ApiModelProperty(notes = "course name")
	private String name;
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
