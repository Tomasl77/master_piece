package fr.formation.masterpiece.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.masterpiece.config.security.SecurityHelper;
import fr.formation.masterpiece.domain.dtos.SubjectDto;
import fr.formation.masterpiece.domain.entities.Member;
import fr.formation.masterpiece.domain.entities.Subject;
import fr.formation.masterpiece.repositories.MemberRepository;
import fr.formation.masterpiece.repositories.SubjectRepository;
import fr.formation.masterpiece.services.SubjectManagerService;

@Service
public class SubjectManagerServiceImpl implements SubjectManagerService {

    private final SubjectRepository repository;

    private final MemberRepository memberRepository;

    @Autowired
    private ModelMapper mapper;

    public SubjectManagerServiceImpl(SubjectRepository repository,
            MemberRepository memberRepository) {
	this.repository = repository;
	this.memberRepository = memberRepository;
    }

    @Override
    public void create(SubjectDto dto) {
	Long userId = SecurityHelper.getUserId();
	Long memberId = memberRepository.getMemberIdByUserId(userId);
	Subject subject = new Subject();
	subject.setCategory(dto.getCategory());
	subject.setDescription(dto.getDescription());
	subject.setTitle(dto.getTitle());
	subject.setVote(0);
	Member member = memberRepository.getOne(memberId);
	subject.setMember(member);
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