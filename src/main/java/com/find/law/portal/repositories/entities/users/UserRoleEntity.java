package com.find.law.portal.repositories.entities.users;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "USER_ROLES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private long id;

    @JoinColumn(name = "USER_USERNAME", nullable = false, referencedColumnName = "USERNAME")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserEntity owner;

    @Column(name = "ROLE", nullable = false, length = 32)
    private String role;
}
