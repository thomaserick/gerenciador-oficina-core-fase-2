package com.fiap.pj.core.cliente.app;


import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.app.usecase.ListarClienteUseCase;
import com.fiap.pj.infra.cliente.controller.request.ListarClienteRequest;
import com.fiap.pj.infra.cliente.controller.response.ClienteResponse;
import com.fiap.pj.infra.sk.api.Slice;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class ListarClienteUseCaseImpl implements ListarClienteUseCase {

    private final ClienteGateway clienteGateway;

    @Override
    public Slice<ClienteResponse> handle(ListarClienteRequest request) {
        return clienteGateway.listarUsuarios(request);
    }
}
