package com.github.leofalco;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaResourceTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getCategoria1ResurnStatusOk() throws Exception {
        this.mvc.perform(get("/categorias/1")).andExpect(status().isOk());
    }

    @Test
    public void getCategoria0ResurnNotFound() throws Exception {
        this.mvc.perform(get("/categorias/0")).andExpect(status().isNotFound());
    }

    @Test
    public void getCategoriasReturnOk() throws Exception {
        MvcResult mvcResult = this.mvc.perform(get("/categorias"))
                .andExpect(status().isOk()).andReturn();

        Assert.assertEquals("application/json;charset=UTF-8", mvcResult.getResponse().getContentType());
    }


}
