package org.wso2.carbon.identity.claim.sample.service.constants;

public class ClaimConstant {
        /**
         * Error codes and messages.
         */
        public enum ErrorMessages {

            ERROR_CODE_INVALID_CLAIM_ID("60001", "Invalid claim is found."),
            ERROR_CODE_UNEXPECTED("60002", "Unexpected error");

            private final String code;
            private final String message;

            ErrorMessages(String code, String message) {

                this.code = code;
                this.message = message;
            }

            public String getCode() {

                return this.code;
            }

            public String getMessage() {

                return this.message;
            }

            @Override
            public String toString() {

                return code + " - " + message;
            }
        }
    }
