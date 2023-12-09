package interfaces.data.laws;

import static com.find.law.portal.core.content.laws.LawType.*;
import static com.find.law.portal.core.content.laws.LawPartPunishType.*;
import static com.find.law.portal.core.content.punishments.PunishmentType.*;

import com.find.law.portal.core.content.punishments.PunishmentData;
import com.find.law.portal.repositories.entities.laws.LawEntity;
import com.find.law.portal.repositories.entities.laws.LawPartEntity;
import com.find.law.portal.repositories.entities.laws.LawPartPunishEntity;
import com.find.law.portal.repositories.entities.punishments.PunishmentEntity;

import java.util.ArrayList;
import java.util.List;


public interface WithPreparedLawEntity {
    /**
     * Данные сущности статьи 105
     */
    static LawEntity getLawEntity105() {
        LawEntity law = new LawEntity("105", "Убийство", INTENTIONAL.name(), null);
        List<LawPartEntity> parts = new ArrayList<>(2);

        {
            LawPartEntity part = new LawPartEntity(0, "Убийство, то есть умышленное причинение смерти другому человеку", law, null);

            part.setPunishments(List.of(new LawPartPunishEntity(
                    0,
                    DEPRIVATION_OF_LIBERTY.name(),
                    new PunishmentEntity(0, "шести", 6D, YEARS.name()),
                    new PunishmentEntity(0, "пятнадцати", 15D, YEARS.name()),
                    false,
                    part,
                    List.of(
                            new LawPartPunishEntity(
                                    0,
                                    RESTRICTION_OF_FREEDOM.name(),
                                    null,
                                    new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                                    false,
                                    null
                            )
                    )
            )));

            parts.add(part);
        }
        {
            LawPartEntity part = new LawPartEntity(
                    0,
                                        """
                                        Убийство:
                                        двух или более лиц
                                        лица или его близких в связи с осуществлением данным лицом служебной деятельности или выполнением общественного долга
                                        малолетнего или иного лица, заведомо для виновного находящегося в беспомощном состоянии, а равно сопряженное с похищением человека
                                        женщины, заведомо для виновного находящейся в состоянии беременности
                                        совершенное с особой жестокостью
                                        совершенное общеопасным способом""",
                    law,
                    null);

            part.setPunishments(List.of(
                    new LawPartPunishEntity(
                            0,
                            DEPRIVATION_OF_LIBERTY.name(),
                            new PunishmentEntity(0, "восьми", 8D, YEARS.name()),
                            new PunishmentEntity(0, "двадцати", 20D, YEARS.name()),
                            false,
                            part,
                            List.of(
                                    new LawPartPunishEntity(
                                            0,
                                            RESTRICTION_OF_FREEDOM.name(),
                                            new PunishmentEntity(0, "одного", 1D, YEARS.name()),
                                            new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                                            false,
                                            null
                                    )
                            )
                    ),

                    new LawPartPunishEntity(
                            0,
                            LIFE_IMPRISONMENT.name(),
                            null,
                            null,
                            true,
                            part
                    )
            ));

            parts.add(part);
        }

        law.setParts(parts);
        return law;
    }

    static LawEntity getLawEntity106() {
        LawEntity law = new LawEntity("106", "Убийство матерью новорожденного ребенка", INTENTIONAL.name(), null);

        LawPartEntity part = new LawPartEntity(
                0,
                "Убийство матерью новорожденного ребенка во время или сразу же после родов, а равно убийство матерью " +
                        "новорожденного ребенка в условиях психотравмирующей ситуации или в состоянии психического расстройства, не исключающего вменяемости",
                law,
                null
        );

        part.setPunishments(List.of(
                new LawPartPunishEntity(
                        0,
                        RESTRICTION_OF_FREEDOM.name(),
                        new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                        new PunishmentEntity(0, "четырех", 4D, YEARS.name()),
                        false,
                        part
                ),

                new LawPartPunishEntity(
                        0,
                        FORCED_LABOR.name(),
                        null,
                        new PunishmentEntity(0, "пяти", 5D, YEARS.name()),
                        false,
                        part
                ),

                new LawPartPunishEntity(
                        0,
                        DEPRIVATION_OF_LIBERTY.name(),
                        null,
                        new PunishmentEntity(0, "пяти", 5D, YEARS.name()),
                        false,
                        part
                )
        ));

        law.setParts(List.of(part));
        return law;
    }

