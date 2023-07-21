package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.model.AbsenModel;
import org.acme.repository.AbsenRepository;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class AbsenService {
    @Inject
    AbsenRepository absenRepository;

    @Inject
    UserService userService;


    @Transactional
    public AbsenModel dataLogin(String nik){
        return absenRepository.persistLogin(nik);
    }

//    @Transactional
//    public LoginModel persistLoginNik(String nik){
//        LoginModel login = new LoginModel();
//        login.absen_masuk = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
//        login.userModel = userRepository.find("nik = ?1",nik).list();
//        login.persist();
//        return login;
//    }
    public AbsenModel getIdByNik(long user_id){
        return absenRepository.findIdByNik(user_id);
    }

    public List getId(String nik){
        return absenRepository.find("userModel", userService.getOne(nik)).list();
    }


    public List<AbsenModel> getListAll(){
        return absenRepository.findAll().list();
    }


    public List<AbsenModel> findDateNow(){
        return absenRepository.find("tanggal", LocalDate.now()).list();
    }

    public List<AbsenModel> validationLogin(String nik){
        return absenRepository.find("tanggal =?1 and userModel =?2", LocalDate.now(), userService.getOne(nik)).list();
    }

    @Transactional
    public AbsenModel updateLogin(long user_id){
        return absenRepository.updateLogin(user_id);
    }

    public List<AbsenModel> findDate(LocalDate tanggal){
        return absenRepository.find("tanggal", tanggal).list();
    }

    public List<AbsenModel> findDates(LocalDate start, LocalDate end){
        return absenRepository.find("tanggal between ?1 and ?2", start,end).list();
    }

}
