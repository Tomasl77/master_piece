package fr.formation.masterpiece.domain.dtos.views;

import org.springframework.beans.factory.annotation.Value;

public interface SubjectViewDto {

    Long getId();

    String getTitle();

    String getDescription();

    String getCategory();

    @Value("#{target.member}")
    MemberInfoViewDto getMember();
}
