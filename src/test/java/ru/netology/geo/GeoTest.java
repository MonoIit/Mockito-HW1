package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoTest {

    private final GeoServiceImpl geoService = new GeoServiceImpl();

    @Test
    void byIpLocalhostTest() {
        Location location = geoService.byIp(GeoServiceImpl.LOCALHOST);
        Assertions.assertNotNull(location);
        Assertions.assertNull(location.getCity());
        Assertions.assertNull(location.getCountry());
        Assertions.assertNull(location.getStreet());
        Assertions.assertEquals(0, location.getBuiling());
    }

    @Test
    void byIpMoscowTest() {
        Location location = geoService.byIp(GeoServiceImpl.MOSCOW_IP);
        Assertions.assertNotNull(location);
        Assertions.assertEquals("Moscow", location.getCity());
        Assertions.assertEquals(Country.RUSSIA, location.getCountry());
        Assertions.assertEquals("Lenina", location.getStreet());
        Assertions.assertEquals(15, location.getBuiling());
    }

    @Test
    void byIpNewYorkTest() {
        Location location = geoService.byIp(GeoServiceImpl.NEW_YORK_IP);
        Assertions.assertNotNull(location);
        Assertions.assertEquals("New York", location.getCity());
        Assertions.assertEquals(Country.USA, location.getCountry());
        Assertions.assertEquals(" 10th Avenue", location.getStreet());
        Assertions.assertEquals(32, location.getBuiling());
    }

    @Test
    void byIpRussiaTest() {
        Location location = geoService.byIp("172.45.67.89");
        Assertions.assertNotNull(location);
        Assertions.assertEquals("Moscow", location.getCity());
        Assertions.assertEquals(Country.RUSSIA, location.getCountry());
        Assertions.assertNull(location.getStreet());
        Assertions.assertEquals(0, location.getBuiling());
    }

    @Test
    void byIpUSATest() {
        Location location = geoService.byIp("96.23.45.67");
        Assertions.assertNotNull(location);
        Assertions.assertEquals("New York", location.getCity());
        Assertions.assertEquals(Country.USA, location.getCountry());
        Assertions.assertNull(location.getStreet());
        Assertions.assertEquals(0, location.getBuiling());
    }

    @Test
    void byIpUnknownTest() {
        Location location = geoService.byIp("123.45.67.89");
        Assertions.assertNull(location);
    }
}
