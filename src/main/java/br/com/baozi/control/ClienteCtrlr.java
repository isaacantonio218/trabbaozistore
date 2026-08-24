package br.com.baozi.control;

import br.com.baozi.model.Cliente;
import br.com.baozi.repo.ClienteRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteCtrlr {

    private final ClienteRepo repo;

    public ClienteCtrlr(ClienteRepo repo) {
        this.repo = repo;
    }

    @PostMapping
    public Cliente criar(@RequestBody Cliente cliente) {
        return repo.save(cliente);
    }

    @GetMapping
    public List<Cliente> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
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
