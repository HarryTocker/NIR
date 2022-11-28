package com.find.law.portal.exceptions;

import com.find.law.portal.repositories.entities.LawEntity;
import com.find.law.portal.repositories.entities.LawPartEntity;
import com.find.law.portal.repositories.entities.LawPartPunishEntity;

public class LawUpdaterException extends RuntimeException {
    public LawUpdaterException(LawEntity law, String message, Exception cause) {
        super("Could not update law [%s]: %s".formatted(law.getArticle(), message), cause);
    }

    public LawUpdaterException(LawEntity law, LawPartEntity lawPart, String message, Exception cause) {
        super("Could not update law [%s] part [%s]: %s".formatted(law.getArticle(), lawPart.getName(), message), cause);
    }

    public LawUpdaterException(LawEntity law, LawPartEntity lawPart, LawPartPunishEntity partPunish, String message, Exception cause) {
        super("Could not update law [%s] part [%s] punishment [%s]: %s".formatted(law.getArticle(), lawPart.getName(), partPunish.getType(), message), cause);
    }
}
