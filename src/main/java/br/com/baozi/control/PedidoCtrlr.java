package br.com.baozi.control;

import br.com.baozi.model.Pedido;
import br.com.baozi.repo.PedidoRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoCtrlr {

    private final PedidoRepo repo;

    public PedidoCtrlr(PedidoRepo repo) {
        this.repo = repo;
    }

    @PostMapping
    public Pedido criar(@RequestBody Pedido pedido) {
        return repo.save(pedido);
    }

    @GetMapping
    public List<Pedido> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long id) {
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