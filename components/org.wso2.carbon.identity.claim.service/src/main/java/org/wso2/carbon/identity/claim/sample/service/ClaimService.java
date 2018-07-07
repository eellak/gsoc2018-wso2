package org.wso2.carbon.identity.claim.sample.service;

import org.wso2.carbon.identity.claim.sample.service.model.ClaimModel;

public interface ClaimService {

    ClaimModel retrieveAttributes(String key);
}
