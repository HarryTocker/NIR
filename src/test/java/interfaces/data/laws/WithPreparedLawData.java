package interfaces.data.laws;

import static com.find.law.portal.core.content.laws.LawType.*;
import static com.find.law.portal.core.content.laws.LawPartPunishType.*;
import static com.find.law.portal.core.content.punishments.PunishmentType.*;

import com.find.law.portal.core.content.laws.LawData;
import com.find.law.portal.core.content.laws.LawPartData;
import com.find.law.portal.core.content.laws.LawPartPunishData;
import com.find.law.portal.core.content.punishments.PunishmentData;

import java.util.List;

public interface WithPreparedLawData {

    /**
     * Данные статьи 105
     */
    static LawData getLawData105() {
        return new LawData("105", "Убийство", INTENTIONAL, List.of(
                new LawPartData("Убийство, то есть умышленное причинение смерти другому человеку", List.of(
                        new LawPartPunishData(
                                DEPRIVATION_OF_LIBERTY,
                                new PunishmentData("шести", YEARS),
                                new PunishmentData("пятнадцати", YEARS),
                                List.of(
                                        new LawPartPunishData(
                                                RESTRICTION_OF_FREEDOM,
                                                null,
                                                new PunishmentData("двух", YEARS)
                                        )
                                )
                        )
                )),

                new LawPartData("""
                                        Убийство:
                                        двух или более лиц
                                        лица или его близких в связи с осуществлением данным лицом служебной деятельности или выполнением общественного долга
                                        малолетнего или иного лица, заведомо для виновного находящегося в беспомощном состоянии, а равно сопряженное с похищением человека
                                        женщины, заведомо для виновного находящейся в состоянии беременности
                                        совершенное с особой жестокостью
                                        совершенное общеопасным способом""", List.of(
                        new LawPartPunishData(
                                DEPRIVATION_OF_LIBERTY,
                                new PunishmentData("восьми", YEARS),
                                new PunishmentData("двадцати", YEARS),
                                List.of(
                                        new LawPartPunishData(
                                                RESTRICTION_OF_FREEDOM,
                                                new PunishmentData("одного", YEARS),
                                                new PunishmentData("двух", YEARS)
                                        )
                                )
                        ),

                        new LawPartPunishData()
                ))
        ));
    }

    /**
     * Данные статьи 106
     */
    static LawData getLawData106() {
        return new LawData("106", "Убийство матерью новорожденного ребенка", INTENTIONAL, List.of(
                new LawPartData("Убийство матерью новорожденного ребенка во время или сразу же после родов, а равно убийство матерью новорожденного ребенка в условиях психотравмирующей ситуации или в состоянии психического расстройства, не исключающего вменяемости",  List.of(
                        new LawPartPunishData(
                                RESTRICTION_OF_FREEDOM,
                                new PunishmentData("двух", YEARS),
                                new PunishmentData("четырех", YEARS)
                        ),
                        new LawPartPunishData(
                                FORCED_LABOR,
                                null,
                                new PunishmentData("пяти", YEARS)
                        ),
                        new LawPartPunishData(
                                DEPRIVATION_OF_LIBERTY,
                                null,
                                new PunishmentData("пяти", YEARS)
                        )
                ))
        ));
    }

    /**
     * Данные статьи 114
     */
    static LawData getLawData114() {
        return new LawData("114", "Причинение тяжкого или средней тяжести вреда здоровью при превышении пределов необходимой обороны либо при превышении мер, необходимых для задержания лица, совершившего преступление", INTENTIONAL, List.of(
                new LawPartData("Умышленное причинение тяжкого вреда здоровью, совершенное при превышении пределов необходимой обороны", List.of(
                        new LawPartPunishData(
                                CORRECTIONAL_LABOR,
                                null,
                                new PunishmentData("одного", YEARS)
                        ),
                        new LawPartPunishData(
                                RESTRICTION_OF_FREEDOM,
                                null,
                                new PunishmentData("одного", YEARS)
                        ),
                        new LawPartPunishData(
                                FORCED_LABOR,
                                null,
                                new PunishmentData("одного", YEARS)
                        ),
                        new LawPartPunishData(
                                DEPRIVATION_OF_LIBERTY,
                                null,
                                new PunishmentData("одного", YEARS)
                        )
                )),

                new LawPartData("Умышленное причинение тяжкого или средней тяжести вреда здоровью, совершенное при превышении мер, необходимых для задержания лица, совершившего преступление", List.of(
                        new LawPartPunishData(
                                CORRECTIONAL_LABOR,
                                null,
                                new PunishmentData("двух", YEARS)
                        ),
                        new LawPartPunishData(
                                RESTRICTION_OF_FREEDOM,
                                null,
                                new PunishmentData("двух", YEARS)
                        ),
                        new LawPartPunishData(
                                FORCED_LABOR,
                                null,
                                new PunishmentData("двух", YEARS)
                        ),
                        new LawPartPunishData(
                                DEPRIVATION_OF_LIBERTY,
                                null,
                                new PunishmentData("двух", YEARS)
                        )
                ))
        ));
    }

