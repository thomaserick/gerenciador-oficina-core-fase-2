package com.fiap.pj.core.usuario.app;

import com.fiap.pj.infra.api.Slice;
import com.fiap.pj.infra.usuario.controller.request.ListarUsuarioRequest;
import com.fiap.pj.infra.usuario.controller.response.UsuarioReponse;
import com.fiap.pj.infra.usuario.persistence.UsuarioRepositoryJpa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarUsuarioServiceTest {

    @Mock
    private UsuarioRepositoryJpa usuarioRepositoryJpa;

    @InjectMocks
    private ListarUsuariorUseCaseImpl listarUsuariorUseCaseImpl;

    @Test
    void deveListarUsuarios() {
        var request = new ListarUsuarioRequest("Teddy", PageRequest.of(0, 10), true);

        var slice = new Slice<UsuarioReponse>(false, List.of());

        when(usuarioRepositoryJpa.findProjectedBy(any(Specification.class), any(PageRequest.class), any(Class.class)))
                .thenReturn(slice);

        var result = listarUsuariorUseCaseImpl.handle(request);

        assertNotNull(result);
        assertEquals(0, result.getItems().size());
    }

} 