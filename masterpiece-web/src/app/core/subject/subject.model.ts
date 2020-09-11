export class Subject{
    id : number;
    title : string;
    description : string;
    category : string;
    memberId : number;
    username : string;

    constructor(id: number, title : string, description : string, category : string, memberId : number, memberUserUsername : string){
        this.id = id,
        this.title = title,
        this.description = description,
        this.category = category,
        this.memberId = memberId,
        this.username = memberUserUsername
    }
}