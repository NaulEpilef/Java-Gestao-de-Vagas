package dev.naul.gestao_vagas.modules.candidates.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyJobRepository extends JpaRepository<ApplyJobRepository, UUID> {

}
