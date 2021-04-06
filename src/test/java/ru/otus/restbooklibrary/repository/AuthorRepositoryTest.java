package ru.otus.restbooklibrary.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.restbooklibrary.domain.Author;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorRepository.class)
class AuthorRepositoryTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorRepository authorRepository;

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    @Test
    void testCreateByStatus() throws Exception {
        Author author = new Author("Author");
        when(authorRepository.save(author)).thenReturn(author);

        mockMvc.perform(post("/author").contentType(APPLICATION_JSON_UTF8)
                .content("{\"name\": \"Author\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetAuthorByNameByStatus() throws Exception {
        when(authorRepository.findByName("James%20Joyce")).thenReturn(List.of(new Author("James Joyce")));

        mockMvc.perform(get("/author/search/name?name=James%20Joyce"))
                .andExpect(status().isOk());
    }
}
