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
import br.com.fidias.chance4j.ChanceException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author atila
 */
@RunWith(Parameterized.class)
public class DateTest extends AbstractChanceTesting {

    private final Date min, max;
    
    private static final Locale LOCALE = new Locale("pt", "BR");
    private final String[] monthNames = DateTimeUtils.getDateFormatSymbols(LOCALE).getMonths();

    public DateTest(Date min, Date max) {
        this.min = min;
        this.max = max;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {date(1980, 5, 10), date(2016, 12, 12)},
            {date(1970, 1, 1), date(2000, 6, 30)},
            {date(1999, 2, 28), date(1999, 3, 10)},
            {date(1999, 10, 26), date(2013, 9, 23)},
            {date(1988, 8, 25), date(2008, 9, 12)},
            {date(1994, 7, 17), date(2014, 7, 8)},
            {date(1976, 11, 4), date(2018, 5, 14)},
            {date(1991, 12, 31), date(2017, 12, 31)},
            {date(1975, 6, 5), date(2006, 4, 15)},
            {date(1972, 4, 1), date(2009, 1, 2)},});
    }

    private static Date date(int year, int month, int day) {
        return new DateTime(year, month, day, 12, 0).toDate();
    }

    @Test
    public void randomDate() throws ChanceException {
        Date date = chance.date(min, max);
        assertTrue("random date", date.getTime() >= min.getTime()
                && date.getTime() <= max.getTime());
    }
    
    @Test
    public void randomDateAsText() throws ChanceException {
        String monthName = chance.dateAsText(min, max, "MMMM", LOCALE);
        assertTrue("random month as text", Arrays.asList(monthNames).contains(monthName));
    }
    
    @Test
    public void randomDateLimitedByYear() throws ChanceException {
        Date date = chance.date(min, max);
        String year = new DateTime(date).toString("yyyy");
        Date randomDate = chance.date(Integer.parseInt(year));
        String randomYear = new DateTime(randomDate).toString("yyyy");
        assertEquals("random date limited by year", year, randomYear);
    }
    
    @Test
    public void randomDateAsTextLimitedByYear() throws ChanceException {
        Date date = chance.date(min, max);
        String year = new DateTime(date).toString("yyyy");
        String randomDate = chance.dateAsText(Integer.parseInt(year), "dd/MM/yyyy");
        assertTrue("random date ends with year", randomDate.endsWith(year));
    }
}
