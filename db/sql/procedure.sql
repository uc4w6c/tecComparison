delimiter //
create procedure datainsert()
begin
declare i int default 0;
while i<30000 do
  set @sql="insert posts (topic_id, name, body) values (1, '鈴木 太郎', 'data');";
prepare stmt from @sql;
execute stmt;
set i=i+1;
end while;
end
//
delimiter ;
call datainsert();

