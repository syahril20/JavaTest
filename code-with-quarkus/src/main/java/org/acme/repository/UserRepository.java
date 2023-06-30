package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.api.dto.AddUserDTO;
import org.acme.model.UserModel;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserModel> {
    @Transactional
    public UserModel persistUser(AddUserDTO addUserDTO){
        UserModel user = new UserModel();
        user.name = addUserDTO.name;
        user.nik = addUserDTO.nik;
        user.persist();
        return user;
    }
}
