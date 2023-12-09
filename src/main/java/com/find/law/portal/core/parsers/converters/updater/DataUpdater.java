package com.find.law.portal.core.parsers.converters.updater;

import com.find.law.portal.core.parsers.converters.ArticleDataLines;
import com.find.law.portal.exceptions.LawDataUpdaterException;

/**
 * Интерфейс обновления данных для определенного типа. Обновление данных происходит из подготовленных данных статьи.
 *
 * @see #update(Object, ArticleDataLines)
 * @see #priority()
 * @param <T> тип, для которого будет вызвано обновление данных.
 */
public interface DataUpdater<T> {
    /**
     * Обновить данные из подготовленных данных статьи {@link ArticleDataLines}.
     * <br>
     * Метод может изменять переданные данные для обновления в зависимости от подготовленных данных статьи.
     * Данные для обновления, в данном случае, являются mutable объектом.
     *
     * @param data данные для обновления.
     * @param articleData подготовленные данные статьи.
     * @throws LawDataUpdaterException исключение при обновлении данных.
     * Выбрасывается, если произошла предсказуемая ошибка из-за которой обновление данных невозможно.
     * Предполагается, что при возникновении данной ошибки, обновление данных будет пропущено для всей статьи {@link ArticleDataLines}
     */
    void update(T data, ArticleDataLines articleData) throws LawDataUpdaterException;

    /**
     * Получить приоритет вызова обновления данных реализуемым классом.
     * <br>
     * При высоком приоритете обновление данных в реализуемых классах будет вызвано раньше.
     */
    DataUpdaterPriority priority();
}
