package com.example.demo.Form.Pessoa;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.Enum.Sexo;
import com.example.demo.Model.Deficiencia;
import com.example.demo.Model.Pessoa;
import com.example.demo.Repository.DeficienciaRepository;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class PessoaForm {

    @NotBlank(message = "Preencha o campo nome.")
    private String nome;

    @NotNull(message = "Preencha o campo data de nascimento.")
    @Past(message = "A data de nascimento deve ser uma data do passado.")
    @JsonFormat(pattern = "dd-mm-yyyy")
    private LocalDate nascimento;

    @NotNull(message = "Preencha o campo sexo.")
    private int sexo;

    @NotNull(message = "Preencha o campo deficiência")
    private Deficiencia deficiencia;
    private List<Deficiencia> listDeficiencias;

    public Pessoa toEntity(){
        Sexo sexo = Sexo.fromCodigo(this.sexo);
        Pessoa pessoa = new Pessoa(nome, nascimento, sexo);
        pessoa.setDeficiencia(deficiencia);
        
        return pessoa;
    }

    public PessoaForm(Pessoa pessoa){
        this.nome = pessoa.getNome();
    }

    public void setDeficiencia(DeficienciaRepository repository){
        this.listDeficiencias = repository.findAll();
    }
}
