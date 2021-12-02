package com.nttdata.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.nttdata.model.Withdrawal;
import com.nttdata.repository.WithdrawalRepository;
import com.nttdata.service.impl.WithdrawalServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Import(WithdrawalServiceImpl.class)
@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = WithdrawalController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
class WithdrawalControllerTest {
  
  @Autowired
  private WebTestClient withdrawal;
  
  @MockBean
  private WithdrawalRepository repository;

  @Test
  void testCreate() {
    
    Withdrawal withdrawals = new Withdrawal("1","4551805235241265",150.0,"Primer Deposito",LocalDateTime.now());
    
    Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(Mono.just(withdrawals));
    withdrawal.post().uri("/withdrawal")
    .contentType(MediaType.APPLICATION_JSON)
    .accept(MediaType.APPLICATION_JSON)
    .body(Mono.just(withdrawals), Withdrawal.class)
    .exchange()
    .expectStatus().isCreated()
    .expectHeader().contentType(MediaType.APPLICATION_JSON)
    .expectBody()
    .jsonPath("$.id").isNotEmpty()
    .jsonPath("$.cardNumber").isEqualTo("4551805235241265")
    .jsonPath("$.amount").isEqualTo(150.0)
    .jsonPath("$.description").isEqualTo("Primer Deposito");
  }

  @Test
  void testFindAll() {
    
    Withdrawal withdrawals = new Withdrawal("2","4551805235241264",130.0,"Segundo Deposito",LocalDateTime.now());
    List<Withdrawal> list = new ArrayList<>();
    list.add(withdrawals);
    Flux<Withdrawal> fluxwithdrawal = Flux.fromIterable(list);
    
    Mockito.when(repository.findAll()).thenReturn(fluxwithdrawal);
    withdrawal.get().uri("/withdrawal").accept(MediaType.APPLICATION_JSON)
    .exchange().expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON)
    .expectBodyList(Withdrawal.class).hasSize(1);
    
  }

  @Test
  void testFindById() {
    Withdrawal withdrawals = new Withdrawal("2","4551805235241264",130.0,"Segundo Deposito",LocalDateTime.now());
    
    Mockito.when(repository.findById("2")).thenReturn(Mono.just(withdrawals));
    withdrawal.get().uri("/withdrawal/2")
    .accept(MediaType.APPLICATION_JSON).exchange()
    .expectStatus().isOk()
    .expectHeader().contentType(MediaType.APPLICATION_JSON)
    .expectBody()
    .jsonPath("$.id").isNotEmpty()
    .jsonPath("$.cardNumber").isEqualTo("4551805235241264")
    .jsonPath("$.amount").isEqualTo(130.0)
    .jsonPath("$.description").isEqualTo("Segundo Deposito");
  }

}
