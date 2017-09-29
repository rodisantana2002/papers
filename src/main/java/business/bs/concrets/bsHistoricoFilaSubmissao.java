/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.bs.concrets;

import business.bs.abstracts.bsGeneric;
import model.entity.HistoricoFilaSubmissao;

/**
 *
 * @author Rodolfo
 */
public class bsHistoricoFilaSubmissao extends bsGeneric<HistoricoFilaSubmissao>{
    
    public bsHistoricoFilaSubmissao(){
        super(new HistoricoFilaSubmissao());
    }
    
    public bsHistoricoFilaSubmissao(HistoricoFilaSubmissao entity) {
        super(entity);
    }    
}
