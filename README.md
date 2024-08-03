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