    /**
     * Данные статьи 115
     */
    static LawData getLawData115() {
        return new LawData("115", "Умышленное причинение легкого вреда здоровью", INTENTIONAL, List.of(
                new LawPartData("Умышленное причинение легкого вреда здоровью, вызвавшего кратковременное расстройство здоровья или незначительную стойкую утрату общей трудоспособности", List.of(
                        new LawPartPunishData(
                                FINE,
                                null,
                                new PunishmentData("сорока", THOUSAND_RUBLES)
                        ),
                        new LawPartPunishData(
                                COMPULSORY_WORKS,
                                null,
                                new PunishmentData("четырехсот восьмидесяти", HOURS)
                        ),
                        new LawPartPunishData(
                                CORRECTIONAL_LABOR,
                                null,
                                new PunishmentData("одного", YEARS)
                        ),
                        new LawPartPunishData(
                                ARREST,
                                null,
                                new PunishmentData("четырех", MONTHS)
                        )
                )),

                new LawPartData("""
                                        То же деяние, совершенное:
                                        из хулиганских побуждений
                                        по мотивам политической, идеологической, расовой, национальной или религиозной ненависти или вражды либо по мотивам ненависти или вражды в отношении какой-либо социальной группы
                                        с применением оружия или предметов, используемых в качестве оружия
                                        в отношении лица или его близких в связи с осуществлением данным лицом служебной деятельности или выполнением общественного долга""", List.of(
                        new LawPartPunishData(
                                COMPULSORY_WORKS,
                                null,
                                new PunishmentData("трехсот шестидесяти", HOURS)
                        ),
                        new LawPartPunishData(
                                CORRECTIONAL_LABOR,
                                null,
                                new PunishmentData("одного", YEARS)
                        ),
                        new LawPartPunishData(
                                RESTRICTION_OF_FREEDOM,
                                null,
                                new PunishmentData("двух", YEARS)
                        ),
                        new LawPartPunishData(
                                FORCED_LABOR,
                                null,
                                new PunishmentData("двух", YEARS)
                        ),
                        new LawPartPunishData(
                                ARREST,
                                null,
                                new PunishmentData("шести", MONTHS)
                        ),
                        new LawPartPunishData(
                                DEPRIVATION_OF_LIBERTY,
                                null,
                                new PunishmentData("двух", YEARS)
                        )
                ))
        ));
    }

    /**
     * Данные статьи 116
     */
    static LawData getLawData116() {
        return new LawData("116", "Побои", INTENTIONAL, List.of(
                new LawPartData("Побои или иные насильственные действия, причинившие физическую боль, но не повлекшие последствий, указанных в статье 115 настоящего Кодекса, совершенные из хулиганских побуждений, а равно по мотивам политической, идеологической, расовой, национальной или религиозной ненависти или вражды либо по мотивам ненависти или вражды в отношении какой-либо социальной группы", List.of(
                        new LawPartPunishData(
                                COMPULSORY_WORKS,
                                null,
                                new PunishmentData("трехсот шестидесяти", HOURS)
                        ),
                        new LawPartPunishData(
                                CORRECTIONAL_LABOR,
                                null,
                                new PunishmentData("одного", YEARS)
                        ),
                        new LawPartPunishData(
                                RESTRICTION_OF_FREEDOM,
                                null,
                                new PunishmentData("двух", YEARS)
                        ),
                        new LawPartPunishData(
                                FORCED_LABOR,
                                null,
                                new PunishmentData("двух", YEARS)
                        ),
                        new LawPartPunishData(
                                ARREST,
                                null,
                                new PunishmentData("шести", MONTHS)
                        ),
                        new LawPartPunishData(
                                DEPRIVATION_OF_LIBERTY,
                                null,
                                new PunishmentData("двух", YEARS)
                        )
                ))
        ));
    }

