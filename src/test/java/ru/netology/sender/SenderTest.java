package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class SenderTest {

    static Stream<Arguments> provideSendTestCases() {
        return Stream.of(
                Arguments.of("172.65.34.44", Country.RUSSIA),
                Arguments.of("96.65.34.44", Country.USA),
                Arguments.of("", Country.USA)
        );
    }

    @ParameterizedTest
    @MethodSource("provideSendTestCases")
    public void testSend(String ip, Country country) {
        Location location = Mockito.mock(Location.class);
        Mockito.when(location.getCountry())
                .thenReturn(country);

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(location);

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> header = new HashMap<String, String>();
        header.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String expected = country == Country.RUSSIA ? "Добро пожаловать" : "Welcome";

        String result = messageSender.send(header);

        Assertions.assertEquals(result, expected);
    }
}
