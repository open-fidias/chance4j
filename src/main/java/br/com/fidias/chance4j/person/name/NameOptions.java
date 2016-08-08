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
package br.com.fidias.chance4j.person.name;

/**
 *
 * @author atila
 */
public class NameOptions {
    
    private boolean middle, middleInitial, prefix;
    
    /**
     * Initialize NameOptions with all options set to false.
     */
    public NameOptions() {
        this.middle = false;
        this.middleInitial = false;
        this.prefix = false;
    }

    public boolean isMiddle() {
        return middle;
    }

    public void setMiddle(boolean middle) {
        this.middle = middle;
    }

    public boolean isMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(boolean middleInitial) {
        this.middleInitial = middleInitial;
    }

    public boolean isPrefix() {
        return prefix;
    }

    public void setPrefix(boolean prefix) {
        this.prefix = prefix;
    }
}
