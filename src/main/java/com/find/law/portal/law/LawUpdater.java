package com.find.law.portal.law;

import com.find.law.portal.exceptions.LawUpdaterException;
import com.find.law.portal.repositories.LawPartPunishRepository;
import com.find.law.portal.repositories.LawPartRepository;
import com.find.law.portal.repositories.LawRepository;
import com.find.law.portal.repositories.entities.LawEntity;
import com.find.law.portal.repositories.entities.LawPartEntity;
import com.find.law.portal.repositories.entities.LawPartPunishEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.thymeleaf.util.StringUtils;

import java.util.List;

/**
 * Сервис обновления законов
 */
public class LawUpdater {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final LawParser lawParser;

    private final LawRepository lawRepository;

    private final LawPartRepository lawPartRepository;

    private final LawPartPunishRepository lawPartPunishRepository;

    private final PlatformTransactionManager transactionManager;

    public LawUpdater(LawParser lawParser, LawRepository lawRepository, LawPartRepository lawPartRepository, LawPartPunishRepository lawPartPunishRepository, PlatformTransactionManager transactionManager) {
        this.lawParser = lawParser;
        this.lawRepository = lawRepository;
        this.lawPartRepository = lawPartRepository;
        this.lawPartPunishRepository = lawPartPunishRepository;
        this.transactionManager = transactionManager;
    }

    /**
     * Обновить законы в базе данных.
     * При возникновении ошибки, данные не будут сохранены.
     */
    public synchronized void update() {
        TransactionTemplate template = new TransactionTemplate(transactionManager);
        template.setReadOnly(false);
        template.executeWithoutResult(transactionStatus -> {
            try {
                updateLaws();
            } catch (Exception cause) {
                logger.error("Transaction will be rolled back due to laws update error");
                throw cause;
            }
        });
    }

    private void updateLaws() {
        try {
            logger.info("Start parsing laws from source");
            List<LawData> laws = lawParser.parseLaws();

            for (LawData law : laws) {
                if (lawRepository.existsById(law.getArticle())) {
                    continue;
                }

                LawEntity lawEntity = new LawEntity();
                lawEntity.setArticle(law.getArticle());
                lawEntity.setName(law.getName());
                if (lawEntity.getArticle() == null || StringUtils.isEmptyOrWhitespace(lawEntity.getArticle())) {
                    throw new LawUpdaterException(lawEntity, "Law [%s] article is null or empty".formatted(lawEntity.getName()), null);
                } else if (law.getParts().size() == 0) {
                    throw new LawUpdaterException(lawEntity, "Law do not contains parts", null);
                }

                try {
                    lawEntity = lawRepository.save(lawEntity);
                } catch (Exception cause) {
                    throw new LawUpdaterException(lawEntity, "Could not save law", cause);
                }

                for (LawPartData part : law.getParts()) {
                    LawPartEntity partEntity = new LawPartEntity();
                    partEntity.setName(part.getName());
                    partEntity.setLaw(lawEntity);
                    try {
                        partEntity = lawPartRepository.save(partEntity);
                    } catch (Exception cause) {
                        throw new LawUpdaterException(lawEntity, partEntity, "Could not save law part", cause);
                    }

                    if (part.getPunishments().size() == 0) {
                        throw new LawUpdaterException(lawEntity, partEntity, "Parts in law do not contains punishments", null);
                    }

                    for (LawPartPunishData punish : part.getPunishments()) {
                        LawPartPunishEntity punishEntity = new LawPartPunishEntity();
                        punishEntity.setType(punish.getType());
                        punishEntity.setMinTime(punish.getMin());
                        punishEntity.setMaxTime(punish.getMax());
                        punishEntity.setDateType(punish.getDateType());
                        punishEntity.setIsLifeTime(punish.isLifeTime());
                        punishEntity.setPart(partEntity);
                        try {
                            lawPartPunishRepository.save(punishEntity);
                        } catch (Exception cause) {
                            throw new LawUpdaterException(lawEntity, partEntity, punishEntity, "Could not save punishment", cause);
                        }
                    }
                }
            }
            logger.info("Saving laws to database");
            lawRepository.flush();
            lawPartRepository.flush();
            lawPartPunishRepository.flush();
        } catch (Exception cause) {
            logger.error("Error occurred while saving laws: {}", cause.getMessage());
            throw new RuntimeException("Could not update laws", cause);
        }
    }
}
