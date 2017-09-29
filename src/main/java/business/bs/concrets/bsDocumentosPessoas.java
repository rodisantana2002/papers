/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.bs.concrets;

import business.bs.abstracts.bsGeneric;
import model.entity.DocumentosPessoas;

/**
 *
 * @author Rodolfo
 */
public class bsDocumentosPessoas extends bsGeneric<DocumentosPessoas>{

    public bsDocumentosPessoas(DocumentosPessoas entity) {
        super(entity);
    }
        
    public bsDocumentosPessoas() {
        super(new DocumentosPessoas());
    }    
}
