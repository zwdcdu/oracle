set serveroutput on;
declare
type t_number is varray (100) of integer not null; --数组
i integer;
j integer;
spaces varchar2(30) :='   '; --三个空格，用于打印时分隔数字
N integer := 9; -- 一共打印6行数字
rowArray t_number := t_number();
begin
dbms_output.put_line('1'); --先打印第1行
dbms_output.put(rpad(1,9,' '));--先打印第2行
dbms_output.put(rpad(1,9,' '));--打印第一个1
dbms_output.put_line(''); --打印换行
  --初始化数组数据
for i in 1 .. N loop
rowArray.extend;
end loop;
rowArray(1):=1;
rowArray(2):=1;    
for i in 3 .. N --打印每行，从第3行起
loop
rowArray(i):=1;    
j:=i-1;
    --准备第j行的数组数据，第j行数据有j个数字，第1和第j个数字为1
    --这里从第j-1个数字循环到第2个数字，顺序是从右到左
while j>1 
loop
rowArray(j):=rowArray(j)+rowArray(j-1);
j:=j-1;
end loop;
    --打印第i行
for j in 1 .. i
loop
dbms_output.put(rpad(rowArray(j),9,' '));--打印第一个1
end loop;
dbms_output.put_line(''); --打印换行
end loop;
END;
/
