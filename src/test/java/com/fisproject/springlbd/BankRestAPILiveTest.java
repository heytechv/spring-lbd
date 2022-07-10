package com.fisproject.springlbd;

import com.fisproject.springlbd.dto.NBPTableDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BankRestAPILiveTest {

    // https://api.nbp.pl/#kursyParams
    // https://www.nbp.pl/home.aspx?f=/kursy/instrukcja_pobierania_kursow_walut.html

    private RestTemplate restTemplate = new RestTemplate();
    private static final String baseURL = "https://api.nbp.pl/api/";


    @Test public void getExchangeRatesPrevDay() {

        LocalDateTime today = LocalDateTime.now();
        today = today.minusDays(1);  // prev day
        // W soboty i niedziele brak kursow walutowych (Forex nieczynny)
        if (today.getDayOfWeek() == DayOfWeek.SATURDAY)
            today = today.minusDays(1);
        if (today.getDayOfWeek() == DayOfWeek.SUNDAY)
            today = today.minusDays(2);
        String todayStr = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Wymaga getterow i setterow w NBPKursDto - oczywiste
        ResponseEntity<NBPTableDto[]> response = restTemplate.getForEntity(baseURL+"exchangerates/tables/a/"+todayStr+"?format=json", NBPTableDto[].class);

        assert response.getBody() != null;

        // troche glupio bo jest lista tylko z jednym root elementem :/
        for (NBPTableDto nbpKursDto : response.getBody())
            System.out.println(nbpKursDto.getPrintableFormatTable());
    }

    @Test public void getUSDLast10Days() {

        LocalDateTime today = LocalDateTime.now();
        String todayStr = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String tenDaysAgoStr = today.minusDays(10).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        ResponseEntity<NBPTableDto> response = restTemplate.getForEntity(baseURL+"exchangerates/rates/a/usd/"+tenDaysAgoStr+"/"+todayStr+"?format=json", NBPTableDto.class);

        assert response.getBody() != null;

        System.out.println(response.getBody().getPrintableFormatSingle());
    }

}
