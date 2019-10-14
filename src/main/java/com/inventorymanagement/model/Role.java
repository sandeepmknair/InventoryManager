package com.inventorymanagement.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "auth_role")
@Data
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "auth_role_id")
	private int id;

	@Column(name = "role_name")
	private String role;

	@Column(name = "role_desc")
	private String desc;

}