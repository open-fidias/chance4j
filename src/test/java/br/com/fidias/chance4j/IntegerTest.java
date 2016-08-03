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
package br.com.fidias.chance4j;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author atila
 */
@RunWith(Parameterized.class)
public class IntegerTest extends AbstractChanceTesting {
    
    private final int min, max;
    
    public IntegerTest(int min, int max) {
        this.min = min;
        this.max = max;
    }
    
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {0, 10},
            {5, 7},
            {10, 100},
            {1000, 100000},
            {-25, 0},
            {-25045667, 90365489},
            {-250, -110},
            {1000, Integer.MAX_VALUE},
            {Integer.MIN_VALUE, 0},
            {Integer.MIN_VALUE, Integer.MAX_VALUE},
        });
    }
    
    @Test(expected = ChanceException.class)
    public void minGreaterThanMax() throws ChanceException {
        chance.integer(max, min);
    }
    
    @Test
    public void positiveCount() throws ChanceException {
        int positiveCount = 0;
        for (int i = 0; i < 1000; i++) {
            if (chance.integer() > 0) {
                positiveCount++;
            }
        }
        assertTrue("is sometimes negative, sometimes positive",
                positiveCount >= 200 && positiveCount <= 800);
        
    }
    
    @Test
    public void aboveMinimum() throws ChanceException {
        int integer = chance.integer(min, max);
        assertTrue("is above minimum", integer >= min);
    }
    
    @Test
    public void belowMaximum() throws ChanceException {
        int integer = chance.integer(min, max);
        assertTrue("is below maximum", integer <= max);
    }
}
