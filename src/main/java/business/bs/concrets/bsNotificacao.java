/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.bs.concrets;

import business.bs.abstracts.bsGeneric;
import model.entity.Notificacao;

/**
 *
 * @author Rodolfo
 */
public class bsNotificacao extends bsGeneric<Notificacao> {
    public bsNotificacao(){
        super(new Notificacao());
    }
    
    public bsNotificacao(Notificacao entity){
        super(entity);
    }    
}
