package com.fiap.pj.core.pecainsumo.domain;

import com.fiap.pj.infra.sk.jpa.BaseCrudRepository;

import java.util.UUID;

public interface PecaInsumoDomainRepository extends BaseCrudRepository<PecaInsumo, UUID> {

    @Override
    PecaInsumo findByIdOrThrowNotFound(UUID id);

    PecaInsumo findByIdOrThrowNotFoundWithLocky(UUID id);
}