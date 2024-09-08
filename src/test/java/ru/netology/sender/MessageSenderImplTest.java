package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class MessageSenderImplTest {

    @Test
    void checkingRussianLanguageOutput() {
        GeoService geoServiceMock = Mockito.mock(GeoService.class);
        Mockito.when(geoServiceMock.byIp("172."))
                .thenReturn(new Location("ТестГород", Country.RUSSIA, "ТестУлица", 38));

        LocalizationService localizationServiceMock = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationServiceMock.locale(Country.RUSSIA))
                .thenReturn("Тест вывод. Русский язык.");

        MessageSender messageSender = new MessageSenderImpl(geoServiceMock, localizationServiceMock);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.");

        Pattern pattern = Pattern.compile("[а-яА-ЯёЁ\\d\\s\\p{Punct}]*");
        Matcher matcher = pattern.matcher(messageSender.send(headers));

        assertTrue(matcher.matches());
    }

    @Test
    void checkingEnglishLanguageOutput() {
        GeoService geoServiceMock = Mockito.mock(GeoService.class);
        Mockito.when(geoServiceMock.byIp("96."))
                .thenReturn(new Location("TestCity", Country.USA, "TestStreet", 56));

        LocalizationService localizationServiceMock = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationServiceMock.locale(Country.USA))
                .thenReturn("Test conclusion. English.");

        MessageSender messageSender = new MessageSenderImpl(geoServiceMock, localizationServiceMock);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.");

        Pattern pattern = Pattern.compile("[a-zA-Z\\d\\s\\p{Punct}]*");
        Matcher matcher = pattern.matcher(messageSender.send(headers));

        assertTrue(matcher.matches());
    }
}
