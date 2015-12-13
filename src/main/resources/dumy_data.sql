INSERT INTO `cashflow`.`user` (`id_user`, `email`, `firstname`, `lastname`, `password`) VALUES ('1', 'catalin@asd.ro', 'Catalin', 'Catalin', '$2a$10$SmuG1uN4bpX.N54huiWFW.u9mpxMFjZdwnziXYydohYPIO8XPlrbK');

INSERT INTO `cashflow`.`budget` (`id_budget`, `active`, `amount`, `cDate`, `id_user`) VALUES ('1', '1', '2400', '2015-08-03', '1');

INSERT INTO `cashflow`.`category` (`id_category`, `name`) VALUES ('1', 'Food');
INSERT INTO `cashflow`.`category` (`id_category`, `name`) VALUES ('2', 'Personal');
INSERT INTO `cashflow`.`category` (`id_category`, `name`) VALUES ('3', 'Household');


INSERT INTO `cashflow`.`category` (`id_category`, `name`, `id_parent`) VALUES ('4', 'Consumables', '3');
INSERT INTO `cashflow`.`category` (`id_category`, `name`, `id_parent`) VALUES ('5', 'Rent', '3');
INSERT INTO `cashflow`.`category` (`id_category`, `description`, `name`, `id_parent`) VALUES ('6', '', 'Clothing', '2');
INSERT INTO `cashflow`.`category` (`id_category`, `name`, `id_parent`) VALUES ('7', 'Other', '2');
INSERT INTO `cashflow`.`category` (`id_category`, `name`, `id_parent`) VALUES ('8', 'Work', '1');
INSERT INTO `cashflow`.`category` (`id_category`, `name`, `id_parent`) VALUES ('9', 'Groceries', '1');
INSERT INTO `cashflow`.`category` (`id_category`, `name`) VALUES ('10', 'Entertainment');
INSERT INTO `cashflow`.`category` (`id_category`, `name`, `id_parent`) VALUES ('11', 'Coffe Shop', '10');
INSERT INTO `cashflow`.`category` (`id_category`, `name`, `id_parent`) VALUES ('12', 'Movies', '10');
INSERT INTO `cashflow`.`category` (`id_category`, `name`, `id_parent`) VALUES ('13', 'Club', '10');

