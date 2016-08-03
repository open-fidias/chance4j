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
import static br.com.fidias.chance4j.Chance.MAX_SENTENCES_FOR_PARAGRAPH;
import static br.com.fidias.chance4j.Chance.MIN_SENTENCES_FOR_PARAGRAPH;
import br.com.fidias.chance4j.ChanceException;
import static br.com.fidias.chance4j.text.Text.PUNCTUATION;
import static org.junit.Assert.assertTrue;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 *
 * @author atila
 */
public class ParagraphTest extends AbstractChanceTesting {
    
    private String paragraph;
    private final int countSymbols = PUNCTUATION.length();
    private final String replaceSymbols = StringUtils.repeat("_", countSymbols);
    
    @Test
    public void randomParagraphFiveSentences() throws ChanceException {
        final int numSentences = 5;
        for (int i = 0; i < 1000; i++) {
            paragraph = chance.paragraph(numSentences);
            String replaceChars = StringUtils.replaceChars(paragraph, PUNCTUATION, replaceSymbols);
            int countMatches = StringUtils.countMatches(replaceChars, '_');
            assertTrue("random paragraph 5 sentences", countMatches == 5);
        }
    }
    
    @Test
    public void randomParagraph() throws ChanceException {
        for (int i = 0; i < 1000; i++) {
            paragraph = chance.paragraph();
            String replaceChars = StringUtils.replaceChars(paragraph, PUNCTUATION, replaceSymbols);
            int countMatches = StringUtils.countMatches(replaceChars, '_');
            assertTrue("random paragraph 5 sentences",
                    countMatches >= MIN_SENTENCES_FOR_PARAGRAPH
                        && countMatches <= MAX_SENTENCES_FOR_PARAGRAPH);
        }
    }
}
