package org.sample.web.service.handler;

import java.util.Map;

/**
 * This interface should implemented for setting external claims
 */
public interface ExternalClaimHandler {

    public Map<String, String> getClaims(String identifier);
}
