package com.teros.dashboard.controller.api.remote.v1.apim.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class RestRemoteController {

    @Value("${extra.param.central-server}")
    private String centralServerUrl;

    @RequestMapping(value = "/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<String> remoteExecute(HttpServletRequest request) {

        String responseBody = "";
        ResponseEntity<String> responseEntity = null;

        try {

            HttpMethod method = null;
            String requestMethodString = request.getMethod();
            String remoteHttpBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

            String requestUrl = centralServerUrl + (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

            // method
            if (requestMethodString.equals("GET") == true)
                method = HttpMethod.GET;
            if (requestMethodString.equals("POST") == true)
                method = HttpMethod.POST;
            if (requestMethodString.equals("PUT") == true)
                method = HttpMethod.PUT;
            if (requestMethodString.equals("DELETE") == true)
                method = HttpMethod.DELETE;

            // header
            HttpHeaders remoteHttpHeaders = Collections.list(request.getHeaderNames())
                    .stream()
                    .collect(Collectors.toMap(
                            Function.identity(),
                            h -> Collections.list(request.getHeaders(h)),
                            (oldValue, newValue) -> newValue,
                            HttpHeaders::new
                    ));

            responseEntity = remoteExecute(requestUrl, method, remoteHttpHeaders, remoteHttpBody);
            responseBody = responseEntity.getBody();

            log.info("BODY : " + responseBody);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseEntity;

    }


    public ResponseEntity<String> remoteExecute(String url, HttpMethod method, HttpHeaders headers, String payload) throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<String>(payload, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, method, entity, String.class);

        return responseEntity;
    }

}