package br.com.campmanager.projeto.dto;

import br.com.campmanager.projeto.entity.Usuario;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO utilizado para retornar dados do usuário para o Frontend.
 * Evita expor o senhaHash.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    private Long idUsuario;
    private String nickname;
    private String nomeCompleto;
    private String email;
    private String discordId;
    private String googleId;
    private String tipoUsuario; // Mapeia o Enum TipoUsuario
    private LocalDateTime dataCriacao;
    private String equipeAtual; // Nome da equipe ou null

    
    public UsuarioResponse() {
		this.idUsuario = idUsuario;
		this.nickname = nickname;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.discordId = discordId;
		this.googleId = googleId;
		this.tipoUsuario = tipoUsuario;
		this.dataCriacao = dataCriacao;
		this.equipeAtual = equipeAtual;
	}
    
	public Long getIdUsuario() {
		return idUsuario;
	}




	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}




	public String getNickname() {
		return nickname;
	}




	public void setNickname(String nickname) {
		this.nickname = nickname;
	}




	public String getNomeCompleto() {
		return nomeCompleto;
	}




	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getDiscordId() {
		return discordId;
	}




	public void setDiscordId(String discordId) {
		this.discordId = discordId;
	}




	public String getGoogleId() {
		return googleId;
	}




	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}




	public String getTipoUsuario() {
		return tipoUsuario;
	}




	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}




	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}




	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}




	public String getEquipeAtual() {
		return equipeAtual;
	}




	public void setEquipeAtual(String equipeAtual) {
		this.equipeAtual = equipeAtual;
	}




	/**
     * Construtor estático para converter a Entidade Usuario para este DTO.
     * * @param usuario A entidade Usuario completa.
     * @return O objeto DTO formatado.
     */
    public static UsuarioResponse fromEntity(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();

        response.setIdUsuario(usuario.getIdUsuario());
        response.setNickname(usuario.getNickname());
        response.setNomeCompleto(usuario.getNomeCompleto());
        response.setEmail(usuario.getEmail());
        response.setDiscordId(usuario.getDiscordId());
        response.setGoogleId(usuario.getGoogleId());
        response.setDataCriacao(usuario.getDataCriacao());

        // Tratamento seguro para TipoUsuario
        if (usuario.getTipoUsuario() != null) {
            response.setTipoUsuario(usuario.getTipoUsuario().name());
        } else {
            response.setTipoUsuario("MEMBER");
        }

        // Verifica se o usuário tem equipe para evitar NullPointerException
        if (usuario.getEquipe() != null) {
            response.setEquipeAtual(usuario.getEquipe().getNomeEquipe());
        } else {
            response.setEquipeAtual(null);
        }

        return response;
    }
}