import { Cliente } from "../cliente/cliente";
import { Tatuador } from "../tatuador/tatuador";

export class User {

  id: number;
  name:string;
  email:string;
  password:string;
  matchPassword:string;
  cliente:Cliente = {
    "id":"",
    "cpf":"",
    "telefone":"",
    "endereco":{
        "id":"",
        "bairro":"",
        "cidade":"",
        "unidadeFederativa":""

    }
  }
  tatuador:Tatuador = {
        "id":"",
        "nomeEstudio":"",
        "nomeSocial":""

  }
 
  };

  
