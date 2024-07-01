/*
 * Interdisciplinary Workshop B
 * Climate Monitoring
 * A.A. 2023-2024
 *
 * Authors:
 * - Iuri Antico, 753144, VA
 * - Beatrice Balzarini, 752257, VA
 * - Michael Bernasconi, 752259, VA
 * - Gabriele Borgia, 753262, VA
 *
 * Some rights reserved.
 * See LICENSE file for additional information.
 */
package org.a3b.clientCM.resource;

import javafx.stage.Stage;
import org.a3b.clientCM.App;
import org.a3b.clientCM.NewMonitoringCenter;
import org.a3b.clientCM.Operator;
import org.a3b.commons.magazzeno.CentroMonitoraggio;
import org.a3b.commons.magazzeno.ListaAree;
import org.a3b.commons.magazzeno.Operatore;

import java.rmi.RemoteException;

/**
 * Questa classe gestisce le operazioni di registrazione di operatori e centri di monitoraggio,
 * mantenendo temporaneamente i dati durante il processo di registrazione.
 */
public class RegisterHandler {
    // Variabili statiche per mantenere temporaneamente i dati durante il processo di registrazione
    public static Operatore tmpOperatore = new Operatore();
    public static CentroMonitoraggio tmpCentro = new CentroMonitoraggio();
    public static ListaAree tmpLista = new ListaAree();
    public static String tmpPassword = "";

    /**
     * Imposta i dati temporanei dell'operatore durante il processo di registrazione.
     *
     * @param attributi Un array di stringhe contenente nome, cognome, CF, email e password dell'operatore.
     */
    public static void setTmpOperator(String[] attributi) {
        tmpOperatore.setNome(attributi[0]);
        tmpOperatore.setCognome(attributi[1]);
        tmpOperatore.setCf(attributi[2]);
        tmpOperatore.setEmail(attributi[3]);

        tmpPassword = attributi[4];
    }

    /**
     * Resetta i dati temporanei per iniziare una nuova registrazione.
     */
    public static void newRegister() {
        tmpLista = new ListaAree();
        tmpOperatore = new Operatore();
        tmpCentro = new CentroMonitoraggio();
        tmpPassword = "";
    }

    /**
     * Registra un nuovo centro di monitoraggio con le aree selezionate temporaneamente.
     *
     * @return true se la registrazione è avvenuta con successo, false altrimenti.
     */
    public static boolean newCenter() {
        try {
            if (!tmpLista.isEmpty()) {
                tmpCentro.setAree(tmpLista);
                tmpCentro = App.server.registraCentroAree(tmpCentro).get();
                App.server.alterListaAree(tmpCentro, tmpLista).get();
                tmpLista = new ListaAree();
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Registra un nuovo operatore collegato al centro di monitoraggio temporaneo.
     *
     * @return true se la registrazione è avvenuta con successo, false altrimenti.
     */
    public static boolean newOperator() {
        try {
            if (newCenter()) {
                if (App.operatore == null) {
                    RegisterHandler.tmpOperatore.setCentro(tmpCentro);
                    App.operatore = App.server.registrazione(tmpOperatore, tmpPassword).get();
                    App.centro = tmpCentro;
                    System.out.println(App.operatore.getUid());
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        newRegister();
        return true;
    }

    /**
     * Registra un nuovo operatore già esistente nel sistema collegato al centro di monitoraggio temporaneo.
     *
     * @return true se la registrazione è avvenuta con successo, false altrimenti.
     */
    public static boolean newOperatorExist() {
        try {
            RegisterHandler.tmpOperatore.setCentro(tmpCentro);
            App.operatore = App.server.registrazione(tmpOperatore, tmpPassword).get();
            App.centro = tmpCentro;
            System.out.println(App.operatore.getUid());
        } catch (Exception e) {
            return false;
        }
        newRegister();
        return true;
    }

    /**
     * Imposta i dati temporanei del centro di monitoraggio durante il processo di registrazione.
     *
     * @param attributi Un array di stringhe contenente i dettagli del centro di monitoraggio.
     * @return true se i dati sono stati impostati correttamente, false altrimenti.
     */
    public static boolean setTmpCentro(String[] attributi) {
        try {
            if (Controller.validCenter(attributi)) {
                tmpCentro.setNome(attributi[0]);
                tmpCentro.setNomeVia(attributi[1]);
                tmpCentro.setCivico(Integer.parseInt(attributi[2]));
                tmpCentro.setCap(Integer.parseInt(attributi[3]));
                tmpCentro.setComune(attributi[4]);
                tmpCentro.setProvincia(attributi[5]);
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Ottiene l'ID geografico da una stringa formattata, utilizzata principalmente per l'identificazione delle aree.
     *
     * @param input Una stringa formattata che include l'ID geografico tra parentesi quadre.
     * @return L'ID geografico estratto come valore long.
     */
    public static long getGeoIDFromString(String input) {
        int spaceIndex = input.indexOf(' ');
        return Long.parseLong(input.substring(1, spaceIndex - 1));
    }
}
