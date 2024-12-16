package br.com.livraria.gestao_livraria.controller;

import br.com.livraria.gestao_livraria.domain.compra.Compra;
import br.com.livraria.gestao_livraria.domain.compra.DadosCadastroCompra;
import br.com.livraria.gestao_livraria.domain.compra.DadosListagemCompra;
import br.com.livraria.gestao_livraria.service.CompraService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("compras")
@SecurityRequirement(name = "bearer-key")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemCompra> comprar( @RequestBody DadosCadastroCompra dados){
        var dtoCompra = compraService.realizarCompra(dados);
        return ResponseEntity.ok(dtoCompra);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCompra>> listarCompras( Pageable pageable){
        var page = compraService.devolverCompras(pageable).map(DadosListagemCompra::new);

        return ResponseEntity.ok(page);
    }



}
