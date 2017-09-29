/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.bs.concrets;

import business.bs.abstracts.bsGeneric;
import model.entity.TipoDocumento;

/**
 *
 * @author Rodolfo
 */
public class bsTipoDocumento extends bsGeneric<TipoDocumento>{
    public bsTipoDocumento(){
        super(new TipoDocumento());
    }
    
    public bsTipoDocumento(TipoDocumento entity){
        super(entity);
    }
}
