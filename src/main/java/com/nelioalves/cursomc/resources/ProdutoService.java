package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    final ProdutoRepository repo;

    @Autowired
    public ProdutoService(ProdutoRepository repo) {
        this.repo = repo;
    }
}
