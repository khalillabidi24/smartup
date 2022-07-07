package com.example.projetcaisse.model.entity;
import lombok.*;
import javax.persistence.*;
@Entity
@Table(name = "ROLES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
    @SequenceGenerator(name = "role_generator", sequenceName = "role_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

}