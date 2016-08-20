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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author atila
 */
@RunWith(Parameterized.class)
public class NaturalTest extends AbstractChanceTesting {
    
    private final int min, max;
    
    public NaturalTest(int min, int max) {
        this.min = min;
        this.max = max;
    }
    
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {0, 10},
            {5, 7},
            {10, 100},
            {1000, 100000},
            {25, 45678},
            {25045667, 90365489},
            {250, 1100},
            {1000, Integer.MAX_VALUE},
            {0, 1},
            {0, Integer.MAX_VALUE},
        });
    }
    
    @Test(expected = ChanceException.class)
    public void minSmallerThanZero() throws ChanceException {
        chance.natural(-max, 0);
    }
    
    @Test
    public void positiveCount() {
        int positiveCount = 0;
        for (int i = 0; i < 1000; i++) {
            if (chance.natural() >= 0) {
                positiveCount++;
            }
        }
        assertTrue("always positive",
                positiveCount == 1000);
    }
    
    @Test
    public void aboveMinimum() throws ChanceException {
        int natural = chance.natural(min, max);
        assertTrue("is above minimum", natural >= min);
    }
    
    @Test
    public void belowMaximum() throws ChanceException {
        int natural = chance.natural(min, max);
        assertTrue("is below maximum", natural <= max);
    }
}
