/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.core;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Rodolfo
 */
@javax.ws.rs.ApplicationPath("services")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(service.wsFacade.concrets.AuthenticationFilter.class);
        resources.add(service.wsFacade.concrets.wsFacadeDestino.class);
        resources.add(service.wsFacade.concrets.wsFacadeDocumento.class);
        resources.add(service.wsFacade.concrets.wsFacadeDocumentosPessoas.class);
        resources.add(service.wsFacade.concrets.wsFacadeDocumentosPessoasFavoritos.class);
        resources.add(service.wsFacade.concrets.wsFacadeFilaSubmissao.class);
        resources.add(service.wsFacade.concrets.wsFacadeHistoricoFilaSubmissao.class);
        resources.add(service.wsFacade.concrets.wsFacadeNotificacao.class);
        resources.add(service.wsFacade.concrets.wsFacadePessoa.class);
        resources.add(service.wsFacade.concrets.wsFacadePessoaFoto.class);
        resources.add(service.wsFacade.concrets.wsFacadeSecurityAutenticar.class);
        resources.add(service.wsFacade.concrets.wsFacadeTipoDocumento.class);
        resources.add(service.wsFacade.concrets.wsFacadeUsuario.class);
    }    
}
