package ru.netology.LocalizationService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationTest {
    LocalizationService localizationService = new LocalizationServiceImpl();


    @ParameterizedTest
    @EnumSource(value = Country.class, names = {"RUSSIA", "USA", "BRAZIL", "GERMANY"})
    public void testLocale(Country country) {
        String expected;
        if (country == Country.RUSSIA) {
            expected = "Добро пожаловать";
        } else {
            expected = "Welcome";
        }

        String result =  localizationService.locale(country);

        Assertions.assertEquals(result, expected);
    }

}
