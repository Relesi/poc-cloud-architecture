INSERT INTO `company` (`id`, `ein`, `update_date`, `creation_date`, `business_name`)
VALUES (NULL, '99670056000110', CURRENT_DATE(), CURRENT_DATE(), 'Relesi Thinking IT');


INSERT INTO `employee` (`id`, `ssn`, `update_date`, `creation_date`, `email`, `name`,
`profile`, `qty_hours_launch`, `qty_hours_worked_day`, `password`, `hour_value`, `company_id`)
VALUES (NULL, '51634696069', CURRENT_DATE(), CURRENT_DATE(), 'admin@relesi.com.br', 'ADMIN', 'ROLE_ADMIN', NULL, NULL,
'$2a$10$6IkkIJQg3oeMaYEU95BAdekKnenjeGDt5x1GAk.evI4.lDb8baetS', NULL,
(SELECT `id` FROM `company` WHERE `ein` = '99670056000110'));








