package fr.formation.masterpiece.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.formation.masterpiece.config.AbstractService;
import fr.formation.masterpiece.config.security.SecurityHelper;
import fr.formation.masterpiece.domain.dtos.SubjectCreateDto;
import fr.formation.masterpiece.domain.dtos.SubjectDto;
import fr.formation.masterpiece.domain.dtos.views.SubjectViewDto;
import fr.formation.masterpiece.domain.entities.Subject;
import fr.formation.masterpiece.domain.entities.UserProfile;
import fr.formation.masterpiece.exceptions.AccountNotFoundException;
import fr.formation.masterpiece.repositories.SubjectRepository;
import fr.formation.masterpiece.repositories.UserProfileRepository;
import fr.formation.masterpiece.services.SubjectService;

@Service
public class SubjectServiceImpl extends AbstractService
        implements SubjectService {

    private final SubjectRepository subjectRepository;

    private final UserProfileRepository userProfileRepository;

    public SubjectServiceImpl(SubjectRepository repository,
            UserProfileRepository userProfileRepository) {
	this.subjectRepository = repository;
	this.userProfileRepository = userProfileRepository;
    }

    @Override
    public SubjectDto create(SubjectCreateDto subjectDto) {
	Long userCredentialsId = SecurityHelper.getUserId();
	Long userId = userProfileRepository
	        .getUserProfileIdByUserId(userCredentialsId);
	UserProfile user = userProfileRepository.getById(userId).orElseThrow(
	        () -> new AccountNotFoundException("Account not found"));
	Subject subject = convert(subjectDto, Subject.class);
	subject.setUser(user);
	Subject subjectToSave = subjectRepository.save(subject);
	return modelMapper.map(subjectToSave, SubjectDto.class);
    }

    @Override
    public void deleteOne(Long id) {
	Subject deleted = convert(subjectRepository.getOne(id), Subject.class);
	subjectRepository.delete(deleted);
    }

    @Override
    public List<SubjectViewDto> getAll() {
	List<Subject> subjects = subjectRepository.getAllProjectedBy();
	return convertList(subjects, SubjectViewDto.class);
    }
}