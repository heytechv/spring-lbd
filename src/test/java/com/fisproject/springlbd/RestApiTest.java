package com.fisproject.springlbd;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestApiTest {

    // https://spring.io/guides/gs/testing-web/

    private RestTemplate restTemplate = new RestTemplate();

    private final Logger LOG = LoggerFactory.getLogger(RestApiTest.class);


    @Test void storyPointsAmountTest() {
        String url = "http://localhost:8080/sprint/1/storypoints";

        String result = restTemplate.getForObject(url, String.class);
        LOG.debug(result);

        assert result != null;
        assert result.contains("7");
    }

    @Test void userStoriesFromSprint(){
        String url = "http://localhost:8080/sprint/1/userstories";

        String result = restTemplate.getForObject(url, String.class);
        LOG.debug(result);

        assert result != null;
        assert result.contains("\"name\":\"UserStory3\"");
    }

    @Test void sprints(){
        String url = "http://localhost:8080/sprint?tasks=false";

        String result = restTemplate.getForObject(url, String.class);
        LOG.debug(result);

        assert result != null;
        assert result.contains("Opis1");
    }

}
