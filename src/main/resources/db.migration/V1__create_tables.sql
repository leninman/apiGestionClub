DROP TABLE IF EXISTS `event_register`;
CREATE TABLE `event_register` (
                                  `event_id` bigint NOT NULL,
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `mark` varchar(255) DEFAULT NULL,
                                  `swimmer_document_number` varchar(255) DEFAULT NULL,
                                  `swimmer_document_type` varchar(255) DEFAULT NULL,
                                  `swimmer_number` varchar(255) DEFAULT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `FK1a9lixgj8o898elra4k6xipl9` (`event_id`),
                                  KEY `FK43yb0267j2xeufr71skjipsjk` (`swimmer_document_number`,`swimmer_document_type`),
                                  CONSTRAINT `FK1a9lixgj8o898elra4k6xipl9` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`),
                                  CONSTRAINT `FK43yb0267j2xeufr71skjipsjk` FOREIGN KEY (`swimmer_document_number`, `swimmer_document_type`) REFERENCES `swimmers` (`document_number`, `document_type`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `events`;

CREATE TABLE `events` (
                          `end_date` date DEFAULT NULL,
                          `event_number` int DEFAULT NULL,
                          `start_date` date DEFAULT NULL,
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `test_id` bigint NOT NULL,
                          `tournament_id` bigint NOT NULL,
                          `name` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `FKhr7k43ind0v5cwovs4igj05hl` (`test_id`),
                          KEY `FKeo3j5v0vc8lyhh31b5evnx9l5` (`tournament_id`),
                          CONSTRAINT `FKeo3j5v0vc8lyhh31b5evnx9l5` FOREIGN KEY (`tournament_id`) REFERENCES `tournaments` (`id`),
                          CONSTRAINT `FKhr7k43ind0v5cwovs4igj05hl` FOREIGN KEY (`test_id`) REFERENCES `tests` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `marks`;

CREATE TABLE `marks` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `test_id` bigint NOT NULL,
                         `mark` varchar(255) DEFAULT NULL,
                         `swimmer_document_number` varchar(255) DEFAULT NULL,
                         `swimmer_document_type` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `FKcrn39xyatovbp33r3egbj6mcl` (`swimmer_document_number`,`swimmer_document_type`),
                         KEY `FKcsosuhaopnpw5elss444go96` (`test_id`),
                         CONSTRAINT `FKcrn39xyatovbp33r3egbj6mcl` FOREIGN KEY (`swimmer_document_number`, `swimmer_document_type`) REFERENCES `swimmers` (`document_number`, `document_type`),
                         CONSTRAINT `FKcsosuhaopnpw5elss444go96` FOREIGN KEY (`test_id`) REFERENCES `tests` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




DROP TABLE IF EXISTS `master_tournaments`;

CREATE TABLE `master_tournaments` (
                                      `id` bigint NOT NULL AUTO_INCREMENT,
                                      `tournament_name` varchar(255) DEFAULT NULL,
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




DROP TABLE IF EXISTS `payments`;

CREATE TABLE `payments` (
                            `created_at` date DEFAULT NULL,
                            `payment_amount` double DEFAULT NULL,
                            `payment_date` date DEFAULT NULL,
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `cel_number` varchar(255) DEFAULT NULL,
                            `dest_account` varchar(255) DEFAULT NULL,
                            `doc_number` varchar(255) DEFAULT NULL,
                            `doc_type` varchar(255) DEFAULT NULL,
                            `email` varchar(255) DEFAULT NULL,
                            `file_name` varchar(255) DEFAULT NULL,
                            `org_account` varchar(255) DEFAULT NULL,
                            `org_bank` varchar(255) DEFAULT NULL,
                            `payment_medium` varchar(255) DEFAULT NULL,
                            `payment_reference` varchar(255) DEFAULT NULL,
                            `payment_voucher` longblob,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `swimmers`;

CREATE TABLE `swimmers` (
                            `age` int DEFAULT NULL,
                            `birth_date` date DEFAULT NULL,
                            `incoming_date` date DEFAULT NULL,
                            `team_id` bigint NOT NULL,
                            `client_code` varchar(255) DEFAULT NULL,
                            `document_number` varchar(255) NOT NULL,
                            `document_type` varchar(255) NOT NULL,
                            `email` varchar(255) DEFAULT NULL,
                            `first_name` varchar(255) DEFAULT NULL,
                            `first_surename` varchar(255) DEFAULT NULL,
                            `gender` varchar(255) DEFAULT NULL,
                            `phone` varchar(255) DEFAULT NULL,
                            `representor_name` varchar(255) DEFAULT NULL,
                            `representor_surename` varchar(255) DEFAULT NULL,
                            `second_name` varchar(255) DEFAULT NULL,
                            `second_surename` varchar(255) DEFAULT NULL,
                            `status` varchar(255) DEFAULT NULL,
                            `swimmer_key` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`document_number`,`document_type`),
                            KEY `FK7pmaa2uskvrclkywab04gxs23` (`team_id`),
                            CONSTRAINT `FK7pmaa2uskvrclkywab04gxs23` FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




DROP TABLE IF EXISTS `teams`;

CREATE TABLE `teams` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `team_name` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `tests`;

CREATE TABLE `tests` (
                         `end_age` int DEFAULT NULL,
                         `length` int DEFAULT NULL,
                         `start_age` int DEFAULT NULL,
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `description` varchar(255) DEFAULT NULL,
                         `gender` varchar(255) DEFAULT NULL,
                         `style` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=216 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `tournaments`;

CREATE TABLE `tournaments` (
                               `end_date` date DEFAULT NULL,
                               `start_date` date DEFAULT NULL,
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `tournament_name` varchar(255) DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




DROP TABLE IF EXISTS `tournaments-teams`;

CREATE TABLE `tournaments-teams` (
                                     `team_position` int DEFAULT NULL,
                                     `id` bigint NOT NULL AUTO_INCREMENT,
                                     `id_team` bigint NOT NULL,
                                     `id_tournament` bigint NOT NULL,
                                     PRIMARY KEY (`id`),
                                     KEY `FKshjf3wtqhvhu2h7iqqss7icqm` (`id_team`),
                                     KEY `FKraxeff46t5abrlkcc7skfaj02` (`id_tournament`),
                                     CONSTRAINT `FKraxeff46t5abrlkcc7skfaj02` FOREIGN KEY (`id_tournament`) REFERENCES `tournaments` (`id`),
                                     CONSTRAINT `FKshjf3wtqhvhu2h7iqqss7icqm` FOREIGN KEY (`id_team`) REFERENCES `teams` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
                         `birth_date` date DEFAULT NULL,
                         `enabled` bit(1) DEFAULT NULL,
                         `first_time` bit(1) DEFAULT NULL,
                         `locked` bit(1) DEFAULT NULL,
                         `number_retries` int DEFAULT NULL,
                         `document_number` varchar(255) NOT NULL,
                         `document_type` varchar(255) NOT NULL,
                         `email` varchar(255) DEFAULT NULL,
                         `first_name` varchar(255) DEFAULT NULL,
                         `last_name` varchar(255) DEFAULT NULL,
                         `password` varchar(255) DEFAULT NULL,
                         `role` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`document_number`,`document_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

