package com.inventorymanagement.model.user;

import com.inventorymanagement.common.Builder;
import com.inventorymanagement.model.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "auth_user")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "auth_user_id")
	private Long id;

	@Column(name = "first_name")
	private String name;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "status")
	private String status;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "auth_user_id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
	private Set<Role> roles;

	public static Builder<User> builder() {
		return Builder.of(User.class);
	}

	public static Builder<User> toBuilder(final UserDTO userDTO) {
		return builder()
				.on(user -> user.getId()).set(userDTO.getId())
				.on(user -> user.getLastName()).set(userDTO.getLastName())
				.on(user -> user.getMobile()).set(userDTO.getMobile())
				.on(user -> user.getName()).set(userDTO.getName())
				.on(user -> user.getUsername()).set(userDTO.getUsername())
				.on(user -> user.getPassword()).set(userDTO.getPassword())
				.on(user -> user.getStatus()).set(userDTO.getStatus())
				.on(user -> user.getRoles()).set(userDTO.getRoles());
	}
}
