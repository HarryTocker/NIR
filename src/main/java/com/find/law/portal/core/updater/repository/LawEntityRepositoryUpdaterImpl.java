package com.find.law.portal.core.updater.repository;

import com.find.law.portal.core.content.laws.LawData;
import com.find.law.portal.core.updater.LawEntityRepositoryUpdater;
import com.find.law.portal.mappers.GenericBeanMapper;
import com.find.law.portal.repositories.LawRepository;
import com.find.law.portal.repositories.PunishmentRepository;
import com.find.law.portal.repositories.entities.laws.LawEntity;
import com.find.law.portal.repositories.entities.laws.LawPartEntity;
import com.find.law.portal.repositories.entities.laws.LawPartPunishEntity;

import java.util.Collection;
import java.util.Optional;

/**
 * Реализация сервиса обновления распарсенных законов в базе данных.
 */
public class LawEntityRepositoryUpdaterImpl implements LawEntityRepositoryUpdater {
    private final LawRepository lawRepository;
    private final PunishmentRepository punishmentRepository;
    private final GenericBeanMapper mapper;

    public LawEntityRepositoryUpdaterImpl(LawRepository lawRepository, PunishmentRepository punishmentRepository, GenericBeanMapper mapper) {
        this.lawRepository = lawRepository;
        this.punishmentRepository = punishmentRepository;
        this.mapper = mapper;
    }

    @Override
    public void update(Collection<LawData> laws) {
        for (LawData data : laws) {
            Optional<LawEntity> entity = lawRepository.findById(data.getArticle());
            update(data, entity.orElse(null));
        }
    }

    /**
     * Обновить данные конкретного закона в базе данных.
     *
     * @param data текущий закон.
     * @param existing существующий закон, может быть null.
     */
    private void update(LawData data, LawEntity existing) {
        LawEntity current = mapper.map(data, LawEntity.class);

        current.getParts().forEach(part -> {
            part.setLaw(current);
            part.getPunishments().forEach(punishment -> punishment.setPart(part));
        });

        if (existing == null) {
            createLaw(current);
        } else {
            updateLaw(current, existing);
        }
    }

    /**
     * Создать закон в базе данных.
     *
     * @param current закон для сохранения.
     */
    private void createLaw(LawEntity current) {
        current.getParts().forEach(part ->  {
            part.setLaw(current);
            part.setPunishments(savePunishments(part, part.getPunishments()));
        });
        lawRepository.save(current);
    }

    /**
     * Обновить закон в базе данных.
     * <br>
     * Если закон существует и не отличается, он будет пропущен.
     * В противном случае происходит удаление существующего закона и сохранение нового.
     *
     * @param current текущий закон.
     * @param existing существующий закон.
     */
    private void updateLaw(LawEntity current, LawEntity existing) {
        if (current.equals(existing)) {
            return;
        }

        existing.getParts().forEach(part -> deletePunishments(part.getPunishments()));
        lawRepository.delete(existing);
        createLaw(current);
    }

    /**
     * Сохранить данные наказаний закона.
     * Так как наказания могут содержать вложенную структуру, метод рекурсивно вызывает сохранение.
     *
     * @param punishments наказания.
     * @return коллекция сохраненных данных наказаний.
     */
    private Collection<LawPartPunishEntity> savePunishments(LawPartEntity part, Collection<LawPartPunishEntity> punishments) {
        if (punishments.isEmpty()) {
            return punishments;
        }

        punishments.forEach(punish -> {
            punish.setPart(part);
            punish.setOptionals(savePunishments(part, punish.getOptionals()));
        });

        punishments.forEach(punish -> {
            if (punish.getMin() != null) {
                punish.setMin(punishmentRepository.save(punish.getMin()));
            }
            if (punish.getMax() != null) {
                punish.setMax(punishmentRepository.save(punish.getMax()));
            }
        });

        return punishments;
    }

    /**
     * Удалить данные наказаний закона.
     * Так как наказания могут содержать вложенную структуру, метод рекурсивно вызывает удаление.
     *
     * @param punishments наказания.
     */
    private void deletePunishments(Collection<LawPartPunishEntity> punishments) {
        if (punishments.isEmpty()) {
            return;
        }

        punishments.forEach(punish -> deletePunishments(punish.getOptionals()));

        punishments.forEach(punish -> {
            if (punish.getMin() != null) {
                punishmentRepository.delete(punish.getMin());
            }
            if (punish.getMax() != null) {
                punishmentRepository.delete(punish.getMax());
            }
        });
    }
}
