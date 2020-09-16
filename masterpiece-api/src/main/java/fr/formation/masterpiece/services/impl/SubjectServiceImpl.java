package fr.formation.masterpiece.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.formation.masterpiece.config.security.SecurityHelper;
import fr.formation.masterpiece.domain.dtos.SubjectDto;
import fr.formation.masterpiece.domain.dtos.views.SubjectViewDto;
import fr.formation.masterpiece.domain.entities.Member;
import fr.formation.masterpiece.domain.entities.Subject;
import fr.formation.masterpiece.repositories.MemberRepository;
import fr.formation.masterpiece.repositories.SubjectRepository;
import fr.formation.masterpiece.services.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    private final MemberRepository memberRepository;

    public SubjectServiceImpl(SubjectRepository repository,
            MemberRepository memberRepository) {
	this.subjectRepository = repository;
	this.memberRepository = memberRepository;
    }

    @Override
    public void create(SubjectDto dto) {
	Long userId = SecurityHelper.getUserId();
	Long memberId = memberRepository.getMemberIdByUserId(userId);
	Member member = memberRepository.getOne(memberId);
	Subject subject = new Subject();
	subject.setCategory(dto.getCategory());
	subject.setDescription(dto.getDescription());
	subject.setTitle(dto.getTitle());
	subject.setVote(0);
	subject.setMember(member);
	subjectRepository.save(subject);
    }

    @Override
    public void delete(Long id) {
	subjectRepository.deleteById(id);
    }

    @Override
    public List<SubjectViewDto> getAll() {
	return subjectRepository.getAllProjectedBy();
    }
}