package iot.technology.dao.device;

import lombok.Data;

import java.io.Serializable;

@Data
public class Device implements Serializable {

    private static final long serialVersionUID = 8250339805336035966L;

    private Integer id;
    private Integer customerId;
    private String name;
    private String searchText;
    private Integer tenantId;
}
