package ru.otus.restbooklibrary.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.restbooklibrary.domain.Author;
import ru.otus.restbooklibrary.domain.Book;
import ru.otus.restbooklibrary.domain.Comment;
import ru.otus.restbooklibrary.domain.Genre;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CommentRepositoryTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CommentRepository commentRepository;

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);
    private final Comment comment = new Comment("Content", new Book("Book", new Author("Author"), new Genre("Genre")));

    @Test
    void testCreateByStatus() throws Exception {
        mockMvc.perform(post("/comment").contentType(APPLICATION_JSON_UTF8)
                .content("{\"content\": \"Content\", \"title\": \"Book\", \"name\": \"Author\", \"name\": \"Genre\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetCommentByContentByStatus() throws Exception {
        commentRepository.save(comment);

        mockMvc.perform(get("/comment/search/content?content=Content"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetCommentByBookTitleByStatus() throws Exception {
        commentRepository.save(comment);

        mockMvc.perform(get("/comment/search/bookTitle?bookTitle=Book"))
                .andExpect(status().isOk());
    }
}
