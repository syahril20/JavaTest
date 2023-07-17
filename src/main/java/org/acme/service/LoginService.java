package org.acme.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.model.LoginModel;
import org.acme.model.UserModel;
import org.acme.repository.LoginRepository;
import org.acme.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class LoginService {
    @Inject
    LoginRepository loginRepository;

    @Inject
    UserService userService;


    @Transactional
    public LoginModel dataLogin(String nik){
        return loginRepository.persistLogin(nik);
    }

//    @Transactional
//    public LoginModel persistLoginNik(String nik){
//        LoginModel login = new LoginModel();
//        login.absen_masuk = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
//        login.userModel = userRepository.find("nik = ?1",nik).list();
//        login.persist();
//        return login;
//    }
    public LoginModel getIdByNik(long user_id){
        return loginRepository.findIdByNik(user_id);
    }

    public List getId(String nik){
        return loginRepository.find("userModel", userService.getOne(nik)).list();
    }


    public List<LoginModel> getListAll(){
        return loginRepository.findAll().list();
    }


    public List<LoginModel> findDateNow(){
        return loginRepository.find("tanggal", LocalDate.now()).list();
    }

    public List<LoginModel> validationLogin(String nik){
        return loginRepository.find("tanggal =?1 and userModel =?2", LocalDate.now(), userService.getOne(nik)).list();
    }

    @Transactional
    public LoginModel updateLogin(long user_id){
        return loginRepository.updateLogin(user_id);
    }

    public List<LoginModel> findDate(LocalDate tanggal){
        return loginRepository.find("tanggal", tanggal).list();
    }

    public List<LoginModel> findDates(LocalDate start, LocalDate end){
        return loginRepository.find("tanggal between ?1 and ?2", start,end).list();
    }

}
