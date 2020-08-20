package fr.formation.masterpiece.services;

import fr.formation.masterpiece.domain.dtos.SubjectDto;

public interface SubjectManagerService {

    void create(SubjectDto dto);

    void delete(Long id);
}
