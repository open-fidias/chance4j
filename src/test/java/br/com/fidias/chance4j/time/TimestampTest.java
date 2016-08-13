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

import br.com.fidias.chance4j.AbstractChanceTesting;
import br.com.fidias.chance4j.ChanceException;
import java.util.Date;
import java.util.Locale;
import org.joda.time.DateTime;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author atila
 */
public class TimestampTest extends AbstractChanceTesting {
    
    private Date timestamp;
    private int year;
    private String timestampAsText;
    private static final Locale LOCALE = new Locale("pt", "BR");
    
    @Test
    public void randomTimestamp() throws ChanceException {
        for (int i = 0; i < 1000; i++) {
            year = chance.year();
            timestamp = chance.timestamp(year);
            DateTime dateTime = new DateTime(timestamp);
            assertTrue("random timestamp limited to a year",
                    dateTime.getYear() == year);
        }
    }
    
    @Test
    public void randomTimestampAsText() throws ChanceException {
        for (int i = 0; i < 1000; i++) {
            year = chance.year();
            timestampAsText = chance.timestampAsText(year, "yyyy", LOCALE);
            assertTrue("random timestamp limited to a year",
                    timestampAsText.equals(String.valueOf(year)));
        }
    }
}
