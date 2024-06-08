package dev.naul.gestao_vagas.modules.company.controllers;

import org.springframework.web.bind.annotation.RestController;

import dev.naul.gestao_vagas.modules.company.dto.CreateJobDTO;
import dev.naul.gestao_vagas.modules.company.entities.JobEntity;
import dev.naul.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/company/jobs")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    public JobEntity create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");

        var jobEntity = JobEntity.builder()
                .companyId(UUID.fromString(companyId.toString()))
                .description(createJobDTO.getDescription())
                .benefits(createJobDTO.getBenefits())
                .level(createJobDTO.getLevel())
                .build();

        return this.createJobUseCase.execute(jobEntity);
    }

}
