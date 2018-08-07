/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.sample.extension.authenticators;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.application.common.model.Property;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * Sample class for testing abstractAuthenticator
 */
public class SampleAuthenticator extends CustomAuthenticator {

        private static final long serialVersionUID = 6439291340285653402L;
        private static final String HWK_APP_URL = "HwkAppUrl";
        private static final String CLAIM_URI = "ClaimProperty";
        private static final String USER_NAME = "UserName";
        private static final String PASSWORD = "Password";
        public static final String USERINFO_ENDPOINT = "UserInfoEndpoint";

        private static final Log log = LogFactory.getLog(SampleAuthenticator.class);

        @Override
        public boolean canHandle(HttpServletRequest request) {
            return true;
        }

        @Override
        protected String getPageUrlProperty() {
            return HWK_APP_URL;
        }

        @Override
        public String getContextIdentifier(HttpServletRequest request) {
            String identifier = request.getParameter("sessionDataKey");
            return identifier;
        }

        @Override
        public String getName() {
            return "SampleAuthenticator";
        }

        @Override
        public String getFriendlyName() {
            return "Sample Authenticator";
        }

        @Override
        public String getClaimDialectURI() {
            return null;
        }

        @Override
        public List<Property> getConfigurationProperties() {
            List<Property> configProperties = new ArrayList<>();

            Property appUrl = new Property();
            appUrl.setName(HWK_APP_URL);
            appUrl.setDisplayName("Token Endpoint URL");
            appUrl.setRequired(true);
            appUrl.setDescription("Enter token endpoint");
            appUrl.setDisplayOrder(0);
            configProperties.add(appUrl);

            Property userInfoEndpoint = new Property();
            userInfoEndpoint.setName(USERINFO_ENDPOINT);
            userInfoEndpoint.setDisplayName("Userinfo Endpoint URL");
            userInfoEndpoint.setRequired(true);
            userInfoEndpoint.setDescription("Enter Userinfo endpoint");
            userInfoEndpoint.setDisplayOrder(1);
            configProperties.add(userInfoEndpoint);

            Property appurl1 = new Property();
            appurl1.setName(CLAIM_URI);
            appurl1.setDisplayName("Token Endpoint URL");
            appurl1.setDescription("Enter token Endpoint");
            appurl1.setDisplayOrder(2);
            configProperties.add(appurl1);

            Property appurl2 = new Property();
            appurl2.setName(USER_NAME);
            appurl2.setDisplayName("User name");
            appurl2.setDescription("Enter User name");
            appurl2.setDisplayOrder(3);
            configProperties.add(appurl2);

            Property appurl3 = new Property();
            appurl3.setName(PASSWORD);
            appurl3.setDisplayName("Password");
            appurl3.setDescription("Enter Password");
            appurl3.setDisplayOrder(4);
            configProperties.add(appurl3);
            //add another property for app endpoint
            return configProperties;
        }
    }