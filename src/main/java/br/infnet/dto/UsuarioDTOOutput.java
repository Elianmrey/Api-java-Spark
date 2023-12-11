package br.infnet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTOOutput {

    private int id;
    private String nome;

    @Override
   public String toString(){
        return "{ id: "+ id + ", nome: " + nome +"}";
    }
}
