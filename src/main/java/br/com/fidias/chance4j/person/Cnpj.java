/**
 * Chance4j is a minimalist generator of random strings, numbers, etc. to
 * help reduce some monotony particularly while writing automated tests or
 * anywhere else you need anything random.
 * Based on the <http://chancejs.com> by Victor Quinn and contributors
 *
 * Copyright (C) 2016 Átila Camurça <camurca.home@gmail.com>
 * Fidias Free Source Team <fidiascom@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.fidias.chance4j.person;

import br.com.fidias.chance4j.ChanceException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author atila
 */
public class Cnpj {
    
    public static int calculateVerifyingDigitOne(int[] values) throws ChanceException {
        if (values == null || values.length != 8) {
            throw new ChanceException("values length must be equal 8");
        }
        
        int startIndex = 7, aux = 6, d1 = 2;
        for (; startIndex >= 0; startIndex--) {
            d1 += values[startIndex] * aux;
            aux++;
            if (aux > 9) {
                aux = 2;
            }
        }
        
        d1 = 11 - (d1 % 11);
        if (d1 >= 10) {
            d1 = 0;
        }
        
        return d1;
    }
    
    public static int calculateVerifyingDigitTwo(int[] values, int d1) throws ChanceException {
        if (values == null || values.length != 8) {
            throw new ChanceException("values length must be equal 8");
        }
        
        int startIndex = 7, aux = 7, d2 = d1 * 2 + 3;
        for (;startIndex >= 0; startIndex--) {
            d2 += values[startIndex] * aux;
            aux++;
            if (aux > 9) {
                aux = 2;
            }
        }
        
        d2 = 11 - (d2 % 11);
        if (d2 >= 10) {
            d2 = 0;
        }
        
        return d2;
    }
    
    public static String format(String cnpj) {
        Pattern pattern = Pattern.compile("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})");
        Matcher matcher = pattern.matcher(cnpj);
        if (matcher.matches()) {
            cnpj = matcher.replaceAll("$1.$2.$3/$4-$5");
        }
        return cnpj;
    }
}
