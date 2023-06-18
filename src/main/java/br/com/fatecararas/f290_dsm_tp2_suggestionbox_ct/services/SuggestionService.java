package br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.services;

import br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.config.exceptions.ObjectNotFoundException;
import br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.model.entities.Suggestion;
import br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.repositories.SuggestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuggestionService {


    //TODO: Alterar o application-test.properties para não criar as entidades Suggestion e Suggestion

    private final SuggestionRepository repository;

    public SuggestionService(SuggestionRepository repository) {
        this.repository = repository;

    }

    public Suggestion salvar(Suggestion suggestion) {
        return repository.save(suggestion);
    }

    public Suggestion atualizar(Integer id, Suggestion pSuggestion) {

        Suggestion suggestion = buscarPorId(id);
        suggestion.setDescription(pSuggestion.getDescription());
        return repository.save(suggestion);

    }
    
    //TODO: Criar método que retorne uma sugestão com base num id.
    public Suggestion buscarPorId(Integer pId) {
        Optional<Suggestion> optional = repository.findById(pId);
        if (optional.isEmpty()) {
            throw new ObjectNotFoundException("Sugestão não localizada. ID: " + pId);
        }
        return optional.get();
    }
    
    //TODO: Criar método que retorne todas as sugestões do banco de dados.
    public List<Suggestion> buscarTodos() {
        return repository.findAll();
    }

    
    //TODO: Criar método que remova uma sugestão do banco de dados.
    public void remover(Integer id) throws Exception{
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ObjectNotFoundException("Sugestão não localizada. ID: " + id);
        }

    }
}
