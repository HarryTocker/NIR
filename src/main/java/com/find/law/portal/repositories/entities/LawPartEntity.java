package com.find.law.portal.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Сущность БД для хранения части закона.
 * Часть закона хранится в таблице LAW_PARTS.
 */
@Entity
@Table(name = "LAW_PARTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LawPartEntity {
    /**
     * Идентификатор части закона.
     * Так как наименование части не является уникальным, используется данный, автоматически увеличивающийся, идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    /**
     * Наименование части закона.
     */
    @Column(name = "NAME", nullable = false, length = 4092)
    private String name;

    /**
     * Связь с законом к которому привязана данная часть.
     */
    @JoinColumn(name = "LAW_ARTICLE", nullable = false, referencedColumnName = "ARTICLE")
    @ManyToOne(fetch = FetchType.LAZY)
    private LawEntity law;

    /**
     * Коллекция всех наказаний данной части закона.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "part", cascade = CascadeType.ALL)
    private List<LawPartPunishEntity> punishments;
}
