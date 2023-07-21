package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "absensi")
@Table(name = "absensi")

public class AbsenModel extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "absen_id")
    public long absenId;

    @Column(name = "absen_masuk")
    public LocalTime absenMasuk;

    @Column(name = "absen_keluar")
    public LocalTime absenKeluar;

    @CreationTimestamp
    @Column(name = "tanggal")
    public LocalDate tanggal;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public UserModel userModel;

    public LocalTime getAbsenMasuk() {
        return absenMasuk;
    }
    public void setAbsenMasuk(LocalTime absenMasuk) {
        this.absenMasuk = absenMasuk;
    }
}
