package fr.formation.masterpiece.domain.dtos;

import lombok.Getter;

@Getter
public class UserEmailCheckDto {

    private boolean isValid;

    public UserEmailCheckDto(boolean isValid) {
	this.isValid = isValid;
    }

    /**
     * Empty no-args constructor
     */
    protected UserEmailCheckDto() {
    }

    public void setValid(boolean isValid) {
	this.isValid = isValid;
    }

    @Override
    public String toString() {
	return "{isValid: " + isValid + "}";
    }
}
