package interfaces.data.categories;

import static com.find.law.portal.core.content.laws.LawType.*;
import static com.find.law.portal.core.content.punishments.PunishmentType.*;
import static com.find.law.portal.core.content.categories.CrimeCategoryType.*;
import static com.find.law.portal.core.content.categories.CrimeCategoryComparisonType.*;

import com.find.law.portal.core.content.categories.CrimeCategoryData;
import com.find.law.portal.core.content.punishments.PunishmentData;

public interface WithPreparedCrimeCategoryData {
    static CrimeCategoryData getIntentionalMinorData() {
        return new CrimeCategoryData(
                INTENTIONAL,
                MINOR,
                LESS,
                new PunishmentData("трех", YEARS)
        );
    }

    static CrimeCategoryData getNegligenceMinorData() {
        return new CrimeCategoryData(
                NEGLIGENCE,
                MINOR,
                LESS,
                new PunishmentData("трех", YEARS)
        );
    }

    static CrimeCategoryData getIntentionalMediumData() {
        return new CrimeCategoryData(
                INTENTIONAL,
                MEDIUM,
                LESS,
                new PunishmentData("пяти", YEARS)
        );
    }

    static CrimeCategoryData getNegligenceMediumData() {
        return new CrimeCategoryData(
                NEGLIGENCE,
                MEDIUM,
                LESS,
                new PunishmentData("десяти", YEARS)
        );
    }

    static CrimeCategoryData getIntentionalSeriousData() {
        return new CrimeCategoryData(
                INTENTIONAL,
                SERIOUS,
                LESS,
                new PunishmentData("десяти", YEARS)
        );
    }

    static CrimeCategoryData getNegligenceSeriousData() {
        return new CrimeCategoryData(
                NEGLIGENCE,
                SERIOUS,
                LESS,
                new PunishmentData("пятнадцати", YEARS)
        );
    }

    static CrimeCategoryData getIntentionalParticularlySeriousData() {
        return new CrimeCategoryData(
                INTENTIONAL,
                PARTICULARLY_SERIOUS,
                BIGGER,
                new PunishmentData("десяти", YEARS)
        );
    }
}
