import {useState} from "react";
import PetService from "../services/PetService";

export default function Home() {
    const [name, setPetName] = useState<string>("");
    const [category, setCategory] = useState<string>("");
    const [description, setDescription] = useState<string>("");
    const [imageUrl, setImageUrl] = useState<string>("");
    const [bornDate, setBornDate] = useState<string>("");
    const [isAvailable, setIsAvailable] = useState<string>("");

    return (<>
        <form>
            <input onChange={event => setPetName(event.target.value)} value={name} required
                   placeholder={"Nome do pet"} type="text"/>
            <input onChange={event => setCategory(event.target.value)} value={category} placeholder={"Categoria"}
                   type="text"/>
            <textarea onChange={event => setDescription(event.target.value)} value={description}
                      placeholder={"Descrição"}/>
            <input onChange={event => setImageUrl(event.target.value)} value={imageUrl} placeholder={"Url da imagem"}
                   type="text"/>
            <input onChange={event => setBornDate(event.target.value)} value={bornDate} required
                   placeholder={"Data de nascimento"} type="date"/>
            <label>O animal ja está disponível para adoção?</label>
            <div>
                <span>Sim</span><input onChange={event => setIsAvailable(event.target.value)} required value={"true"}
                                       name={"availability"} type="radio"/>
                <span>Não</span><input required value={"false"} name={"availability"} type="radio"/>
            </div>
            <button onClick={() => createPets()} type={"button"}>Enviar</button>
        </form>
    </>)

    async function createPets() {
        PetService.createPet({
            bornDate,
            isAvailable: Boolean(isAvailable),
            imageUrl,
            description,
            category,
            name
        })
            .then(() => clearFields())
            .catch((e) => console.error(e))

    }

    function clearFields() {
        setBornDate("")
        setIsAvailable("")
        setImageUrl("")
        setDescription("")
        setCategory("")
        setPetName("")
    }
}
