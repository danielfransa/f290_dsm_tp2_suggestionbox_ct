package br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.api.v1.resources;

import br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.model.entities.Suggestion;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {


    private Integer id;
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SuggestionDTO> suggestions = new ArrayList<>();
}
