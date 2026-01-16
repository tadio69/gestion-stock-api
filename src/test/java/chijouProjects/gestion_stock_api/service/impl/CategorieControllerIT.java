package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.CategorieDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class CategorieControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // Pour convertir les objets en JSON

    @Test
    @WithMockUser(roles = "ADMIN") // On simule un ADMIN pour ce test
    void shouldCreateCategorieAndReturn201() throws Exception {
        // GIVEN
        CategorieDto dto = CategorieDto.builder()
                .code("CAT-001")
                .designation("Électronique")
                .identreprise(1)
                .build();

        // WHEN
        mockMvc.perform(post("/api/categories") // Vérifie ton @RequestMapping
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))

                // THEN
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("CAT-001"))
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @WithMockUser(roles = "USER") // Un simple USER ne doit pas pouvoir créer
    void shouldReturn403WhenUserIsNotAdmin() throws Exception {
        CategorieDto dto = CategorieDto.builder().code("X").designation("X").build();

        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isForbidden()); // On attend une erreur 403
    }

    @Test
    @WithMockUser // Utilisateur connecté par défaut
    void shouldReturnCategorieById() throws Exception {
        // Ici, tu peux injecter le service pour créer une catégorie avant le test
        // ou tester un ID que tu sais exister (ex: ID 1)
        mockMvc.perform(get("/api/categories/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
