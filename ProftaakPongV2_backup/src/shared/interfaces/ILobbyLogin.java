/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shared.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Merijn
 */
public interface ILobbyLogin extends Remote
{
    public ILobbySignedIn login(IClient client, String naam, String wachtwoord) throws RemoteException;
    public boolean register(String naam, String wachtwoord) throws RemoteException; 
}
