import { Endereco } from "../endereco/endereco";


export class Cliente{
    id:number;
    cpf:string;
    telefone:string;
    endereco:Endereco = {
            "id": "",
            "cidade":"",
            "bairro":"",
            "unidadeFederativa":""
    }

}
