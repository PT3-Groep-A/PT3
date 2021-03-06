/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shared.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import Server.components.Persoon;

/**
 *
 * @author Merijn
 */
public interface IClient extends Remote
{
    public void updatePlayerList(List<String> spelers) throws RemoteException;
    public void updateGameLobbyList(List<String> gameLobbys) throws RemoteException;
    public void updateGameLobbyPlayers(List<String> gameLobbySpelers) throws RemoteException;
    public boolean ping() throws RemoteException;
}
