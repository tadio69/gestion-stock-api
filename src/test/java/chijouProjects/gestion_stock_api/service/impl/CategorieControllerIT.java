package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.CategorieDto;
import chijouProjects.gestion_stock_api.model.Entreprise;
import chijouProjects.gestion_stock_api.repository.EntrepriseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
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

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CategorieControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    private Entreprise testEntreprise;

    @BeforeEach
    void setUp() {
        // On prépare une entreprise pour tous les tests
        testEntreprise = Entreprise.builder()
                .nom("Entreprise Test")
                .description("Description Test")
                .codefiscal("690000000")
                .build();
        testEntreprise = entrepriseRepository.save(testEntreprise);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldCreateCategorieAndReturn200() throws Exception {

        mockMvc.perform(post("/gestionstock/v1/categories/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                  "code":"CAT-001",
                  "designation":"Électronique",
                  "identreprise": %d
                }
            """.formatted(testEntreprise.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("CAT-001"));
    }


    @Test
    @WithMockUser(roles = "USER")
        // Un simple USER ne doit pas pouvoir créer
    void shouldReturn403WhenUserIsNotAdmin() throws Exception {

        CategorieDto dto = CategorieDto.builder()
                .code("CAT01")
                .designation("Categorie test")
                .identreprise(testEntreprise.getId())
                .build();

        mockMvc.perform(post("/gestionstock/v1/categories/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
        // Utilisateur connecté par défaut
    void shouldReturnCategorieById() throws Exception {
        // Ici, tu peux injecter le service pour créer une catégorie avant le test
        // ou tester un ID que tu sais exister (ex: ID 1)
        mockMvc.perform(get("/gestionstock/v1/categories/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
