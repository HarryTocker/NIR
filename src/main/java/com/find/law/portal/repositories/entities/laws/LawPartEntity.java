package com.find.law.portal.repositories.entities.laws;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

/**
 * Сущность БД для хранения части закона.
 * Часть закона хранится в таблице LAW_PARTS.
 */
@Entity
@Table(name = "LAW_PARTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LawPartEntity {
    /**
     * Идентификатор части закона.
     * Так как наименование части не является уникальным, используется данный, автоматически увеличивающийся, идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    /**
     * Наименование части закона.
     */
    @Column(name = "NAME", nullable = false, length = 8192)
    private String name;

    /**
     * Связь с законом к которому привязана данная часть.
     */
    @JoinColumn(name = "LAW_ARTICLE", nullable = false, referencedColumnName = "ARTICLE")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private LawEntity law;

    /**
     * Коллекция всех наказаний данной части закона.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "part", cascade = CascadeType.ALL)
    private Collection<LawPartPunishEntity> punishments;
}
