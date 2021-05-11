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
package br.com.fidias.chance4j.miscellaneous;

import br.com.fidias.chance4j.AbstractChanceTesting;
import br.com.fidias.chance4j.miscellaneous.GuidOptions.GuidVersion;
import java.util.regex.Pattern;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author lucas
 */
public class GuidTest extends AbstractChanceTesting {

    @Test
    public void properGuid() {
        String guid = chance.guid();
        assertTrue(Pattern.matches("([0-9a-fA-F]){8}(-([0-9a-fA-F]){4}){3}-([0-9a-fA-F]){12}", guid));
    }

    @Test
    public void guidV1() {
        String guid = chance.guid(GuidVersion.VERSION_1);
        assertTrue(Pattern.matches("([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-1([0-9a-fA-F]){3}-([ab89])([0-9a-fA-F]){3}-([0-9a-fA-F]){12}", guid));
    }

    @Test
    public void guidV2() {
        String guid = chance.guid(GuidVersion.VERSION_2);
        assertTrue(Pattern.matches("([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-2([0-9a-fA-F]){3}-([ab89])([0-9a-fA-F]){3}-([0-9a-fA-F]){12}", guid));
    }

    @Test
    public void guidV3() {
        String guid = chance.guid(GuidVersion.VERSION_3);
        assertTrue(Pattern.matches("([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-3([0-9a-fA-F]){3}-([ab89])([0-9a-fA-F]){3}-([0-9a-fA-F]){12}", guid));
    }

    @Test
    public void guidV4() {
        String guid = chance.guid(GuidVersion.VERSION_4);
        assertTrue(Pattern.matches("([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-4([0-9a-fA-F]){3}-([ab89])([0-9a-fA-F]){3}-([0-9a-fA-F]){12}", guid));
    }

    @Test
    public void guidV5() {
        String guid = chance.guid(GuidVersion.VERSION_5);
        assertTrue(Pattern.matches("([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-5([0-9a-fA-F]){3}-([ab89])([0-9a-fA-F]){3}-([0-9a-fA-F]){12}", guid));
    }
}
