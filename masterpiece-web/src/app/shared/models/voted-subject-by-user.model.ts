export class VotedSubjectByUser {
    id: number;
    title: string;
    description: string;
    category: string;
    requesterName: string;
    requestDate: Date;

    constructor(
        id: number, 
        title: string,
        desription: string,
        category: string, 
        requesterName: string, 
        requesterDate: Date
    ) {
        this.id = id;
        this.title = title;
        this.description = desription;
        this.category = category;
        this.requesterName = requesterName;
        this.requestDate = requesterDate;
    }
    
}