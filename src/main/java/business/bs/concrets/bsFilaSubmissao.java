/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.bs.concrets;

import business.bs.abstracts.bsGeneric;
import model.entity.FilaSubmissao;

/**
 *
 * @author Rodolfo
 */
public class bsFilaSubmissao extends bsGeneric<FilaSubmissao>{
    
    public bsFilaSubmissao(){
        super(new FilaSubmissao());
    }
    
    public bsFilaSubmissao(FilaSubmissao entity) {
        super(entity);
    }        
}
