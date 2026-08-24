package br.com.baozi.control;

import br.com.baozi.model.Produto;
import br.com.baozi.repo.ProdutoRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoCtrlr {

    private final ProdutoRepo repo;

    public ProdutoCtrlr(ProdutoRepo repo) {
        this.repo = repo;
    }

    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return repo.save(produto);
    }

    @GetMapping
    public List<Produto> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!repo.existsById(id))
            return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}