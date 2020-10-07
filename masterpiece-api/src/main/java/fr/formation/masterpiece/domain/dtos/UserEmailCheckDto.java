package fr.formation.masterpiece.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEmailCheckDto {

    private boolean isValid;

    public UserEmailCheckDto(boolean isValid) {
	this.isValid = isValid;
    }
}
