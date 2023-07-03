package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.api.dto.LoginDTO;
import org.acme.model.LoginModel;
import org.acme.model.UserModel;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class LoginRepository implements PanacheRepository<LoginModel> {
    @Transactional
    public LoginModel persistLogin(LoginDTO loginDTO){
        UserModel user = UserModel.findById(loginDTO.user_id );
        LoginModel login = new LoginModel();
        login.absen_masuk = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        login.userModel = user;
        System.out.println(login.tanggal);
        login.persist();
        return login;
    }

    @Transactional
    public LoginModel updateLogin(Long login_id){
        LoginModel login = LoginModel.findById(login_id);
        login.absen_keluar = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        login.persist(login);
        return login;
    }
}
