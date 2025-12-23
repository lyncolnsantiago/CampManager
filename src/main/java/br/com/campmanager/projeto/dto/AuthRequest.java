package br.com.campmanager.projeto.dto;

import jakarta.validation.constraints.NotBlank;
// Se estiver usando Spring Boot antigo (versão 2.x), mude para:
// import javax.validation.constraints.NotBlank;

public class AuthRequest {

    @NotBlank(message = "O email é obrigatório")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    // --- Construtores ---
    public AuthRequest() {
    }

    public AuthRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    // --- Getters e Setters ---
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}