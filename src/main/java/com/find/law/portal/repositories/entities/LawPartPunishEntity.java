package com.find.law.portal.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность БД для хранения наказания части закона.
 * Наказание части закона хранится в таблице LAW_PART_PUNISHMENTS.
 */
@Entity
@Table(name = "LAW_PART_PUNISHMENTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LawPartPunishEntity {
    /**
     * Идентификатор наказания части закона.
     * Так как наименование наказания не является уникальным, используется данный, автоматически увеличивающийся, идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    /**
     * Тип наказания.
     */
    @Column(name = "TYPE", nullable = false, length = 512)
    private String type;

    /**
     * Минимальный срок наказания.
     */
    @Column(name = "MIN_TIME", length = 2048)
    private String minTime;

    /**
     * Максимальный срок наказания.
     */
    @Column(name = "MAX_TIME", length = 2048)
    private String maxTime;

    /**
     * Тип времени наказания.
     */
    @Column(name = "DATE_TYPE", length = 128)
    private String dateType;

    /**
     * Является ли наказание пожизненным.
     */
    @Column(name = "IS_LIFE_TIME", nullable = false)
    private Boolean isLifeTime;

    /**
     * Связь с частью закона к которому привязано данное наказание.
     */
    @JoinColumn(name = "PART_ID", nullable = false, referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private LawPartEntity part;
}
