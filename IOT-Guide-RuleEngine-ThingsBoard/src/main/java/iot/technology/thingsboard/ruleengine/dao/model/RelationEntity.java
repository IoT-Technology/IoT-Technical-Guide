package iot.technology.thingsboard.ruleengine.dao.model;

import lombok.Data;

import javax.persistence.*;


import static iot.technology.thingsboard.ruleengine.common.ModelConstants.*;

/**
 * @author mushuwei
 */
@Data
@Entity
@Table(name = RELATION_COLUMN_FAMILY_NAME)
@IdClass(RelationCompositeKey.class)
public class RelationEntity {

    @Id
    @Column(name = RELATION_FROM_ID_PROPERTY)
    private Long fromId;

    @Id
    @Column(name = RELATION_FROM_TYPE_PROPERTY)
    private String fromType;

    @Id
    @Column(name = RELATION_TO_ID_PROPERTY)
    private Long toId;

    @Id
    @Column(name = RELATION_TO_TYPE_PROPERTY)
    private String toType;

    @Id
    @Column(name = RELATION_TYPE_GROUP_PROPERTY)
    private String relationTypeGroup;

    @Id
    @Column(name = RELATION_TYPE_PROPERTY)
    private String relationType;
}
