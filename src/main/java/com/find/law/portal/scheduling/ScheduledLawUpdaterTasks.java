package com.find.law.portal.scheduling;

import com.find.law.portal.law.LawUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Задачи по обновлению законов.
 */
@Component
public class ScheduledLawUpdaterTasks {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final LawUpdater lawUpdater;

    private int updateCount = 0;

    @Value("${portal.laws.updater.update-enable:true}")
    private String shouldUpdateLaws;

    @Value("${portal.laws.updater.update-on-start:false}")
    private String shouldUpdateLawsOnStart;

    public ScheduledLawUpdaterTasks(LawUpdater lawUpdater) {
        this.lawUpdater = lawUpdater;
    }

    /**
     * Обновить законы раз в сутки.
     */
    @Scheduled(timeUnit = TimeUnit.HOURS, fixedRate = 24)
    public void updateLaws() {
        logger.info("Start update laws by scheduled task");

        // Если обновление законов отключено через настройки - пропускаем задачу.
        if (!shouldUpdateLaws.equals("true")) {
            logger.info("Update laws disabled by settings.");
            return;
        }

        // Если обновление законов при запуске отключено через настройки - пропускаем задачу.
        if (updateCount++ == 0 && !shouldUpdateLawsOnStart.equals("true")) {
            logger.info("Update laws on startup disabled by setting.");
            return;
        }

        try {
            lawUpdater.update();
        } catch (Exception exception) {
            logger.error("An error occurred while updating laws: {}", exception);
            return;
        }

        logger.info("Laws have been successfully updated.");
    }
}
