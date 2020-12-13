package fr.formation.masterpiece.domain.dtos.subjects;

import lombok.Getter;

@Getter
public class VoteSubjectDto {

    private Long id;

    public VoteSubjectDto(Long id) {
	this.id = id;
    }

    public VoteSubjectDto() {
    }
}
