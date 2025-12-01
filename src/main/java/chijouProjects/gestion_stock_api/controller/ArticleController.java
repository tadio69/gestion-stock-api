package chijouProjects.gestion_stock_api.controller;

import chijouProjects.gestion_stock_api.controller.api.ArticleApi;
import chijouProjects.gestion_stock_api.dto.ArticleDto;
import chijouProjects.gestion_stock_api.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController implements ArticleApi {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
    @Override
    public ResponseEntity<ArticleDto> save(ArticleDto articleDto) {
        return ResponseEntity.ok(articleService.save(articleDto)); //ou bien
        //return ResponseEntity.status(HttpStatus.OK).body(articleService.save(articleDto));
    }

    @Override
    public ResponseEntity<ArticleDto> findById(Integer id) {
        return ResponseEntity.ok(articleService.findById(id));
        //return ResponseEntity.status(HttpStatus.OK).body(articleService.findById(id));
    }

    @Override
    public ResponseEntity<ArticleDto> findByCode(String code) {
        return ResponseEntity.ok(articleService.findByCode(code));
        //return ResponseEntity.status(HttpStatus.OK).body(articleService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<ArticleDto>> findAll() {
        return ResponseEntity.ok(articleService.findAll());
        //return ResponseEntity.status(HttpStatus.OK).body(articleService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        articleService.delete(id);
        //return ResponseEntity.ok().build(); si on est sûr à 100% sinon on utilise
        return ResponseEntity.noContent().build();
    }
}
