package fr.formation.masterpiece.domain.dtos.subjects;

public class SubjectViewDtoWithRequester {

    private String title;

    private String requester;

    protected SubjectViewDtoWithRequester() {
    }

    public SubjectViewDtoWithRequester(String title, String requester) {
	this.title = title;
	this.requester = requester;
    }

    public String getTitle() {
	return title;
    }

    public String getRequester() {
	return requester;
    }
}
