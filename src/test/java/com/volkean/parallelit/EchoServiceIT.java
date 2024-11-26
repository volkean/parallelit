package com.volkean.parallelit;

import com.volkean.parallelit.dto.EchoRequest;
import com.volkean.parallelit.dto.EchoResponse;
import com.volkean.parallelit.service.EchoService;
import com.volkean.parallelit.service.IpQueryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class EchoServiceIT {

    @TestConfiguration
    static class TestConfig {
        @Bean
        public IpQueryService ipQueryService() {
            return Mockito.mock(IpQueryService.class);
        }
    }

    @Autowired
    EchoService echoService;
    @Autowired
    IpQueryService ipQueryService;

    @Test
    void echoServiceTest() {
        when(ipQueryService.queryLocation(any())).thenReturn("this is localhost");

        EchoResponse echoResponse = echoService.echo(EchoRequest.builder().message("hello").build(), "0.0.0.0");

        assertEquals("hello", echoResponse.getMessage());
        assertEquals("this is localhost", echoResponse.getLocation());
    }

}
