-- auto Generated on 2018-09-08
-- DROP TABLE IF EXISTS permission_entity;
CREATE TABLE permission_entity(
	id BIGINT (15) NOT NULL AUTO_INCREMENT COMMENT 'id',
	name VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'name',
	label VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'label',
	description VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'description',
	service_name VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'serviceName',
	create_owner VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'createOwner',
	create_time DATETIME NOT NULL DEFAULT '' COMMENT 'createTime',
	update_owner VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'updateOwner',
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updateTime',
	type VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'type',
	resource VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'resource',
	bound_status VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'boundStatus',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'permission_entity';
