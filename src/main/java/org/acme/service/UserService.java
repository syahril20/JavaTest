package org.acme.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
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

    public UserModel getOne(String nik){
        return userRepository.find("nik", nik).singleResult();
    }

    public List<UserModel> getListAll(){
        List<UserModel> users = userRepository.findAll().list();
        return users;
    }

    public List<UserModel> findByNik(String nik){
        return userRepository.find("nik = ?1", nik).list();
    }

    public List<Object[]> getByNik(String nik){
        return userRepository.getByNik(nik);
    }

    public UserModel dataUser(String nik, String name){
        return userRepository.persistUser(nik, name);
    }


}
