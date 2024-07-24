import {useEffect, useState} from "react";
import PetService from "../services/PetService";
import Pet from "../models/Pet";

export default function Home() {
    const [petList, setPetList] = useState<Pet[]>([]);

    useEffect(() => {
        fetchPets()
    }, []);




    return (<>
        <div className={"pet-card-grid"}>
            {petList.map(pet => (
                <div className={"petCard"}>
                    <p><strong>Nome:</strong> {pet.name}</p>
                    <span><strong>Categoria:</strong> {pet.category}</span>
                    <p><strong>Descrição:</strong> {pet.description}</p>
                    <p><strong>Data de nascimento:</strong> {pet.bornDate} ({pet.age})</p>
                    <div><strong>Disponível? </strong><input checked={pet.isAvailable} onChange={event => updatePetStatus(pet.id!, event.target.checked)}
                                                         type={"checkbox"}/></div>
                    <img src={pet.imageUrl} alt={"pet photo"}></img>
                </div>
            ))
            }
        </div>
    </>)

    async function fetchPets() {
        var result = await PetService.getPets();
        result.forEach(pet => {
            // petList.push(pet);
            setPetList(result)
        })
    };

    async function updatePetStatus(petId: number, newStatus: boolean) {
        console.log("novo status:", newStatus);
        await PetService.updatePetStatus(petId, newStatus);
        fetchPets()
    };
}
