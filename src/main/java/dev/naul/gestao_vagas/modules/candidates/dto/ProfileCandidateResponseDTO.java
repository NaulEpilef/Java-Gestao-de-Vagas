package dev.naul.gestao_vagas.modules.candidates.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {

    @Schema(example = "Clara da Silva")
    private String name;

    @Schema(example = "clara")
    private String username;

    @Schema(example = "clara@gmail.com")
    private String email;

    @Schema(example = "Desenvolvedora Java")
    private String description;
    private String curriculum;
}
