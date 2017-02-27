/*
-- Query: SELECT * FROM jennifer_db.user_info
LIMIT 0, 1000
*/
INSERT INTO `user_info` (`ID`,`FULLNAME`,`EMAIL`,`PASSWORD`,`ROLE`) VALUES (1,'Hien Nguyen','lily31392@gmail.com','$2a$10$sHTfDyNShMdjRJ2hn2/pKusTH4fYzPUk7hYO0F0uXWXwykwcpDS3q','MANAGER');
INSERT INTO `user_info` (`ID`,`FULLNAME`,`EMAIL`,`PASSWORD`,`ROLE`) VALUES (2,'Lily Nguyen','thuhien@gmail.com','$2a$10$OoaQ.USmHJkbeA3kdiIsxObmXxzA6MKyOs6V9mQuupyEcKA3MN9ci','CUSTOMER');
INSERT INTO `user_info` (`ID`,`FULLNAME`,`EMAIL`,`PASSWORD`,`ROLE`) VALUES (3,'Ho Hoang Phuc','jamesho287@gmail.com','$2a$10$HRrqfsMeigkhmnNyvygR/.SHv0CIJgJAZDdMoFyUml4937n0sW.bS','CUSTOMER');
/*
-- Query: SELECT * FROM jennifer_db.delivery_method
LIMIT 0, 1000
*/
INSERT INTO `delivery_method` (`ID`,`NAME`,`COST`,`START_DATE`,`END_DATE`) VALUES (1,'Domestic Standard',3.99,'2017-02-01','2017-12-31');
INSERT INTO `delivery_method` (`ID`,`NAME`,`COST`,`START_DATE`,`END_DATE`) VALUES (2,'Domestic Expedited',6.99,'2017-02-01','2017-12-31');
INSERT INTO `delivery_method` (`ID`,`NAME`,`COST`,`START_DATE`,`END_DATE`) VALUES (3,'Domestic Two-Day',14.95,'2017-02-01','2017-12-31');
INSERT INTO `delivery_method` (`ID`,`NAME`,`COST`,`START_DATE`,`END_DATE`) VALUES (4,'Domestic One-Day',24.99,'2017-02-01','2017-12-31');
INSERT INTO `delivery_method` (`ID`,`NAME`,`COST`,`START_DATE`,`END_DATE`) VALUES (5,'International Standard',14.95,'2017-02-01','2017-12-31');
INSERT INTO `delivery_method` (`ID`,`NAME`,`COST`,`START_DATE`,`END_DATE`) VALUES (6,'International Expedited',46.95,'2017-02-01','2017-12-31');
/*
-- Query: SELECT * FROM jennifer_db.shipping_address
LIMIT 0, 1000
*/
INSERT INTO `shipping_address` (`ID`,`RECIPIENT`,`ADDRESS`,`CITY`,`POSTAL_CODE`,`USER_ID`) VALUES (1,'Olympiakatu 10 A11','Olympiakatu 10 A11','Vaasa','65100',2);
INSERT INTO `shipping_address` (`ID`,`RECIPIENT`,`ADDRESS`,`CITY`,`POSTAL_CODE`,`USER_ID`) VALUES (2,'Konepajakatu 12 P27','Konepajakatu 12 P27','Vaasa','65100',3);