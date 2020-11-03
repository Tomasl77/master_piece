package fr.formation.masterpiece.services;

import java.util.List;

import fr.formation.masterpiece.domain.dtos.SharingSessionCreateDto;
import fr.formation.masterpiece.domain.dtos.views.SharingSessionViewDto;

public interface SharingSessionService {

    void create(SharingSessionCreateDto dto);

    List<SharingSessionViewDto> getAllSessions();
}
