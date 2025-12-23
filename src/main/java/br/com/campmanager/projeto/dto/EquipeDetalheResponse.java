package br.com.campmanager.projeto.dto;

import br.com.campmanager.projeto.entity.Equipe;
import br.com.campmanager.projeto.entity.Usuario;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipeDetalheResponse {

    private Long idEquipe;
    private String nomeEquipe;
    private String tagGuilda;
    private String capitaoNickname;
    private String dataCriacao;
    private Set<MembroEquipeDTO> membros;


    public EquipeDetalheResponse() {
		this.idEquipe = idEquipe;
		this.nomeEquipe = nomeEquipe;
		this.tagGuilda = tagGuilda;
		this.capitaoNickname = capitaoNickname;
		this.dataCriacao = dataCriacao;
		this.membros = membros;
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

	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Set<MembroEquipeDTO> getMembros() {
		return membros;
	}

	public void setMembros(Set<MembroEquipeDTO> membros) {
		this.membros = membros;
	}



	// --- DTO Interno (Agora sem Builder para evitar erros) ---
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MembroEquipeDTO {
        private Long idUsuario;
        private String nickname;
        private String email;
        private String tipoUsuario;
        private boolean isCapitao;

        public MembroEquipeDTO() {
			this.idUsuario = idUsuario;
			this.nickname = nickname;
			this.email = email;
			this.tipoUsuario = tipoUsuario;
			this.isCapitao = isCapitao;
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




		public String getEmail() {
			return email;
		}




		public void setEmail(String email) {
			this.email = email;
		}




		public String getTipoUsuario() {
			return tipoUsuario;
		}




		public void setTipoUsuario(String tipoUsuario) {
			this.tipoUsuario = tipoUsuario;
		}




		public boolean isCapitao() {
			return isCapitao;
		}




		public void setCapitao(boolean isCapitao) {
			this.isCapitao = isCapitao;
		}




		public static MembroEquipeDTO fromEntity(Usuario usuario, Long capitaoId) {
            MembroEquipeDTO dto = new MembroEquipeDTO();
            dto.setIdUsuario(usuario.getIdUsuario());
            dto.setNickname(usuario.getNickname());
            dto.setEmail(usuario.getEmail());
            
            if (usuario.getTipoUsuario() != null) {
                dto.setTipoUsuario(usuario.getTipoUsuario().name());
            }

            // Verifica se capitaoId não é nulo antes de comparar
            if (capitaoId != null) {
                dto.setCapitao(usuario.getIdUsuario().equals(capitaoId));
            } else {
                dto.setCapitao(false);
            }
            
            return dto;
        }
    }

    /**
     * Construtor estático para mapear a Entidade Equipe para este DTO.
     */
    public static EquipeDetalheResponse fromEntity(Equipe equipe) {
        EquipeDetalheResponse response = new EquipeDetalheResponse();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        response.setIdEquipe(equipe.getIdEquipe());
        response.setNomeEquipe(equipe.getNomeEquipe());
        response.setTagGuilda(equipe.getTagGuilda());
        
        if (equipe.getDataCriacao() != null) {
            response.setDataCriacao(equipe.getDataCriacao().format(formatter));
        }

        // Tratamento seguro para o Capitão (evita NullPointerException)
        Long capitaoId = null;
        if (equipe.getCapitao() != null) {
            response.setCapitaoNickname(equipe.getCapitao().getNickname());
            capitaoId = equipe.getCapitao().getIdUsuario();
        }

        // Tratamento seguro para a Lista de Membros
        if (equipe.getMembros() != null) {
            Long finalCapitaoId = capitaoId; // Variável efetivamente final para usar no lambda
            Set<MembroEquipeDTO> membrosList = equipe.getMembros().stream()
                    .map(membro -> MembroEquipeDTO.fromEntity(membro, finalCapitaoId))
                    .collect(Collectors.toSet());
            response.setMembros(membrosList);
        } else {
            response.setMembros(new HashSet<>());
        }

        return response;
    }
}