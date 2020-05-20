package iot.technology.dao.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 8250339805336035966L;

    private Integer id;
    private Integer customerId;
    private String email;
    private String firstName;
    private String lastName;
    private Integer tenantId;
}
