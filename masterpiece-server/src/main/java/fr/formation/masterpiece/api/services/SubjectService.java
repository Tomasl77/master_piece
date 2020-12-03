package fr.formation.masterpiece.api.services;

import java.util.List;

import fr.formation.masterpiece.domain.dtos.subjects.SubjectCreateDto;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectDto;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectViewDto;

/**
 * Service to handle {@code Subject} logic.
 *
 * @author Tomas LOBGEOIS
 */
public interface SubjectService {

    SubjectDto create(SubjectCreateDto dto);

    void deleteOne(Long id);

    List<SubjectViewDto> getAllNotScheduled();
}
