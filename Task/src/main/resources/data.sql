insert into role (id,name)  values (1001,'SYS_ADMIN');
insert into role (id,name)  values (1002,'CLIENT_USER');

insert into role_privileges (role_id, privileges) VALUES (1001,'USER_WHO_CAN_GET_ALL_FARMS_FROM_ALL_USERS');
insert into role_privileges (role_id, privileges) VALUES (1001,'USER_WHO_CAN_GET_ALL_USERS');
insert into role_privileges (role_id, privileges) VALUES (1002,'USER_WHO_CAN_GET_ALL_ACCOUNTS');
insert into role_privileges (role_id, privileges) VALUES (1002,'USER_WHO_CAN_GET_ALL_FARMS');

insert into user (user, id,first_name, last_name, password, salt, username, role_id) VALUES ('User', 1001, 'Admin', 'Admincic', '$2a$10$cCXUY2t.o7NsRo9.ddsImOoLFsX.xcyT7ZHCwmfmiiVK7nVdFO19u', 'nekisalt', 'admin', 1001);
insert into user (user, id,first_name, last_name, password, salt, username, role_id) VALUES ('Customer', 1002, 'Client', 'Clientcic', '$2a$10$cCXUY2t.o7NsRo9.ddsImOoLFsX.xcyT7ZHCwmfmiiVK7nVdFO19u', 'nekisalt', 'client', 1002);
insert into customer (id) VALUES (1002);

insert into account (id, bank_key, bank_name, currency, customer_id) VALUES (1001, '214-4215-12-52', 'Erste', 0, 1002);
insert into account (id, bank_key, bank_name, currency, customer_id) VALUES (1002, '315-4215-12-52', 'UniCredit', 1, 1002);

insert into farm (id, description, name, account_id) VALUES (1001, 'Some description', 'name' , 1001);
insert into farm (id, description, name, account_id) VALUES (1002, 'Some description for another farm', 'Other name' , 1001);

