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
package br.com.fidias.chance4j.text;

import br.com.fidias.chance4j.ChanceException;
import br.com.fidias.chance4j.number.Number;
import br.com.fidias.chance4j.text.TextOptions.PoolType;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author atila
 */
public class Character implements Text {

    private final TextOptions options;
    private String letters;
    
    public Character(TextOptions options) {
        this.options = options;
        switch (this.options.getCasing()) {
            case lower:
                this.letters = CHARS_LOWER;
                break;
            case upper:
                this.letters = CHARS_UPPER;
                break;
            default:
                this.letters = CHARS_LOWER + CHARS_UPPER;
        }
        
        switch (this.options.getPoolType()) {
            case alpha:
                this.options.setPool(this.letters);
                break;
            case symbols:
                this.options.setPool(SYMBOLS);
                break;
            case alphanumeric:
                this.options.setPool(this.letters + Number.NUMBERS);
                break;
            case numeric:
                this.options.setPool(Number.NUMBERS);
                break;
            case any:
                this.options.setPool(this.letters + Number.NUMBERS + SYMBOLS);
                break;
        }
    }
    
    @Override
    public String getTextPool() throws ChanceException {
        if (this.options.getPoolType() == PoolType.custom
                && StringUtils.isBlank(this.options.getPool())) {
            throw new ChanceException("Custom pool not defined.");
        }
        return this.options.getPool();
    }
}
