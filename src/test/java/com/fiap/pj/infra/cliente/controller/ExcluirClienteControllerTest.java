package com.fiap.pj.infra.cliente.controller;


import com.fiap.pj.core.cliente.app.usecase.ExcluirClienteUserCase;
import com.fiap.pj.core.cliente.app.usecase.command.ExcluirClienteCommand;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteComRelacionamentoException;
import com.fiap.pj.core.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ExcluirClienteControllerTest {

    @Mock
    private ExcluirClienteUserCase excluirClienteUserCase;

    @InjectMocks
    private ClienteController clienteController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    void deveExcluirCliente() throws Exception {

        mock.perform(delete(
                TestUtils.buildURL(ClienteController.PATH, UUID.randomUUID().toString()))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().is2xxSuccessful());
    }

    @Test
    void deveRetornarServicoComRelacionamentoException() {

        Mockito.doThrow(DataIntegrityViolationException.class)
                .when(excluirClienteUserCase)
                .handle(Mockito.any(ExcluirClienteCommand.class));

        var thrown = catchThrowable(() -> mock.perform(delete(
                TestUtils.buildURL(ClienteController.PATH, UUID.randomUUID().toString()))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ));
        assertThat(thrown.getCause()).isInstanceOf(ClienteComRelacionamentoException.class);
    }

}
