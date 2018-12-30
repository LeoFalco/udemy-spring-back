package com.github.leofalco.service;

import com.github.leofalco.repository.ProdutoRepository;
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