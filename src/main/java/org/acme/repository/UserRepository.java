package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.acme.api.dto.AddUserDTO;
import org.acme.model.UserModel;

import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserModel> {

    @Inject
    EntityManager em;
    @Transactional
    public UserModel persistUser(String nik, String name){
        UserModel user = new UserModel();
        user.nik = nik;
        user.name = name;
        user.persist();
        return user;
    }

    public List<Object[]> getByNik(String nik) {
        TypedQuery<Object[]> query = em.createQuery("SELECT u.user_id, u.nik, u.name FROM user u WHERE u.nik = :nik", Object[].class);
        query.setParameter("nik", nik);
        return query.getResultList();
    }
}
