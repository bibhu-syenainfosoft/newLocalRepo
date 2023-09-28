# This is my Local Repo

create table dynamicSQL(col1 varchar2(20),col2 number);
insert into dynamicSQL values('abc',78);
insert into dynamicSQL values('def',10);
insert into dynamicSQL values('ghi',99);
select * from dynamicSQL;

create view view1 as select * from dynamicSQL;

create procedure drop_table(tName in varchar2)
is 
BEGIN
execute immediate 'drop table '||tName;
END;
/

execute drop_table('dynamicSQL');

create procedure drop_object(oType in varchar2,oName in varchar2)
is 
BEGIN
execute immediate 'drop '||oType||' '||oName;
END;
/

execute drop_object('view','view1');

DECLARE 
query varchar2(400);
countn number;
BEGIN
query:='select count(*) from dynamicSQL';
Execute Immediate query into countn;
dbms_output.put_line('Total Employees are:'||countn);
END;
/


--Bind Variable 
--=============
-- There are 3 ways to print variables
--1. through DBMS_OUTPUT.PUT_LINE  
--2. through print
--3. through set autoprint on

variable abc number;
exec :abc :=10;

print :abc;

Begin
dbms_output.put_line(:abc);
END;
/

set autoprint on;

DECLARE 
query varchar2(400);
BEGIN
query:='insert into dynamicSQL values(:val1,:val2)';
Execute Immediate query using 'DB',34;
dbms_output.put_line('Data Inserted');
END;
/
