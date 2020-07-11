package iot.technology.gateway.opcua.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author james mu
 * @date 2020/7/11 22:03
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class NodeEntity {

    private Integer index;
    private String identifier;
    private String value;
    private String type;
    private Integer clientHandle;
}
