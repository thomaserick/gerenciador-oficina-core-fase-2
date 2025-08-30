package com.fiap.pj.infra.usuario.controller;

import com.fiap.pj.core.sk.web.ResponseEntityUtils;
import com.fiap.pj.core.sk.web.ResponseEntityUtils.ResponseId;
import com.fiap.pj.core.usuario.app.usecase.AlterarUsuarioUseCase;
import com.fiap.pj.core.usuario.app.usecase.CriarUsuarioUseCase;
import com.fiap.pj.core.usuario.app.usecase.command.AlterarUsuarioCommand;
import com.fiap.pj.core.usuario.app.usecase.command.CriarUsuarioCommand;
import com.fiap.pj.infra.usuario.controller.openapi.UsuarioControllerOpenApi;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = UsuarioController.PATH)
@AllArgsConstructor
public class UsuarioController implements UsuarioControllerOpenApi {

    public static final String PATH = "v1/usuarios";

    private final CriarUsuarioUseCase criarUsuarioUseCase;
    private final AlterarUsuarioUseCase alterarUsuarioUseCase;
//    private final ListarUsuarioUseCase listarUsuarioUseCase;
//    private final ExcluirUsuarioUseCase excluirUsuarioUseCase;


    @PostMapping
    public ResponseEntity<ResponseId> criarUsuario(@Valid @RequestBody CriarUsuarioCommand cmd) {
        var usuarioId = criarUsuarioUseCase.handle(cmd);
        return ResponseEntityUtils.create(getClass(), usuarioId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alteraUsuario(@Valid @PathVariable UUID id, @RequestBody AlterarUsuarioCommand cmd) {
        cmd.setId(id);
        alterarUsuarioUseCase.handle(cmd);
        return ResponseEntity.ok().build();
    }
//
//    @PostMapping("{id}/inativar")
//    public ResponseEntity<Void> inativarUsuario(@PathVariable UUID id) {
//        inativarUsuarioUseCase.handle(new InativarUsuarioCommand(id));
//        return ResponseEntity.ok().build();
//    }
//
//
//    @PostMapping("{id}/ativar")
//    public ResponseEntity<Void> ativarUsuario(@PathVariable UUID id) {
//        ativarUsuarioUseCase.handle(new AtivarUsuarioCommand(id));
//        return ResponseEntity.ok().build();
//    }
//
//    @Override
//    @GetMapping
//    public Slice<UsuarioReponse> listarUsuario(@ParameterObject ListarUsuarioRequest filterRequest, @ParameterObject Pageable pageable) {
//        filterRequest.setPageable(pageable);
//        return listarUsuarioUseCase.handle(filterRequest);
//    }
//
//    @Override
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> excluirUsuario(@PathVariable UUID id) {
//        try {
//            excluirUsuarioUseCase.handle(new ExcluirUsuarioCommand(id));
//        } catch (DataIntegrityViolationException e) {
//            throw new UsuarioComRelacionamentoException();
//        }
//        return ResponseEntity.ok().build();
//    }
}
