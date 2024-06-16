package com.marvel.avengers.controllers;

import com.marvel.avengers.AbstractIT;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@Sql(value = "/test-data.sql")
class SuperheroControllerTest extends AbstractIT {

    @Test
    void testGetAllSuperheroes() {
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/superheroes")
                .then()
                .statusCode(200)
                .body("data", hasSize(3))
                .body("totalElements", is(9))
                .body("pageNumber", is(1))
                .body("totalPages", is(3))
                .body("isFirst", is(true))
                .body("isLast", is(false))
                .body("hasNext", is(true))
                .body("hasPrevious", is(false));
    }

    @Test
    void testGetSuperheroById() {
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/superheroes/{id}", 100)
                .then()
                .statusCode(200)
                .body("alias", is("Captain Marvel"))
                .body("name", is("Carol Danvers"))
                .body("origin", is("Exposed to Space Stone reactor overload"));
    }

    @Test
    void shouldReturnNotFoundWhenSuperheroIdNotExists() {
        int id = 1000;
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/superheroes/{id}", id)
                .then()
                .statusCode(404)
                .body("detail", is("Superhero with id " + id + " not found"));
    }

    @Test
    void testCreateSuperhero() {
        given().contentType(ContentType.JSON)
                .body("{\"alias\": \"Spiderman\", \"name\": \"Peter Parker\", \"origin\": \"Bitten by a radioactive spider\"}")
                .when()
                .post("/api/superheroes")
                .then()
                .statusCode(201);
    }

    @Test
    void shouldReturnBadRequestWhenAliasIsEmpty() {
        given().contentType(ContentType.JSON)
                .body("{\"alias\": \"\", \"name\": \"Carol Danvers\", \"origin\": \"Exposed to Space Stone reactor overload\"}")
                .when()
                .post("/api/superheroes")
                .then()
                .statusCode(400)
                .body("detail", is("Alias is required for a Superhero"));

    }

    @Test
    void shouldReturnErrorWhenSuperheroAliasExists() {
        given().contentType(ContentType.JSON)
                .body("{\"alias\": \"Captain Marvel\", \"name\": \"Carol Danvers\", \"origin\": \"Exposed to Space Stone reactor overload\"}")
                .when()
                .post("/api/superheroes")
                .then()
                .statusCode(500)
                .body("detail", is("could not execute statement [ERROR: duplicate key value violates unique constraint \"superhero_alias_key\"\n  Detail: Key (alias)=(Captain Marvel) already exists.] [insert into superhero (alias,name,origin) values (?,?,?) returning id]; SQL [insert into superhero (alias,name,origin) values (?,?,?) returning id]; constraint [superhero_alias_key]"));
    }
}
