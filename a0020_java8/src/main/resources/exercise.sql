-- 指定存储引擎
create table T(c int) engine=InnoDB;

-- 查看事务隔离级别
show variables like 'transaction_isolation';

-- 查找持续时间超过 60s 的事务
select * from information_schema.innodb_trx where TIME_TO_SEC(timediff(now(),trx_started))>60


