/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rodolfo
 */
@Entity
@XmlRootElement
public class Destino implements Serializable, Comparable<Destino>{
    private Integer id;
    private String descricao;
    private String classificacao;
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }   

    @Override
    public int compareTo(Destino d) {
        if(d!=null){
            return this.getDescricao().compareTo(d.getDescricao());
        }        
        else{
            return 0;
        }
    }
}
