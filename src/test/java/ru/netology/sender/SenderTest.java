package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

public class SenderTest {

    @ParameterizedTest
    @ValueSource(strings = {"172.0.0.1", "172.0.0.2", "172.65.34.44"})
    public void russianLanguageTest(String ip) {
        Location location = Mockito.mock(Location.class);
        Mockito.when(location.getCountry())
                .thenReturn(Country.RUSSIA);

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(location);

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> header = new HashMap<String, String>();
        header.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String expected = "Добро пожаловать";

        String result = messageSender.send(header);

        Assertions.assertEquals(result, expected);
    }


    @ParameterizedTest
    @ValueSource(strings = {"96.0.0.1", "96.0.0.2", "96.65.34.44"})
    public void englishLanguageTest(String ip) {
        Location location = Mockito.mock(Location.class);
        Mockito.when(location.getCountry())
                .thenReturn(Country.USA);

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(location);

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> header = new HashMap<String, String>();
        header.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String expected = "Welcome";

        String result = messageSender.send(header);

        Assertions.assertEquals(result, expected);
    }


}
