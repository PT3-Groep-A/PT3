/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shared.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import shared.observer.RemotePublisher;
import shared.serializable.ChatBericht;

/**
 *
 * @author Merijn
 */
public interface IGame extends Remote
{
    /**
    * Met deze methode kan je een chatbericht verzenden die wordt naar de chatbox gestuurd
    * elke gebruiker kan deze methode gebruiken als je ingelogd bent.
    * @param bericht is het bericht(String) wat de gebruiker heeft verstuurd.
    * @throws RemoteException omdat deze class remote extends.
    */
    public void sendChat(String bericht) throws RemoteException;
    
    /**
    * Met deze methode haal je de Chatbox op zodat de mensen
    * berichten kunnen lezen van andere mensen.
    * @return een RemotePublisher
    * @throws RemoteException omdat deze class remote extends
    */
    public RemotePublisher getChatboxRemote() throws RemoteException;
    /**
     * Door  middel van deze methode wordt aan de server doorgegeven of het batje naar links beweegt.
     * @param moveleft Je kan aan de waarde true of false zien het batje naar links beweegt.
     * @throws RemoteException een exception voor als er iets fout gaat met het remote deel.
     */
    public void setMoveLeft(boolean moveleft) throws RemoteException;
    
    /**
     * Door middel van deze methode wordt aan de server doorgegeven of het batje naar rechte beweegt.
     *
     * @param moveright Je kan aan de waarde true of false zien of het batje naar rechts beweegt.
     * @throws RemoteException een exception voor als er iets fout gaat met het remote deel.
     */
    public void setMoveRight(boolean moveright) throws RemoteException;
 
    /**
     * Met deze methode wordt het scherm gedraait op het scherm hierdoor ben jij zelf altijd de onderste speler
     * Dit is fijn en voorkomt veel verwarring. Als toeschouwer staan altijd dezelfde kleuren op dezelfde plek.
     * @return -
     * @throws RemoteException een exception voor als er iets fout gaat met het remote deel.
     */
    public int rotatePosition() throws RemoteException;
    
}
