package com.abel.eventbookingservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

//@Entity
//@Getter
//@Setter
//@Builder
//@ToString
public class User extends AbstractEntity {

//	@Column(nullable = false)
//	private String name;
//	@Column(name="email", unique = true,nullable = false)
//	private String email;
//	@Column(nullable = false)
//	private String password;
//	@ManyToMany
//	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private Set<Role> roles;

}
