package ru.otus.restbooklibrary.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.restbooklibrary.domain.Genre;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GenreRepositoryTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GenreRepository genreRepository;

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    @Test
    void testCreateByStatus() throws Exception {
        mockMvc.perform(post("/genre").contentType(APPLICATION_JSON_UTF8)
                .content("{\"name\": \"Genre\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetGenreByNameByStatus() throws Exception {
        genreRepository.save(new Genre("Modernist novel"));

        mockMvc.perform(get("/genre/search/name?name=Modernist novel"))
                .andExpect(status().isOk());
    }
}
