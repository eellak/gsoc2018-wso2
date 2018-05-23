package gsoc.sample.user.store.manager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.user.api.RealmConfiguration;
import org.wso2.carbon.user.core.UserRealm;
import org.wso2.carbon.user.core.UserStoreException;
import org.wso2.carbon.user.core.claim.ClaimManager;
import org.wso2.carbon.user.core.jdbc.JDBCUserStoreManager;
import org.wso2.carbon.user.core.profile.ProfileConfigurationManager;

import java.util.Map;


/**
 * Sample User Store Manager Class
 * <p/>
 * This is a sample user store manage for a user table which contains columns -
 * customer_id, customer_name and password
 * <p/>
 * This has been extended the JDBCUserStoreManager class  which is shipped with carbon.user.core
 * bundle and override some methods.
 * <p/>
 * JDBCUserStoreManager can not be used for a user table with contains two columns. Therefore these
 * override method just ensure that reading is done according to the custom schema.
 * Therefore most of the override methods are same as the methods in JDBCUserStoreManager class.
 * <p/>
 * Some functionality has been limited this user table such as tenant aware, salted password
 * value ,creating time of user and etc.
 * <p/>
 * This class only a sample demonstration of writing a custom user store manager. Also anyone can
 * write their own implementation by extending AbstractUserStoreManager or implementing UserStoreManager
 */

/**
 * Sample User Store Manager Class
 * <p/>
 * This is a sample user store manage for a user table which contains columns -
 * customer_id, customer_name and password
 * <p/>
 * This has been extended the JDBCUserStoreManager class  which is shipped with carbon.user.core
 * bundle and override some methods.
 * <p/>
 * JDBCUserStoreManager can not be used for a user table with contains two columns. Therefore these
 * override method just ensure that reading is done according to the custom schema.
 * Therefore most of the override methods are same as the methods in JDBCUserStoreManager class.
 * <p/>
 * Some functionality has been limited this user table such as tenant aware, salted password
 * value ,creating time of user and etc.
 * <p/>import sun.rmi.runtime.Log;
 * This class only a sample demonstration of writing a custom user store manager. Also anyone can
 * write their own implementation by extending AbstractUserStoreManager or implementing UserStoreManager
 */
public class CustomReadOnlyJDBCUserStoreManager extends JDBCUserStoreManager {


    private static Log log = LogFactory.getLog(CustomReadOnlyJDBCUserStoreManager.class);

    public CustomReadOnlyJDBCUserStoreManager() {
    }

    public CustomReadOnlyJDBCUserStoreManager(RealmConfiguration realmConfig,
                                              Map<String, Object> properties,
                                              ClaimManager claimManager,
                                              ProfileConfigurationManager profileManager,
                                              UserRealm realm, Integer tenantId)
            throws UserStoreException {
        super(realmConfig, properties, claimManager, profileManager, realm, tenantId, false);
    }


    @Override
    public boolean doAuthenticate(String userName, Object credential) throws UserStoreException {

        return false;
    }
}