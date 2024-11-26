package com.volkean.parallelit.service;

import com.volkean.parallelit.dto.EchoRequest;
import com.volkean.parallelit.dto.EchoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EchoService {

    @Autowired
    private IpQueryService ipQueryService;

    public EchoResponse echo(EchoRequest echoRequest, String ipAddress) {
        String location = ipQueryService.queryLocation(ipAddress);
        return EchoResponse.builder().message(echoRequest.getMessage()).location(location).build();
    }
}
