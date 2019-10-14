package com.inventorymanagement.serviceImpl.user;

import com.inventorymanagement.model.User;
import com.inventorymanagement.repository.UserRepository;
import com.inventorymanagement.serviceImpl.BaseServiceConvertorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService extends BaseServiceConvertorImpl<User, User> {

    @Autowired
    public UserService(final UserRepository userRepository) {
        super(userRepository,
                (user) -> User.toBuilder(user).build(),
                (liveChat) -> User.toBuilder(user).build());
    }

    @Override
    protected User buildToPersistObject(Long id, User entityObject) {
        return new User();
    }
}
