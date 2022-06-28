create table emails(
emailid number(10) not null primary key,
email varchar(255) not null
)
GO
create sequence auto_id_emails
start with 1000000000
increment by 1
nomaxvalue
GO
create or replace trigger auto_id_emails
before insert on emails for each row
begin
    select auto_id_emails.nextval
    into :new.emailid
    from dual;
end;
GO