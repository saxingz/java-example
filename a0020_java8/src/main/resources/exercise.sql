-- 指定存储引擎
create table T(c int) engine=InnoDB;

-- 查看事务隔离级别
show variables like 'transaction_isolation';

-- 查找持续时间超过 60s 的事务
select * from information_schema.innodb_trx where TIME_TO_SEC(timediff(now(),trx_started))>60

-- 存储过程来插入数据
delimiter ;;
create procedure idata()
begin
  declare i int;
  set i=1;
  while(i<=100000)do
    insert into t values(i, i, i);
    set i=i+1;
  end while;
end;;
delimiter ;
call idata();

-- explain
explain select * from t where a between 10000 and 20000;




