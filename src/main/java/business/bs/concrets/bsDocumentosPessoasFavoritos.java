/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.bs.concrets;

import business.bs.abstracts.bsGeneric;
import model.entity.DocumentosPessoasFavoritos;

/**
 *
 * @author Rodolfo
 */
public class bsDocumentosPessoasFavoritos extends bsGeneric<DocumentosPessoasFavoritos>{

    public bsDocumentosPessoasFavoritos(){
        super(new DocumentosPessoasFavoritos());
    }
    
    public bsDocumentosPessoasFavoritos(DocumentosPessoasFavoritos entity) {
        super(entity);
    }    
}
