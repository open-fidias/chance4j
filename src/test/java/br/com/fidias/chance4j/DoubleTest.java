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
public class DoubleTest extends AbstractChanceTesting {

    private final Integer min, max;
    private final static int LIMIT_NUMBER =
            (int) Math.pow(10, Chance.DECIMAL_SIZE_DEFAULT_VALUE + Chance.FORCE_INCREASE_FIXED);

    public DoubleTest(Integer min, Integer max) {
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
            {-210456, 213654},
            {-250, -110},
            {1000, (int) Integer.MAX_VALUE / LIMIT_NUMBER},
            {(int) Integer.MIN_VALUE / LIMIT_NUMBER, 0},
            {(int) Integer.MIN_VALUE / LIMIT_NUMBER, (int) Integer.MAX_VALUE / LIMIT_NUMBER}
        });
    }

    @Test(expected = ChanceException.class)
    public void minGreaterThanMax() throws ChanceException {
        chance.getDouble(max, min, Chance.DECIMAL_SIZE_DEFAULT_VALUE);
    }
    
    @Test
    public void positiveCount() throws ChanceException {
        long positiveCount = 0;
        for (int i = 0; i < 1000; i++) {
            if (chance.getDouble() > 0) {
                positiveCount++;
            }
        }
        assertTrue("is sometimes negative, sometimes positive",
                positiveCount >= 200 && positiveCount <= 800);
    }

    @Test
    public void randomDouble() throws ChanceException {
        double value = chance.getDouble(min, max, Chance.DECIMAL_SIZE_DEFAULT_VALUE);
        assertTrue("random double", value >= min && value <= max);
    }
    
    @Test
    public void randomDoubleFixedSize() throws ChanceException {
        int fixed, indexOf;
        double value;
        String result;
        for (int i = 0; i < 1000; i++) {
            fixed = chance.natural(1, 6);
            value = chance.getDoublePositive(fixed);
            result = String.valueOf(value);
            indexOf = result.indexOf(".");
            assertTrue("random double with fixed size",
                    result.substring(indexOf + 1).length() <= fixed);
        }
    }
    
    @Test(expected = ChanceException.class)
    public void overflowMaxValue() throws ChanceException {
        chance.getDouble(0, Integer.MAX_VALUE, 2);
    }
    
    @Test(expected = ChanceException.class)
    public void overflowMinValue() throws ChanceException {
        chance.getDouble(Integer.MIN_VALUE, 100, 2);
    }
}
