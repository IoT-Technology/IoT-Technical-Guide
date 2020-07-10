package iot.technology.gateway.opcua.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author james mu
 * @date 2020/7/10 09:29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NodeEntity {

    private Integer index;
    private String identifier;
    private Object value;
    private String type;
    private Integer clientHandle;
}
