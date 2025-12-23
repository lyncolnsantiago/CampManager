package br.com.campmanager.projeto.dto;

public class AuthResponse {

    private String token;
    private String mensagem;

    // --- Construtor vazio (Boas práticas para frameworks) ---
    public AuthResponse() {
    }

    // --- Construtor usado no seu AuthController ---
    public AuthResponse(String token, String mensagem) {
        this.token = token;
        this.mensagem = mensagem;
    }

    // --- Getters e Setters ---

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
