/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.bs.concrets;

import business.bs.abstracts.bsGeneric;
import model.entity.Documento;

/**
 *
 * @author Rodolfo
 */
public class bsDocumento extends bsGeneric<Documento>{

    public bsDocumento() {
        super(new Documento());
    }   
    
    public bsDocumento(Documento entity) {
        super(entity);
    }   
}
