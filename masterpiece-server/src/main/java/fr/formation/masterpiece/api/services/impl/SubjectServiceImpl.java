package fr.formation.masterpiece.api.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.formation.masterpiece.api.repositories.CustomUserRepository;
import fr.formation.masterpiece.api.repositories.SubjectRepository;
import fr.formation.masterpiece.api.services.SubjectService;
import fr.formation.masterpiece.commons.config.AbstractService;
import fr.formation.masterpiece.commons.exceptions.ResourceNotFoundException;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectCreateDto;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectDto;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectViewDto;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectViewDtoWithVote;
import fr.formation.masterpiece.domain.entities.CustomUser;
import fr.formation.masterpiece.domain.entities.Subject;
import fr.formation.masterpiece.security.SecurityHelper;

/**
 * Default concrete implementation of {@link SubjectService}
 *
 * @author Tomas LOBGEOIS
 *
 */
@Service
public class SubjectServiceImpl extends AbstractService
        implements SubjectService {

    private final SubjectRepository subjectRepository;

    private final CustomUserRepository userRepository;

    public SubjectServiceImpl(SubjectRepository repository,
            CustomUserRepository userRepository) {
	this.subjectRepository = repository;
	this.userRepository = userRepository;
    }

    @Override
    public SubjectDto create(SubjectCreateDto subjectDto) {
	Long userCredentialsId = SecurityHelper.getUserId();
	CustomUser user = userRepository.findById(userCredentialsId)
	        .orElseThrow(() -> new ResourceNotFoundException(
	                "Account not found"));
	Subject subject = convert(subjectDto, Subject.class);
	subject.setUser(user);
	Subject subjectToSave = subjectRepository.save(subject);
	return convert(subjectToSave, SubjectDto.class);
    }

    @Override
    public void deleteOne(Long id) {
	Subject deleted = subjectRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException(
	                "Resource not found : " + id));
	subjectRepository.delete(deleted);
    }

    @Override
    public List<SubjectViewDto> getAllNotScheduled() {
	List<Subject> subjects = subjectRepository
	        .findAllByScheduleAndRequesterEnabledTrue(false);
	List<SubjectViewDto> test = convertList(subjects, SubjectViewDto.class);
	return test;
    }

    @Override
    public List<SubjectViewDtoWithVote> getAllNotScheduledWithVote() {
	Long userId = SecurityHelper.getUserId();
	List<SubjectViewDtoWithVote> subjectWithVote = subjectRepository
	        .findAllWithVotes();
	subjectWithVote.forEach(subject -> subject.setHasVoted(
	        subjectRepository.findIfUserAsVote(userId, subject.getId())));
	return subjectWithVote;
    }
}