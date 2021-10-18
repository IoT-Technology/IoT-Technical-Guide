package iot.technology.thingsboard.ruleengine.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author mushuwei
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RelationCompositeKey implements Serializable {

    @Transient
    private static final long serialVersionUID = -4089175869616037592L;

    private Long fromId;
    private String fromType;
    private Long toId;
    private String toType;
    private String relationType;
    private String relationTypeGroup;
    
}
