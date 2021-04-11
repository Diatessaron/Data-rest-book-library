package ru.otus.restbooklibrary.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.otus.restbooklibrary.domain.Book;

import java.util.List;

@RepositoryRestResource(path = "book")
public interface BookRepository extends MongoRepository<Book, String> {
    @RestResource(path = "title", rel = "title")
    List<Book> findByTitle(@Param("title") String title);

    @RestResource(path = "author", rel = "author")
    List<Book> findByAuthor_Name(@Param("author") String author);

    @RestResource(path = "genre", rel = "genre")
    List<Book> findByGenre_Name(@Param("genre") String genre);
}
