package fr.formation.masterpiece.domain.dtos;

import lombok.Getter;

@Getter
public class UsernameCheckDto {

    private boolean valid;

    public UsernameCheckDto(boolean valid) {
	this.valid = valid;
    }

    /**
     * Empty no-args constructor
     */
    protected UsernameCheckDto() {
	//
    }

    @Override
    public String toString() {
	return "{isValid: " + valid + "}";
    }

    public void setValid(boolean valid) {
	this.valid = valid;
    }
}
