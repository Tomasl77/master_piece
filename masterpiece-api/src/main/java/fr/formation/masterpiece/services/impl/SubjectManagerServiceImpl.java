package fr.formation.masterpiece.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.masterpiece.config.security.SecurityHelper;
import fr.formation.masterpiece.domain.dtos.SubjectDto;
import fr.formation.masterpiece.domain.entities.UserAuth;
import fr.formation.masterpiece.domain.entities.Subject;
import fr.formation.masterpiece.repositories.UserJpaRepository;
import fr.formation.masterpiece.repositories.SubjectRepository;
import fr.formation.masterpiece.services.SubjectManagerService;

@Service
public class SubjectManagerServiceImpl implements SubjectManagerService {

    private final SubjectRepository repository;

    private final UserJpaRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    public SubjectManagerServiceImpl(SubjectRepository repository,
            UserJpaRepository userRepository) {
	this.repository = repository;
	this.userRepository = userRepository;
    }

    @Override
    public void create(SubjectDto dto) {
	Long id = SecurityHelper.getUserId();
	Subject subject = new Subject();
	subject.setCategory(dto.getCategory());
	subject.setDescription(dto.getDescription());
	subject.setTitle(dto.getTitle());
	subject.setVote(0);
	UserAuth user = userRepository.getOne(id);
	subject.setUser(user);
	repository.save(subject);
    }

    public void create2(SubjectDto dto) {
	mapper.map(dto, Subject.class);
    }

    @Override
    public void delete(Long id) {
	repository.deleteById(id);
    }
}