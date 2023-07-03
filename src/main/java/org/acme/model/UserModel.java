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
    public long user_id;

    @Column(name = "nik")
    public String nik;

    @Column(name = "name")
    public String name;





}
