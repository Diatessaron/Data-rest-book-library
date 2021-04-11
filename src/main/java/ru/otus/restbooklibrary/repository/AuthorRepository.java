package ru.otus.restbooklibrary.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.otus.restbooklibrary.domain.Author;

import java.util.List;

@RepositoryRestResource(path = "author")
public interface AuthorRepository extends MongoRepository<Author, String> {
    @RestResource(path = "name", rel = "name")
    List<Author> findByName(@Param("name") String name);
}
