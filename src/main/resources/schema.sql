CREATE TABLE `events` (
                          `event_number` int(11) DEFAULT NULL,
                          `id` bigint(20) NOT NULL,
                          `test_id` bigint(20) NOT NULL,
                          `tournament_id` bigint(20) NOT NULL,
                          `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `event_register` (
                                  `event_id` bigint(20) NOT NULL,
                                  `id` bigint(20) NOT NULL,
                                  `swimmer_number` bigint(20) DEFAULT NULL,
                                  `tournament_id` bigint(20) NOT NULL,
                                  `swimmer_document_number` varchar(255) DEFAULT NULL,
                                  `swimmer_document_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `levels` (
                          `id` bigint(20) NOT NULL,
                          `level_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `master_tournaments` (
                                      `id` bigint(20) NOT NULL,
                                      `tournament_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `plains` (
                          `id` bigint(20) NOT NULL,
                          `plan_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `representors` (
                                `document_number` varchar(255) NOT NULL,
                                `document_type` varchar(255) NOT NULL,
                                `email` varchar(255) DEFAULT NULL,
                                `first_name` varchar(255) DEFAULT NULL,
                                `first_surename` varchar(255) DEFAULT NULL,
                                `phone` varchar(255) DEFAULT NULL,
                                `second_name` varchar(255) DEFAULT NULL,
                                `second_surename` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `roles` (
                         `id` bigint(20) NOT NULL,
                         `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `schedules` (
                             `active` bit(1) NOT NULL,
                             `time` time(6) DEFAULT NULL,
                             `id` bigint(20) NOT NULL,
                             `day` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `swimmers` (
                            `age` int(11) DEFAULT NULL,
                            `birth_date` date DEFAULT NULL,
                            `days_active` int(11) DEFAULT NULL,
                            `incoming_date` date DEFAULT NULL,
                            `months_active` int(11) DEFAULT NULL,
                            `years_active` int(11) DEFAULT NULL,
                            `team_id` bigint(20) NOT NULL,
                            `document_number` varchar(255) NOT NULL,
                            `document_type` varchar(255) NOT NULL,
                            `email` varchar(255) DEFAULT NULL,
                            `first_name` varchar(255) DEFAULT NULL,
                            `first_surename` varchar(255) DEFAULT NULL,
                            `friday` varchar(255) DEFAULT NULL,
                            `gender` varchar(255) DEFAULT NULL,
                            `level` varchar(255) DEFAULT NULL,
                            `monday` varchar(255) DEFAULT NULL,
                            `phone` varchar(255) DEFAULT NULL,
                            `plain` varchar(255) DEFAULT NULL,
                            `representor_name` varchar(255) DEFAULT NULL,
                            `representor_surename` varchar(255) DEFAULT NULL,
                            `saturday` varchar(255) DEFAULT NULL,
                            `second_name` varchar(255) DEFAULT NULL,
                            `second_surename` varchar(255) DEFAULT NULL,
                            `status` varchar(255) DEFAULT NULL,
                            `swimmer_key` varchar(255) DEFAULT NULL,
                            `thursday` varchar(255) DEFAULT NULL,
                            `tuesday` varchar(255) DEFAULT NULL,
                            `wednesday` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;




CREATE TABLE `teams` (
                         `id` bigint(20) NOT NULL,
                         `team_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `tests` (
                         `end_age` int(11) DEFAULT NULL,
                         `length` int(11) DEFAULT NULL,
                         `start_age` int(11) DEFAULT NULL,
                         `id` bigint(20) NOT NULL,
                         `description` varchar(255) DEFAULT NULL,
                         `gender` varchar(255) DEFAULT NULL,
                         `style` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `tournaments` (
                               `end_date` date DEFAULT NULL,
                               `start_date` date DEFAULT NULL,
                               `id` bigint(20) NOT NULL,
                               `tournament_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `tournaments-teams` (
                                     `team_position` int(11) DEFAULT NULL,
                                     `id` bigint(20) NOT NULL,
                                     `id_team` bigint(20) NOT NULL,
                                     `id_tournament` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `users` (
                         `birth_date` date DEFAULT NULL,
                         `enabled` bit(1) DEFAULT NULL,
                         `first_time` bit(1) DEFAULT NULL,
                         `locked` bit(1) DEFAULT NULL,
                         `number_retries` int(11) DEFAULT NULL,
                         `document_number` varchar(255) NOT NULL,
                         `document_type` varchar(255) NOT NULL,
                         `email` varchar(255) DEFAULT NULL,
                         `first_name` varchar(255) DEFAULT NULL,
                         `last_name` varchar(255) DEFAULT NULL,
                         `password` varchar(255) DEFAULT NULL,
                         `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


ALTER TABLE `events`
    ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKch1xhiy9ga85ysv1s0np9wyxq` (`test_id`),
  ADD KEY `FKeo3j5v0vc8lyhh31b5evnx9l5` (`tournament_id`);


ALTER TABLE `event_register`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FK1a9lixgj8o898elra4k6xipl9` (`event_id`),
  ADD KEY `FK43yb0267j2xeufr71skjipsjk` (`swimmer_document_number`,`swimmer_document_type`),
  ADD KEY `FK3l03q1taa4go81fdmhda28l6a` (`tournament_id`);


ALTER TABLE `levels`
    ADD PRIMARY KEY (`id`);


ALTER TABLE `master_tournaments`
    ADD PRIMARY KEY (`id`);


ALTER TABLE `plains`
    ADD PRIMARY KEY (`id`);


ALTER TABLE `representors`
    ADD PRIMARY KEY (`document_number`,`document_type`);


ALTER TABLE `roles`
    ADD PRIMARY KEY (`id`);


ALTER TABLE `schedules`
    ADD PRIMARY KEY (`id`);


ALTER TABLE `swimmers`
    ADD PRIMARY KEY (`document_number`,`document_type`),
  ADD KEY `FK7pmaa2uskvrclkywab04gxs23` (`team_id`);


ALTER TABLE `teams`
    ADD PRIMARY KEY (`id`);


ALTER TABLE `tests`
    ADD PRIMARY KEY (`id`);


ALTER TABLE `tournaments`
    ADD PRIMARY KEY (`id`);


ALTER TABLE `tournaments-teams`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FKshjf3wtqhvhu2h7iqqss7icqm` (`id_team`),
  ADD KEY `FKraxeff46t5abrlkcc7skfaj02` (`id_tournament`);

ALTER TABLE `users`
    ADD PRIMARY KEY (`document_number`,`document_type`);



ALTER TABLE `events`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;


ALTER TABLE `event_register`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;


ALTER TABLE `levels`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;


ALTER TABLE `master_tournaments`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;


ALTER TABLE `plains`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;


ALTER TABLE `roles`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;


ALTER TABLE `schedules`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;


ALTER TABLE `teams`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;


ALTER TABLE `tests`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;


ALTER TABLE `tournaments`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;


ALTER TABLE `tournaments-teams`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;




ALTER TABLE `events`
    ADD CONSTRAINT `FKeo3j5v0vc8lyhh31b5evnx9l5` FOREIGN KEY (`tournament_id`) REFERENCES `tournaments` (`id`),
  ADD CONSTRAINT `FKhr7k43ind0v5cwovs4igj05hl` FOREIGN KEY (`test_id`) REFERENCES `tests` (`id`);


ALTER TABLE `event_register`
    ADD CONSTRAINT `FK1a9lixgj8o898elra4k6xipl9` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`),
  ADD CONSTRAINT `FK3l03q1taa4go81fdmhda28l6a` FOREIGN KEY (`tournament_id`) REFERENCES `tournaments` (`id`),
  ADD CONSTRAINT `FK43yb0267j2xeufr71skjipsjk` FOREIGN KEY (`swimmer_document_number`,`swimmer_document_type`) REFERENCES `swimmers` (`document_number`, `document_type`);


ALTER TABLE `swimmers`
    ADD CONSTRAINT `FK7pmaa2uskvrclkywab04gxs23` FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`);


ALTER TABLE `tournaments-teams`
    ADD CONSTRAINT `FKraxeff46t5abrlkcc7skfaj02` FOREIGN KEY (`id_tournament`) REFERENCES `tournaments` (`id`),
  ADD CONSTRAINT `FKshjf3wtqhvhu2h7iqqss7icqm` FOREIGN KEY (`id_team`) REFERENCES `teams` (`id`);
COMMIT;


