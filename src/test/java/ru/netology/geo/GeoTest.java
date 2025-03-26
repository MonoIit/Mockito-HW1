package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;


import java.util.stream.Stream;



public class GeoTest {

    GeoServiceImpl geoService = new GeoServiceImpl();

    static Stream<Arguments> provideByIpTestCases() {
        return Stream.of(
                Arguments.of("127.0.0.1", null, null, null, 0),
                Arguments.of(GeoServiceImpl.MOSCOW_IP, "Moscow", Country.RUSSIA, "Lenina", 15),
                Arguments.of(GeoServiceImpl.NEW_YORK_IP, "New York", Country.USA, " 10th Avenue", 32),
                Arguments.of("172.45.67.89", "Moscow", Country.RUSSIA, null, 0),
                Arguments.of("96.23.45.67", "New York", Country.USA, null, 0)
        );
    }


    @ParameterizedTest
    @MethodSource("provideByIpTestCases")
    void testByIp(String ip, String city, Country country, String street, int building) {
        Location location = geoService.byIp(ip);

        Assertions.assertEquals(city, location.getCity());
        Assertions.assertEquals(country, location.getCountry());
        Assertions.assertEquals(street, location.getStreet());
        Assertions.assertEquals(building, location.getBuiling());
    }

    @Test
    void testByCoordinates() {
        Class<RuntimeException> expected = RuntimeException.class;

        Executable executable = () -> geoService.byCoordinates(10.2, 9.2);

        Assertions.assertThrows(expected, executable);
    }
}
