package fr.formation.masterpiece.api.controllers;

import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.masterpiece.api.services.SubjectService;
import fr.formation.masterpiece.commons.annotations.HasRoleAdmin;
import fr.formation.masterpiece.commons.annotations.HasRoleUser;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectCreateDto;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectViewDto;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectViewDtoWithVote;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectVoteUpdateDto;

/**
 * A {@link RestController} to handle {@code Subject}
 *
 * @author Tomas LOBGEOIS
 *
 */
@RestController
@RequestMapping("/subjects")
@HasRoleUser
public class SubjectController {

    private final SubjectService service;

    public SubjectController(SubjectService service) {
	this.service = service;
    }

    /**
     * Persists a {@code Subject}
     *
     * @param dto the {@code SubjectCreateDto} containing the inputs
     * @return a {@code SubjectViewDto} encapsulating the title of subject
     */
    @PostMapping
    public SubjectViewDto postSubject(
            @Valid @RequestBody SubjectCreateDto dto) {
	return service.create(dto);
    }

    /**
     * Delete a {@code Subject} associated with the id
     *
     * @param id the id to delete
     * @throws MessagingException messaging exception if error with mail occured
     */
    @DeleteMapping("/{id}")
    @HasRoleAdmin
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteSubject(@PathVariable Long id) throws MessagingException {
	service.deleteOne(id);
    }

    /**
     * Change the vote of a specific {@code Subject}:
     * <ul>
     * <li>Upvote if user hasn't vote for the {@code Subject} yet</li>
     * <li>Withdraw vote if user has already vote for the {@code Subject}</li>
     * </ul>
     *
     * @param voteDto the dto containing the status of the vote by the user
     * @param id      the id of {@code Subject} to vote/withdraw for
     * @return a {@code SubjectViewDtoWithVote} containing the new vote status
     *         for the user
     */
    @PostMapping("/vote/{id}")
    public SubjectViewDtoWithVote changeVote(
            @Valid @RequestBody SubjectVoteUpdateDto voteDto,
            @PathVariable Long id) {
	return service.changeVote(voteDto, id);
    }

    /**
     * Retrieves a {@code List} of {@code SubjectViewDtoWithVote} corresponding
     * to the user's id.
     *
     * @return a {@code List} of {@code SubjectViewDtoWithVote}
     */
    @GetMapping
    public List<SubjectViewDtoWithVote> getAllNotScheduledWithVote() {
	return service.getAllNotScheduledWithVote();
    }
}