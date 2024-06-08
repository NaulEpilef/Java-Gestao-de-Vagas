package dev.naul.gestao_vagas.modules.candidates.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.naul.gestao_vagas.modules.candidates.CandidateRepository;
import dev.naul.gestao_vagas.modules.candidates.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID candidateId) {
        var candidate = this.candidateRepository.findById(candidateId)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found");
                });

        var profileCandidateResponseDTO = ProfileCandidateResponseDTO.builder()
                .email(candidate.getEmail())
                .name(candidate.getName())
                .username(candidate.getUsername())
                .curriculum(candidate.getCurriculum())
                .description(candidate.getDescription())
                .build();

        return profileCandidateResponseDTO;
    }
}
