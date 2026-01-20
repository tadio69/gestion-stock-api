package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.CategorieDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.exception.ErrorCodes;
import chijouProjects.gestion_stock_api.exception.InvalidEntityException;
import chijouProjects.gestion_stock_api.model.Categorie;
import chijouProjects.gestion_stock_api.repository.CategorieRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // Uniquement Mockito, pas de Spring
class CategorieServiceImplTest {

    @Mock
    private CategorieRepository categorieRepository; // On simule la BDD

    @Mock
    private EntityManager entityManager; // Nécessaire car utilisé dans ton constructeur

    @InjectMocks
    private CategorieServiceImpl service; // Le vrai service avec les mocks injectés

    @Test
    public void shouldSavedCategorieWithSuccess() {
        // GIVEN (On prépare les données et le comportement du mock)
        CategorieDto dto = CategorieDto.builder()
                .code("Cat test")
                .designation("Désignation test")
                .identreprise(1)
                .build();

        Categorie entity = CategorieDto.toEntity(dto);
        entity.setId(100); // On simule que la BDD génère l'ID 100

        // On dit au mock : "Quand on sauvegarde n'importe quelle entité, renvoie l'entité avec ID"
        Mockito.when(categorieRepository.save(Mockito.any(Categorie.class))).thenReturn(entity);

        // WHEN (On exécute la méthode du service)
        CategorieDto savedCategorie = service.save(dto);

        // THEN (On vérifie les résultats)
        assertNotNull(savedCategorie);//import statique donc pas Assertions.assertNotNull
        assertEquals(100, savedCategorie.getId());
        assertEquals("Cat test", savedCategorie.getCode());

        // On vérifie aussi que le repository a bien été sollicité une fois
        Mockito.verify(categorieRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void shouldUpdateCategorieWithSuccess(){
        // 1. Préparation de la catégorie existante (simulée avec un ID)
        CategorieDto dtoInitial = CategorieDto.builder()
                .id(1) // On fait comme s'il venait déjà de la BDD
                .code("Cat test")
                .designation("Désignation test")
                .identreprise(1)
                .build();

        // On prépare la version modifiée
        Categorie entityModifiee = CategorieDto.toEntity(dtoInitial);
        entityModifiee.setCode("Cat updated");

        // 2. Programmation des Mocks
        // On dit au repository : "Peu importe ce qu'on te donne à enregistrer, renvoie l'entité modifiée"
        Mockito.when(categorieRepository.save(Mockito.any(Categorie.class))).thenReturn(entityModifiee);

        // 3. Exécution de la mise à jour
        dtoInitial.setCode("Cat updated"); // On change le code dans le DTO
        CategorieDto savedCategorie = service.save(dtoInitial);

        // 4. Vérifications (Assertions)
        assertNotNull(savedCategorie);
        assertNotNull(savedCategorie.getId());
        assertEquals("Cat updated", savedCategorie.getCode());
        assertEquals(dtoInitial.getDesignation(), savedCategorie.getDesignation());

        // On vérifie que le repository a été appelé
        Mockito.verify(categorieRepository).save(Mockito.any(Categorie.class));
    }

    @Test
    public void shouldThrowInvalidEntityException() {
        // GIVEN (Un DTO vide)
        CategorieDto expectedCategorie = CategorieDto.builder().build();

        // WHEN & THEN (On vérifie que l'exception est levée par la logique du service)
        InvalidEntityException expectedException = assertThrows(InvalidEntityException.class,
                () -> service.save(expectedCategorie));

        assertEquals(ErrorCodes.CATEGORIE_NOT_VALID, expectedException.getErrorCode());
        // Ici, pas besoin de mock car la validation échoue AVANT d'appeler le repository
    }

    @Test
    public void shouldThrowEntityNotFoundException() {
        // GIVEN (On dit au mock de renvoyer "vide")
        Mockito.when(categorieRepository.findById(0)).thenReturn(Optional.empty());

        // WHEN & THEN
        EntityNotFoundException expectedException = assertThrows(EntityNotFoundException.class,
                () -> service.findById(0));

        assertEquals(ErrorCodes.CATEGORIE_NOT_FOUND, expectedException.getErrorCode());
    }

    @Test
    public void shouldThrowInvalidEntityExceptionWhenDeleteIdIsNull() {
        // WHEN & THEN
        InvalidEntityException exception = assertThrows(
                InvalidEntityException.class,
                () -> service.delete(null)
        );

        assertEquals(ErrorCodes.CATEGORIE_NOT_VALID, exception.getErrorCode());

        Mockito.verify(categorieRepository, Mockito.never()).deleteById(Mockito.any());
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenCategorieDoesNotExist() {
        // GIVEN
        Integer id = 10;
        Mockito.when(categorieRepository.existsById(id)).thenReturn(false);

        // WHEN & THEN
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> service.delete(id)
        );

        assertEquals(ErrorCodes.CATEGORIE_NOT_FOUND, exception.getErrorCode());

        Mockito.verify(categorieRepository, Mockito.times(1)).existsById(id);
        Mockito.verify(categorieRepository, Mockito.never()).deleteById(Mockito.any());
    }


    @Test
    public void shouldDeleteCategorieWhenIdExists() {
        // GIVEN
        Integer id = 5;
        Mockito.when(categorieRepository.existsById(id)).thenReturn(true);

        // WHEN
        service.delete(id);

        // THEN
        Mockito.verify(categorieRepository, Mockito.times(1)).existsById(id);
        Mockito.verify(categorieRepository, Mockito.times(1)).deleteById(id);
    }

}
