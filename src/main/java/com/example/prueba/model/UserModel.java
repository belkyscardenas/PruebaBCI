package com.example.prueba.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private Date created;
    private Date modified;

    @Column(name="last_login")
    private Date lastLogin;

    @Column(name="is_active")
    private Boolean isActive;

    private String token;
}
