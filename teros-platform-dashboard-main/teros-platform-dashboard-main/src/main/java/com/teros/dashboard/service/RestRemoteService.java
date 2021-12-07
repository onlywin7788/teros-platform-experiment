package com.teros.dashboard.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Setter
@Getter
@Service
public class RestRemoteService {

    public ResponseEntity<String> remoteExecute(String url, HttpMethod method, HttpHeaders acquireHeader, String payload) throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = acquireHeader;

        if(headers == null) {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

        }

        HttpEntity<String> entity = new HttpEntity<String>(payload, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, method, entity, String.class);

        return responseEntity;
    }

}
