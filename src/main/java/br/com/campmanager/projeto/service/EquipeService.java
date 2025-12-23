package br.com.campmanager.projeto.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.campmanager.projeto.dto.CriarEquipeRequest;
import br.com.campmanager.projeto.entity.Equipe;
import br.com.campmanager.projeto.entity.Usuario;
import br.com.campmanager.projeto.repositories.EquipeRepository;
import br.com.campmanager.projeto.repositories.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EquipeService {

    private final EquipeRepository equipeRepository;
    private final UsuarioRepository usuarioRepository;

    // Injeção de dependência
    public EquipeService(EquipeRepository equipeRepository, UsuarioRepository usuarioRepository) {
        this.equipeRepository = equipeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Cria uma nova equipe, validando unicidade e definindo o usuário como capitão.
     * 
     * @param request   O DTO com o nome e a tag da equipe.
     * @param capitaoId O ID do usuário autenticado que está criando a equipe.
     * @return A Entidade Equipe salva.
     * @throws Exception se o nome ou a tag já estiverem em uso.
     */
    @Transactional // Garante que as operações de BD sejam atômicas
    public Equipe criarNovaEquipe(CriarEquipeRequest request, Long capitaoId) throws Exception {

        // 1. Validar unicidade do Nome
        if (equipeRepository.findByNomeEquipe(request.getNomeEquipe()).isPresent()) {
            throw new Exception("Nome de equipe já está em uso.");
        }

        // 2. Validar unicidade da Tag
        if (equipeRepository.findByTagGuilda(request.getTagGuilda()).isPresent()) {
            throw new Exception("TAG de guilda já está em uso.");
        }

        // 3. Buscar o Capitão (Usuário)
        Usuario capitao = usuarioRepository.findById(capitaoId)
                .orElseThrow(() -> new Exception("Capitão (Usuário) não encontrado."));

        // 4. Mapear e Criar a Entidade Equipe
        Equipe novaEquipe = new Equipe();
        novaEquipe.setNomeEquipe(request.getNomeEquipe());
        novaEquipe.setTagGuilda(request.getTagGuilda());
        novaEquipe.setCapitao(capitao);
        novaEquipe.setDataCriacao(LocalDateTime.now());
        novaEquipe.setStatus("ATIVA");

        // 5. Salvar a Equipe
        Equipe equipeSalva = equipeRepository.save(novaEquipe);

        // 6. Atualizar o Usuário (Capitão) com a nova Equipe
        capitao.setEquipe(equipeSalva);
        usuarioRepository.save(capitao); // Persiste a mudança no Usuário

        return equipeSalva;
    }

    public void enviarConvite(Long capitaoId, String nicknameConvidado) throws Exception {
        // 1. Validar se quem está convidando é realmente o Capitão
        Usuario capitao = usuarioRepository.findById(capitaoId)
                .orElseThrow(() -> new Exception("Capitão não encontrado."));
        
        if (capitao.getEquipe() == null) {
            throw new Exception("Você não possui uma equipe para convidar alguém.");
        }

        if (!capitao.getEquipe().getCapitao().getIdUsuario().equals(capitaoId)) {
            throw new Exception("Apenas o capitão pode enviar convites.");
        }

        // 2. Buscar o usuário convidado
        Usuario convidado = usuarioRepository.findByNickname(nicknameConvidado)
                .orElseThrow(() -> new Exception("Jogador com este nickname não encontrado."));

        // 3. Validações de Regra de Negócio
        if (convidado.getEquipe() != null) {
            throw new Exception("Este jogador já está em uma equipe.");
        }

        // --- PONTO DE DECISÃO ---
        // Aqui você tem duas opções:
        // A) Criar um registro na tabela 'Convite' (Recomendado para histórico)
        // B) Adicionar direto (Não recomendado, pois o jogador não aceitou ainda)
        
        // Exemplo da lógica A (imaginando que você criará um ConviteRepository):
        /*
        Convite novoConvite = new Convite();
        novoConvite.setEquipe(capitao.getEquipe());
        novoConvite.setConvidado(convidado);
        novoConvite.setStatus("PENDENTE");
        conviteRepository.save(novoConvite);
        */
        
        System.out.println("Convite enviado para " + convidado.getEmail()); 
    }

	public Optional<Equipe> buscarPerfilEquipePorUsuarioId(Long usuarioId) {
	    // Busca o usuário, se existir, mapeia para pegar a equipe dele
	    return usuarioRepository.findById(usuarioId)
	            .map(Usuario::getEquipe);
	}

	@Transactional
	public Equipe aceitarConvite(Long usuarioId) throws Exception {
	    // 1. Buscar o usuário
	    Usuario usuario = usuarioRepository.findById(usuarioId)
	            .orElseThrow(() -> new Exception("Usuário não encontrado."));

	    // 2. Buscar se existe convite pendente para ele
	    // (Isso requereria um ConviteRepository)
	    /*
	    Convite convite = conviteRepository.buscarConvitePendentePorUsuario(usuarioId)
	            .orElseThrow(() -> new Exception("Você não tem convites pendentes."));
	    
	    Equipe equipe = convite.getEquipe();
	    */

	    // 3. Adicionar o usuário na equipe
	    // usuario.setEquipe(equipe);
	    // usuarioRepository.save(usuario);

	    // 4. Baixar o convite (Mudar status para ACEITO ou deletar)
	    // convite.setStatus("ACEITO");
	    // conviteRepository.save(convite);

	    // return equipe;
	    return null; // Placeholder até criar a entidade Convite
	}
}