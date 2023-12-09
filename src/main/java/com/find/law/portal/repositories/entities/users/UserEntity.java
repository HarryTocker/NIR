package com.find.law.portal.repositories.entities.users;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserEntity {
    @Id
    @Column(name = "USERNAME", nullable = false, length = 64)
    private String username;

    @Column(name = "PASSWORD", nullable = false, length = 512)
    @EqualsAndHashCode.Exclude
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.ALL)
    private Collection<UserRoleEntity> roles;
}
