package fr.formation.masterpiece.api.services;

import java.util.List;

import fr.formation.masterpiece.domain.dtos.SubjectCreateDto;
import fr.formation.masterpiece.domain.dtos.SubjectDto;
import fr.formation.masterpiece.domain.dtos.views.SubjectViewDto;

public interface SubjectService {

    SubjectDto create(SubjectCreateDto dto);

    void deleteOne(Long id);

    List<SubjectViewDto> getAllNotScheduled();
}
