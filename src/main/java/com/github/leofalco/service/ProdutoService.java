package com.github.leofalco.service;

import com.github.leofalco.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ProdutoService {
    private final ProdutoRepository repo;
}
