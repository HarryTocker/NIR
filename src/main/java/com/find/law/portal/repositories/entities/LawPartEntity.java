package com.find.law.portal.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "LAW_PARTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LawPartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "NAME", nullable = false, length = 4092)
    private String name;

    @JoinColumn(name = "LAW_ARTICLE", nullable = false, referencedColumnName = "ARTICLE")
    @ManyToOne(fetch = FetchType.LAZY)
    private LawEntity law;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "part", cascade = CascadeType.ALL)
    private List<LawPartPunishEntity> punishments;
}
