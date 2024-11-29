package com.volkean.parallelit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.volkean.parallelit.dto.IpInfoResponse;
import com.volkean.parallelit.service.IpQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class IpQueryServiceTest {

    @TestConfiguration
    static class TestConfig {
        @Bean
        public RestTemplate getRestTemplate() {
            return new RestTemplate();
        }
    }

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    IpQueryService ipQueryService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void test() throws Exception {
        IpInfoResponse ipInfoResponse = IpInfoResponse.builder().city("Ankara").countryName("Turkiye").build();
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
        mockServer.expect(requestTo(matchesPattern(".*"))).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(objectMapper.writeValueAsString(ipInfoResponse), MediaType.APPLICATION_JSON));

        String result = ipQueryService.queryLocation("0.0.0.0");
        mockServer.verify();
        assertEquals("Ankara", result);
    }

}