    /**
     * Данные статьи 124.1
     */
    static LawData getLawData124P1() {
        return new LawData("124.1", "Воспрепятствование оказанию медицинской помощи", INTENTIONAL, List.of(
                new LawPartData("Воспрепятствование в какой бы то ни было форме законной деятельности медицинского работника по оказанию медицинской помощи, если это повлекло по неосторожности причинение тяжкого вреда здоровью пациента", List.of(
                        new LawPartPunishData(
                                FINE,
                                null,
                                new PunishmentData("восьмидесяти", THOUSAND_RUBLES)
                        ),
                        new LawPartPunishData(
                                RESTRICTION_OF_FREEDOM,
                                null,
                                new PunishmentData("трех", YEARS)
                        ),
                        new LawPartPunishData(
                                FORCED_LABOR,
                                null,
                                new PunishmentData("двух", YEARS)
                        ),
                        new LawPartPunishData(
                                ARREST,
                                null,
                                new PunishmentData("шести", MONTHS)
                        ),
                        new LawPartPunishData(
                                DEPRIVATION_OF_LIBERTY,
                                null,
                                new PunishmentData("двух", YEARS)
                        )
                )),
                new LawPartData("То же деяние, если оно повлекло по неосторожности смерть пациента", List.of(
                        new LawPartPunishData(
                                RESTRICTION_OF_FREEDOM,
                                null,
                                new PunishmentData("четырех", YEARS)
                        ),
                        new LawPartPunishData(
                                FORCED_LABOR,
                                null,
                                new PunishmentData("четырех", YEARS)
                        ),
                        new LawPartPunishData(
                                DEPRIVATION_OF_LIBERTY,
                                null,
                                new PunishmentData("четырех", YEARS)
                        )
                ))
        ));
    }

    /**
     * Данные статьи 158
     */
    static LawData getLawData158() {
        return new LawData("158", "Кража", INTENTIONAL, List.of(
                new LawPartData("Кража, то есть тайное хищение чужого имущества", List.of(
                        new LawPartPunishData(
                                FINE,
                                null,
                                new PunishmentData("восьмидесяти", THOUSAND_RUBLES)
                        ),
                        new LawPartPunishData(
                                COMPULSORY_WORKS,
                                null,
                                new PunishmentData("трехсот шестидесяти", HOURS)
                        ),
                        new LawPartPunishData(
                                CORRECTIONAL_LABOR,
                                null,
                                new PunishmentData("одного", YEARS)
                        ),
                        new LawPartPunishData(
                                RESTRICTION_OF_FREEDOM,
                                null,
                                new PunishmentData("двух", YEARS)
                        ),
                        new LawPartPunishData(
                                FORCED_LABOR,
                                null,
                                new PunishmentData("двух", YEARS)
                        ),
                        new LawPartPunishData(
                                ARREST,
                                null,
                                new PunishmentData("четырех", MONTHS)
                        ),
                        new LawPartPunishData(
                                DEPRIVATION_OF_LIBERTY,
                                null,
                                new PunishmentData("двух", YEARS)
                        )
                )),

                new LawPartData("""
                                        Кража, совершенная:
                                        группой лиц по предварительному сговору
                                        с незаконным проникновением в помещение либо иное хранилище
                                        с причинением значительного ущерба гражданину
                                        из одежды, сумки или другой ручной клади, находившихся при потерпевшем""", List.of(
                        new LawPartPunishData(
                                FINE,
                                null,
                                new PunishmentData("двухсот", THOUSAND_RUBLES)
                        ),
                        new LawPartPunishData(
                                COMPULSORY_WORKS,
                                null,
                                new PunishmentData("четырехсот восьмидесяти", HOURS)
                        ),
                        new LawPartPunishData(
                                CORRECTIONAL_LABOR,
                                null,
                                new PunishmentData("двух", YEARS)
                        ),
                        new LawPartPunishData(
                                FORCED_LABOR,
                                null,
                                new PunishmentData("пяти", YEARS),
                                List.of(
                                        new LawPartPunishData(
                                                RESTRICTION_OF_FREEDOM,
                                                null,
                                                new PunishmentData("одного", YEARS)
                                        )
                                )
                        ),
                        new LawPartPunishData(
                                DEPRIVATION_OF_LIBERTY,
                                null,
                                new PunishmentData("пяти", YEARS),
                                List.of(
                                        new LawPartPunishData(
                                                RESTRICTION_OF_FREEDOM,
                                                null,
                                                new PunishmentData("одного", YEARS)
                                        )
                                )
                        )
                )),

                new LawPartData("""
                                        Кража, совершенная:
                                        с незаконным проникновением в жилище
                                        из нефтепровода, нефтепродуктопровода, газопровода
                                        в крупном размере
                                        с банковского счета, а равно в отношении электронных денежных средств""", List.of(
                        new LawPartPunishData(
                                FINE,
                                new PunishmentData("ста", THOUSAND_RUBLES),
                                new PunishmentData("пятисот", THOUSAND_RUBLES)
                        ),
                        new LawPartPunishData(
                                FORCED_LABOR,
                                null,
                                new PunishmentData("пяти", YEARS),
                                List.of(
                                        new LawPartPunishData(
                                                RESTRICTION_OF_FREEDOM,
                                                null,
                                                new PunishmentData("одного", YEARS)
                                        )
                                )
                        ),
                        new LawPartPunishData(
                                DEPRIVATION_OF_LIBERTY,
                                null,
                                new PunishmentData("шести", YEARS),
                                List.of(
                                        new LawPartPunishData(
                                                FINE,
                                                null,
                                                new PunishmentData("восьмидесяти", THOUSAND_RUBLES)
                                        ),
                                        new LawPartPunishData(
                                                RESTRICTION_OF_FREEDOM,
                                                null,
                                                new PunishmentData("одного", YEARS)
                                        )
                                )
                        )
                )),

                new LawPartData("""
                                        Кража, совершенная:
                                        организованной группой
                                        в особо крупном размере""", List.of(
                        new LawPartPunishData(
                                DEPRIVATION_OF_LIBERTY,
                                null,
                                new PunishmentData("десяти", YEARS),
                                List.of(
                                        new LawPartPunishData(
                                                FINE,
                                                null,
                                                new PunishmentData("одного", MILLION_RUBLES)
                                        ),
                                        new LawPartPunishData(
                                                RESTRICTION_OF_FREEDOM,
                                                null,
                                                new PunishmentData("двух", YEARS)
                                        )
                                )
                        )
                ))
        ));
    }

