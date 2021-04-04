package ru.otus.restbooklibrary.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.otus.restbooklibrary.domain.Comment;

import java.util.List;

@RepositoryRestResource(path = "comment")
public interface CommentRepository extends MongoRepository<Comment, String> {
    @RestResource(path = "content", rel = "content")
    List<Comment> findByContent(@Param("content") String content);

    @RestResource(path = "bookTitle", rel = "bookTitle")
    List<Comment> findByBook_Title(@Param("bookTitle") String bookTitle);
}
