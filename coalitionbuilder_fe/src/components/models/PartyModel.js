export default class PartyModel {

    constructor(name, ideology, color, number) {
        this.id = "id" + Math.random().toString(16).slice(2);
        this.name = name;
        this.ideology = ideology;
        this.color = color;
        this.seats = number;
        this.inCoalition = false;
    }

    static createSampleParty() {
        const party = new PartyModel(
                this.makeid(10), 
                this.makeid(5), 
                "#" + Math.floor(Math.random()*16777215).toString(16),
                Math.floor(Math.random() * 30) + 1
            );
        return party;
    }

    static makeid(length) {
        let result = '';
        const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
        const charactersLength = characters.length;
        let counter = 0;
        while (counter < length) { 
          result += characters.charAt(Math.floor(Math.random() * charactersLength));
          counter += 1;
        }
        return result;
    }

}

