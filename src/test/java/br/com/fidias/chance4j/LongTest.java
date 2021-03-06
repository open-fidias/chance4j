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
package br.com.fidias.chance4j;

import static br.com.fidias.chance4j.AbstractChanceTesting.chance;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author atila
 */
@RunWith(Parameterized.class)
public class LongTest extends AbstractChanceTesting {

    private final long min, max;

    public LongTest(long min, long max) {
        this.min = min;
        this.max = max;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {0, 10},
            {5, 7},
            {10, 100},
            {1000, 100000},
            {-25, 0},
            {-25045667, 90365489},
            {-250, -110},
            {1000, Long.MAX_VALUE},
            {Long.MIN_VALUE, 0},
            {Long.MIN_VALUE, Long.MAX_VALUE},});
    }

    @Test(expected = ChanceException.class)
    public void minGreaterThanMax() throws ChanceException {
        chance.getLong(max, min);
    }

    @Test
    public void positiveCount() throws ChanceException {
        int positiveCount = 0;
        for (int i = 0; i < 1000; i++) {
            if (chance.getLong() > 0) {
                positiveCount++;
            }
        }
        assertTrue("is sometimes negative, sometimes positive",
                positiveCount >= 200 && positiveCount <= 800);
    }

    @Test
    public void randomLong() throws ChanceException {
        long value = chance.getLong(min, max);
        assertTrue("random long", value >= min && value <= max);
    }
}
