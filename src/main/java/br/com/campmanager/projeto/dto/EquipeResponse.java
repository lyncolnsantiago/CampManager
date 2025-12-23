package br.com.campmanager.projeto.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

import br.com.campmanager.projeto.entity.Equipe;

/**
 * DTO utilizado para retornar os dados da Equipe recém-criada ao frontend.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipeResponse {

    private Long idEquipe;
    private String nomeEquipe;
    private String tagGuilda;
    private String capitaoNickname; 
    private LocalDateTime dataCriacao;
    private String status;
    private int numeroMembros; 

    public EquipeResponse() {
		this.idEquipe = idEquipe;
		this.nomeEquipe = nomeEquipe;
		this.tagGuilda = tagGuilda;
		this.capitaoNickname = capitaoNickname;
		this.dataCriacao = dataCriacao;
		this.status = status;
		this.numeroMembros = numeroMembros;
	}

	public Long getIdEquipe() {
		return idEquipe;
	}





	public void setIdEquipe(Long idEquipe) {
		this.idEquipe = idEquipe;
	}





	public String getNomeEquipe() {
		return nomeEquipe;
	}





	public void setNomeEquipe(String nomeEquipe) {
		this.nomeEquipe = nomeEquipe;
	}





	public String getTagGuilda() {
		return tagGuilda;
	}





	public void setTagGuilda(String tagGuilda) {
		this.tagGuilda = tagGuilda;
	}





	public String getCapitaoNickname() {
		return capitaoNickname;
	}





	public void setCapitaoNickname(String capitaoNickname) {
		this.capitaoNickname = capitaoNickname;
	}





	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}





	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}





	public String getStatus() {
		return status;
	}





	public void setStatus(String status) {
		this.status = status;
	}





	public int getNumeroMembros() {
		return numeroMembros;
	}





	public void setNumeroMembros(int numeroMembros) {
		this.numeroMembros = numeroMembros;
	}

	/**
     * Método estático para converter a Entidade Equipe para este DTO de Resposta.
     */
    public static EquipeResponse fromEntity(Equipe equipe) {
        // 1. Lógica para contar membros
        int membrosCount = (equipe.getMembros() != null) ? equipe.getMembros().size() : 0;

        if (membrosCount == 0 && equipe.getCapitao() != null) {
            membrosCount = 1;
        }

        // 2. Criando o objeto da resposta do jeito clássico (sem Builder) para evitar erros
        EquipeResponse response = new EquipeResponse();
        
        response.setIdEquipe(equipe.getIdEquipe());
        response.setNomeEquipe(equipe.getNomeEquipe());
        response.setTagGuilda(equipe.getTagGuilda());
        response.setDataCriacao(equipe.getDataCriacao());
        response.setStatus(equipe.getStatus());
        response.setNumeroMembros(membrosCount);

        // Verificação de segurança para o Capitão
        if (equipe.getCapitao() != null) {
            response.setCapitaoNickname(equipe.getCapitao().getNickname());
        } else {
            response.setCapitaoNickname(null);
        }

        return response;
    }
}