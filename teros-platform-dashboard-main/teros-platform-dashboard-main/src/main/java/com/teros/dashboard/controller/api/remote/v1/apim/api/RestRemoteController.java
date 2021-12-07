package com.teros.dashboard.controller.api.remote.v1.apim.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.teros.dashboard.service.dsm.flow.FlowService;
import com.teros.ext.common.parser.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/central-server/v1")
public class RestRemoteController {

    @Value("${extra.param.central-server}")
    private String centralServerUrl;

    private final FlowService flowService;

    public RestRemoteController(FlowService flowService) {
        this.flowService = flowService;
    }

    @RequestMapping(value = "/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<String> remoteExecute(HttpServletRequest request) {

        String responseBody = "";
        ResponseEntity<String> responseEntity = null;

        try {

            HttpMethod method = null;
            String requestMethodString = request.getMethod();
            String remoteHttpBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

            String requestPath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
            String requestUrl = centralServerUrl + requestPath;

            // GET PARAMETER
            String getParam = "";
            Enumeration eParam = request.getParameterNames();
            while (eParam.hasMoreElements()) {
                String pName = (String)eParam.nextElement();
                String pValue = request.getParameter(pName);

                getParam += String.format("?%s=%s", pName, pValue);
            }

            log.info("REQUEST REMOTE PATH : [" + requestPath + "]");
            int paramIndex = 0;
            while (eParam.hasMoreElements()) {

                String paramToken = "?";

                String pName = (String)eParam.nextElement();
                String pValue = request.getParameter(pName);
                if(paramIndex != 0)
                    paramToken = "&";

                getParam += String.format(paramToken + "%s=%s", pName, pValue);
                paramIndex++;
            }

            log.info("REQUEST REMOTE PATH : [" + requestPath + getParam + "]");

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


            remoteHttpBody = updatePayload(requestPath, requestMethodString, remoteHttpBody);
            log.info(remoteHttpBody);

            responseEntity = remoteExecute(requestUrl + getParam, method, remoteHttpHeaders, remoteHttpBody);
            responseBody = responseEntity.getBody();

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

    public String updatePayload(String urlPath, String requestMethod, String requestBody) throws Exception {

        String contents = requestBody;

        if (requestMethod.equals("GET") == true) {

        }
        if (requestMethod.equals("POST") == true) {

        }
        if (requestMethod.equals("PUT") == true) {

            if (urlPath.contains("/central-server/v1/flow/config/") == true) {
                contents = getFlowConfig(contents);
            }
        }
        if (requestMethod.equals("DELETE") == true) {

        }

        return contents;
    }

    public String getFlowConfig(String contents) throws Exception {

        String layout = "";
        String configContents = "";
        String result = "";

        JsonParser jsonParser = new JsonParser();
        jsonParser.loadString(contents);
        layout = jsonParser.getTextFromElement("layout");

        configContents = flowService.getResult(layout);

        JsonElement rootElement = jsonParser.getJsonElementFromPath("/");
        JsonObject rootObject = rootElement.getAsJsonObject();
        jsonParser.addJsonObject(rootObject, "configContents", configContents);

        result = jsonParser.getJsonString();

        return result;
    }


}