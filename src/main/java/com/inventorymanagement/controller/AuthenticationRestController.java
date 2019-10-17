package com.inventorymanagement.controller;

import com.inventorymanagement.common.Constants;
import com.inventorymanagement.model.user.User;
import com.inventorymanagement.model.user.UserDTO;
import com.inventorymanagement.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/authentication")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationRestController extends BaseController<UserDTO> {

    private BaseService<UserDTO> userService;

    @Autowired
    public AuthenticationRestController(final BaseService<UserDTO> userService) {//TO DO User Service Class Creation
        super(userService);
        this.userService = userService;
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getMessage() {
        return Optional.ofNullable(new User())//TO DO get From Service
                .map(e -> new ResponseEntity(e, HttpStatus.OK))
                .orElse(new ResponseEntity(Constants.Error.NOTFOUND, HttpStatus.NOT_FOUND));
    }

}