    /**
     * Данные статьи 200.1
     */
    static LawData getLawData200P1() {
        return new LawData("200.1", "Контрабанда наличных денежных средств и (или) денежных инструментов", INTENTIONAL, List.of(
                new LawPartData("Незаконное перемещение через таможенную границу Таможенного союза в рамках ЕврАзЭС наличных денежных средств и (или) денежных инструментов, совершенное в крупном размере", List.of(
                        new LawPartPunishData(
                                FINE,
                                new PunishmentData("трехкратной", AMOUNT),
                                new PunishmentData("десятикратной", AMOUNT)
                        ),
                        new LawPartPunishData(
                                RESTRICTION_OF_FREEDOM,
                                null,
                                new PunishmentData("двух", YEARS)
                        ),
                        new LawPartPunishData(
                                FORCED_LABOR,
                                null,
                                new PunishmentData("двух", YEARS)
                        )
                )),

                new LawPartData("""
                                        Деяние, предусмотренное частью первой настоящей статьи, совершенное:
                                        в особо крупном размере
                                        группой лиц""", List.of(
                        new LawPartPunishData(
                                FINE,
                                new PunishmentData("десятикратной", AMOUNT),
                                new PunishmentData("пятнадцатикратной", AMOUNT)
                        ),
                        new LawPartPunishData(
                                RESTRICTION_OF_FREEDOM,
                                null,
                                new PunishmentData("четырех", YEARS)
                        ),
                        new LawPartPunishData(
                                FORCED_LABOR,
                                null,
                                new PunishmentData("четырех", YEARS)
                        )
                ))
        ));
    }

