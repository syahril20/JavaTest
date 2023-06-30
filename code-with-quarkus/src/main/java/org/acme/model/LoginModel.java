package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.vertx.ext.auth.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.mapping.Join;

import java.security.PrivateKey;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "absensi")

public class LoginModel extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_id")
    public long login_id;



    @Column(name = "absen_masuk")
    public LocalTime absen_masuk;

    @Column(name = "absen_keluar")
    public LocalTime absen_keluar;

    @CreationTimestamp
    @Column(name = "tanggal")
    public LocalDate tanggal;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    
    public UserModel userModel;



}
