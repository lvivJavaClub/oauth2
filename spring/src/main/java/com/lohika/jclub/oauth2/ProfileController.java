package com.lohika.jclub.oauth2;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class ProfileController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/profile")
    public String profile(Principal principal, Map<String, Object> model) {
        addPrincipalToMap(principal, model);
        return "profile";
    }

    @RequestMapping("profile_short")
    public String profileShort(Principal principal, Map<String, Object> model) {
        addPrincipalToMap(principal, model);
        HttpHeaders headers = new HttpHeaders();
        if (principal instanceof OAuth2Authentication) {
            Object details = ((OAuth2Authentication) principal).getDetails();
            if (details instanceof OAuth2AuthenticationDetails) {
                headers.put("Authorization", Collections.singletonList("Bearer " + ((OAuth2AuthenticationDetails) details).getTokenValue()));
                HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
                ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8081/me/protected", HttpMethod.GET, entity, String.class);
                model.put("protected", exchange.getBody());
            }
        }
        return "profile_short";
    }

    private void addPrincipalToMap(Principal principal, Map<String, Object> model) {
        if (principal instanceof OAuth2Authentication) {
            Object principalDetail = ((OAuth2Authentication) principal).getDetails();
            if (principalDetail instanceof OAuth2AuthenticationDetails) {
                System.out.println(((OAuth2AuthenticationDetails) principalDetail).getTokenValue());
            }
            Object details = ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
            model.put("user", details);
        }
    }
}
