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
package br.com.fidias.chance4j.time;

import br.com.fidias.chance4j.AbstractChanceTesting;
import java.util.Arrays;
import java.util.Locale;
import org.joda.time.DateTimeUtils;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author atila
 */
public class MonthTest extends AbstractChanceTesting {
    
    private int month;
    private String monthAsText;
    
    private static final Locale LOCALE = new Locale("pt", "BR");
    private final String[] monthNames = DateTimeUtils.getDateFormatSymbols(LOCALE).getMonths();
    
    @Test
    public void randomMonth() {
        for (int i = 0; i < 1000; i++) {
            month = chance.month();
            assertTrue("random month", month >= Month.MIN && month <= Month.MAX);
        }
    }
    
    @Test
    public void randomMonthAsText() {
        for (int i = 0; i < 1000; i++) {
            monthAsText = chance.monthAsText(LOCALE);
            assertTrue("random month as text", Arrays.asList(monthNames).contains(monthAsText));
        }
    }
}
