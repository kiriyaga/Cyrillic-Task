insert into role (id,name)  values (1001,'SYS_ADMIN');
insert into role (id,name)  values (1002,'CLIENT_USER');

insert into role_privileges (role_id, privileges) VALUES (1001,'ADD_USER');
insert into role_privileges (role_id, privileges) VALUES (1001,'DELETE_USER');
insert into role_privileges (role_id, privileges) VALUES (1002,'ADD_ACCOUNT');
insert into role_privileges (role_id, privileges) VALUES (1002,'DELETE_ACCOUNT');
insert into role_privileges (role_id, privileges) VALUES (1002,'ADD_FARM');
insert into role_privileges (role_id, privileges) VALUES (1002,'DELETE_FARM');
