package com.lohika.jclub.oauth2;

import java.security.Principal;
import java.util.Map;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileController {

    @RequestMapping("/profile")
    public String profile(Principal principal, Map<String, Object> model) {
        if (principal instanceof OAuth2Authentication) {
            Object principalDetail = ((OAuth2Authentication) principal).getDetails();
            if (principalDetail instanceof OAuth2AuthenticationDetails) {
                System.out.println(((OAuth2AuthenticationDetails) principalDetail).getTokenValue());
            }
            Object details = ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
            model.put("user", details);
        }
        return "profile";
    }
}
