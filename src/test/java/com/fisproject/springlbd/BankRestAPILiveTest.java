package com.fisproject.springlbd;

import com.fisproject.springlbd.dto.NBPKursDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BankRestAPILiveTest {


    private RestTemplate restTemplate = new RestTemplate();
    private static final String baseURL = "http://api.nbp.pl/api/";


    @Test public void getExchangeRatesPrevDay() {
        // Wymaga getterow i setterow w NBPKursDto - oczywiste
        ResponseEntity<NBPKursDto[]> response = restTemplate.getForEntity(baseURL+"exchangerates/tables/a/2022-07-05?format=json", NBPKursDto[].class);

        assert response.getBody() != null;


        for (NBPKursDto nbpKursDto : response.getBody())
            System.out.println(nbpKursDto.getPrintableFormat());

    }

}
