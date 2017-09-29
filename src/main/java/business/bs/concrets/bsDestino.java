/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.bs.concrets;

import business.bs.abstracts.bsGeneric;
import model.entity.Destino;

/**
 *
 * @author Rodolfo
 */
public class bsDestino extends bsGeneric<Destino> {
    public bsDestino(){
        super(new Destino());
    }
    
    public bsDestino(Destino entity){
        super(entity);
    }
}
