create schema `questiondb`;
use `questiondb`;
create schema `quizdb`;
use `quizdb`;

CREATE TABLE IF NOT EXISTS `quizdb`.`quiz` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;


CREATE TABLE IF NOT EXISTS `questiondb`.`question` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `category` VARCHAR(255) NULL DEFAULT NULL,
  `difficultylevel` VARCHAR(255) NULL DEFAULT NULL,
  `option1` VARCHAR(255) NULL DEFAULT NULL,
  `option2` VARCHAR(255) NULL DEFAULT NULL,
  `option3` VARCHAR(255) NULL DEFAULT NULL,
  `option4` VARCHAR(255) NULL DEFAULT NULL,
  `question_title` VARCHAR(255) NULL DEFAULT NULL,
  `right_answer` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;

INSERT INTO question
(category, difficultylevel, option1, option2, option3, option4, question_title, right_answer)
VALUES
('Java', 'Easy',
 'JDK', 'JRE', 'JVM', 'JIT',
 'Which component executes Java bytecode?',
 'JVM'),

('Java', 'Easy',
 'int', 'String', 'float', 'char',
 'Which of the following is not a primitive data type in Java?',
 'String'),

('Java', 'Medium',
 'extends', 'implements', 'inherits', 'instanceof',
 'Which keyword is used to inherit a class in Java?',
 'extends'),

('Spring Boot', 'Easy',
 'Tomcat', 'Maven', 'Hibernate', 'Jenkins',
 'Which embedded server is commonly used in Spring Boot?',
 'Tomcat'),

('Spring Boot', 'Medium',
 '@Controller', '@Service', '@Autowired', '@Repository',
 'Which annotation is used for dependency injection in Spring?',
 '@Autowired'),

('SQL', 'Easy',
 'DELETE', 'DROP', 'REMOVE', 'TRUNCATE',
 'Which SQL command removes all rows but keeps the table structure?',
 'TRUNCATE'),

('SQL', 'Easy',
 'WHERE', 'ORDER BY', 'GROUP BY', 'HAVING',
 'Which clause is used to filter records?',
 'WHERE'),

('SQL', 'Medium',
 'INNER JOIN', 'OUTER JOIN', 'LEFT JOIN', 'RIGHT JOIN',
 'Which JOIN returns only matching records from both tables?',
 'INNER JOIN'),

('Cloud', 'Easy',
 'IaaS', 'PaaS', 'SaaS', 'All of the above',
 'Which of the following is a cloud service model?',
 'All of the above'),

('Cloud', 'Medium',
 'EC2', 'S3', 'RDS', 'Lambda',
 'Which AWS service provides virtual machines?',
 'EC2');
 
ALTER TABLE question
CHANGE COLUMN difficultylevel difficulty_level VARCHAR(255);

DESCRIBE question;

 
 