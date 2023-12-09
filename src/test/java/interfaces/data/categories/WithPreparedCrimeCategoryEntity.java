package interfaces.data.categories;

import com.find.law.portal.repositories.entities.categories.CrimeCategoryEntity;
import com.find.law.portal.repositories.entities.punishments.PunishmentEntity;

import static com.find.law.portal.core.content.categories.CrimeCategoryComparisonType.BIGGER;
import static com.find.law.portal.core.content.categories.CrimeCategoryComparisonType.LESS;
import static com.find.law.portal.core.content.categories.CrimeCategoryType.*;
import static com.find.law.portal.core.content.laws.LawType.INTENTIONAL;
import static com.find.law.portal.core.content.laws.LawType.NEGLIGENCE;
import static com.find.law.portal.core.content.punishments.PunishmentType.YEARS;

public interface WithPreparedCrimeCategoryEntity {
    static CrimeCategoryEntity getIntentionalMinorEntity() {
        return new CrimeCategoryEntity(
                0,
                INTENTIONAL.name(),
                MINOR.name(),
                LESS.name(),
                new PunishmentEntity(0, "трех", 3D, YEARS.name())
        );
    }

    static CrimeCategoryEntity getNegligenceMinorEntity() {
        return new CrimeCategoryEntity(
                0,
                NEGLIGENCE.name(),
                MINOR.name(),
                LESS.name(),
                new PunishmentEntity(0, "трех", 3D, YEARS.name())
        );
    }

    static CrimeCategoryEntity getIntentionalMediumEntity() {
        return new CrimeCategoryEntity(
                0,
                INTENTIONAL.name(),
                MEDIUM.name(),
                LESS.name(),
                new PunishmentEntity(0, "пяти", 5D, YEARS.name())
        );
    }

    static CrimeCategoryEntity getNegligenceMediumEntity() {
        return new CrimeCategoryEntity(
                0,
                NEGLIGENCE.name(),
                MEDIUM.name(),
                LESS.name(),
                new PunishmentEntity(0, "десяти", 10D, YEARS.name())
        );
    }

    static CrimeCategoryEntity getIntentionalSeriousEntity() {
        return new CrimeCategoryEntity(
                0,
                INTENTIONAL.name(),
                SERIOUS.name(),
                LESS.name(),
                new PunishmentEntity(0, "десяти", 10D, YEARS.name())
        );
    }

    static CrimeCategoryEntity getNegligenceSeriousEntity() {
        return new CrimeCategoryEntity(
                0,
                NEGLIGENCE.name(),
                SERIOUS.name(),
                LESS.name(),
                new PunishmentEntity(0, "пятнадцати", 15D, YEARS.name())
        );
    }

    static CrimeCategoryEntity getIntentionalParticularlySeriousEntity() {
        return new CrimeCategoryEntity(
                0,
                INTENTIONAL.name(),
                PARTICULARLY_SERIOUS.name(),
                BIGGER.name(),
                new PunishmentEntity(0, "десяти", 10D, YEARS.name())
        );
    }
}
