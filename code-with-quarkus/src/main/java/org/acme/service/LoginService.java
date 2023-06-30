package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.api.dto.LoginDTO;
import org.acme.model.LoginModel;
import org.acme.repository.LoginRepository;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class LoginService {
    @Inject
    LoginRepository loginRepository;

    @Transactional
    public LoginModel dataLogin(LoginDTO loginDTO){
        return loginRepository.persistLogin(loginDTO);
    }

    public List<LoginModel> getListAll(){
        return loginRepository.findAll().list();
    }

    public List<LoginModel> findDateNow(){
        return loginRepository.find("tanggal", LocalDate.now()).list();
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
