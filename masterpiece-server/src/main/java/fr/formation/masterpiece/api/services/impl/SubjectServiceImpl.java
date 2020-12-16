package fr.formation.masterpiece.api.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.masterpiece.api.repositories.CategoryRepository;
import fr.formation.masterpiece.api.repositories.CustomUserRepository;
import fr.formation.masterpiece.api.repositories.SubjectRepository;
import fr.formation.masterpiece.api.services.SubjectService;
import fr.formation.masterpiece.commons.config.AbstractService;
import fr.formation.masterpiece.commons.exceptions.ResourceNotFoundException;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectCreateDto;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectDto;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectViewDtoWithVote;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectVoteUpdateDto;
import fr.formation.masterpiece.domain.dtos.subjects.VoteSubjectDto;
import fr.formation.masterpiece.domain.entities.Category;
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

    private final CategoryRepository categoryRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository,
            CustomUserRepository userRepository,
            CategoryRepository categoryRepository) {
	this.subjectRepository = subjectRepository;
	this.userRepository = userRepository;
	this.categoryRepository = categoryRepository;
    }

    @Override
    @Modifying
    @Transactional
    public SubjectDto create(SubjectCreateDto subjectDto) {
	Long userCredentialsId = SecurityHelper.getUserId();
	CustomUser user = userRepository.findById(userCredentialsId)
	        .orElseThrow(() -> new ResourceNotFoundException(
	                "Account doesn't exists"));
	Subject subject = convert(subjectDto, Subject.class);
	subject.setRequestDate(LocalDateTime.now());
	subject.setUser(user);
	Category category = categoryRepository
	        .findById(subjectDto.getCategoryId())
	        .orElseThrow(() -> new ResourceNotFoundException(
	                "Category doesn't exists"));
	subject.setCategory(category);
	Subject subjectToSave = subjectRepository.save(subject);
	return convert(subjectToSave, SubjectDto.class);
    }

    @Override
    @Transactional
    public void deleteOne(Long id) {
	subjectRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubjectViewDtoWithVote> getAllNotScheduledWithVote() {
	Long userId = SecurityHelper.getUserId();
	List<SubjectViewDtoWithVote> subjectsWithVotes = subjectRepository
	        .findAllWithVotes();
	List<VoteSubjectDto> votes = subjectRepository.findVoteByUserId(userId);
	subjectsWithVotes.forEach(subject -> subject
	        .setHasVoted(hasUserVotedForSubject(subject.getId(), votes)));
	return subjectsWithVotes;
    }

    @Override
    @Transactional
    public SubjectViewDtoWithVote changeVote(SubjectVoteUpdateDto voteDto,
            Long subjectId) {
	Long userId = SecurityHelper.getUserId();
	CustomUser user = userRepository.getOne(userId);
	Subject subject = subjectRepository.getOne(subjectId);
	boolean hasVoted = voteDto.isHasVoted();
	if (!hasVoted) {
	    subject.addVote(user);
	} else {
	    subject.remove(user);
	}
	List<VoteSubjectDto> votes = subjectRepository.findVoteByUserId(userId);
	SubjectViewDtoWithVote subjectToReturn = subjectRepository
	        .findSubjectWithVote(subjectId);
	subjectToReturn.setHasVoted(hasUserVotedForSubject(subjectId, votes));
	return subjectToReturn;
    }

    /**
     * Check if a {@code CustomUser} has vote for a specific {@code Subject}
     *
     * @param subjectId the {@code Subject} to check
     * @param votes     the {@code List} of votes that the {@code CustomUser}
     *                  has already voted
     * @return {@code true} if the {@code CustomUser} has already voted to the
     *         subject, {@code false} otherwise
     */
    private boolean hasUserVotedForSubject(Long subjectId,
            List<VoteSubjectDto> votes) {
	return votes.stream().filter(vote -> vote.getId().equals(subjectId))
	        .findFirst().isPresent();
    }
}