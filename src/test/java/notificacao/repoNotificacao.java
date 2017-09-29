/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notificacao;

import helpers.mensagens.clsPSR;
import model.entity.Notificacao;
import model.entity.Pessoa;
import model.enumeration.Status;
import repository.core.interfaces.Irepository;
import repository.factory.repositoryFactory;

/**
 *
 * @author Rodolfo
 */
public class repoNotificacao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1);
                
        Notificacao notificacao = new Notificacao();
        notificacao.setId(null);
        notificacao.setPessoa(pessoa);
        notificacao.setDtCriacao("14/09/2017");
        notificacao.setHoraCriacao("13:00:00");
        notificacao.setStatus(Status.PENDENTE);
        notificacao.setConteudo("minha primeira mensagem");
        
        
        Irepository d = new repositoryFactory<Notificacao>(notificacao).getRepository();
        
        clsPSR.prt(d.salvar(notificacao).toString());
       
        System.exit(0);
        
    }
    
}
