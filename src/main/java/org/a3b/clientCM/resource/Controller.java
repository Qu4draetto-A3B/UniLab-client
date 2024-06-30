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

public class Controller{

    public static boolean loginControl(String userID, String password){
        try {
            if(isLong(userID)) {
                long userIdLong = Long.parseLong(userID);
                return isOperatore(userIdLong,password);
            } else return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validOperatore(String[] attributi){
        if(attributi[2].length() != 16) return false;
        if(attributi[0].length() < 0 || attributi[1].length() < 0 ||
                attributi[3].length() < 0 || attributi[4].length() < 0) return false;

        RegisterHandler.setTmpOperator(attributi);
        return true;
    }
    public static boolean validCenter(String[] attributi){
        if(attributi[0].length() < 0 || attributi[1].length() < 0 ||
                attributi[2].length() < 0 || attributi[3].length() < 0) return false;
        return true;
    }
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

    private static boolean isOperatore(Long userId,String password) {

        if (password == null || password.isEmpty()) {
            return false;
        }
        try {
            App.operatore = App.server.login(userId,password).get();
            App.centro = App.operatore.getCentro();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
