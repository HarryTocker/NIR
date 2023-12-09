package com.find.law.portal.core.updater;

import com.find.law.portal.core.parsers.LawDataContentParser;
import com.find.law.portal.core.content.LawDataContent;
import com.find.law.portal.core.content.categories.CrimeCategoryData;
import com.find.law.portal.core.content.laws.LawData;
import com.find.law.portal.exceptions.CrimeCategoryParseException;
import com.find.law.portal.exceptions.LawParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.IOException;
import java.util.List;

/**
 * Сервис обновления законов
 */
public class LawUpdater {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final LawDataContentParser contentParser;

    private final PlatformTransactionManager transactionManager;

    private final LawEntityRepositoryUpdater lawEntitiesUpdater;

    private final CrimeCategoryRepositoryUpdater crimeCategoriesUpdater;

    public LawUpdater(
            LawDataContentParser contentParser,
            PlatformTransactionManager transactionManager,
            LawEntityRepositoryUpdater lawEntitiesUpdater,
            CrimeCategoryRepositoryUpdater crimeCategoriesUpdater
    )
    {
        this.contentParser = contentParser;
        this.transactionManager = transactionManager;
        this.lawEntitiesUpdater = lawEntitiesUpdater;
        this.crimeCategoriesUpdater = crimeCategoriesUpdater;
    }

    /**
     * Обновить законы в базе данных.
     * При возникновении ошибки, данные не будут сохранены.
     */
    public synchronized void update() {
        // Создание транзакции в рамках которой будет выполняться обновление.
        // Необходимо для того, чтобы при возникновении ошибки данные не были сохранены в БД и произошел откат.
        var template = new TransactionTemplate(transactionManager);
        template.setReadOnly(false);
        template.executeWithoutResult(status -> {
            try {
                updateContent();
            } catch (Throwable cause) {
                logger.error("Transaction will be rolled back due to laws update error.");
                throw cause;
            }
        });
    }

    /**
     * Получить распарсенные данные законов и создать/обновить их в базе данных.
     */
    private void updateContent() {
        try {
            // Получаем распарсенные законы.
            var content = contentParser.parse();
            // Обновляем категории преступлений.
            updateCategories(content.getCategories());
            // Обновляем законы.
            updateLaws(content.getLaws());
        } catch (IOException cause) {
            logger.error("Could not get laws data for parser: {}", cause.getMessage());
            throw new RuntimeException("Could not get laws data for parser", cause);
        } catch (CrimeCategoryParseException cause) {
            logger.error("Could not parse crime categories: {}", cause.getMessage());
            throw new RuntimeException("Could not parse crime categories", cause);
        } catch (LawParseException cause) {
            logger.error("Could not parse laws: {}", cause.getMessage());
            throw new RuntimeException("Could not parse laws", cause);
        }
    }

    /**
     * Обновить категории преступлений.
     * @param categories категории преступлений.
     */
    private void updateCategories(List<CrimeCategoryData> categories) {
        crimeCategoriesUpdater.update(categories);
        logger.info("All crime categories saved to database");
    }

    /**
     * Обновить законы.
     * @param laws законы.
     */
    private void updateLaws(List<LawData> laws) {
        lawEntitiesUpdater.update(laws);
        logger.info("All laws saved to database");
    }
}
