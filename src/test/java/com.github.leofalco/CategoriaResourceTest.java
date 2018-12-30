package com.github.leofalco;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaResourceTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void exampleTest() throws Exception {
        this.mvc.perform(get("/categorias/1")).andExpect(status().isOk());
    }

    @Test
    public void exampleTest2() throws Exception {
        this.mvc.perform(get("/categorias/0")).andExpect(status().isNotFound());
    }

    @Test
    public void exampleTest3() throws Exception {
        this.mvc.perform(get("/categorias/0")).andExpect(status().isOk());
    }
}