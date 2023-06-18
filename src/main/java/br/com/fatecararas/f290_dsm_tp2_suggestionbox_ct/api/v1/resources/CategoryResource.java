package br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.api.v1.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.model.entities.Suggestion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.model.entities.Category;
import br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryResource {

    private final CategoryService service;

    public CategoryResource(CategoryService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<Category> save(@RequestBody Category category) {
        category.setId(null);
        Category cat = service.salvar(category);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(cat.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public CategoryDTO update(@PathVariable("id") Integer pId, @RequestBody Category category) {
        return convertToDTO(service.atualizar(pId, category));
    }

    @GetMapping("/{id}")
    public CategoryDTO findById(@PathVariable("id") Integer pId) {
        Category category = service.buscarPorId(pId);
       return convertToDTO(category);
    }

    @GetMapping("/all")
    public List<CategoryDTO> getAll() {
        return service.buscarTodos().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private CategoryDTO convertToDTO(Category category){
        CategoryDTO dto = new CategoryDTO(category.getId(), category.getDescription(), new ArrayList<>());
        for (Suggestion suggestion: category.getSuggestions()) {
            SuggestionDTO suggestionDTO = new SuggestionDTO(suggestion.getId(), suggestion.getDescription(), suggestion.getData(), null);
            dto.getSuggestions().add(suggestionDTO);
        }
        return dto;
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer pId) throws Exception {
        service.remover(pId);
    }
}
