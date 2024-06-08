package dev.naul.gestao_vagas.modules.candidates.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthCandidateResponseDTO {

    private String access_token;
    private Long expires_in;
}
