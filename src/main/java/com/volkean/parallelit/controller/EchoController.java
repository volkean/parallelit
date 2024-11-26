package com.volkean.parallelit.controller;

import com.volkean.parallelit.dto.EchoRequest;
import com.volkean.parallelit.service.EchoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class EchoController {

    @Autowired
    private EchoService echoService;

    @PostMapping(value = "/echo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> echo(@RequestBody EchoRequest echoRequest, HttpServletRequest servletRequest) {

        String ipAddress =
                Optional.ofNullable(servletRequest.getHeader("X-FORWARDED-FOR"))
                        .orElse(servletRequest.getRemoteAddr());
        return ResponseEntity.ok(echoService.echo(echoRequest, ipAddress));
    }
}
