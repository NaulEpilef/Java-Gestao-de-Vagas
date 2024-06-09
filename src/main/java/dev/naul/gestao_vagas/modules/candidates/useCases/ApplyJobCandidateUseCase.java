package dev.naul.gestao_vagas.modules.candidates.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.naul.gestao_vagas.exceptions.JobNotFoundException;
import dev.naul.gestao_vagas.exceptions.UserNotFoundException;
import dev.naul.gestao_vagas.modules.candidates.entity.ApplyJobEntity;
import dev.naul.gestao_vagas.modules.candidates.repositories.ApplyJobRepository;
import dev.naul.gestao_vagas.modules.candidates.repositories.CandidateRepository;
import dev.naul.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID candidateId, UUID jobId) {
        this.candidateRepository.findById(candidateId)
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                });

        this.jobRepository.findById(jobId)
                .orElseThrow(() -> {
                    throw new JobNotFoundException();
                });

        var applyJob = ApplyJobEntity.builder()
                .candidateId(candidateId)
                .jobId(jobId).build();

        applyJob = this.applyJobRepository.save(applyJob);

        return applyJob;
    }
}
