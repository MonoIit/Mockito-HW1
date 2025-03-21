package ru.netology.LocalizationService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationTest {
    LocalizationService localizationService = new LocalizationServiceImpl();

    @Test
    public void RussiaTest() {
        String expected = "Добро пожаловать";

        String result =  localizationService.locale(Country.RUSSIA);

        Assertions.assertEquals(result, expected);
    }

    @ParameterizedTest
    @EnumSource(value = Country.class, names = {"USA", "BRAZIL", "GERMANY"})
    public void nonRussiaTest(Country country) {
        String expected = "Welcome";

        String result = localizationService.locale(country);

        Assertions.assertEquals(result, expected);
    }

}
