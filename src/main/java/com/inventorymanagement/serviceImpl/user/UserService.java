package com.inventorymanagement.serviceImpl.user;

import com.inventorymanagement.model.user.User;
import com.inventorymanagement.model.user.UserDTO;
import com.inventorymanagement.repository.UserRepository;
import com.inventorymanagement.serviceImpl.BaseServiceConvertorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService extends BaseServiceConvertorImpl<UserDTO, User> {

    @Autowired
    public UserService(final UserRepository userRepository) {
        super(userRepository,
                (userDTO) -> User.toBuilder(userDTO).build(),
                (user) -> UserDTO.toBuilder(user).build());
    }

    @Override
    protected User buildToPersistObject(Long id, UserDTO userDTO) {
        return User.toBuilder(userDTO).on(userdto -> userdto.getId()).set(id)
                .build();
    }
}
