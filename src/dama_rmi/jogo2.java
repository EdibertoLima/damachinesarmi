/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dama_rmi;

/**
 *
 * @author ediberto
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ediberto
 */
 public class jogo2 extends UnicastRemoteObject implements IterfeceRemota {
        String nome = "cliente2";
        String endereço = "//localhost:5555";
        loby l ;

        jogo2() throws RemoteException{
            super();
            l= new loby(nome,"vermelha",false);
            l.setVisible(true);
        }

        @Override
        public void RecebeMensagem(Mensagem m) throws RemoteException {

            l.RecebeMensagem(m);
            //String aux = l.jTextArea2.getText();
            //jTextArea2.setText(aux + "\n" + nome + " ---->" + mensa); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
         
    public void EnviarMensagem(Mensagem m) throws RemoteException {
        try {
            String[] objetos = Naming.list("rmi:"+endereço);
            int tam = objetos.length;
            for (int i = 0; i < tam; i++) {
                if(!objetos[i].equals(endereço+"/"+nome)){
                IterfeceRemota client2 = (IterfeceRemota) Naming.lookup("rmi:"+objetos[i]);

                client2.RecebeMensagem(m);}
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(jogo1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(jogo1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void RecebeJogada(Mensagem m) throws RemoteException {
       
        l.RecebeJogada(m);
        //dama.recebeJogada(posicaox, posicaoy, row, col, cor); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void EnviarJogada(Mensagem m) throws RemoteException {
        try {
            String[] objetos = Naming.list("rmi:"+endereço);
            int tam = objetos.length;
            for (int i = 0; i < tam; i++) {
                if(!objetos[i].equals(endereço+"/"+nome)){
                IterfeceRemota client2 = (IterfeceRemota) Naming.lookup("rmi:"+objetos[i]);

                client2.RecebeJogada(m);}
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(jogo1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(jogo1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void RecebeLog(Mensagem m) throws RemoteException {
        l.log(m); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void EnviarLog(Mensagem m) throws RemoteException {
        try {
            String[] objetos = Naming.list("rmi:"+endereço);
            int tam = objetos.length;
            for (int i = 0; i < tam; i++) {
                if(!objetos[i].equals(endereço+"/"+nome)){
                IterfeceRemota client2 = (IterfeceRemota) Naming.lookup("rmi:"+objetos[i]);

                client2.RecebeLog(m);}
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(jogo1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(jogo1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

