package com.inventorymanagement.model.user;

import com.inventorymanagement.common.Builder;
import com.inventorymanagement.model.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {

    private Long id;

    private String name;

    private String lastName;

    private String username;

    private String password;

    private String mobile;

    private String status;

    private Set<Role> roles;

    public static Builder<UserDTO> builder() {
        return Builder.of(UserDTO.class);
    }

    public static Builder<UserDTO> toBuilder(final User user) {
        return builder()
                .on(userDTO -> userDTO.getId()).set(user.getId())
                .on(userDTO -> userDTO.getLastName()).set(user.getLastName())
                .on(userDTO -> userDTO.getMobile()).set(user.getMobile())
                .on(userDTO -> userDTO.getName()).set(user.getName())
                .on(userDTO -> userDTO.getUsername()).set(user.getUsername())
                .on(userDTO -> userDTO.getPassword()).set(user.getPassword())
                .on(userDTO -> userDTO.getStatus()).set(user.getStatus())
                .on(userDTO -> userDTO.getRoles()).set(user.getRoles());
    }
}
