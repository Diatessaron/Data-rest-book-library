package ru.otus.restbooklibrary.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.restbooklibrary.domain.Author;
import ru.otus.restbooklibrary.domain.Book;
import ru.otus.restbooklibrary.domain.Genre;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookRepositoryTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    public BookRepository bookRepository;

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);
    private final Book book = new Book("Book", new Author("Author"), new Genre("Genre"));

    @Test
    void testCreateByStatus() throws Exception {
        mockMvc.perform(post("/book").contentType(APPLICATION_JSON_UTF8)
                .content("{\"title\": \"Book\", \"name\": \"Author\", \"name\": \"Genre\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetByTitleByStatus() throws Exception {
        bookRepository.save(book);

        mockMvc.perform(get("/book/search/title?title=Book"))
                .andExpect(status().isOk());
    }

    @Test
    void testByAuthorNameByStatus() throws Exception  {
        bookRepository.save(book);

        mockMvc.perform(get("/book/search/author?author=Author"))
                .andExpect(status().isOk());
    }

    @Test
    void testByGenreNameByStatus() throws Exception {
        bookRepository.save(book);

        mockMvc.perform(get("/book/search/genre?genre=Genre"))
                .andExpect(status().isOk());
    }
}
