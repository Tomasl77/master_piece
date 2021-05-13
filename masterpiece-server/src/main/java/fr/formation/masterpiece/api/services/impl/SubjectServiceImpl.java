package fr.formation.masterpiece.api.services.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.masterpiece.api.repositories.CategoryRepository;
import fr.formation.masterpiece.api.repositories.EntityUserRepository;
import fr.formation.masterpiece.api.repositories.SubjectRepository;
import fr.formation.masterpiece.api.services.SubjectService;
import fr.formation.masterpiece.commons.config.AbstractService;
import fr.formation.masterpiece.commons.exceptions.ResourceNotFoundException;
import fr.formation.masterpiece.commons.utils.EmailManager;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectCreateDto;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectViewDto;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectViewDtoWithRequester;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectViewDtoWithVote;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectVoteUpdateDto;
import fr.formation.masterpiece.domain.dtos.subjects.VotedSubjectByUserDto;
import fr.formation.masterpiece.domain.dtos.subjects.VotedSubjectIdDto;
import fr.formation.masterpiece.domain.entities.Category;
import fr.formation.masterpiece.domain.entities.EntityUser;
import fr.formation.masterpiece.domain.entities.Mail;
import fr.formation.masterpiece.domain.entities.Subject;
import fr.formation.masterpiece.security.SecurityHelper;
import javaslang.Tuple2;

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

    private final EntityUserRepository userRepository;

    private final CategoryRepository categoryRepository;

    private final EmailManager emailManager;

    public SubjectServiceImpl(SubjectRepository subjectRepository,
            EntityUserRepository userRepository,
            CategoryRepository categoryRepository, EmailManager emailManager) {
	this.subjectRepository = subjectRepository;
	this.userRepository = userRepository;
	this.categoryRepository = categoryRepository;
	this.emailManager = emailManager;
    }

    @Override
    @Transactional
    public SubjectViewDto create(SubjectCreateDto subjectDto) {
	Long userCredentialsId = SecurityHelper.getUserId();
	EntityUser user = userRepository.getOne(userCredentialsId);
	Subject subject = convert(subjectDto, Subject.class);
	subject.setRequestDate(LocalDateTime.now());
	subject.setUser(user);
	Category category = categoryRepository
	        .findById(subjectDto.getCategoryId())
	        .orElseThrow(() -> new ResourceNotFoundException(
	                "Category doesn't exists"));
	subject.setCategory(category);
	Subject subjectToSave = subjectRepository.save(subject);
	return convert(subjectToSave, SubjectViewDto.class);
    }

    @Override
    @Transactional
    public void deleteOne(Long id) throws MessagingException {
	SubjectViewDtoWithRequester subjectDto = subjectRepository
	        .findTitleAndRequesterBySubjectId(id);
	subjectRepository.deleteById(id);
	Tuple2<Map<String, Object>, String> mailToConstruct = buildArgsAndGetTemplate(
	        subjectDto.getTitle());
	String content = emailManager.buildMailContent(mailToConstruct._1,
	        mailToConstruct._2);
	Mail mail = emailManager.buildMail("Subject Deleted", content,
	        Collections.singletonList(subjectDto.getRequester()));
	emailManager.send(mail);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubjectViewDtoWithVote> getAllNotScheduledWithVote() {
	Long userId = SecurityHelper.getUserId();
	List<SubjectViewDtoWithVote> subjectsWithVotes = subjectRepository
	        .findAllWithVotes();
	List<VotedSubjectIdDto> votes = getVoteForSpecificUserId(userId);
	subjectsWithVotes.forEach(subject -> subject
	        .setHasVoted(hasUserVotedForSubject(subject.getId(), votes)));
	return subjectsWithVotes;
    }

    @Override
    @Transactional
    public SubjectViewDtoWithVote changeVote(SubjectVoteUpdateDto voteDto,
            Long subjectId) {
	Long userId = SecurityHelper.getUserId();
	EntityUser user = userRepository.getOne(userId);
	Subject subject = subjectRepository.getOne(subjectId);
	boolean hasVoted = voteDto.isHasVoted();
	if (!hasVoted) {
	    subject.addVote(user);
	} else {
	    subject.removeVote(user);
	}
	List<VotedSubjectIdDto> votes = getVoteForSpecificUserId(userId);
	SubjectViewDtoWithVote subjectToReturn = subjectRepository
	        .findSubjectWithVote(subjectId);
	subjectToReturn.setHasVoted(hasUserVotedForSubject(subjectId, votes));
	return subjectToReturn;
    }

    @Override
    @Transactional(readOnly = true)
    public List<VotedSubjectByUserDto> getAllVotedSubjectByUserId() {
	Long userId = SecurityHelper.getUserId();
	return subjectRepository.findVotedSubjectForSpecificUser(userId);
    }

    /**
     * Check if a {@code EntityUser} has vote for a specific {@code Subject}
     *
     * @param subjectId the {@code Subject} to check
     * @param votes     the {@code List} of votes that the {@code EntityUser}
     *                  has already voted
     * @return {@code true} if the {@code EntityUser} has already voted to the
     *         subject, {@code false} otherwise
     */
    private boolean hasUserVotedForSubject(Long subjectId,
            List<VotedSubjectIdDto> votes) {
	return votes.stream().filter(vote -> vote.getId().equals(subjectId))
	        .findFirst().isPresent();
    }

    private List<VotedSubjectIdDto> getVoteForSpecificUserId(Long userId) {
	return subjectRepository.findVoteByUserId(userId);
    }

    private Tuple2<Map<String, Object>, String> buildArgsAndGetTemplate(
            String subjectTitle) {
	String template = "DeleteSubjectMail";
	Map<String, Object> args = new HashMap<>();
	args.put("title", subjectTitle);
	return new Tuple2<>(args, template);
    }
}