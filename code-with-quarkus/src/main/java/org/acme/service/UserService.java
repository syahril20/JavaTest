package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.api.dto.AddUserDTO;
import org.acme.model.UserModel;
import org.acme.repository.UserRepository;

import java.util.List;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    public List<UserModel> getListAll(){
        List<UserModel> users = userRepository.findAll().list();
        return users;
    }

    public UserModel dataUser(AddUserDTO addUserDTO){
        return userRepository.persistUser(addUserDTO);
    }


}
