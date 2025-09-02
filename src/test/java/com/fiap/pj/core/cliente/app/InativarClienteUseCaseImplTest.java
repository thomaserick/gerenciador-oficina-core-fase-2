package com.fiap.pj.core.cliente.app;


import com.fiap.pj.core.cliente.app.usecase.command.InativarClienteCommand;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteNaoEncontradoException;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.infra.cliente.persistence.ClienteRepositoryJpa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InativarClienteUseCaseImplTest {

    @Mock
    private ClienteRepositoryJpa clienteRepositoryJpa;

    @InjectMocks
    private InativarClienteUseCaseImpl inativarClienteUseCaseImpl;

    @Test
    void deveAtivarCliente() {
        var id = UUID.randomUUID();
        when(clienteRepositoryJpa.findByIdOrThrowNotFound(id)).thenReturn(ClienteTestFactory.umCliente());
        inativarClienteUseCaseImpl.handle(new InativarClienteCommand(id));
        verify(clienteRepositoryJpa).save(Mockito.any(Cliente.class));
    }

    @Test
    void deveRetornarClienteNaoEncontradoException() {
        var id = UUID.randomUUID();

        Mockito.doThrow(new ClienteNaoEncontradoException())
                .when(clienteRepositoryJpa)
                .findByIdOrThrowNotFound(id);

        var thrown = catchThrowable(() -> inativarClienteUseCaseImpl.handle(new InativarClienteCommand(id)));
        assertThat(thrown).isInstanceOf(ClienteNaoEncontradoException.class);

    }


}
