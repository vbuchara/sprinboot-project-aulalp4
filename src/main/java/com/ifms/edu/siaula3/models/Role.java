package com.ifms.edu.siaula3.models;

import java.text.MessageFormat;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Role {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @NotNull
	private String role;
	
	@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
	private Collection<User> users;

	public Role() {

    }

	public Role(String role) {
		this.role = role;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsuarios(Collection<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return MessageFormat.format("Role [role={0}]", this.role);
    }
}
