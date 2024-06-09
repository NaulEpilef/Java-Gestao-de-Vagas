package dev.naul.gestao_vagas.modules.candidates.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.naul.gestao_vagas.modules.candidates.entity.ApplyJobEntity;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {

}
