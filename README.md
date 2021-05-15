# SpringCloudOAuth2

## Description
The **SpringCloudOAuth2** is a project that show how to implement a authentication server using the Spring Cloud OAuth2.

## Technologies
The project use the following technologies:

* Java 11
* Apache Maven
* Spring Boot
* Spring Cloud OAuth2
* MySQL Server
* IDE Eclipse

## Database
The demo database must be created by SQL script below.

```
CREATE SCHEMA springcloudoauth DEFAULT CHARACTER SET utf8;

USE springcloudoauth;

CREATE TABLE users
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NOT NULL,
  `enabled` ENUM('true','false') NOT NULL DEFAULT 'true',
  `login` VARCHAR(20) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `email` VARCHAR(40) NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `user_login_UNIQUE` ON `users` (`login` ASC);


CREATE TABLE roles
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `role_name_UNIQUE` ON `roles` (`name` ASC);


CREATE TABLE users_roles
(
  `iduser` INT NOT NULL,
  `idrole` INT NOT NULL,
  PRIMARY KEY (`iduser`, `idrole`),
  CONSTRAINT `fk_users_roles_user`
    FOREIGN KEY (`iduser`)
    REFERENCES `users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_users_roles_role`
    FOREIGN KEY (`idrole`)
    REFERENCES `roles` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS permissions
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `permissions_name_UNIQUE` ON `permissions` (`name` ASC);


CREATE TABLE IF NOT EXISTS roles_permissions
 (
  `idrole` INT NOT NULL,
  `idpermission` INT NOT NULL,
  PRIMARY KEY (`idrole`, `idpermission`),
  CONSTRAINT `fk_roles_permissions_role`
    FOREIGN KEY (`idrole`)
    REFERENCES `roles` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_roles_permissions_permission`
    FOREIGN KEY (`idpermission`)
    REFERENCES `permissions` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;


INSERT INTO `users` (`id`, `name`, `enabled`, `login`, `password`, `email`)
VALUES (1, 'Administrador', DEFAULT, 'admin', '21232f297a57a5a743894a0e4a801fc3', NULL);

INSERT INTO `roles` (`id`, `name`) VALUES (1, 'Administrador');

INSERT INTO `users_roles` (`iduser`, `idrole`) VALUES (1, 1);

INSERT INTO `permissions` (`id`, `name`) VALUES (1, 'Listar Usuários');
INSERT INTO `permissions` (`id`, `name`) VALUES (2, 'Editar Usuário');
INSERT INTO `permissions` (`id`, `name`) VALUES (3, 'Inserir Usuário');
INSERT INTO `permissions` (`id`, `name`) VALUES (4, 'Remover Usuário');
INSERT INTO `permissions` (`id`, `name`) VALUES (5, 'Listar Roles');
INSERT INTO `permissions` (`id`, `name`) VALUES (6, 'Editar Role');
INSERT INTO `permissions` (`id`, `name`) VALUES (7, 'Inserir Role');
INSERT INTO `permissions` (`id`, `name`) VALUES (8, 'Remover Role');

INSERT INTO `roles_permissions` (`idrole`, `idpermission`) VALUES (1, 1);
INSERT INTO `roles_permissions` (`idrole`, `idpermission`) VALUES (1, 2);
INSERT INTO `roles_permissions` (`idrole`, `idpermission`) VALUES (1, 3);
INSERT INTO `roles_permissions` (`idrole`, `idpermission`) VALUES (1, 4);
INSERT INTO `roles_permissions` (`idrole`, `idpermission`) VALUES (1, 5);
INSERT INTO `roles_permissions` (`idrole`, `idpermission`) VALUES (1, 6);
INSERT INTO `roles_permissions` (`idrole`, `idpermission`) VALUES (1, 7);
INSERT INTO `roles_permissions` (`idrole`, `idpermission`) VALUES (1, 8);
```

## Testing the project
**Step 1:** Start the application

**Step 2:** Run the following 

```
curl \
--location --request POST 'http://localhost:8890/oauth/token' \
--header 'Authorization: Basic YXBwbmFtZTEyMzphcHBzZWNyZXQxMjM=' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'username=admin' \
--data-urlencode 'password=admin' \
--data-urlencode 'grant_type=password'
```

The result of above command will be a token in JWT format.

```
{
  "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MjEwNDg5MjYsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BZG1pbmlzdHJhZG9yIl0sImp0aSI6ImE4M2Q5MWVhLTdkODEtNDI5ZC1iYzc0LWM1ZjA5YmEzOWEzOCIsImNsaWVudF9pZCI6ImFwcG5hbWUxMjMiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.HZc4BWuH7hVRiEDZjIWWu6tekD7t-IbDJgOzMJWFga8",
  "token_type": "bearer",
  "expires_in": 1799,
  "scope": "read write",
  "jti": "a83d91ea-7d81-429d-bc74-c5f09ba39a38"
}
```

You can see token details using the link [JWT.io](https://jwt.io/)

## Release History
* 1.0.0 (2021-05-15)
    * First version