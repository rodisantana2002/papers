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
import model.enumeration.Situacao;

/**
 *
 * @author Rodolfo
 */
@Entity
@XmlRootElement
public class HistoricoFilaSubmissao implements Serializable, Comparable<HistoricoFilaSubmissao>{
    private Integer id;
    private Situacao situacao;
    private String versao;
    private String dtAtualizacao;
    private String horaAtualizacao;
    private String comentario;
    private FilaSubmissao filaSubmissao;
    private Pessoa criadoPor;        

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)           
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Enumerated(EnumType.ORDINAL)     
    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(String dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    public String getHoraAtualizacao() {
        return horaAtualizacao;
    }

    public void setHoraAtualizacao(String horaAtualizacao) {
        this.horaAtualizacao = horaAtualizacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @OneToOne
    @JoinColumn(name = "id_filaSubmissao") 
    public FilaSubmissao getFilaSubmissao() {
        return filaSubmissao;
    }

    public void setFilaSubmissao(FilaSubmissao filaSubmissao) {
        this.filaSubmissao = filaSubmissao;
    }

    @OneToOne
    @JoinColumn(name = "criadoPor")         
    public Pessoa getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(Pessoa criadoPor) {
        this.criadoPor = criadoPor;
    }

    @Override
    public String toString(){
        return getDtAtualizacao() + " " + getHoraAtualizacao();
    }
    
    @Override
    public int compareTo(HistoricoFilaSubmissao h) {
        if(h!=null){
            return this.toString().compareTo(h.toString());
        }
        return 0;
    }
}
