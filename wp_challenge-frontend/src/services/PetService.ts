import axios from "axios";
import Pet from "../models/Pet";

export default class PetService {
    static async getPets(): Promise<Pet[]> {
        let result: Pet[] = [];
        await axios.get("http://localhost:8080/pets")
            .then(response => {
                console.info("response: ", response)
                result = response.data;
            }).catch(error => console.log(error))
        return result;
    }

    static async createPet(pet: Pet) {
        await axios.post("http://localhost:8080/pets", pet)
            .then(response => {
                console.info(response)
            }).catch(error => console.log(error))
    }

    static async updatePetStatus(petId: number, newStatus: boolean) {
        await axios.patch(`http://localhost:8080/pets?petId=${petId}&newStatus=${newStatus}`)
            .then(response => {
                console.info(response)
            }).catch(error => console.log(error))
    }

}