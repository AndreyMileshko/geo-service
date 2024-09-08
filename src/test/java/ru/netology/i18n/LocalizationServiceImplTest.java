package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

public class LocalizationServiceImplTest {

    @Test
    void checkingOutputOfLocaleMethod() {
        LocalizationService localizationService = new LocalizationServiceImpl();

        Assertions.assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
    }
}
