package fr.formation.masterpiece.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.masterpiece.config.security.SecurityHelper;
import fr.formation.masterpiece.domain.dtos.SubjectDto;
import fr.formation.masterpiece.domain.entities.Member;
import fr.formation.masterpiece.domain.entities.Subject;
import fr.formation.masterpiece.repositories.MemberJpaRepository;
import fr.formation.masterpiece.repositories.SubjectRepository;
import fr.formation.masterpiece.services.SubjectManagerService;

@Service
public class SubjectManagerServiceImpl implements SubjectManagerService {

    private final SubjectRepository repository;

    private final MemberJpaRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    public SubjectManagerServiceImpl(SubjectRepository repository,
            MemberJpaRepository userRepository) {
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
	Member user = userRepository.getOne(id);
	subject.setUser(user);
	repository.save(subject);
    }

    public void create2(SubjectDto dto) {
	mapper.map(dto, Subject.class);
    }
}