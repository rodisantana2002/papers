/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoaFoto;

import helpers.mensagens.clsPSR;
import model.entity.Pessoa;
import model.entity.PessoaFoto;
import repository.core.interfaces.Irepository;
import repository.factory.repositoryFactory;

/**
 *
 * @author rodolfosantana
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            // TODO code application logic here
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1);
                
        PessoaFoto pessoaFoto = new PessoaFoto();
        pessoaFoto.setPessoa(pessoa);
        pessoaFoto.setFoto(null);
        
        
        Irepository d = new repositoryFactory<PessoaFoto>(pessoaFoto).getRepository();
        
        clsPSR.prt(d.salvar(pessoaFoto).toString());
       
        System.exit(0);

    }
    
}
