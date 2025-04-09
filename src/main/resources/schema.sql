--
-- Base de datos: `sumadeportes`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `events`
--

CREATE TABLE IF NOT EXISTS `events` (
  `event_number` int(11) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `test_id` bigint(20) NOT NULL,
  `tournament_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `event_register`
--

CREATE TABLE IF NOT EXISTS `event_register` (
  `event_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `mark` varchar(255) DEFAULT NULL,
  `swimmer_document_number` varchar(255) DEFAULT NULL,
  `swimmer_document_type` varchar(255) DEFAULT NULL,
  `swimmer_number` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marks`
--

CREATE TABLE IF NOT EXISTS `marks` (
  `id` bigint(20) NOT NULL,
  `test_id` bigint(20) NOT NULL,
  `mark` varchar(255) DEFAULT NULL,
  `swimmer_document_number` varchar(255) DEFAULT NULL,
  `swimmer_document_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `master_tournaments`
--

CREATE TABLE IF NOT EXISTS `master_tournaments` (
  `id` bigint(20) NOT NULL,
  `tournament_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `swimmers`
--

CREATE TABLE IF NOT EXISTS `swimmers` (
  `age` int(11) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `incoming_date` date DEFAULT NULL,
  `team_id` bigint(20) NOT NULL,
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
  `swimmer_key` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `teams`
--

CREATE TABLE IF NOT EXISTS `teams` (
  `id` bigint(20) NOT NULL,
  `team_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tests`
--

CREATE TABLE IF NOT EXISTS `tests` (
  `end_age` int(11) DEFAULT NULL,
  `length` int(11) DEFAULT NULL,
  `start_age` int(11) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `style` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tournaments`
--

CREATE TABLE IF NOT EXISTS `tournaments` (
  `end_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `tournament_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tournaments-teams`
--

CREATE TABLE IF NOT EXISTS `tournaments-teams` (
  `team_position` int(11) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `id_team` bigint(20) NOT NULL,
  `id_tournament` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE IF NOT EXISTS `users` (
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

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhr7k43ind0v5cwovs4igj05hl` (`test_id`),
  ADD KEY `FKeo3j5v0vc8lyhh31b5evnx9l5` (`tournament_id`);

--
-- Indices de la tabla `event_register`
--
ALTER TABLE `event_register`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1a9lixgj8o898elra4k6xipl9` (`event_id`),
  ADD KEY `FK43yb0267j2xeufr71skjipsjk` (`swimmer_document_number`,`swimmer_document_type`);

--
-- Indices de la tabla `marks`
--
ALTER TABLE `marks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcrn39xyatovbp33r3egbj6mcl` (`swimmer_document_number`,`swimmer_document_type`),
  ADD KEY `FKcsosuhaopnpw5elss444go96` (`test_id`);

--
-- Indices de la tabla `master_tournaments`
--
ALTER TABLE `master_tournaments`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `swimmers`
--
ALTER TABLE `swimmers`
  ADD PRIMARY KEY (`document_number`,`document_type`),
  ADD KEY `FK7pmaa2uskvrclkywab04gxs23` (`team_id`);

--
-- Indices de la tabla `teams`
--
ALTER TABLE `teams`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tests`
--
ALTER TABLE `tests`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tournaments`
--
ALTER TABLE `tournaments`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tournaments-teams`
--
ALTER TABLE `tournaments-teams`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKshjf3wtqhvhu2h7iqqss7icqm` (`id_team`),
  ADD KEY `FKraxeff46t5abrlkcc7skfaj02` (`id_tournament`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`document_number`,`document_type`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `events`
--
ALTER TABLE `events`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `event_register`
--
ALTER TABLE `event_register`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `marks`
--
ALTER TABLE `marks`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `master_tournaments`
--
ALTER TABLE `master_tournaments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `teams`
--
ALTER TABLE `teams`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tests`
--
ALTER TABLE `tests`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tournaments`
--
ALTER TABLE `tournaments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tournaments-teams`
--
ALTER TABLE `tournaments-teams`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `FKeo3j5v0vc8lyhh31b5evnx9l5` FOREIGN KEY (`tournament_id`) REFERENCES `tournaments` (`id`),
  ADD CONSTRAINT `FKhr7k43ind0v5cwovs4igj05hl` FOREIGN KEY (`test_id`) REFERENCES `tests` (`id`);

--
-- Filtros para la tabla `event_register`
--
ALTER TABLE `event_register`
  ADD CONSTRAINT `FK1a9lixgj8o898elra4k6xipl9` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`),
  ADD CONSTRAINT `FK43yb0267j2xeufr71skjipsjk` FOREIGN KEY (`swimmer_document_number`,`swimmer_document_type`) REFERENCES `swimmers` (`document_number`, `document_type`);

--
-- Filtros para la tabla `marks`
--
ALTER TABLE `marks`
  ADD CONSTRAINT `FKcrn39xyatovbp33r3egbj6mcl` FOREIGN KEY (`swimmer_document_number`,`swimmer_document_type`) REFERENCES `swimmers` (`document_number`, `document_type`),
  ADD CONSTRAINT `FKcsosuhaopnpw5elss444go96` FOREIGN KEY (`test_id`) REFERENCES `tests` (`id`);

--
-- Filtros para la tabla `swimmers`
--
ALTER TABLE `swimmers`
  ADD CONSTRAINT `FK7pmaa2uskvrclkywab04gxs23` FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`);

--
-- Filtros para la tabla `tournaments-teams`
--
ALTER TABLE `tournaments-teams`
  ADD CONSTRAINT `FKraxeff46t5abrlkcc7skfaj02` FOREIGN KEY (`id_tournament`) REFERENCES `tournaments` (`id`),
  ADD CONSTRAINT `FKshjf3wtqhvhu2h7iqqss7icqm` FOREIGN KEY (`id_team`) REFERENCES `teams` (`id`);
COMMIT;
