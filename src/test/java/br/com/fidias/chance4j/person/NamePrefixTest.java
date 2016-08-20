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

import br.com.fidias.chance4j.AbstractChanceTesting;
import br.com.fidias.chance4j.ChanceException;
import br.com.fidias.chance4j.person.name.PrefixSuffixOptions;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author atila
 */
public class NamePrefixTest extends AbstractChanceTesting {
    
    private String prefix;
    
    @Test
    public void randomNamePrefix() throws ChanceException {
        Gender gender;
        PrefixSuffixOptions psOptions;
        PrefixSuffixOptions[] values = PrefixSuffixOptions.values();
        for (int i = 0; i < 1000; i++) {
            gender = chance.gender();
            psOptions = values[chance.natural(values.length - 1)];
            prefix = chance.namePrefix(gender, psOptions);
            assertTrue("random name prefix", prefix instanceof String);
        }
    }
}
