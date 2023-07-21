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
    public AbsenModel dataAbsen(String nik){
        return absenRepository.persistAbsen(nik);
    }

//    @Transactional
//    public AbsenModel persistAbsenNik(String nik){
//        AbsenModel absen = new AbsenModel();
//        absen.absen_masuk = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
//        absen.userModel = userRepository.find("nik = ?1",nik).list();
//        absen.persist();
//        return absen;
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

    public List<AbsenModel> validationAbsen(String nik){
        return absenRepository.find("tanggal =?1 and userModel =?2", LocalDate.now(), userService.getOne(nik)).list();
    }

    @Transactional
    public AbsenModel updateAbsen(long user_id){
        return absenRepository.updateAbsen(user_id);
    }

    public List<AbsenModel> findDate(LocalDate tanggal){
        return absenRepository.find("tanggal", tanggal).list();
    }

    public List<AbsenModel> findDates(LocalDate start, LocalDate end){
        return absenRepository.find("tanggal between ?1 and ?2", start,end).list();
    }

}
