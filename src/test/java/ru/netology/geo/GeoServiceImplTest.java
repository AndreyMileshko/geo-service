package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoServiceImplTest {

    @Test
    void checkingLocationDetectionByIP() {
        GeoService geoService = new GeoServiceImpl();
        Location location = new Location("Moscow", Country.RUSSIA, "Lenina", 15);

        Assertions.assertEquals(location, geoService.byIp("172.0.32.11"));
    }
}
