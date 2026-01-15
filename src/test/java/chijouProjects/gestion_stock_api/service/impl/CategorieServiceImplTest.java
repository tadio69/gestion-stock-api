package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.CategorieDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.exception.ErrorCodes;
import chijouProjects.gestion_stock_api.exception.InvalidEntityException;
import chijouProjects.gestion_stock_api.service.CategorieService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class CategorieServiceImplTest {

    @Autowired
    private CategorieService service;

    @Test
    public void shouldSavedCategorieWithSuccess(){
        CategorieDto expectedCategorie = CategorieDto.builder()
                .code("Cat test")
                .designation("Désignation test")
                .identreprise(1)
                .build();
        CategorieDto savedCategorie = service.save(expectedCategorie);

        Assertions.assertNotNull(savedCategorie);
        assertNotNull(savedCategorie.getId());//import statique
        assertEquals(expectedCategorie.getCode(), savedCategorie.getCode());
        assertEquals(expectedCategorie.getDesignation(), savedCategorie.getDesignation());
        assertEquals(expectedCategorie.getIdentreprise(), savedCategorie.getIdentreprise());
    }

    @Test
    public void shouldUpdateCategorieWithSuccess(){
        CategorieDto expectedCategorie = CategorieDto.builder()
                .code("Cat test")
                .designation("Désignation test")
                .identreprise(1)
                .build();
        CategorieDto savedCategorie = service.save(expectedCategorie);

        CategorieDto categorieToUpdate = savedCategorie;
        categorieToUpdate.setCode("Cat updated");

        savedCategorie = service.save(categorieToUpdate);

        assertNotNull(categorieToUpdate);
        assertNotNull(categorieToUpdate.getId());//import statique
        assertEquals(categorieToUpdate.getCode(), savedCategorie.getCode());
        assertEquals(categorieToUpdate.getDesignation(), savedCategorie.getDesignation());
        assertEquals(categorieToUpdate.getIdentreprise(), savedCategorie.getIdentreprise());
    }

    @Test
    public void shouldThrowInvalidEntityException(){
        CategorieDto expectedCategorie = CategorieDto.builder().build();

        InvalidEntityException expectedException = assertThrows(InvalidEntityException.class, () -> service.save(expectedCategorie));

        assertEquals(ErrorCodes.CATEGORIE_NOT_VALID, expectedException.getErrorCode());
        assertEquals(1, expectedException.getErrors().size());//la taille de Errors doit être 1
        assertEquals("Veuillez renseigner le code de la catégorie", expectedException.getErrors().get(0));
    }

    @Test
    public void shouldThrowEntityNotFoundException(){

        EntityNotFoundException expectedException = assertThrows(EntityNotFoundException.class, () -> service.findById(0));

        assertEquals(ErrorCodes.CATEGORIE_NOT_FOUND, expectedException.getErrorCode());
        assertEquals("Aucune catégorie avec l'ID = 0 n'a été trouvée dans la BDD", expectedException.getMessage());
    }

    @Test
    public void shouldThrowEntityNotFoundException2(){
        assertThrows(EntityNotFoundException.class, () -> service.findById(0));
    }
}