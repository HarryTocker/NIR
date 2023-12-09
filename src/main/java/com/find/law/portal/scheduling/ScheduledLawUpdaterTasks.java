package com.find.law.portal.scheduling;

import com.find.law.portal.core.updater.LawUpdater;
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
    private boolean shouldUpdateLaws;

    @Value("${portal.laws.updater.update-on-start:false}")
    private boolean shouldUpdateLawsOnStart;

    public ScheduledLawUpdaterTasks(LawUpdater lawUpdater) {
        this.lawUpdater = lawUpdater;
    }

    /**
     * Обновить законы раз в сутки.
     */
    @Scheduled(timeUnit = TimeUnit.HOURS, fixedRate = 24)
    public void updateLaws() {
        logger.info("Start update laws by scheduled task.");

        // Если обновление законов отключено через настройки - пропускаем задачу.
        if (!shouldUpdateLaws) {
            logger.info("Update laws disabled by settings.");
            return;
        }

        // Если обновление законов при запуске отключено через настройки - пропускаем задачу.
        if (updateCount++ == 0 && !shouldUpdateLawsOnStart) {
            logger.info("Update laws on startup disabled by setting.");
            return;
        }

        logger.info("Update task number [{}] is started.", updateCount);
        try {
            lawUpdater.update();
            logger.info("Laws have been successfully updated by task number [{}]", updateCount);
        } catch (Throwable exception) {
            logger.error("An error occurred while updating laws by task number [{}]: {}", updateCount, exception.toString());
        }
    }
}
