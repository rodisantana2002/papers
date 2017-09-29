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
public class FilaSubmissao implements Serializable, Comparable<FilaSubmissao>{
    private Integer id;
    private Situacao situacao;
    private String versao;
    private Destino destino;
    private Documento documento;
    private String idioma;
    private String observacao;
    private String dtLimiteSubmissao;
    private String dtPublicacao;
    private String dtUltAtualizacao;
    private String horaUltAtualizacao;
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

    @OneToOne
    @JoinColumn(name = "destino")      
    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    @OneToOne
    @JoinColumn(name = "id_documento")      
    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getDtLimiteSubmissao() {
        return dtLimiteSubmissao;
    }

    public void setDtLimiteSubmissao(String dtLimiteSubmissao) {
        this.dtLimiteSubmissao = dtLimiteSubmissao;
    }

    public String getDtPublicacao() {
        return dtPublicacao;
    }

    public void setDtPublicacao(String dtPublicacao) {
        this.dtPublicacao = dtPublicacao;
    }

    public String getDtUltAtualizacao() {
        return dtUltAtualizacao;
    }

    public void setDtUltAtualizacao(String dtUltAtualizacao) {
        this.dtUltAtualizacao = dtUltAtualizacao;
    }

    public String getHoraUltAtualizacao() {
        return horaUltAtualizacao;
    }

    public void setHoraUltAtualizacao(String horaUltAtualizacao) {
        this.horaUltAtualizacao = horaUltAtualizacao;
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
        return this.dtLimiteSubmissao;
    }
    
    @Override
    public int compareTo(FilaSubmissao f) {
        if(f!=null){
            return f.toString().compareTo(this.toString());
        }
        return 0;
    }
}
