/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package azamak.utils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 *
 * @author EU
 */
public class StrUtils {

    public static boolean estUnMot(String text) {
        return spacecount(text) == 0;
    }

    public static int spacecount(String s) {
        int spaceCount = 0;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                spaceCount++;
            }
        }
        return spaceCount;
    }

    public static String removeSpaces(String s) {

        return org.apache.commons.lang3.StringUtils.normalizeSpace(s);
    }

    public static String translate(String s, Config conf) {
        String result = "" ;
        Map<String, String> all = conf.loadAll();
        String aTraduire = s.toLowerCase();
        aTraduire = StrUtils.removeSpaces(aTraduire);
        /**
         * - si c'est un mot, alors on lit dans le fichier de properties
         */

        if (StrUtils.estUnMot(aTraduire)) {
            String traduire = conf.getProperty(aTraduire);
            byte[] bytes = traduire.getBytes(StandardCharsets.UTF_8);
            String utf8EncodedString = new String(bytes, StandardCharsets.UTF_8);
            result = utf8EncodedString ;
//                yembaTxtField.setText(utf8EncodedString);
        } else {
            if (all.containsKey((String) aTraduire)) {
                String traduire = conf.getProperty(aTraduire);
                byte[] bytes = traduire.getBytes(StandardCharsets.UTF_8);
                String utf8EncodedString = new String(bytes, StandardCharsets.UTF_8);
//                yembaTxtField.setText(utf8EncodedString);
                result = utf8EncodedString ;
            } else {
                //decouper et traduire
                String[] tab = aTraduire.split(" ");
                
                for (int i = 0; i < tab.length ; i++) {
                    String str = tab[i];
                    if (str.equalsIgnoreCase("le") || str.equalsIgnoreCase("la")) {
                        // combiner le suivant et traduire
                        result += " " + conf.getProperty(tab[i + 1]);
                        i++;
                        if (i == tab.length-1) {
                            break;
                        }
                    } else {
                        result+= " " + conf.getProperty(str);
                    }
                }
                
                result = removeSpaces(result);
            }

        }
        return result ;
    }
}
