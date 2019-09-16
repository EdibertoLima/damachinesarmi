/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dama_rmi;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 
 */
public class Mensagem implements Serializable{
    
    private String operacao;
    
    
    /* 
    chave : Object
    */
    
    Map<String, Object> params;
    
    public Mensagem(String operacao)
    {
       this.operacao = operacao;
       params = new HashMap<>();
    }
    
    public String getOperacao()
    {
        return operacao;
    }
    
  /*
        "NOME" --> "José"
        "IDADE" --> 35
     */
    public void setParam( String chave, Object valor )
    {
        params.put( chave, valor );
    }
    
    public Object getParam( String chave )
    {
        return params.get(chave);
    }
    
    @Override
    public String toString()
    {
        String m = "Operacao: "+ operacao;
       
        
        m += "\nParâmetros:\n ";
        for (String p : params.keySet() ) { 
            m += p+": " + params.get(p)+"\n";  
        }
        return m;
    }
    
    
}
