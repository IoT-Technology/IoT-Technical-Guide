create table if not exists relation
(
    from_id bigint null comment '源',
    from_type varchar(255) null comment '"源"类型',
    to_id bigint null comment '目标',
    to_type varchar(255) null comment '目标类型',
    relation_type_group varchar(255) null,
    relation_type varchar(255) null
) comment '关系表';

create table if not exists rule_chain
(
    id bigint auto_increment
        primary key,
    create_time bigint null comment '创建时间',
    name varchar(255) null,
    type varchar(255) null,
    first_rule_node_id bigint null
) comment '规则链';

create table if not exists rule_node
(
    id bigint not null
        primary key,
    rule_chain_id bigint not null comment '规则链编号',
    configuration varchar(10000) null comment '配置项',
    type varchar(255) null comment '类型',
    name varchar(255) null comment '名称',
    create_time bigint null comment '创建时间'
) comment '规则点';




