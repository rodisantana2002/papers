/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.validator.concrets;

import business.core.Ibusiness;
import business.factory.businessFactory;
import business.validator.abstracts.validGeneric;
import helpers.excecoes.excMessages;
import java.util.function.Predicate;
import model.entity.Documento;
import model.entity.DocumentosPessoas;
import model.entity.DocumentosPessoasFavoritos;
import model.entity.FilaSubmissao;

/**
 *
 * @author Rodolfo
 */
public class validDocumento extends validGeneric<Documento> {
    public validDocumento(){
        super();
    }
       
    public void validarCamposObrigatorios(Documento entity){
        if (entity.getId()!=null && entity.getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Identificador" + ").");                    
        }
        if (entity.getTitulo()==null || entity.getTitulo().trim().isEmpty()){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Titulo" + ").");            
        }
        if (entity.getPalavrasChave()==null || entity.getPalavrasChave().trim().isEmpty()){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Palvaras-Chave" + ").");                        
        }
        
        if (entity.getTipoDocumento()==null || entity.getTipoDocumento().getId()==null || entity.getTipoDocumento().getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Tipo Documento" + ").");                        
        }
        
        if (entity.getPessoa()==null || entity.getPessoa().getId()==null || entity.getPessoa().getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Criado Por" + ").");                        
        }        
    }

    public void validarRegistroCadastrado(Documento entity){
        Ibusiness<Documento> ibusiness = new businessFactory<Documento>(entity).getBusiness();
        Predicate<Documento> predID = p -> p.getId().equals(entity.getId());
        Predicate<Documento> predTitulo = p -> p.getTitulo().equalsIgnoreCase(entity.getTitulo());
        
        if (ibusiness.listarByFilter(entity, predID).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        }  
        
        if (ibusiness.listarByFilter(entity, predTitulo).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        }  
    }
    
    public void validarRegistroNaoCadastrado(Documento entity){
        Ibusiness<Documento> ibusiness = new businessFactory<Documento>(entity).getBusiness();
        Predicate<Documento> predID = p -> p.getId().equals(entity.getId());
        if (ibusiness.listarByFilter(entity, predID).isEmpty()){
            getLstMsg().add(excMessages.STR_REG_NAO_EXISTE);
        }          
    }    
    
    public void validarDocumentoVinculado(Documento entity){
        //fila de submissao
        Ibusiness<FilaSubmissao> ibusiness = new businessFactory<FilaSubmissao>(new FilaSubmissao()).getBusiness();                
        Predicate<FilaSubmissao> predDestino = p -> p.getDocumento().getId().equals(entity.getId());
        FilaSubmissao filaSubmissao = new FilaSubmissao();
        filaSubmissao.setDocumento(entity);
        
        if (!ibusiness.listarByFilter(filaSubmissao, predDestino).isEmpty()){
            getLstMsg().add(excMessages.STR_DEL_VINCULO_DOCUMENTO_FILA);
        }          
        
        ///documento pessoas
        Ibusiness<DocumentosPessoas> ibusinessPessoas = new businessFactory<DocumentosPessoas>(new DocumentosPessoas()).getBusiness();                
        Predicate<DocumentosPessoas> predDocPessoas = p -> p.getDocumento().getId().equals(entity.getId());
        DocumentosPessoas documentosPessoas = new DocumentosPessoas();
        documentosPessoas.setDocumento(entity);
        
        if (!ibusinessPessoas.listarByFilter(documentosPessoas, predDocPessoas).isEmpty()){
            getLstMsg().add(excMessages.STR_DEL_VINCULO_DOCUMENTO_PARTICIPANTE);
        }          

        
        ///documento favoritos
        Ibusiness<DocumentosPessoasFavoritos> ibusinessPessoasFav = new businessFactory<DocumentosPessoasFavoritos>(new DocumentosPessoasFavoritos()).getBusiness();                
        Predicate<DocumentosPessoasFavoritos> predDocPessoasFav = p -> p.getDocumento().getId().equals(entity.getId());
        DocumentosPessoasFavoritos documentosPessoasFavoritos = new DocumentosPessoasFavoritos();
        documentosPessoasFavoritos.setDocumento(entity);
        
        if (!ibusinessPessoasFav.listarByFilter(documentosPessoasFavoritos, predDocPessoasFav).isEmpty()){
            getLstMsg().add(excMessages.STR_DEL_VINCULO_DOCUMENTO_FAVORITO);
        }          
    } 
}
