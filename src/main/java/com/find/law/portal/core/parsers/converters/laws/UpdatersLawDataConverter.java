package com.find.law.portal.core.parsers.converters.laws;

import com.find.law.portal.core.parsers.converters.LawDataConverter;
import com.find.law.portal.core.parsers.converters.updater.DataUpdater;
import com.find.law.portal.exceptions.LawDataUpdaterException;
import com.find.law.portal.core.parsers.converters.ArticleDataLines;
import com.find.law.portal.core.content.laws.LawData;
import com.find.law.portal.exceptions.law.converter.LawDataConverterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * Конвертер {@link ArticleDataLines} в данные закона {@link LawData}.
 * <br>
 * Основан на последовательном обновлении данных через коллекцию {@link DataUpdater}.
 */
public class UpdatersLawDataConverter implements LawDataConverter {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Коллекция парсеров, основанных на обновлении данных.
     */
    private final List<DataUpdater<LawData>> updaters;

    public UpdatersLawDataConverter(List<DataUpdater<LawData>> updaters) {
        this.updaters = updaters;
    }

    @Override
    public List<LawData> convert(List<ArticleDataLines> lawData) throws LawDataConverterException {
        return lawData.stream().map(this::convert).filter(Optional::isPresent).map(Optional::get).toList();
    }

    /**
     * Конвертировать подготовленные данные статьи в данные закона.
     * <br>
     * При возникновении исключения {@link LawDataUpdaterException}
     *
     * @param dataLines подготовленные данные.
     * @return данные закона.
     */
    private Optional<LawData> convert(ArticleDataLines dataLines) {
        LawData law = new LawData();
        try {
            for (DataUpdater<LawData> updater : updaters) {
                updater.update(law, dataLines);
            }
        } catch (LawDataUpdaterException cause) {
            logger.warn("Could not update law [{}]: {}", dataLines.article(), cause.getMessage());
            return Optional.empty();
        } catch (Throwable cause) {
            logger.error("Critical convert error [{}]: {}", dataLines.article(), cause.getMessage());
            throw new LawDataConverterException(law, "Critical convert error", cause);
        }

        return Optional.of(law);
    }
}
