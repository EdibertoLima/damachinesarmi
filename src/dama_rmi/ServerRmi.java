package dama_rmi;

import java.net.*;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import javax.swing.JOptionPane;

class ServerRmi {

    public static void main(String args[]) {
      
        try {
            //Cria HelloImpl
            System.setProperty("java.rmi.server.hostname", "localhost");
            LocateRegistry.createRegistry(5555);
            jogo1 obj = new jogo1("cliente1","localhost","5555",true,"azul");
            Naming.rebind("rmi://localhost:5555/cliente1", obj);
            jogo1 obj2 = new jogo1("cliente2","localhost","5555",false,"vermelha");
            Naming.rebind("rmi://localhost:5555/cliente2", obj2);

            JOptionPane.showMessageDialog(null, "server iniciou");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"HelloServer erro " + e.getMessage());
            
        }
        }

    }

