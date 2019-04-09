package com.sanshengshui.token.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Author: 穆书伟
 * @Date: 19-4-9 上午10:01
 * @Version 1.0
 */
public class User {

    private static final long serialVersionUID = 8250339805336035966L;

    private String id;
    private Authority authority;
    private String email;
    private String password;
    private String tenantId;
    private String customerId;

    public User() {
        super();
    }


    public User(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.authority = user.getAuthority();
        this.password = user.getPassword();
        this.tenantId = user.getTenantId();
        this.customerId = user.getCustomerId();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [id=");
        builder.append(id);
        builder.append(", authority=");
        builder.append(authority);
        builder.append(", email=");
        builder.append(email);
        builder.append(", password=");
        builder.append(password);
        builder.append(", tenantId=");
        builder.append(tenantId);
        builder.append(", customerId=");
        builder.append(customerId);
        builder.append("]");
        return builder.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @JsonIgnore
    public boolean isSystemAdmin() {
        return tenantId == null;
    }

    @JsonIgnore
    public boolean isTenantAdmin() {
        return !isSystemAdmin() && (customerId == null);
    }

    @JsonIgnore
    public boolean isCustomerUser() {
        return !isSystemAdmin() && !isTenantAdmin();
    }
}
