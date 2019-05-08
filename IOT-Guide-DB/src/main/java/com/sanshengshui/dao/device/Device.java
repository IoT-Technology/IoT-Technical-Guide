package com.sanshengshui.dao.device;

import lombok.Data;

import java.io.Serializable;

@Data
public class Device implements Serializable {

    private static final long serialVersionUID = 8250339805336035966L;

    private int id;
    private int customerId;
    private String name;
    private String searchText;
    private int tenantId;
}
