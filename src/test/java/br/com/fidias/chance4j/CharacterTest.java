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

import br.com.fidias.chance4j.text.TextOptions;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author atila
 */
public class CharacterTest extends AbstractChanceTesting {
    
    private static TextOptions options;
    
    @Before
    public void setUp() {
        options = new TextOptions();
    }
    
    @Test
    public void chooseFromCustomPool() throws ChanceException {
        options.setPoolType(TextOptions.PoolType.custom);
        options.setPool("abcd");
        char chr;
        for (int i = 0; i < 1000; i++) {
            chr = chance.character(options);
            assertTrue("choose only from the pool",
                    String.valueOf(chr).matches("[abcd]"));
        }
        
        options.setPool("zx01");
        for (int i = 0; i < 1000; i++) {
            chr = chance.character(options);
            assertTrue("choose only from the pool",
                    String.valueOf(chr).matches("[zx01]"));
        }
    }
    
    @Test
    public void chooseFromAlpha() throws ChanceException {
        options.setPoolType(TextOptions.PoolType.alpha);
        options.setCasing(TextOptions.Casing.lower);
        char chr;
        for (int i = 0; i < 1000; i++) {
            chr = chance.character(options);
            assertTrue("choose only from alpha lower case",
                    String.valueOf(chr).matches("[a-z]"));
        }
        
        options.setCasing(TextOptions.Casing.upper);
        for (int i = 0; i < 1000; i++) {
            chr = chance.character(options);
            assertTrue("choose only from alpha upper case",
                    String.valueOf(chr).matches("[A-Z]"));
        }
        
        options.setCasing(TextOptions.Casing.both);
        for (int i = 0; i < 1000; i++) {
            chr = chance.character(options);
            assertTrue("choose only from alpha both lower and upper case",
                    String.valueOf(chr).matches("[a-zA-Z]"));
        }
    }
    
    @Test
    public void chooseFromAlphaNumeric() throws ChanceException {
        options.setPoolType(TextOptions.PoolType.alphanumeric);
        options.setCasing(TextOptions.Casing.both);
        char chr;
        for (int i = 0; i < 1000; i++) {
            chr = chance.character(options);
            assertTrue("choose only from alpha numeric both lower and upper case",
                    String.valueOf(chr).matches("[a-zA-Z0-9]"));
        }
    }
}
