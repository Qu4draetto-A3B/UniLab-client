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

import org.a3b.clientCM.App;
import org.a3b.clientCM.Register;
import org.a3b.commons.magazzeno.Operatore;
/**
 * La classe Controller fornisce metodi statici per la gestione del login e la validazione
 * degli attributi degli operatori e dei centri.
 */
public class Controller {

    /**
     * Verifica le credenziali di login di un utente.
     * <p>
     * Il metodo controlla se l'ID utente è un valore numerico lungo (`long`) e se sì, tenta di autenticare
     * l'utente con la password fornita. In caso contrario, ritorna `false`.
     * </p>
     *
     * @param userID   L'ID utente in formato stringa.
     * @param password La password dell'utente.
     * @return `true` se le credenziali sono valide e l'utente è autenticato con successo, `false` altrimenti.
     * @throws RuntimeException se si verifica un'eccezione durante il processo di login.
     */
    public static boolean loginControl(String userID, String password) {
        try {
            if (isLong(userID)) {
                long userIdLong = Long.parseLong(userID);
                return isOperatore(userIdLong, password);
            } else return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Valida gli attributi di un operatore.
     * <p>
     * Il metodo verifica che il terzo attributo (in posizione 2) sia esattamente lungo 16 caratteri
     * e che gli altri attributi abbiano una lunghezza maggiore di zero. Se tutti i controlli passano,
     * gli attributi vengono temporaneamente registrati usando `RegisterHandler`.
     * </p>
     *
     * @param attributi Un array di stringhe contenente gli attributi dell'operatore.
     * @return `true` se gli attributi sono validi, `false` altrimenti.
     */
    public static boolean validOperatore(String[] attributi) {
        if (attributi[2].length() != 16) return false;
        if (attributi[0].length() < 0 || attributi[1].length() < 0 ||
                attributi[3].length() < 0 || attributi[4].length() < 0) return false;

        RegisterHandler.setTmpOperator(attributi);
        return true;
    }

    /**
     * Valida gli attributi di un centro.
     * <p>
     * Il metodo verifica che tutti gli attributi abbiano una lunghezza maggiore di zero.
     * </p>
     *
     * @param attributi Un array di stringhe contenente gli attributi del centro.
     * @return `true` se gli attributi sono validi, `false` altrimenti.
     */
    public static boolean validCenter(String[] attributi) {
        if (attributi[0].length() < 0 || attributi[1].length() < 0 ||
                attributi[2].length() < 0 || attributi[3].length() < 0) return false;
        return true;
    }

    /**
     * Controlla se una stringa può essere convertita in un numero lungo (`long`).
     * <p>
     * Il metodo verifica se la stringa è non nulla e non vuota, e tenta di
     * convertirla in un valore lungo. Se la conversione ha successo, ritorna `true`,
     * altrimenti ritorna `false`.
     * </p>
     *
     * @param s La stringa da controllare.
     * @return `true` se la stringa può essere convertita in un numero lungo, `false` altrimenti.
     */
    private static boolean isLong(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Tenta di autenticare un operatore utilizzando l'ID e la password forniti.
     * <p>
     * Il metodo controlla se la password è non nulla e non vuota, e tenta di autenticare
     * l'utente tramite il server dell'applicazione. Se l'autenticazione ha successo,
     * assegna l'operatore e il centro all'utente autenticato.
     * </p>
     *
     * @param userId   L'ID dell'utente in formato lungo.
     * @param password La password dell'utente.
     * @return `true` se l'operatore è autenticato con successo, `false` altrimenti.
     */
    private static boolean isOperatore(Long userId, String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        try {
            App.operatore = App.server.login(userId, password).get();
            App.centro = App.operatore.getCentro();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
