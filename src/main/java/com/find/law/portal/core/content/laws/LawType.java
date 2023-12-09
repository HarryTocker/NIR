package com.find.law.portal.core.content.laws;

/**
 * Тип преступления для статьи закона.
 *
 * @see #INTENTIONAL
 * @see #NEGLIGENCE
 * @see #UNKNOWN
 */
public enum LawType {
    /**
     * Умышленное.
     */
    INTENTIONAL,

    /**
     * По неосторожности.
     */
    NEGLIGENCE,

    /**
     * Неизвестно.
     */
    UNKNOWN
}
