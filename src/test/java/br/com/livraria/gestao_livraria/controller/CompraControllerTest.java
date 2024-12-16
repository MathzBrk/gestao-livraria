package br.com.livraria.gestao_livraria.controller;

import br.com.livraria.gestao_livraria.domain.autor.Autor;
import br.com.livraria.gestao_livraria.domain.cliente.Cliente;
import br.com.livraria.gestao_livraria.domain.compra.Compra;
import br.com.livraria.gestao_livraria.domain.compra.DadosCadastroCompra;
import br.com.livraria.gestao_livraria.domain.compra.DadosListagemCompra;
import br.com.livraria.gestao_livraria.domain.livro.Categoria;
import br.com.livraria.gestao_livraria.domain.livro.Livro;
import br.com.livraria.gestao_livraria.service.CompraService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class CompraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompraService compraService;

    @Autowired
    private JacksonTester<DadosCadastroCompra> dadosCadastroCompraJson;

    @Autowired
    private JacksonTester<DadosListagemCompra> dadosListagemCompraJson;

    @Test
    @DisplayName("Deveria devolver coódigo http 400 quando informações estão inválidas")
    @WithMockUser
    void comprarCenario1() throws Exception {
        var response = mockMvc.perform(post("/compras"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver coódigo http 200 quando informações estão válidas")
    @WithMockUser
    void comprarCenario2() throws Exception {
        var data = LocalDate.now();

        var dadosCadastro = new DadosCadastroCompra(1L, 1L, data, 3);
        var dadosListagem = new DadosListagemCompra(null, dadosCadastro.idCliente(), dadosCadastro.idLivro(), dadosCadastro.dataCompra());


        when(compraService.realizarCompra(any())).thenReturn(dadosListagem);

        var response = mockMvc.perform(post("/compras")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosCadastroCompraJson.write(dadosCadastro).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosListagemCompraJson.write(
                dadosListagem
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }



}