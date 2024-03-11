CREATE TABLE IF NOT EXISTS `people` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `first_name` varchar(80) NOT NULL,
    `last_name` varchar(80) NOT NULL,
    `address` VARCHAR(100) NOT NULL,
    `gender` varchar(6) NOT NULL,
    PRIMARY KEY (`id`)
);