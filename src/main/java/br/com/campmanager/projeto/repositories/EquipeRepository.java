package br.com.campmanager.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.campmanager.projeto.entity.Equipe;

import java.util.Optional;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {
    // ERRADO (causa o erro atual):
    // Optional<Equipe> findByTag(String tag);

    // CERTO (combina com o atributo private String tagGuilda;):
    Optional<Equipe> findByTagGuilda(String tagGuilda);
    
    // O outro método parece estar certo se o atributo for 'nomeEquipe':
    Optional<Equipe> findByNomeEquipe(String nomeEquipe); 
}