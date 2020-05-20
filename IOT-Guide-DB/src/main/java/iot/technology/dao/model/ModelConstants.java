package iot.technology.dao.model;

public class ModelConstants {

    private ModelConstants() {

    }

    /**
     * Generic constants.
     */
    public static final String ID_PROPERTY = "id";
    public static final String TENANT_ID_PROPERTY = "tenant_id";
    public static final String CUSTOMER_ID_PROPERTY = "customer_id";

    /**
     * repository constants
     */
    public static final String USER_PG_HIBERNATE_COLUMN_FAMILY_NAME = "ga_user";
    public static final String USER_TENANT_ID_PROPERTY = TENANT_ID_PROPERTY;
    public static final String USER_EMAIL_PROPERTY = "email";
    public static final String USER_FIRST_NAME_PROPERTY = "first_name";
    public static final String USER_LAST_NAME_PROPERTY = "last_name";
    public static final String USER_CUSTOMER_ID_PROPERTY = CUSTOMER_ID_PROPERTY;

    /**
     * tenant constants
     */
    public static final String TENANT_PG_HIBERNATE_COLUMN_FAMILY_NAME = "tenant";
    public static final String TENANT_ADDRESS_PROPERTY = "address";
    public static final String TENANT_EMAIL_PROPERTY = "email";
    public static final String TENANT_PHONE_PROPERTY = "phone";
    public static final String TENANT_STATE_PROPERTY = "state";
    public static final String TENANT_ZIP_PROPERTY = "zip";

    /**
     * customer constants
     */
    public static final String CUSTOMER_PG_HIBERNATE_COLUMN_FAMILY_NAME = "customer";
    public static final String CUSTOMER_ADDRESS_PROPERTY = "address";
    public static final String CUSTOMER_EMAIL_PROPERTY = "email";
    public static final String CUSTOMER_PHONE_PROPERTY = "phone";
    public static final String CUSTOMER_STATE_PROPERTY = "state";
    public static final String CUSTOMER_ZIP_PROPERTY = "zip";

    /**
     * device constants
     */
    public static final String DEVICE_PG_HIBERNATE_COLUMN_FAMILY_NAME = "device";
    public static final String DEVICE_CUSTOMER_ID_PROPERTY = CUSTOMER_ID_PROPERTY;
    public static final String DEVICE_NAME_PROPERTY = "name";
    public static final String DEVICE_SEARCH_TEXT_PROPERTY = "search_text";
    public static final String DEVICE_TENANT_ID_PROPERTY = TENANT_ID_PROPERTY;
}
