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


--Bulk Collect into 
--=================

create type type1 as object(empno1 number,empno2 number,empno3 number);
create type emp_type is table of type1;
create table employee(ename varchar2(20),esal number,empno emp_type)nested table empno store as emp_bulk;
insert into employee values('Bibhu',23900,emp_type(type1(100,200,300)));
insert into employee values('Sahil',30000,emp_type(type1(10,20,40),type1(1,2,3)));
desc employee;
select * from employee;
drop type emp_type force;
drop table employee;
select type_name from user_types;

create type emp_nos is table of number;
create table employee2(ename varchar2(20),esal number,empno emp_nos)nested table empno store as emp2_bulk;
insert into employee2 values('Bibhu',20900,emp_nos(10,20,30));
insert into employee2 values('Nirmal',29000,emp_nos(100,200,300,400));
insert into employee2 values('Khirod',32000,emp_nos(1,2));
select * from employee2;


DECLARE
cursor exp_cursor is select ename from employee2;
type emp_name is table of varchar2(20);
enm emp_name;
BEGIN
open exp_cursor;
LOOP
fetch exp_cursor bulk collect into enm;
exit when enm.COUNT=0;
for idx in enm.FIRST..enm.LAST
LOOP
dbms_output.put_line(idx||'-'||enm(idx));
END LOOP;
END LOOP;
close exp_cursor;
END;
/


DECLARE
cursor exp_cursor is select ename from employee2;
type emp_name is table of varchar2(20);
enm emp_name;
BEGIN
open exp_cursor;
fetch exp_cursor bulk collect into enm limit 2;
for idx in 1..enm.count
LOOP
dbms_output.put_line(idx||'-'||enm(idx));
END LOOP;
close exp_cursor;
END;
/


--PL/SQL Collections and its Methods
--==================================
create type emp_nos is table of number;
create table employee2(ename varchar2(20),esal number,empno emp_nos)nested table empno store as emp2_bulk;
insert into employee2 values('Bibhu',20900,emp_nos(10,20,30));
insert into employee2 values('Nirmal',29000,emp_nos(100,200,300,400));
insert into employee2 values('Khirod',32000,emp_nos(1,2));
select * from employee2;

DECLARE 
type type2 is table of number index by varchar2(20);
abc type2;
BEGIN
abc('num1'):=100;
abc('num2'):=200;
abc('num3'):=400;
abc('num4'):=300;
dbms_output.put_line(abc('num1'));
END;
/


DECLARE 
type type2 is table of number index by varchar2(20);
abc type2;
i varchar2(20);
BEGIN
abc('num1'):=100;
abc('num2'):=200;
abc('num3'):=400;
abc('num4'):=300;
i:=abc.First;
WHILE i is not null
LOOP
dbms_output.put_line(i||'-'||abc(i));
i:=abc.next(i);
END LOOP;
END;
/


DECLARE 
type type2 is table of number;
abc type2:=type2(10,20,30,40,50,60);
BEGIN
dbms_output.put_line(abc.Count);
END;
/

DECLARE 
type type2 is table of number;
abc type2:=type2(10,20,30,40,50,60);
BEGIN
IF abc.Count>9 then
dbms_output.put_line('The collection contains more than 5 elements');
END IF;
END;
/

DECLARE 
type type2 is table of number;
abc type2:=type2(10,20,30,40,50,60);
BEGIN
For i in 1..abc.COUNT
Loop
dbms_output.put_line(i||'-'||abc(i));
END LOOP;
END;
/

DECLARE 
type type2 is table of number;
abc type2:=type2(10,20,30,40,50,60);
BEGIN
IF abc.EXISTS(8) then 
dbms_output.put_line('The Data is present at index no 2 is:'||abc(2));
ELSE
dbms_output.put_line('The Data does not exist at the particular index');
END IF;
END;
/

DECLARE 
type type2 is table of number;
abc type2:=type2(10,20,30,40,50,60);
BEGIN
dbms_output.put_line('1st:'||abc.FIRST);
dbms_output.put_line('last:'||abc.LAST);
abc.DELETE(1);
abc.TRIM;
dbms_output.put_line('Now 1st:'||abc.FIRST);
dbms_output.put_line('Now last:'||abc.LAST);
END;
/

DECLARE 
type type2 is varray(10) of number;
abc type2:=type2(10,20,30,40,50,60);
BEGIN
dbms_output.put_line('Size:'||abc.LIMIT);
dbms_output.put_line('No of Elements:'||abc.COUNT);
END;
/


DECLARE 
type type2 is varray(10) of number;
abc type2:=type2();
BEGIN
abc.Extend;
abc(1):=22;
dbms_output.put_line('Size:'||abc.LIMIT);
dbms_output.put_line('No of Elements:'||abc.COUNT);
END;
/


DECLARE 
type type2 is table of number;
abc type2:=type2(10,20,30,40,50,60);
BEGIN
dbms_output.put_line('Index before 3rd index is:'||abc.PRIOR(3));
dbms_output.put_line('Value before 3rd index is:'||abc(abc.PRIOR(3)));
abc.DELETE(4);
dbms_output.put_line('Index after 3rd index is:'||abc.NEXT(3));
dbms_output.put_line('Value after 3rd index is:'||abc(abc.NEXT(3)));
END;
/


DECLARE 
type type2 is table of number;
abc type2:=type2(10,20,30,40,50,60);
BEGIN
ABC.DELETE(2);
dbms_output.put_line(abc.COUNT);
dbms_output.put_line(abc(2));
END;
/


DECLARE 
type type2 is table of number;
abc type2:=type2(10,20,30,40,50,60,70,80);
BEGIN
abc.DELETE(3,5);
For i in 1..abc.COUNT
Loop
IF abc.Exists(i) Then 
dbms_output.put_line(i||'-'||abc(i));
END IF;
END LOOP;
dbms_output.put_line(abc.COUNT);
END;
/



DECLARE 
type type2 is table of number;
abc type2:=type2();
BEGIN
abc.Extend;
abc(1):=22;
dbms_output.put_line('Element is:'||abc(1));
abc.Extend(5,1);
dbms_output.put_line('Element is:'||abc(1));
END;
/

DECLARE 
type type2 is table of number;
abc type2:=type2(10,20,30,40,50,60,70,80);
BEGIN
abc.TRIM;
abc.TRIM(3);
For i in 1..abc.COUNT
Loop
dbms_output.put_line(abc(i));
END LOOP;
END;
/
