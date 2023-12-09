package com.find.law.portal.configurations;

import com.find.law.portal.core.parsers.LawDataContentParserProvider;
import com.find.law.portal.core.updater.CrimeCategoryRepositoryUpdater;
import com.find.law.portal.core.updater.LawEntityRepositoryUpdater;
import com.find.law.portal.core.updater.LawUpdater;
import com.find.law.portal.core.updater.repository.CrimeCategoryRepositoryUpdaterImpl;
import com.find.law.portal.core.updater.repository.LawEntityRepositoryUpdaterImpl;
import com.find.law.portal.mappers.GenericBeanMapper;
import com.find.law.portal.repositories.CrimeCategoryRepository;
import com.find.law.portal.repositories.LawRepository;
import com.find.law.portal.repositories.PunishmentRepository;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Конфигурация сервиса обновления законов.
 */
@Configuration
public class LawUpdaterConfiguration {
    @Resource
    private PlatformTransactionManager transactionManager;

    /**
     * Создать сервис обновления законов.
     *
     * @return сервис обновления законов.
     */
    @Bean
    public LawUpdater getLawUpdater(
            LawDataContentParserProvider provider,
            LawRepository lawRepository,
            PunishmentRepository punishmentRepository,
            CrimeCategoryRepository crimeCategoryRepository,
            GenericBeanMapper mapper)
    {
        LawEntityRepositoryUpdater lawEntitiesUpdater =
                new LawEntityRepositoryUpdaterImpl(lawRepository, punishmentRepository, mapper);

        CrimeCategoryRepositoryUpdater crimeCategoriesUpdater =
                new CrimeCategoryRepositoryUpdaterImpl(punishmentRepository, crimeCategoryRepository, mapper);

        return new LawUpdater(
                provider.get(),
                transactionManager,
                lawEntitiesUpdater,
                crimeCategoriesUpdater
        );
    }
}
