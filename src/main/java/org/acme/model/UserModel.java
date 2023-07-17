package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "user")
@Table(name = "users", indexes = {
        @Index(name = "idx_user", columnList = "nik")
}, uniqueConstraints = {
        @UniqueConstraint(name = "unique_nik", columnNames = {"nik"})

})

public class UserModel extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public long userId;

    @Column(name = "nik")
    public String nik;

    @Column(name = "name")
    public String name;

    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNik() {
        return nik;
    }
    public void setNik(String nik) {
        this.nik = nik;
    }



}
