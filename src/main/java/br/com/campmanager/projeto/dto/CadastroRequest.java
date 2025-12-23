package br.com.campmanager.projeto.dto; // Nota: O ideal seria mover para um pacote .dto, mas pode deixar aqui por enquanto

//Se estiver usando Spring Boot 3+ (o mais comum hoje), use jakarta.
//Se estiver usando uma versão antiga (2.7 ou menos), mude "jakarta" para "javax".
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CadastroRequest {

 @NotBlank(message = "O nome é obrigatório")
 private String nome;

 @NotBlank(message = "O email é obrigatório")
 @Email(message = "O email deve ser válido")
 private String email;

 @NotBlank(message = "A senha é obrigatória")
 @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
 private String senha;

 // --- Construtores (Opcional, mas útil) ---
 public CadastroRequest() {
 }

 public CadastroRequest(String nome, String email, String senha) {
     this.nome = nome;
     this.email = email;
     this.senha = senha;
 }

 // --- Getters e Setters (Obrigatórios para o Spring funcionar) ---

 public String getNome() {
     return nome;
 }

 public void setNome(String nome) {
     this.nome = nome;
 }

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

 public CharSequence getPassword() {
	// TODO Auto-generated method stub
	return null;
 }
}
