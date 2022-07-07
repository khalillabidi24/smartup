package com.example.projetcaisse.model.entity;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(	name = "Utilisateur",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "util_generator")
    @SequenceGenerator(name = "util_generator", sequenceName = "util_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @NotBlank
    @Size(max = 20)
    private String username;
    private Long telephone;
    private String adresse;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(max = 120)
    private String password;
    private int active=1;

    @OneToMany(mappedBy="utilisateur")
    private List<Reservation> reservations;

    @OneToMany(mappedBy="utilisateur")
    private List<Reclamation> reclamations;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    public Utilisateur(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Reclamation> getReclamations() {
        return reclamations;
    }

    public void setReclamations(List<Reclamation> reclamations) {
        this.reclamations = reclamations;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }


    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
