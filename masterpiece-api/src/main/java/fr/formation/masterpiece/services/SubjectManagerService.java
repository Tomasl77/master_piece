package fr.formation.masterpiece.services;

import java.util.List;

import fr.formation.masterpiece.domain.dtos.SubjectDto;
import fr.formation.masterpiece.domain.dtos.views.SubjectViewDto;

public interface SubjectManagerService {

    void create(SubjectDto dto);

    void delete(Long id);

    List<SubjectViewDto> getAll();
}
