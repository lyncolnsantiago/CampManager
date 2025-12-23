package br.com.campmanager.projeto.controller;

import br.com.campmanager.projeto.dto.UsuarioResponse;
import br.com.campmanager.projeto.entity.Usuario;
import br.com.campmanager.projeto.service.UsuarioService; // Certifique-se que existe
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/geral")
public class GeralController {

    private final UsuarioService usuarioService;

    // Injeção de dependência via construtor
    public GeralController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint simples para testar se a API está no ar.
     * URL: GET http://localhost:8080/api/v1/geral/status
     */
    @GetMapping("/status")
    public ResponseEntity<String> checkStatus() {
        return ResponseEntity.ok("API está online e funcionando!");
    }

    /**
     * Retorna os dados do usuário logado atualmente (baseado no Token JWT).
     * URL: GET http://localhost:8080/api/v1/geral/me
     */
    @GetMapping("/me")
    public ResponseEntity<UsuarioResponse> getMyProfile() {
        // 1. Pega o email do usuário que está no Token JWT
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // 2. Busca o usuário completo no banco de dados
        // Nota: Seu UsuarioService precisa ter um método para buscar por email
        Usuario usuario = usuarioService.buscarPorEmail(email);

        // 3. Converte para o DTO (usando o método fromEntity que arrumamos agora pouco)
        UsuarioResponse response = UsuarioResponse.fromEntity(usuario);

        return ResponseEntity.ok(response);
    }
}