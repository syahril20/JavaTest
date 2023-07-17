package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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

    public List findByNik(String nik){
        return userRepository.find("nik = ?1", nik).list();
    }

    @Inject
    EntityManager em;

    public List<Object[]> getByNik(String nik){
        return userRepository.getByNik(nik);
    }

    public UserModel dataUser(AddUserDTO addUserDTO){
        return userRepository.persistUser(addUserDTO);
    }


}
