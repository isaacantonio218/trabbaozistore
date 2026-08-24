package br.com.baozi.repo;

import br.com.baozi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepo extends JpaRepository<Produto, Long> {
}
