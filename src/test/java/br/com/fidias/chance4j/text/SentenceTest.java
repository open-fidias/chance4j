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
package br.com.fidias.chance4j.text;

import br.com.fidias.chance4j.AbstractChanceTesting;
import static br.com.fidias.chance4j.Chance.MAX_WORDS_FOR_SENTENCE;
import static br.com.fidias.chance4j.Chance.MIN_WORDS_FOR_SENTENCE;
import br.com.fidias.chance4j.ChanceException;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author atila
 */
public class SentenceTest extends AbstractChanceTesting {
    
    private String sentence;
    
    @Test
    public void randomSentenceFiveWords() throws ChanceException {
        final int numWords = 5;
        for (int i = 0; i < 1000; i++) {
            sentence = chance.sentence(numWords);
            assertTrue("random sentence 5 words", sentence.split("\\s").length == numWords);
        }
    }
    
    @Test
    public void randomSentence() throws ChanceException {
        for (int i = 0; i < 1000; i++) {
            sentence = chance.sentence();
            int length = sentence.split("\\s").length;
            assertTrue("random sentence",
                    length >= MIN_WORDS_FOR_SENTENCE
                            && length <= MAX_WORDS_FOR_SENTENCE);
        }
    }
}
