package br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.api.v1.resources;

import br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.model.entities.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionDTO {


    private Integer id;
    private String description;
    private LocalDate data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CategoryDTO category;
}
