package fr.formation.masterpiece.services;

import java.util.List;

import fr.formation.masterpiece.domain.dtos.SubjectDto;
import fr.formation.masterpiece.domain.dtos.views.SubjectViewDto;

public interface SubjectService {

    void create(SubjectDto dto);

    void deleteOne(Long id);

    List<SubjectViewDto> getAll();
}
