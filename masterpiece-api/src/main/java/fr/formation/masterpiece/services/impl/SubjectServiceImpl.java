package fr.formation.masterpiece.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.formation.masterpiece.config.AbstractService;
import fr.formation.masterpiece.config.security.SecurityHelper;
import fr.formation.masterpiece.domain.dtos.SubjectCreateDto;
import fr.formation.masterpiece.domain.dtos.SubjectDto;
import fr.formation.masterpiece.domain.dtos.views.SubjectViewDto;
import fr.formation.masterpiece.domain.entities.CustomUser;
import fr.formation.masterpiece.domain.entities.Subject;
import fr.formation.masterpiece.repositories.SubjectRepository;
import fr.formation.masterpiece.repositories.UserJpaRepository;
import fr.formation.masterpiece.services.SubjectService;

@Service
public class SubjectServiceImpl extends AbstractService
        implements SubjectService {

    private final SubjectRepository subjectRepository;

    private final UserJpaRepository userRepository;

    public SubjectServiceImpl(SubjectRepository repository,
            UserJpaRepository userRepository) {
	this.subjectRepository = repository;
	this.userRepository = userRepository;
    }

    @Override
    public SubjectDto create(SubjectCreateDto subjectDto) {
	Long userId = SecurityHelper.getUserId();
	CustomUser user = userRepository.getOne(userId);
	Subject subject = convert(subjectDto, Subject.class);
	subject.setUser(user);
	Subject subjectToSave = subjectRepository.save(subject);
	return modelMapper.map(subjectToSave, SubjectDto.class);
    }

    @Override
    public void deleteOne(Long id) {
	subjectRepository.deleteById(id);
    }

    @Override
    public List<SubjectViewDto> getAll() {
	return subjectRepository.getAllProjectedBy();
    }
}