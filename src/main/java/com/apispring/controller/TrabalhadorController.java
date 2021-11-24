package com.apispring.controller;

import com.apispring.model.Trabalhador;
import com.apispring.repository.TrabalhadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class TrabalhadorController {

    @Autowired
    private TrabalhadorRepository trabalhadorRepository;

    @GetMapping("/trabalhadores")
    @ResponseBody
    public ResponseEntity<List<Trabalhador>> TodosTrabalhadores() {
        List<Trabalhador> trabalhadores = trabalhadorRepository.findAll();
        return new ResponseEntity<List<Trabalhador>>(trabalhadores, HttpStatus.OK);
    }

    @GetMapping("/trabalhador/{id}")
    @ResponseBody
    public ResponseEntity<Trabalhador> ClientePorId(@PathVariable(value = "id") long id) {
        Optional<Trabalhador> trabalhador = trabalhadorRepository.findById(id);
        if (trabalhador.isPresent()){
            return new ResponseEntity<Trabalhador>(trabalhador.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/trabalhador")
    @ResponseBody
    public ResponseEntity<Trabalhador> adicionarCliente(@RequestBody Trabalhador trabalhador) {
        Trabalhador novoTrabalhador = trabalhadorRepository.save(trabalhador);
        return new ResponseEntity<Trabalhador>(novoTrabalhador, HttpStatus.OK);
    }

    @PutMapping("/alterar-trabalhador")
    @ResponseBody
    public ResponseEntity<Trabalhador> alterarCliente(@RequestBody Trabalhador trabalhador) {
        Trabalhador trabalhadorExistente = trabalhadorRepository.save(trabalhador);
        return new ResponseEntity<Trabalhador>(trabalhadorExistente, HttpStatus.OK);
    }

    @DeleteMapping("/trabalhador/{id}")
    @ResponseBody()
    public ResponseEntity<Object> deletarCliente(@PathVariable(value = "id") Long id) {
        Optional<Trabalhador> trabalhador = trabalhadorRepository.findById(id);
        if (id == null || id < 0) {
            return new ResponseEntity<>("Id inválido", HttpStatus.NOT_FOUND);
        }
        if (trabalhador.isPresent()) {
            trabalhadorRepository.delete(trabalhador.get());
            return new ResponseEntity<>("Usuário Deletado com sucesso!", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
    }


}
