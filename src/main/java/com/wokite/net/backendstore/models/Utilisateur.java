package com.wokite.net.backendstore.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "utilisateur", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
@SuperBuilder
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 50)
    @Column(name = "name")
    private String name;

    @Size(min = 3, max = 50)
    @NotNull
    @Column(name = "username")
    private String username;

    @Size(max = 50)
    @Email
    @Column(name = "email")
    private String email;

    @Size(min = 6, max = 100)
    @NotNull
    @Column(name = "password")
    private String password;

    private String photo = "avatar.jpg";

    private boolean isActive;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Column(name = "actif")
    private int actif;

    public void setActif(boolean actif) {
        if (actif)
            this.actif = 1;
        else
            this.actif = 0;
    }

    public boolean isActif() {
        if (actif == 1)
            return true;
        return false;
    }

    public Utilisateur() {
        super();
    }

    public Utilisateur(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;

    }

    @JsonCreator
    public Utilisateur (@JsonProperty("id") Long id ) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    //@JsonIgnore
    public String getPassword() {
        return password;
    }

    //	@JsonSetter
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
