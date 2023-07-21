package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.acme.model.AbsenModel;
import org.acme.service.UserService;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class AbsenRepository implements PanacheRepository<AbsenModel> {
    @Inject
    UserService userService;

    @Transactional
    public AbsenModel persistAbsen(String nik){
        AbsenModel absen = new AbsenModel();
        absen.absenMasuk = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        absen.userModel = userService.getOne(nik);
        System.out.println(absen.tanggal);
        absen.persist();
        return absen;
    }

    @Transactional
    public AbsenModel updateAbsen(Long absen_id){
        AbsenModel absen = AbsenModel.findById(absen_id);
        absen.absenKeluar = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        absen.persist(absen);
        return absen;
    }
    @Inject
    EntityManager em;

    public AbsenModel findIdByNik(long user_id){
        TypedQuery<AbsenModel> query = em.createQuery("select a.user_id from absensi a where a.user_id = :user_id", AbsenModel.class);
        query.setParameter("user_id", user_id);
        return query.getSingleResult();
    }

}