    static LawEntity getLawEntity114() {
        LawEntity law = new LawEntity("114", "Причинение тяжкого или средней тяжести вреда здоровью при " +
                "превышении пределов необходимой обороны либо при превышении мер, необходимых для задержания лица, " +
                "совершившего преступление", INTENTIONAL.name(), null);
        List<LawPartEntity> parts = new ArrayList<>(2);

        {
            LawPartEntity part = new LawPartEntity(
                    0,
                    "Умышленное причинение тяжкого вреда здоровью, совершенное при превышении пределов необходимой обороны",
                    law,
                    null
            );

            part.setPunishments(List.of(
                    new LawPartPunishEntity(
                            0,
                            CORRECTIONAL_LABOR.name(),
                            null,
                            new PunishmentEntity(0, "одного", 1D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            RESTRICTION_OF_FREEDOM.name(),
                            null,
                            new PunishmentEntity(0, "одного", 1D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            FORCED_LABOR.name(),
                            null,
                            new PunishmentEntity(0, "одного", 1D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            DEPRIVATION_OF_LIBERTY.name(),
                            null,
                            new PunishmentEntity(0, "одного", 1D, YEARS.name()),
                            false,
                            part
                    )
            ));

            parts.add(part);
        }
        {
            LawPartEntity part = new LawPartEntity(
                    0,
                    "Умышленное причинение тяжкого или средней тяжести вреда здоровью, совершенное при превышении " +
                            "мер, необходимых для задержания лица, совершившего преступление",
                    law,
                    null
            );

            part.setPunishments(List.of(
                    new LawPartPunishEntity(
                            0,
                            CORRECTIONAL_LABOR.name(),
                            null,
                            new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            RESTRICTION_OF_FREEDOM.name(),
                            null,
                            new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            FORCED_LABOR.name(),
                            null,
                            new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            DEPRIVATION_OF_LIBERTY.name(),
                            null,
                            new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                            false,
                            part
                    )
            ));

            parts.add(part);
        }

        law.setParts(parts);
        return law;
    }

    static LawEntity getLawEntity115() {
        LawEntity law = new LawEntity("115", "Умышленное причинение легкого вреда здоровью", INTENTIONAL.name(), null);
        List<LawPartEntity> parts = new ArrayList<>(2);
        {
            LawPartEntity part = new LawPartEntity(0, "Умышленное причинение легкого вреда здоровью, вызвавшего " +
                    "кратковременное расстройство здоровья или незначительную стойкую утрату общей трудоспособности", law, null);
            part.setPunishments(List.of(
                    new LawPartPunishEntity(
                            0,
                            FINE.name(),
                            null,
                            new PunishmentEntity(0, "сорока", 40D, THOUSAND_RUBLES.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            COMPULSORY_WORKS.name(),
                            null,
                            new PunishmentEntity(0,"четырехсот восьмидесяти", 480D, HOURS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            CORRECTIONAL_LABOR.name(),
                            null,
                            new PunishmentEntity(0, "одного", 1D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            ARREST.name(),
                            null,
                            new PunishmentEntity(0, "четырех", 4D, MONTHS.name()),
                            false,
                            part
                    )
            ));

            parts.add(part);
        }
        {
            LawPartEntity part = new LawPartEntity(0, """
                                        То же деяние, совершенное:
                                        из хулиганских побуждений
                                        по мотивам политической, идеологической, расовой, национальной или религиозной ненависти или вражды либо по мотивам ненависти или вражды в отношении какой-либо социальной группы
                                        с применением оружия или предметов, используемых в качестве оружия
                                        в отношении лица или его близких в связи с осуществлением данным лицом служебной деятельности или выполнением общественного долга""", law, null);

            part.setPunishments(List.of(
                    new LawPartPunishEntity(
                            0,
                            COMPULSORY_WORKS.name(),
                            null,
                            new PunishmentEntity(0, "трехсот шестидесяти", 360D, HOURS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            CORRECTIONAL_LABOR.name(),
                            null,
                            new PunishmentEntity(0, "одного", 1D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            RESTRICTION_OF_FREEDOM.name(),
                            null,
                            new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            FORCED_LABOR.name(),
                            null,
                            new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            ARREST.name(),
                            null,
                            new PunishmentEntity(0, "шести", 6D, MONTHS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            DEPRIVATION_OF_LIBERTY.name(),
                            null,
                            new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                            false,
                            part
                    )
            ));

            parts.add(part);
        }

        law.setParts(parts);
        return law;
    }

    static LawEntity getLawEntity116() {
        LawEntity law = new LawEntity("116", "Побои", INTENTIONAL.name(), null);
        LawPartEntity part = new LawPartEntity(0, "Побои или иные насильственные действия, причинившие " +
                "физическую боль, но не повлекшие последствий, указанных в статье 115 настоящего Кодекса, совершенные из " +
                "хулиганских побуждений, а равно по мотивам политической, идеологической, расовой, национальной или " +
                "религиозной ненависти или вражды либо по мотивам ненависти или вражды в отношении какой-либо социальной группы", law, null);
        part.setPunishments(List.of(
                new LawPartPunishEntity(
                        0,
                        COMPULSORY_WORKS.name(),
                        null,
                        new PunishmentEntity(0, "трехсот шестидесяти", 360D, HOURS.name()),
                        false,
                        part
                ),
                new LawPartPunishEntity(
                        0,
                        CORRECTIONAL_LABOR.name(),
                        null,
                        new PunishmentEntity(0, "одного", 1D, YEARS.name()),
                        false,
                        part
                ),
                new LawPartPunishEntity(
                        0,
                        RESTRICTION_OF_FREEDOM.name(),
                        null,
                        new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                        false,
                        part
                ),
                new LawPartPunishEntity(
                        0,
                        FORCED_LABOR.name(),
                        null,
                        new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                        false,
                        part
                ),
                new LawPartPunishEntity(
                        0,
                        ARREST.name(),
                        null,
                        new PunishmentEntity(0, "шести", 6D, MONTHS.name()),
                        false,
                        part
                ),
                new LawPartPunishEntity(
                        0,
                        DEPRIVATION_OF_LIBERTY.name(),
                        null,
                        new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                        false,
                        part
                )
        ));

        law.setParts(List.of(part));
        return law;
    }

    static LawEntity getLawEntity124P1() {
        LawEntity law = new LawEntity("124.1", "Воспрепятствование оказанию медицинской помощи", INTENTIONAL.name(), null);
        List<LawPartEntity> parts = new ArrayList<>(2);
        {
            LawPartEntity part = new LawPartEntity(0, "Воспрепятствование в какой бы то ни было форме законной " +
                    "деятельности медицинского работника по оказанию медицинской помощи, если это повлекло по неосторожности " +
                    "причинение тяжкого вреда здоровью пациента", law, null);
            part.setPunishments(List.of(
                    new LawPartPunishEntity(
                            0,
                            FINE.name(),
                            null,
                            new PunishmentEntity(0, "восьмидесяти", 80D, THOUSAND_RUBLES.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            RESTRICTION_OF_FREEDOM.name(),
                            null,
                            new PunishmentEntity(0, "трех", 3D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            FORCED_LABOR.name(),
                            null,
                            new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            ARREST.name(),
                            null,
                            new PunishmentEntity(0, "шести", 6D, MONTHS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            DEPRIVATION_OF_LIBERTY.name(),
                            null,
                            new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                            false,
                            part
                    )
            ));
            parts.add(part);
        }
        {
            LawPartEntity part = new LawPartEntity(0, "То же деяние, если оно повлекло по неосторожности смерть пациента", law, null);
            part.setPunishments(List.of(
                    new LawPartPunishEntity(
                            0,
                            RESTRICTION_OF_FREEDOM.name(),
                            null,
                            new PunishmentEntity(0, "четырех", 4D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            FORCED_LABOR.name(),
                            null,
                            new PunishmentEntity(0, "четырех", 4D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            DEPRIVATION_OF_LIBERTY.name(),
                            null,
                            new PunishmentEntity(0, "четырех", 4D, YEARS.name()),
                            false,
                            part
                    )
            ));
            parts.add(part);
        }

        law.setParts(parts);
        return law;
    }

    static LawEntity getLawEntity158() {
        LawEntity law = new LawEntity("158", "Кража", INTENTIONAL.name(), null);
        List<LawPartEntity> parts = new ArrayList<>(4);

        {
            LawPartEntity part = new LawPartEntity(0, "Кража, то есть тайное хищение чужого имущества", law, null);
            part.setPunishments(List.of(
                    new LawPartPunishEntity(
                            0,
                            FINE.name(),
                            null,
                            new PunishmentEntity(0, "восьмидесяти", 80D, THOUSAND_RUBLES.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            COMPULSORY_WORKS.name(),
                            null,
                            new PunishmentEntity(0, "трехсот шестидесяти", 360D, HOURS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            CORRECTIONAL_LABOR.name(),
                            null,
                            new PunishmentEntity(0, "одного", 1D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            RESTRICTION_OF_FREEDOM.name(),
                            null,
                            new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            FORCED_LABOR.name(),
                            null,
                            new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            ARREST.name(),
                            null,
                            new PunishmentEntity(0, "четырех", 4D, MONTHS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            DEPRIVATION_OF_LIBERTY.name(),
                            null,
                            new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                            false,
                            part
                    )
            ));

            parts.add(part);
        }
        {
            LawPartEntity part = new LawPartEntity(0, """
                                        Кража, совершенная:
                                        группой лиц по предварительному сговору
                                        с незаконным проникновением в помещение либо иное хранилище
                                        с причинением значительного ущерба гражданину
                                        из одежды, сумки или другой ручной клади, находившихся при потерпевшем""", law, null);
            part.setPunishments(List.of(
                    new LawPartPunishEntity(
                            0,
                            FINE.name(),
                            null,
                            new PunishmentEntity(0, "двухсот", 200D, THOUSAND_RUBLES.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            COMPULSORY_WORKS.name(),
                            null,
                            new PunishmentEntity(0, "четырехсот восьмидесяти", 480D, HOURS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            CORRECTIONAL_LABOR.name(),
                            null,
                            new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            FORCED_LABOR.name(),
                            null,
                            new PunishmentEntity(0, "пяти", 5D, YEARS.name()),
                            false,
                            part,
                            List.of(
                                    new LawPartPunishEntity(
                                            0,
                                            RESTRICTION_OF_FREEDOM.name(),
                                            null,
                                            new PunishmentEntity(0, "одного", 1D, YEARS.name()),
                                            false,
                                            null
                                    )
                            )
                    ),
                    new LawPartPunishEntity(
                            0,
                            DEPRIVATION_OF_LIBERTY.name(),
                            null,
                            new PunishmentEntity(0, "пяти", 5D, YEARS.name()),
                            false,
                            part,
                            List.of(
                                    new LawPartPunishEntity(
                                            0,
                                            RESTRICTION_OF_FREEDOM.name(),
                                            null,
                                            new PunishmentEntity(0, "одного", 1D, YEARS.name()),
                                            false,
                                            null
                                    )
                            )
                    )
            ));

            parts.add(part);
        }
        {
            LawPartEntity part = new LawPartEntity(0, """
                                        Кража, совершенная:
                                        с незаконным проникновением в жилище
                                        из нефтепровода, нефтепродуктопровода, газопровода
                                        в крупном размере
                                        с банковского счета, а равно в отношении электронных денежных средств""", law, null);
            part.setPunishments(List.of(
                    new LawPartPunishEntity(
                            0,
                            FINE.name(),
                            new PunishmentEntity(0, "ста", 100D, THOUSAND_RUBLES.name()),
                            new PunishmentEntity(0, "пятисот", 500D, THOUSAND_RUBLES.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            FORCED_LABOR.name(),
                            null,
                            new PunishmentEntity(0, "пяти", 5D, YEARS.name()),
                            false,
                            part,
                            List.of(
                                    new LawPartPunishEntity(
                                            0,
                                            RESTRICTION_OF_FREEDOM.name(),
                                            null,
                                            new PunishmentEntity(0, "одного", 1D, YEARS.name()),
                                            false,
                                            null
                                    )
                            )
                    ),
                    new LawPartPunishEntity(
                            0,
                            DEPRIVATION_OF_LIBERTY.name(),
                            null,
                            new PunishmentEntity(0, "шести", 6D, YEARS.name()),
                            false,
                            part,
                            List.of(
                                    new LawPartPunishEntity(
                                            0,
                                            FINE.name(),
                                            null,
                                            new PunishmentEntity(0, "восьмидесяти", 80D, THOUSAND_RUBLES.name()),
                                            false,
                                            null
                                    ),
                                    new LawPartPunishEntity(
                                            0,
                                            RESTRICTION_OF_FREEDOM.name(),
                                            null,
                                            new PunishmentEntity(0, "одного", 1D, YEARS.name()),
                                            false,
                                            null
                                    )
                            )
                    )
            ));

            parts.add(part);
        }
        {
            LawPartEntity part = new LawPartEntity(0, """
                                        Кража, совершенная:
                                        организованной группой
                                        в особо крупном размере""", law, null);
            part.setPunishments(List.of(
                    new LawPartPunishEntity(
                            0,
                            DEPRIVATION_OF_LIBERTY.name(),
                            null,
                            new PunishmentEntity(0, "десяти", 10D, YEARS.name()),
                            false,
                            part,
                            List.of(
                                    new LawPartPunishEntity(
                                            0,
                                            FINE.name(),
                                            null,
                                            new PunishmentEntity(0, "одного", 1D, MILLION_RUBLES.name()),
                                            false,
                                            null
                                    ),
                                    new LawPartPunishEntity(
                                            0,
                                            RESTRICTION_OF_FREEDOM.name(),
                                            null,
                                            new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                                            false,
                                            null
                                    )
                            )
                    )
            ));

            parts.add(part);
        }

        law.setParts(parts);
        return law;
    }

    static LawEntity getLawEntity200P1() {
        LawEntity law = new LawEntity("200.1", "Контрабанда наличных денежных средств и (или) денежных инструментов", INTENTIONAL.name(), null);
        List<LawPartEntity> parts = new ArrayList<>(2);

        {
            LawPartEntity part = new LawPartEntity(0, "Незаконное перемещение через таможенную границу Таможенного " +
                    "союза в рамках ЕврАзЭС наличных денежных средств и (или) денежных инструментов, совершенное в крупном размере", law, null);
            part.setPunishments(List.of(
                    new LawPartPunishEntity(
                            0,
                            FINE.name(),
                            new PunishmentEntity(0, "трехкратной", 3D, AMOUNT.name()),
                            new PunishmentEntity(0, "десятикратной", 10D, AMOUNT.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            RESTRICTION_OF_FREEDOM.name(),
                            null,
                            new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            FORCED_LABOR.name(),
                            null,
                            new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                            false,
                            part
                    )
            ));

            parts.add(part);
        }
        {
            LawPartEntity part = new LawPartEntity(0, """
                                        Деяние, предусмотренное частью первой настоящей статьи, совершенное:
                                        в особо крупном размере
                                        группой лиц""", law, null);
            part.setPunishments(List.of(
                    new LawPartPunishEntity(
                            0,
                            FINE.name(),
                            new PunishmentEntity(0, "десятикратной", 10D, AMOUNT.name()),
                            new PunishmentEntity(0, "пятнадцатикратной", 15D, AMOUNT.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            RESTRICTION_OF_FREEDOM.name(),
                            null,
                            new PunishmentEntity(0, "четырех", 4D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            FORCED_LABOR.name(),
                            null,
                            new PunishmentEntity(0, "четырех", 4D, YEARS.name()),
                            false,
                            part
                    )
            ));

            parts.add(part);
        }

        law.setParts(parts);
        return law;
    }

    static LawEntity getLawEntity333() {
        LawEntity law = new LawEntity("333", "Сопротивление начальнику или принуждение его к нарушению обязанностей военной службы", INTENTIONAL.name(), null);
        List<LawPartEntity> parts = new ArrayList<>(3);

        {
            LawPartEntity part = new LawPartEntity(0, "Сопротивление начальнику, а равно иному лицу, исполняющему " +
                    "возложенные на него обязанности военной службы, или принуждение его к нарушению этих обязанностей, " +
                    "сопряженные с насилием или с угрозой его применения", law, null);
            part.setPunishments(List.of(
                    new LawPartPunishEntity(
                            0,
                            MILITARY_SERVICE_RESTRICTION.name(),
                            null,
                            new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            DETENTION_IN_DISCIPLINARY_UNIT.name(),
                            null,
                            new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            DEPRIVATION_OF_LIBERTY.name(),
                            null,
                            new PunishmentEntity(0, "пяти", 5D, YEARS.name()),
                            false,
                            part
                    )
            ));

            parts.add(part);
        }
        {
            LawPartEntity part = new LawPartEntity(0, """
                                        Те же деяния, совершенные:
                                        группой лиц, группой лиц по предварительному сговору или организованной группой
                                        с применением оружия
                                        с причинением тяжкого или средней тяжести вреда здоровью либо иных тяжких последствий""", law, null);
            part.setPunishments(List.of(
                    new LawPartPunishEntity(
                            0,
                            DEPRIVATION_OF_LIBERTY.name(),
                            null,
                            new PunishmentEntity(0, "восьми", 8D, YEARS.name()),
                            false,
                            part
                    )
            ));

            parts.add(part);
        }
        {
            LawPartEntity part = new LawPartEntity(0, "Деяния, предусмотренные частями первой или второй настоящей " +
                    "статьи, совершенные в период мобилизации или военного положения, в военное время либо в условиях " +
                    "вооруженного конфликта или ведения боевых действий", law, null);
            part.setPunishments(List.of(
                    new LawPartPunishEntity(
                            0,
                            DEPRIVATION_OF_LIBERTY.name(),
                            new PunishmentEntity(0, "пяти", 5D, YEARS.name()),
                            new PunishmentEntity(0, "пятнадцати", 15D, YEARS.name()),
                            false,
                            part
                    )
            ));

            parts.add(part);
        }

        law.setParts(parts);
        return law;
    }

    static LawEntity getLawEntity334() {
        LawEntity law = new LawEntity("334", "Насильственные действия в отношении начальника", INTENTIONAL.name(), null);
        List<LawPartEntity> parts = new ArrayList<>(3);

        {
            LawPartEntity part = new LawPartEntity(0, "Нанесение побоев или применение иного насилия в отношении " +
                    "начальника, совершенные во время исполнения им обязанностей военной службы или в связи с исполнением этих обязанностей", law, null);
            part.setPunishments(List.of(
                    new LawPartPunishEntity(
                            0,
                            MILITARY_SERVICE_RESTRICTION.name(),
                            null,
                            new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            DETENTION_IN_DISCIPLINARY_UNIT.name(),
                            null,
                            new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            DEPRIVATION_OF_LIBERTY.name(),
                            null,
                            new PunishmentEntity(0, "пяти", 5D, YEARS.name()),
                            false,
                            part
                    )
            ));

            parts.add(part);
        }
        {
            LawPartEntity part = new LawPartEntity(0, """
                        Те же деяния, совершенные:
                        группой лиц, группой лиц по предварительному сговору или организованной группой
                        с применением оружия
                        с причинением тяжкого или средней тяжести вреда здоровью либо иных тяжких последствий""", law, null);
            part.setPunishments(List.of(
                    new LawPartPunishEntity(
                            0,
                            DEPRIVATION_OF_LIBERTY.name(),
                            null,
                            new PunishmentEntity(0, "восьми", 8D, YEARS.name()),
                            false,
                            part
                    )
            ));

            parts.add(part);
        }
        {
            LawPartEntity part = new LawPartEntity(0, "Деяния, предусмотренные частями первой или второй " +
                    "настоящей статьи, совершенные в период мобилизации или военного положения, в военное время либо в " +
                    "условиях вооруженного конфликта или ведения боевых действий", law, null);
            part.setPunishments(List.of(
                    new LawPartPunishEntity(
                            0,
                            DEPRIVATION_OF_LIBERTY.name(),
                            new PunishmentEntity(0, "пяти", 5D, YEARS.name()),
                            new PunishmentEntity(0, "пятнадцати", 15D, YEARS.name()),
                            false,
                            part
                    )
            ));

            parts.add(part);
        }

        law.setParts(parts);
        return law;
    }

    static LawEntity getLawEntity335() {
        LawEntity law = new LawEntity("335", "Нарушение уставных правил взаимоотношений между военнослужащими " +
                "при отсутствии между ними отношений подчиненности", INTENTIONAL.name(), null);
        List<LawPartEntity> parts = new ArrayList<>(3);

        {
            LawPartEntity part = new LawPartEntity(0, "Нарушение уставных правил взаимоотношений между " +
                    "военнослужащими при отсутствии между ними отношений подчиненности, связанное с унижением чести и " +
                    "достоинства или издевательством над потерпевшим либо сопряженное с насилием", law, null);
            part.setPunishments(List.of(
                    new LawPartPunishEntity(
                            0,
                            DETENTION_IN_DISCIPLINARY_UNIT.name(),
                            null,
                            new PunishmentEntity(0, "двух", 2D, YEARS.name()),
                            false,
                            part
                    ),
                    new LawPartPunishEntity(
                            0,
                            DEPRIVATION_OF_LIBERTY.name(),
                            null,
                            new PunishmentEntity(0, "трех", 3D, YEARS.name()),
                            false,
                            part
                    )
            ));

            parts.add(part);
        }
        {
            LawPartEntity part = new LawPartEntity(0, """
                        То же деяние, совершенное:
                        в отношении двух или более лиц
                        группой лиц, группой лиц по предварительному сговору или организованной группой
                        с применением оружия
                        с причинением средней тяжести вреда здоровью""", law, null);
            part.setPunishments(List.of(
                    new LawPartPunishEntity(
                            0,
                            DEPRIVATION_OF_LIBERTY.name(),
                            null,
                            new PunishmentEntity(0, "пяти", 5D, YEARS.name()),
                            false,
                            part
                    )
            ));

            parts.add(part);
        }
        {
            LawPartEntity part = new LawPartEntity(0, "Деяния, предусмотренные частями первой или второй " +
                    "настоящей статьи, повлекшие тяжкие последствия", law, null);
            part.setPunishments(List.of(
                    new LawPartPunishEntity(
                            0,
                            DEPRIVATION_OF_LIBERTY.name(),
                            null,
                            new PunishmentEntity(0, "десяти", 10D, YEARS.name()),
                            false,
                            part
                    )
            ));

            parts.add(part);
        }

        law.setParts(parts);
        return law;
    }
}
