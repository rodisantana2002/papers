/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import model.enumeration.Status;

/**
 *
 * @author Rodolfo
 */
@Entity
@XmlRootElement 
public class Notificacao implements Serializable, Comparable<Notificacao>{
    private Integer id;
    private Pessoa pessoa;  
    private Documento documento;
    private String dtCriacao;
    private String horaCriacao;
    private String conteudo;
    private Status status;    

    public Notificacao(){
        pessoa = new Pessoa();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "destinatario")
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @OneToOne
    @JoinColumn(name = "documento")
    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }    
    
    public String getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(String dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public String getHoraCriacao() {
        return horaCriacao;
    }

    public void setHoraCriacao(String horaCriacao) {
        this.horaCriacao = horaCriacao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @Enumerated(EnumType.ORDINAL)     
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return getDtCriacao() + " " + getHoraCriacao();
    }
    
    @Override
    public int compareTo(Notificacao n) {
        if(n!=null){
            n.toString().compareTo(this.toString());
        }
        return 0;
    }   
}
