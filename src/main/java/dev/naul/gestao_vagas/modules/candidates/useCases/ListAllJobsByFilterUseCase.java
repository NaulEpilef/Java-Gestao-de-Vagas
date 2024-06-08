package dev.naul.gestao_vagas.modules.candidates.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.naul.gestao_vagas.modules.company.entities.JobEntity;
import dev.naul.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ListAllJobsByFilterUseCase {

    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(String filter) {
        return this.jobRepository.findByDescriptionContainingIgnoreCase(filter);
    }
}
