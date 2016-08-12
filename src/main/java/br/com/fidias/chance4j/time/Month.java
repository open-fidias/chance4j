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
package br.com.fidias.chance4j.time;

import java.util.Locale;
import org.joda.time.LocalDate;

/**
 *
 * @author atila
 */
public class Month {

    public static final int MIN = 1;
    public static final int MAX = 12;

    public enum MonthOptions {

        numeric("MM"),
        shortName("MMM"),
        fullName("MMMM");

        private final String format;

        private MonthOptions(String format) {
            this.format = format;
        }

        public String getFormat() {
            return this.format;
        }
    }

    public static String monthName(int month, MonthOptions options, Locale locale) {
        if (options == null) {
            options = MonthOptions.fullName;
        }

        if (locale == null) {
            locale = Locale.getDefault();
        }

        LocalDate date = new LocalDate(1994, month, 1);
        return date.toString(options.getFormat(), locale);
    }
}
