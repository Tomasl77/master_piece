package fr.formation.masterpiece.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsernameCheckDto {

    private boolean valid;

    public UsernameCheckDto(boolean valid) {
	this.valid = valid;
    }

    public UsernameCheckDto() {
	//
    }

    @Override
    public String toString() {
	return "{isValid: " + valid + "}";
    }
}
