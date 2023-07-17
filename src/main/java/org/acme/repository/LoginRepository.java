package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.acme.api.dto.LoginDTO;
import org.acme.model.LoginModel;
import org.acme.model.UserModel;
import org.acme.service.UserService;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class LoginRepository implements PanacheRepository<LoginModel> {
    @Inject
    UserService userService;

    @Transactional
    public LoginModel persistLogin(String nik){
        LoginModel login = new LoginModel();
        login.absenMasuk = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        login.userModel = userService.getOne(nik);
        System.out.println(login.tanggal);
        login.persist();
        return login;
    }

    @Transactional
    public LoginModel updateLogin(Long login_id){
        LoginModel login = LoginModel.findById(login_id);
        login.absenKeluar = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        login.persist(login);
        return login;
    }
    @Inject
    EntityManager em;

    public LoginModel findIdByNik(long user_id){
        TypedQuery<LoginModel> query = em.createQuery("select a.user_id from absensi a where a.user_id = :user_id", LoginModel.class);
        query.setParameter("user_id", user_id);
        return query.getSingleResult();
    }

}
