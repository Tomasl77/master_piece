package fr.formation.masterpiece.domain.dtos.views;

import org.springframework.beans.factory.annotation.Value;

public interface SubjectViewDto {

    Long getId();

    String getTitle();

    String getDescription();

    String getCategory();

    Long getVote();

    @Value("#{target.member}")
    MemberInfoViewDto getMember();
}
