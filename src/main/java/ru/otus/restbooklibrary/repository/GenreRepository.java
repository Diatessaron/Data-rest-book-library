package ru.otus.restbooklibrary.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.otus.restbooklibrary.domain.Genre;

@RepositoryRestResource(path = "genre")
public interface GenreRepository extends MongoRepository<Genre, String> {
    @RestResource(path = "name", rel = "name")
    Genre findByName(@Param("name") String name);
}
