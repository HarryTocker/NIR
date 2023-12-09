package com.find.law.portal.repositories.entities.punishments;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PUNISHMENTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PunishmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "TEXT", nullable = false, length = 128)
    private String text;

    @Column(name = "NUMBER", nullable = false)
    private Double number;

    @Column(name = "TYPE", nullable = false, length = 64)
    private String type;
}
