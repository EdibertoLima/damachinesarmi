/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dama_rmi;

import java.net.*;
import java.rmi.*;
/**
 *
 * @author ediberto
 */
public interface IterfeceRemota extends Remote{
    public void RecebeMensagem(Mensagem m) throws RemoteException;
    public void EnviarMensagem(Mensagem a) throws RemoteException;// nao uso
    public void RecebeJogada(Mensagem m) throws RemoteException;
    public void EnviarJogada(Mensagem c) throws RemoteException;// nao uso
    public void RecebeLog(Mensagem m)throws RemoteException;
    public void EnviarLog(Mensagem m)throws RemoteException;
}
