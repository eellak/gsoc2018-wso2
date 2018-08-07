package org.sample.web.service.handler;

import java.util.HashMap;
import java.util.Map;

public class APIClaimHandlerImpl implements ExternalClaimHandler {


    public Map<String, String> getClaims(String identifier) {


        Map<String, String> userInfo = new HashMap<String, String>();

        //compare with unique identifier's value
        if(identifier.equals("234567889")) {
            userInfo.put("city", "Colombo");
            userInfo.put("firstname", "Nick");
            userInfo.put("lastname", "Johns");
            userInfo.put("nickname", "NJ");
        }

        return userInfo;
    }

}
