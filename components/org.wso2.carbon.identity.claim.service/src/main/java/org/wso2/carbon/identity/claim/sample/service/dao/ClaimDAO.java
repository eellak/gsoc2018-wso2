package org.wso2.carbon.identity.claim.sample.service.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.claim.sample.service.constants.ClaimConstant;
import org.wso2.carbon.identity.claim.sample.service.exception.ClaimServiceException;
import org.wso2.carbon.identity.claim.sample.service.model.ClaimModel;
import org.wso2.carbon.identity.core.util.IdentityDatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClaimDAO {

    private static final String RETRIEVE_CLAIM_ATTRIBUTE = "SELECT LASTNAME,AGE,DEPENDANCIES FROM TBL_EMPLOYEE " +
            "WHERE KEY = ?;";

    private static final Log log = LogFactory.getLog(ClaimDAO.class);
    /**
     * Get a attributes by key
     *
     * @param key Id of the attribute
     * @return resource description for the provided ID
     * @throws ClaimServiceException
     */
    public static ClaimModel retrieveAttributes(String key) throws ClaimServiceException {

        ClaimModel claimModel = new ClaimModel();

        try (Connection connection = IdentityDatabaseUtil.getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(RETRIEVE_CLAIM_ATTRIBUTE);
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                claimModel.setKey(null);
                throw new ClaimServiceException(ClaimConstant.ErrorMessages.ERROR_CODE_INVALID_CLAIM_ID, null);
            } else {
                while (resultSet.next()) {

                    if (resultSet.getString(1) != null) {
                        claimModel.setLastname(resultSet.getString(1));
                    }
                    if (resultSet.getString(2) != null) {
                        claimModel.setAge(resultSet.getString(2));
                    }
                    if (resultSet.getString(3) != null) {
                        claimModel.setDependancies(resultSet.getString(3));
                    }
                }
            }
            log.info("Successfully retrieved the resource details from the database.");
        } catch (SQLException e) {
            log.error("Error when retrieving the resource description. ");
            throw new ClaimServiceException(ClaimConstant.ErrorMessages.ERROR_CODE_INVALID_CLAIM_ID,
                    "Database" +
                            "error.Could not get resource.Resource Id can not be found in data base. - " +
                            e.getMessage(), e);

        }
        return claimModel;
    }

}
