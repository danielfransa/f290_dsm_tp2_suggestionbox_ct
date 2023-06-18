package br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.api.v1.resources;


import br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.model.entities.Category;
import br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.model.entities.Suggestion;
import br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.services.SuggestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

//TODO: Configurar RestController para o recurso /suggestion
@RestController
@RequestMapping("/suggestion")
public class SuggestionResource {

    private final SuggestionService service;

    public SuggestionResource(SuggestionService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<Suggestion> save(@RequestBody Suggestion suggestion) {
        suggestion.setId(null);
        Suggestion sug = service.salvar(suggestion);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(sug.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public SuggestionDTO update(@PathVariable("id") Integer pId, @RequestBody Suggestion suggestion) {
        return convertToDTO(service.atualizar(pId, suggestion));
    }

    @GetMapping("/{id}")
    public SuggestionDTO findById(@PathVariable("id") Integer pId) {
        return convertToDTO(service.buscarPorId(pId));
    }

    @GetMapping("/all")
    public List<SuggestionDTO> getAll() {
        return service.buscarTodos().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private SuggestionDTO convertToDTO(Suggestion suggestion){
        Category category = suggestion.getCategory();
        CategoryDTO categoryDTO = new CategoryDTO(category.getId(), category.getDescription(), null);
        SuggestionDTO dto = new SuggestionDTO(suggestion.getId(), suggestion.getDescription(), suggestion.getData(), categoryDTO);
        return dto;
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer pId) throws Exception {
        service.remover(pId);
    }
}
