package org.wso2.carbon.identity.claim.sample.service.impl;

import org.wso2.carbon.identity.claim.sample.service.ClaimService;
import org.wso2.carbon.identity.claim.sample.service.model.ClaimModel;
import org.wso2.carbon.user.core.claim.dao.ClaimDAO;

public class ClaimServiceImpl implements ClaimService {

    public ClaimModel retrieveAttributes(String key)  {

        ClaimModel claimModel = retrieveAttributes(key);
        return claimModel;

    }
}
