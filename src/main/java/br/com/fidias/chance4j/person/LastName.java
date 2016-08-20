/**
 * Chance4j is a minimalist generator of random strings, numbers, etc. to
 * help reduce some monotony particularly while writing automated tests or
 * anywhere else you need anything random.
 * Based on the <http://chancejs.com> by Victor Quinn and contributors
 *
 * Copyright (C) 2016 Átila Camurça <camurca.home@gmail.com>
 * Fidias Free and Open Source Team <fidiascom@gmail.com>
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

import br.com.fidias.chance4j.person.name.br.BrazilianLastName;
import br.com.fidias.chance4j.person.name.en.EnglishLastName;
import br.com.fidias.chance4j.person.name.it.ItalianLastName;
import br.com.fidias.chance4j.person.name.Nationality;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author atila
 */
public class LastName {
    
    public static String[] getLastNameList(Nationality nationality) {
        if (nationality == null) {
            nationality = Nationality.English;
        }
        switch (nationality) {
            case Brazilian:
                return BrazilianLastName.NAMES;
            case English:
                return EnglishLastName.NAMES;
            case Italian:
                return ItalianLastName.NAMES;
            default:
                // fallback to English
                String message = String.format(
                        "Last Name for '%s' not found. Using English instead.", nationality.name());
                Logger.getLogger(LastName.class.getName()).log(Level.WARNING, message);
                return EnglishLastName.NAMES;
        }
    }
}
