package gsoc.sample.user.store.manager.internal;

import gsoc.sample.user.store.manager.CustomReadOnlyJDBCUserStoreManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.user.core.service.RealmService;


/**
 * @scr.component name="custom.user.store.manager.dscomponent" immediate=true
 * @scr.reference name="user.realmservice.default"
 * interface="org.wso2.carbon.user.core.service.RealmService"
 * cardinality="1..1" policy="dynamic" bind="setRealmService"
 * unbind="unsetRealmService"
 */
public class CustomUserStoreMgtDSComponent {
    private static Log log = LogFactory.getLog(CustomUserStoreMgtDSComponent.class);
    private static RealmService realmService;

    protected void activate(org.osgi.service.component.ComponentContext ctxt) {

        CustomReadOnlyJDBCUserStoreManager customUserStoreManager = new CustomReadOnlyJDBCUserStoreManager();
        ctxt.getBundleContext().registerService(org.wso2.carbon.user.api.UserStoreManager.class.getName(), customUserStoreManager, null);
        log.info("CustomReadOnlyJDBCUserStoreManager bundle activated successfully..");
    }

    protected void deactivate(org.osgi.service.component.ComponentContext ctxt) {
        if (log.isDebugEnabled()) {
            log.debug("Custom User Store Manager is deactivated ");
        }
    }

    protected void setRealmService(RealmService rlmService) {
        realmService = rlmService;
    }

    protected void unsetRealmService(RealmService realmService) {
        realmService = null;
    }
}
