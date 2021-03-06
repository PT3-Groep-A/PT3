/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server.components;

import Server.components.Game;
import Server.components.Speler;
import java.io.Serializable;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.interfaces.IClient;
import shared.interfaces.IGame;
import shared.interfaces.ILobbySignedIn;
import shared.observer.RemotePublisher;
import shared.serializable.ChatBericht;

/**
 *
 * @author Merijn
 */
public class Persoon extends UnicastRemoteObject implements ILobbySignedIn, Serializable 
{
    private transient IClient client;
    private String naam;
    private transient Lobby lobby;
    private transient GameLobby gameLobby;
    
    public Persoon(IClient client, String naam, Lobby lobby) throws RemoteException
    {
        this.client = client;
        this.naam = naam;
        this.lobby = lobby;
    }

    @Override
    public void StartGame() 
    {
        if (this.gameLobby != null)
        {
            gameLobby.startGame();
        }
    }

    @Override
    public RemotePublisher getChatboxLobby() 
    {
        return (RemotePublisher)lobby.getChatBox();
    }
    
    @Override
    public void sendChatLobby(String bericht) 
    {
        lobby.getChatBox().addBericht(new ChatBericht(bericht,(Persoon)this));
    }
    
    @Override
    public boolean CreateGame() 
    {
        if (this.gameLobby == null)
        {
            this.gameLobby = lobby.createGame(this);
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void JoinGame(String gamename) 
    {
        for(GameLobby l : this.lobby.getGames())
        {
            if(l.getName().equals(gamename))
            {
                l.addSpelers(this);
                this.gameLobby = l;
            }
        }
    }

    @Override
    public void spectateGame(String gamename) 
    {
        for(GameLobby l : this.lobby.getGames())
        {
            if(l.getName().equals(gamename))
            {
                l.addSpectators(this);
                this.gameLobby = l;
            }
        }
    }

    public String getGebruikersnaam() 
    {
        return this.naam;
    }
    
    public IClient getClient()
    {
        return client;
    }

    @Override
    public void logOut() throws RemoteException 
    {
        lobby.getPersonen().remove(this);
        lobby.updatePersonen();
        
        //Om het object daadwerkelijk te verwijderen anders blijft hij bestaan
        //word niet opgehaald door garbage collector!
        UnicastRemoteObject.unexportObject(this, true);
    }

    @Override
    public String showGebruikersNaam() throws RemoteException 
    {
        return this.getGebruikersnaam();
    }
    
    public Speler getSpeler(Game game)
    {
        try 
        {
            return new Speler(this.client, this.naam, this.lobby, game);
        } 
        catch (RemoteException ex) 
        {
            Logger.getLogger(Persoon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    @Override
    public int getSpelerSize()
    {
        if(this.gameLobby != null)
        {
            return this.gameLobby.getSpelerSize(this);
        }
        else
        {
            return 0;
        }
    }
    
    public void notifyGameStart(IGame game)
    {
        try {
            this.client.startGameClient(game);
        } catch (RemoteException ex) {
            Logger.getLogger(Persoon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
