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

/**
 *
 * @author lucas
 */
public class GuidOptions {

    public static final String GUID_POOL = "abcdef1234567890";
    public static final String VARIANT_POOL = "ab89";

    /**
     * <pre>GuidOptions.GuidVersion</pre>
     */
    public enum GuidVersion {

        VERSION_1("1"),
        VERSION_2("2"),
        VERSION_3("3"),
        VERSION_4("4"),
        VERSION_5("5");

        private final String value;

        GuidVersion(String version) {
            this.value = version;
        }

        public String getValue() {
            return value;
        }
    }

    private final GuidVersion version;

    public GuidOptions(GuidVersion version) {
        this.version = version;
    }

    public GuidVersion getVersion() {
        return version;
    }
}
