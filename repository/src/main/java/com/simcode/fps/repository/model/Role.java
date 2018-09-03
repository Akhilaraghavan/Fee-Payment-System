package com.simcode.fps.repository.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ROLES")
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID", nullable=false, updatable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="ROLE_NAME", nullable=false)
	private String roleName;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinTable(name="USERS_ROLES", 
		joinColumns= {@JoinColumn(name="FK_ROLE_ID", referencedColumnName="ID")},
		inverseJoinColumns= {@JoinColumn(name="FK_USER_ID", referencedColumnName="ID")})
	private User user;
	
	Role(){}
	
	public Role(String roleName) {
		this.roleName = roleName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
