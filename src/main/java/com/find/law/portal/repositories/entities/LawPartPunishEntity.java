package com.find.law.portal.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LAW_PART_PUNISHMENTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LawPartPunishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "TYPE", nullable = false, length = 512)
    private String type;

    @Column(name = "MIN_TIME", length = 2048)
    private String minTime;

    @Column(name = "MAX_TIME", length = 2048)
    private String maxTime;

    @Column(name = "DATE_TYPE", length = 128)
    private String dateType;

    @Column(name = "IS_LIFE_TIME", nullable = false)
    private Boolean isLifeTime;

    @JoinColumn(name = "PART_ID", nullable = false, referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private LawPartEntity part;
}
