package com.find.law.portal.configurations;

import com.find.law.portal.law.LawParser;
import com.find.law.portal.law.LawUpdater;
import com.find.law.portal.law.RemoteLawParser;
import com.find.law.portal.repositories.LawPartPunishRepository;
import com.find.law.portal.repositories.LawPartRepository;
import com.find.law.portal.repositories.LawRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.net.URL;

@Configuration
public class UpdaterConfiguration {
    private final LawRepository lawRepository;

    private final LawPartRepository lawPartRepository;

    private final LawPartPunishRepository lawPartPunishRepository;

    @Resource
    private PlatformTransactionManager transactionManager;

    @Value("${portal.laws.updater.update-on-start:false}")
    private String shouldUpdateOnStart;

    @Value("${portal.laws.updater.parser.type}")
    private String parserType;

    @Value("${portal.laws.updater.parser.path}")
    private String parserPath;

    public UpdaterConfiguration(LawRepository lawRepository, LawPartRepository lawPartRepository, LawPartPunishRepository lawPartPunishRepository) {
        this.lawRepository = lawRepository;
        this.lawPartRepository = lawPartRepository;
        this.lawPartPunishRepository = lawPartPunishRepository;
    }

    /**
     * Создать сервис обновления законов.
     *
     * @return сервис обновления.
     * @throws IOException исключение, если невозможно прочитать данные из источника
     */
    @Bean
    public LawUpdater getLawUpdater() throws IOException {
        if (parserType == null || StringUtils.isEmptyOrWhitespace(parserType)) {
            throw new IllegalArgumentException("Parser type not specified in portal settings [portal.laws.updater.parser.type]");
        }

        if (parserPath == null || StringUtils.isEmptyOrWhitespace(parserPath)) {
            throw new IllegalArgumentException("Parser path not specified in portal settings [portal.laws.updater.parser.path]");
        }

        if (!parserType.equals("remote")) {
            throw new IllegalArgumentException("Only remote request parser is available. Use [remote] parameter for [portal.laws.updater.parser.type]");
        }

        LawParser parser = new RemoteLawParser(new URL(parserPath));

        LawUpdater updater = new LawUpdater(parser, lawRepository, lawPartRepository, lawPartPunishRepository, transactionManager);
        if (shouldUpdateOnStart.equals("true")) {
            updater.update();
        }
        return updater;
    }
}
