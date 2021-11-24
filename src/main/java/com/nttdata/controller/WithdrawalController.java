
package com.nttdata.controller;

import com.nttdata.model.Withdrawal;
import com.nttdata.service.WithdrawalService;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**Dentro de la clase WithdrawalController se realizan los metodos http.*/
@RestController
@RequestMapping("/withdrawal")
public class WithdrawalController {
  
  @Autowired
  private WithdrawalService service;
  
  
  
  /** Crea los registros de la tabla.* @return crea registros de la tabla. */
  @PostMapping
  public Mono<ResponseEntity<Withdrawal>> 
      create(@RequestBody Withdrawal withdrawal, final ServerHttpRequest request) {
    
    return service.create(withdrawal)
        .map(c -> ResponseEntity
            .created(URI.create(request.getURI().toString().concat("/").concat(c.getId())))
            .contentType(MediaType.APPLICATION_JSON)
            .body(c));
  }
  
  /** Muestra los registros de la tabla * @return registro de la tabla seleccionada. */
  @GetMapping
  public Mono<ResponseEntity<Flux<Withdrawal>>> findAll() {
    Flux<Withdrawal> withdrawal = service.findAll();
    
    return Mono.just(ResponseEntity
        .ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(withdrawal));              
    
  }
  
  /**Muestra los registros de la tabla a traves de un id.
   * * @return registro de la tabla seleccionada por id.*/
  @GetMapping("/{id}")
  public Mono<ResponseEntity<Withdrawal>> findById(@PathVariable("id") String id) {
    
    return service.findById(id)
        .map(foundObject -> ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(foundObject))
        .defaultIfEmpty(ResponseEntity.noContent().build());
    
  }
  
}
