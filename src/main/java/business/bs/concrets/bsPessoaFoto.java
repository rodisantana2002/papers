/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.bs.concrets;

import business.bs.abstracts.bsGeneric;
import model.entity.PessoaFoto;

/**
 *
 * @author rodolfosantana
 */
public class bsPessoaFoto extends bsGeneric<PessoaFoto>{

    public bsPessoaFoto(){
        super(new PessoaFoto());
    }
    
    public bsPessoaFoto(PessoaFoto entity) {
        super(entity);
    }    
}
