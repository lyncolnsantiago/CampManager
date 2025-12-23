package br.com.campmanager.projeto.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "jogos")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJogo;

    private String nome;
    private String desenvolvedora;
    private String modoPadrao;
    private String timeCasa;
    private String timeVisitante;
    private String placarCasa;
    private String placarVisitante;
    
	public Jogo() {
		
	}

	public Jogo(Long idJogo, String nome, String desenvolvedora, String modoPadrao, String timeCasa,
			String timeVisitante, String placarCasa, String placarVisitante) {
		super();
		this.idJogo = idJogo;
		this.nome = nome;
		this.desenvolvedora = desenvolvedora;
		this.modoPadrao = modoPadrao;
		this.timeCasa = timeCasa;
		this.timeVisitante = timeVisitante;
		this.placarCasa = placarCasa;
		this.placarVisitante = placarVisitante;
	}

	public String getTimeCasa() {
		return timeCasa;
	}

	public void setTimeCasa(String timeCasa) {
		this.timeCasa = timeCasa;
	}

	public String getTimeVisitante() {
		return timeVisitante;
	}

	public void setTimeVisitante(String timeVisitante) {
		this.timeVisitante = timeVisitante;
	}

	public String getPlacarCasa() {
		return placarCasa;
	}

	public void setPlacarCasa(String placarCasa) {
		this.placarCasa = placarCasa;
	}

	public String getPlacarVisitante() {
		return placarVisitante;
	}

	public void setPlacarVisitante(String placarVisitante) {
		this.placarVisitante = placarVisitante;
	}

	public Long getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(Long idJogo) {
		this.idJogo = idJogo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDesenvolvedora() {
		return desenvolvedora;
	}

	public void setDesenvolvedora(String desenvolvedora) {
		this.desenvolvedora = desenvolvedora;
	}

	public String getModoPadrao() {
		return modoPadrao;
	}

	public void setModoPadrao(String modoPadrao) {
		this.modoPadrao = modoPadrao;
	}

	
	
}
