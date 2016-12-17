/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_classes;

/**
 *
 * @author Notebook
 */
public class MessageHandler {
    
    public String getIndexSiteMessage(int messageId){
        switch(messageId){
            case 0: return "";
            case 1: return "<p class='bg-warning'>Ungültiger Benutzername und/oder Passwort!</p>";
            case 2: return "<p class='bg-info'>Bitte vorher anmelden.</p>";
            case 3: return "<p class='bg-success'>Erfolgreich abgemeldet</p>";
            default: return "";
        }
    }
}
