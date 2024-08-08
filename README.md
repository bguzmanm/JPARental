# JPARental
Proyecto de ejemplo de uso de JPA con Spring Boot.

## Requisitos
1. Base de Datos: El proyecto funciona con la base de datos de ejemplo [DVDRental de PostgreSQL](https://www.postgresqltutorial.com/postgresql-getting-started/postgresql-sample-database/).
2. Dependencias:
    1. spring-boot-starter-data-jpa
    2. spring-boot-starter-thymeleaf
    3. spring-boot-starter-web
    4. postgresql
    5. spring-boot-starter-tomcat

## Configuración
Para configurarlo, debes editar el archivo application.properties e indicar los datos de conexión a la base de datos:

```editorconfig
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/dvdrental
spring.datasource.schema=public
spring.datasource.username=[nombre de usuario]
spring.datasource.password=[contraseña]
```
## Entity
Para trabajar con JPA, debes definir las Entities. Es decir, crear clases que representen a una entidad en la BD, 
con un poco de configuración.

```java
@Entity
@Table(name = "film")
public class Film {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "film_id")
  private int id;
  private String title;
  private String description;
  @Column(name = "release_year")
  private int year;
  
  /**
   * incluir constructores y getters y setters
   */
}
```
## CRUD
Luego, en el package de repository, encontrarás los repositorios que utilizando `JPA` establecen las
operaciones básicas con la BD. Para ello, se extienden `JpaRepository`, indicando la Entity y el tipado (en su
versión `wrapper`) de la llave primaria.

```java
public interface FilmRepository extends JpaRepository<Film, Integer> {}
```

# Tablas de Usuario / Roles
Para implementar la sección de `Spring Security`, debes contar con estas tablas.
Los usuarios son `admin@dvdrental.com` y `user@dvdrental.com` y la contraseña para ambos es `1234`.

```sql
drop table t_user;
create table t_user (
    user_id serial primary key,
    username varchar(100),
    password varchar(500),
    first_name varchar(100),
    last_name varchar(100)
);
drop table t_role;
create table t_role (
    role_id serial primary key,
    name varchar(100)
);

drop table t_user_role;
create table t_user_role (
    user_id int,
    role_id int,
    primary key (user_id, role_id),
    foreign key (user_id) references t_user(user_id),
    foreign key (role_id) references t_role(role_id)
);
```
y considera agregar los siguientes datos a tu BD:
```sql
insert into t_role (name) values ('ROLE_USER'), ('ROLE_ADMIN');

insert into t_user (username, password, first_name, last_name)
values  ('admin@dvdrental.com', '$2a$12$qf6UvIuNM2un0xBl1a.bROvff3h5phePEp4WpsCsyUEmm0jDIGPcO', 'administrador', 'dvdrental'),
        ('user@dvdrental.com', '$2a$12$qf6UvIuNM2un0xBl1a.bROvff3h5phePEp4WpsCsyUEmm0jDIGPcO', 'usuario', 'dvdrental');

insert into t_user_role (user_id, role_id) values (1, 1), (2,2);
```