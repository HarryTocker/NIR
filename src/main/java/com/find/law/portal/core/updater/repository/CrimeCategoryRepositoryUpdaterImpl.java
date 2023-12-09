package com.find.law.portal.core.updater.repository;

import com.find.law.portal.core.content.categories.CrimeCategoryData;
import com.find.law.portal.core.updater.CrimeCategoryRepositoryUpdater;
import com.find.law.portal.mappers.GenericBeanMapper;
import com.find.law.portal.repositories.CrimeCategoryRepository;
import com.find.law.portal.repositories.PunishmentRepository;
import com.find.law.portal.repositories.entities.categories.CrimeCategoryEntity;

import java.util.Collection;
import java.util.Optional;

/**
 * Реализация сервиса обновления распарсенных категорий преступлений в базе данных.
 */
public class CrimeCategoryRepositoryUpdaterImpl implements CrimeCategoryRepositoryUpdater {
    private final PunishmentRepository punishmentRepository;
    private final CrimeCategoryRepository crimeCategoryRepository;
    private final GenericBeanMapper mapper;

    public CrimeCategoryRepositoryUpdaterImpl(PunishmentRepository punishmentRepository, CrimeCategoryRepository crimeCategoryRepository, GenericBeanMapper mapper) {
        this.punishmentRepository = punishmentRepository;
        this.crimeCategoryRepository = crimeCategoryRepository;
        this.mapper = mapper;
    }

    @Override
    public void update(Collection<CrimeCategoryData> categories) {
        Collection<CrimeCategoryEntity> existing = crimeCategoryRepository.findAll();
        for (CrimeCategoryData data : categories) {
            update(data, existing);
        }
    }

    /**
     * Обновить данные категории преступления в базе данных.
     * <br>
     * Перед обновлением проверяется существование указанной категории в базе данных.
     * Если данная категория уже существует и имеет такой же срок наказания, обновления не происходит.
     * В противном случае существующая категория будет удалена перед сохранением новой.
     *
     * @param data
     * @param existing
     */
    private void update(CrimeCategoryData data, Collection<CrimeCategoryEntity> existing) {
        CrimeCategoryEntity current = mapper.map(data, CrimeCategoryEntity.class);
        Optional<CrimeCategoryEntity> entity = existing.stream().filter(current::equals).findFirst();
        if (entity.isPresent() && entity.get().getPunishment().equals(current.getPunishment())) {
            return;
        }

        entity.ifPresent(e -> {
            crimeCategoryRepository.delete(e);
            punishmentRepository.delete(e.getPunishment());
        });
        current.setPunishment(punishmentRepository.save(current.getPunishment()));
        crimeCategoryRepository.save(current);
    }
}
