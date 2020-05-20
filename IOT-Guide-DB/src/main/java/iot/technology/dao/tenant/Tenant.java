package iot.technology.dao.tenant;

import lombok.Data;

import java.io.Serializable;

@Data
public class Tenant implements Serializable {

    private static final long serialVersionUID = 8250339805336035966L;

    private Integer id;
    private String address;
    private String email;
    private String phone;
    private String state;
    private String zip;
}