    /**
     * Данные статьи 333
     */
    static LawData getLawData333() {
        return new LawData("333", "Сопротивление начальнику или принуждение его к нарушению обязанностей военной службы", INTENTIONAL, List.of(
                new LawPartData("Сопротивление начальнику, а равно иному лицу, исполняющему возложенные на него обязанности военной службы, или принуждение его к нарушению этих обязанностей, сопряженные с насилием или с угрозой его применения", List.of(
                        new LawPartPunishData(
                                MILITARY_SERVICE_RESTRICTION,
                                null,
                                new PunishmentData("двух", YEARS)
                        ),
                        new LawPartPunishData(
                                DETENTION_IN_DISCIPLINARY_UNIT,
                                null,
                                new PunishmentData("двух", YEARS)
                        ),
                        new LawPartPunishData(
                                DEPRIVATION_OF_LIBERTY,
                                null,
                                new PunishmentData("пяти", YEARS)
                        )
                )),

                new LawPartData("""
                                        Те же деяния, совершенные:
                                        группой лиц, группой лиц по предварительному сговору или организованной группой
                                        с применением оружия
                                        с причинением тяжкого или средней тяжести вреда здоровью либо иных тяжких последствий""", List.of(
                        new LawPartPunishData(
                                DEPRIVATION_OF_LIBERTY,
                                null,
                                new PunishmentData("восьми", YEARS)
                        )
                )),

                new LawPartData("Деяния, предусмотренные частями первой или второй настоящей статьи, совершенные в период мобилизации или военного положения, в военное время либо в условиях вооруженного конфликта или ведения боевых действий", List.of(
                        new LawPartPunishData(
                                DEPRIVATION_OF_LIBERTY,
                                new PunishmentData("пяти", YEARS),
                                new PunishmentData("пятнадцати", YEARS)
                        )
                ))
        ));
    }

    /**
     * Данные статьи 334
     */
    static LawData getLawData334() {
        return new LawData("334", "Насильственные действия в отношении начальника", INTENTIONAL, List.of(
                new LawPartData("Нанесение побоев или применение иного насилия в отношении начальника, совершенные во время исполнения им обязанностей военной службы или в связи с исполнением этих обязанностей", List.of(
                        new LawPartPunishData(
                                MILITARY_SERVICE_RESTRICTION,
                                null,
                                new PunishmentData("двух", YEARS)
                        ),
                        new LawPartPunishData(
                                DETENTION_IN_DISCIPLINARY_UNIT,
                                null,
                                new PunishmentData("двух", YEARS)
                        ),
                        new LawPartPunishData(
                                DEPRIVATION_OF_LIBERTY,
                                null,
                                new PunishmentData("пяти", YEARS)
                        )
                )),

                new LawPartData("""
                        Те же деяния, совершенные:
                        группой лиц, группой лиц по предварительному сговору или организованной группой
                        с применением оружия
                        с причинением тяжкого или средней тяжести вреда здоровью либо иных тяжких последствий""", List.of(
                                new LawPartPunishData(
                                        DEPRIVATION_OF_LIBERTY,
                                        null,
                                        new PunishmentData("восьми", YEARS)
                                )
                )),

                new LawPartData("Деяния, предусмотренные частями первой или второй настоящей статьи, совершенные в период мобилизации или военного положения, в военное время либо в условиях вооруженного конфликта или ведения боевых действий", List.of(
                        new LawPartPunishData(
                                DEPRIVATION_OF_LIBERTY,
                                new PunishmentData("пяти", YEARS),
                                new PunishmentData("пятнадцати", YEARS)
                        )
                ))
        ));
    }

    /**
     * Данные статьи 335
     */
    static LawData getLawData335() {
        return new LawData("335", "Нарушение уставных правил взаимоотношений между военнослужащими при отсутствии между ними отношений подчиненности", INTENTIONAL, List.of(
                new LawPartData("Нарушение уставных правил взаимоотношений между военнослужащими при отсутствии между ними отношений подчиненности, связанное с унижением чести и достоинства или издевательством над потерпевшим либо сопряженное с насилием", List.of(
                        new LawPartPunishData(
                                DETENTION_IN_DISCIPLINARY_UNIT,
                                null,
                                new PunishmentData("двух", YEARS)
                        ),
                        new LawPartPunishData(
                                DEPRIVATION_OF_LIBERTY,
                                null,
                                new PunishmentData("трех", YEARS)
                        )
                )),

                new LawPartData("""
                        То же деяние, совершенное:
                        в отношении двух или более лиц
                        группой лиц, группой лиц по предварительному сговору или организованной группой
                        с применением оружия
                        с причинением средней тяжести вреда здоровью""", List.of(
                                new LawPartPunishData(
                                        DEPRIVATION_OF_LIBERTY,
                                        null,
                                        new PunishmentData("пяти", YEARS)
                                )
                )),

                new LawPartData("Деяния, предусмотренные частями первой или второй настоящей статьи, повлекшие тяжкие последствия", List.of(
                        new LawPartPunishData(
                                DEPRIVATION_OF_LIBERTY,
                                null,
                                new PunishmentData("десяти", YEARS)
                        )
                ))
        ));
    }
}
