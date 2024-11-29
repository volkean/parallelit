package com.volkean.parallelit.service;

import com.volkean.parallelit.dto.IpInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Component
public class IpQueryService {

    @Value("${ipstack.key}")
    private String ipstackKey;

    @Autowired
    private RestTemplate restTemplate;

    public String queryLocation(String ip) {
        String url = "http://api.ipstack.com/" + ip + "?access_key=" + ipstackKey;
        ResponseEntity<IpInfoResponse> ipInfoResponseEntity = restTemplate.getForEntity(url, IpInfoResponse.class);
        log.debug(ipInfoResponseEntity.toString());
        IpInfoResponse ipInfoResponse = ipInfoResponseEntity.getBody();
        return Optional.ofNullable(ipInfoResponse).map(IpInfoResponse::getCity).orElse("");
    }
}
