package fr.formation.masterpiece.services.impl;

import org.springframework.stereotype.Service;

import fr.formation.masterpiece.domain.dtos.SubjectDto;
import fr.formation.masterpiece.domain.entities.CustomUser;
import fr.formation.masterpiece.domain.entities.Subject;
import fr.formation.masterpiece.exceptions.AccountNotFoundException;
import fr.formation.masterpiece.repositories.CustomUserJpaRepository;
import fr.formation.masterpiece.repositories.SubjectRepository;
import fr.formation.masterpiece.services.SubjectManagerService;

@Service
public class SubjectManagerServiceImpl implements SubjectManagerService {

    private final SubjectRepository repository;

    private final CustomUserJpaRepository userRepository;

    public SubjectManagerServiceImpl(SubjectRepository repository,
            CustomUserJpaRepository userRepository) {
	this.repository = repository;
	this.userRepository = userRepository;
    }

    @Override
    public void create(SubjectDto dto) {
	Subject subject = new Subject();
	subject.setCategory(dto.getCategory());
	subject.setDescription(dto.getDescription());
	subject.setTitle(dto.getTitle());
	CustomUser user = userRepository.findById(dto.getUserId())
	        .orElseThrow(() -> new AccountNotFoundException(
	                dto.getUserId() + " not found"));
	subject.setUser(user);
	repository.save(subject);
    }
}