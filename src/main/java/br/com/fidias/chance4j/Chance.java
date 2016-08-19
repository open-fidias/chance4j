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

import br.com.fidias.chance4j.person.AgeOptions;
import br.com.fidias.chance4j.person.Cnpj;
import br.com.fidias.chance4j.person.CnpjOptions;
import br.com.fidias.chance4j.person.Cpf;
import br.com.fidias.chance4j.person.CpfOptions;
import br.com.fidias.chance4j.person.FirstName;
import br.com.fidias.chance4j.person.Gender;
import br.com.fidias.chance4j.person.LastName;
import br.com.fidias.chance4j.person.NamePrefix;
import br.com.fidias.chance4j.person.Ssn;
import br.com.fidias.chance4j.person.SsnOptions;
import br.com.fidias.chance4j.person.name.NameOptions;
import br.com.fidias.chance4j.person.name.Nationality;
import br.com.fidias.chance4j.person.name.PrefixSuffixOptions;
import br.com.fidias.chance4j.text.TextOptions;
import br.com.fidias.chance4j.text.Character;
import br.com.fidias.chance4j.time.Hour;
import br.com.fidias.chance4j.time.Millisecond;
import br.com.fidias.chance4j.time.Minute;
import br.com.fidias.chance4j.time.Month;
import br.com.fidias.chance4j.time.Second;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.MonthDay;

/**
 *
 * @author Átila Camurça camurca.home@gmail.com
 */
public class Chance {

    public final static int DECIMAL_SIZE_DEFAULT_VALUE = 2;
    /**
     * Force increase the localFixed value in
     * {@link Chance#getBigDecimal(java.lang.Integer, java.lang.Integer, int)}
     * to obtain a smaller number to maxLocal.
     * Otherwise the double value will overflow and give wrong number of decimals.
     */
    public final static int FORCE_INCREASE_FIXED = 2;
    public final static int MIN_SENTENCES_FOR_PARAGRAPH = 3;
    public final static int MAX_SENTENCES_FOR_PARAGRAPH = 7;
    public final static int MIN_WORDS_FOR_SENTENCE = 12;
    public final static int MAX_WORDS_FOR_SENTENCE = 18;
    public final static int MIN_SYLLABLES_FOR_WORD = 1;
    public final static int MAX_SYLLABLES_FOR_WORD = 3;
    public final static int MIN_CHARS_FOR_STRING = 5;
    public final static int MAX_CHARS_FOR_STRING = 20;
    public final static int MIN_CHAR_FOR_SYLLABLE = 2;
    public final static int MAX_CHAR_FOR_SYLLABLE = 3;

    private final RandomDataGenerator random;

    /**
     * Creates an instance of RandomDataGenerator with the Mersenne Twister
     * generator created by Apache Commons Math library.
     *
     * The Mersenne Twister is a pseudorandom number generator (PRNG). It is by
     * far the most widely used general-purpose PRNG.[1] Its name derives from
     * the fact that its period length is chosen to be a Mersenne prime.
     */
    public Chance() {
        this.random = new RandomDataGenerator(new MersenneTwister());
    }

    /**
     * Return a random integer.
     *
     * @param min Minimum value to choose from
     * @param max Maximum value to choose from
     * @return A single random integer number
     * @throws ChanceException Min cannot be greater than Max.
     */
    public int integer(int min, int max) throws ChanceException {
        if (min > max) {
            throw new ChanceException("Min cannot be greater than Max.");
        }
        return random.nextInt(min, max);
    }

    /**
     * Return a random integer.
     *
     * @return A single random integer number
     * @throws ChanceException
     */
    public int integer() throws ChanceException {
        return integer(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Return a random long.
     * <pre>
     * chance.getLong(0, 100);
     * => 42
     * </pre>
     *
     * @param min
     * @param max
     * @return
     * @throws ChanceException
     */
    public long getLong(long min, long max) throws ChanceException {
        if (min > max) {
            throw new ChanceException("Min cannot be greater than Max.");
        }
        return random.nextLong(min, max);
    }

    /**
     * Return a random long.
     * <pre>
     * chance.getLong();
     * => 16
     * </pre>
     *
     * @return
     * @throws ChanceException
     */
    public long getLong() throws ChanceException {
        return getLong(Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public int natural() {
        int result = 0;
        try {
            result = natural(0, Integer.MAX_VALUE);
        } catch (ChanceException e) {
            // it's never throw
        }
        return result;
    }

    public int natural(int max) throws ChanceException {
        return natural(0, max);
    }

    /**
     * Return a random natural. NOTE the max and min are INCLUDED in the range.
     *
     * @param min Minimum value to choose from
     * @param max Maximum value to choose from
     * @return A single random integer number
     * @throws ChanceException min cannot be greater than max. Min cannot be
     * less than zero. Max must be greater than zero.
     */
    public int natural(int min, int max) throws ChanceException {
        if (min < 0) {
            throw new ChanceException("Min cannot be less than zero.");
        }
        if (max <= 0) {
            throw new ChanceException("Max must be greater than zero.");
        }
        if (max < min) {
            throw new ChanceException("Max must be greater than min.");
        }
        return integer(min, max);
    }

    /**
     * Return a random bool, either true or false.
     *
     * @param likelihood Alter the likelihood of receiving a true or false value
     * back.
     * @return Either true or false
     * @throws ChanceException if the likelihood is out of bounds
     */
    public boolean bool(double likelihood) throws ChanceException {
        if (likelihood < 0 || likelihood > 100) {
            throw new ChanceException("Likelihood accepts values from 0 to 100.");
        }
        return random.nextUniform(0, 100) < likelihood;
    }

    /**
     * Return a random bool, either true or false.
     *
     * @return Either true or false
     */
    public boolean bool() {
        boolean result = false;
        try {
            result = bool(50);
        } catch (ChanceException e) {
            // it's never throw
        }
        return result;
    }

    /**
     * Return a random BigDecimal number.
     * <pre>
     * chance.getBigDecimal(-10, 100, 2);
     * => 45.89
     * </pre>
     *
     * @param min Minimum value to choose from
     * @param max Maximum value to choose from
     * @param fixed Specify a fixed precision
     * @return A random BigDecimal
     * @throws ChanceException
     */
    public BigDecimal getBigDecimal(Integer min, Integer max, int fixed) throws ChanceException {
        if (min != null && max != null && max < min) {
            throw new ChanceException("Max must be greater than min.");
        }

        int num;
        int localFixed = (int) Math.pow(10, fixed + FORCE_INCREASE_FIXED);
        int localMax = (int) (Integer.MAX_VALUE / localFixed);
        int localMin = -localMax;
        if (max == null) {
            max = localMax;
        } else if (max > localMax) {
            final String message = "Max specified (%d) is out of range with fixed. "
                    + "Max should be, at most, %d";
            throw new ChanceException(String.format(message, max, localMax));
        }
        if (min == null) {
            min = localMin;
        } else if (min < localMin) {
            final String message = "Min specified (%d) is out of range with fixed. "
                    + "Min should be, at least, %d";
            throw new ChanceException(String.format(message, min, localMin));
        }
        num = integer(min * localFixed, max * localFixed);
        BigDecimal bd = new BigDecimal(num)
                .divide(new BigDecimal(localFixed), MathContext.UNLIMITED);
        return bd.setScale(fixed, RoundingMode.UP);
    }

    /**
     * Return a random floating point number.
     * <pre>
     * chance.floating(-10, 100, 2);
     * => 57.66
     * </pre>
     *
     * @param min Minimum value to choose from
     * @param max Maximum value to choose from
     * @param fixed Specify a fixed precision
     * @return A random float
     * @throws ChanceException Min cannot be greater than Max.
     */
    public float floating(Integer min, Integer max, int fixed) throws ChanceException {
        return getBigDecimal(min, max, fixed).floatValue();
    }

    /**
     * Return a random floating point number.
     * <pre>
     * chance.floating(2);
     * => 48.92
     * </pre>
     *
     * @param fixed Specify a fixed precision
     * @return A random float
     * @throws ChanceException
     */
    public float floating(int fixed) throws ChanceException {
        return floating(null, null, fixed);
    }

    /**
     * Return a random floating point number with fixed size
     * {@value #DECIMAL_SIZE_DEFAULT_VALUE}.
     * <pre>
     * chance.floating();
     * => -89.31
     * chance.floating();
     * => 94.31
     * </pre>
     *
     * @return A random float
     * @throws ChanceException
     */
    public float floating() throws ChanceException {
        return floating(DECIMAL_SIZE_DEFAULT_VALUE);
    }

    /**
     * Return a random floating point number.
     * <pre>
     * chance.getFloat(-10, 100, 2);
     * => 57.66
     * </pre>
     *
     * @param min Minimum value to choose from
     * @param max Maximum value to choose from
     * @param fixed Specify a fixed precision
     * @return A random float
     * @throws ChanceException Min cannot be greater than Max.
     */
    public float getFloat(Integer min, Integer max, int fixed) throws ChanceException {
        return floating(min, max, fixed);
    }

    /**
     * Return a random floating point number.
     * <pre>
     * chance.getFloat(2);
     * => 48.92
     * </pre>
     *
     * @param fixed Specify a fixed precision
     * @return A random float
     * @throws ChanceException
     */
    public float getFloat(int fixed) throws ChanceException {
        return floating(fixed);
    }

    /**
     * Return a random floating point number.
     * <pre>
     * chance.getFloat();
     * => -89.31
     * chance.getFloat();
     * => 94.31
     * </pre>
     *
     * @return A random float
     * @throws ChanceException
     */
    public float getFloat() throws ChanceException {
        return floating();
    }

    /**
     * Return a random floating positive point number.
     * <pre>
     * chance.getFloatPositive(2);
     * => 48.92
     * </pre>
     *
     * @param fixed Specify a fixed precision
     * @return A random float
     * @throws ChanceException
     */
    public float getFloatPositive(int fixed) throws ChanceException {
        int limit = (int) Math.pow(10, fixed + FORCE_INCREASE_FIXED);
        return floating(0, (int) Integer.MAX_VALUE / limit, fixed);
    }

    /**
     * Return a random floating positive point number with fixed size
     * {@value #DECIMAL_SIZE_DEFAULT_VALUE}.
     * <pre>
     * chance.getFloatPositive();
     * => 51.63
     * chance.getFloatPositive();
     * => 77.39
     * </pre>
     *
     * @return A random float
     * @throws ChanceException
     */
    public float getFloatPositive() throws ChanceException {
        return getFloatPositive(DECIMAL_SIZE_DEFAULT_VALUE);
    }

    /**
     * Return a random double.
     * <pre>
     * chance.getDouble(-10, 100, 3);
     * => -6.742
     * </pre>
     *
     * @param min Minimum value to choose from
     * @param max Maximum value to choose from
     * @param fixed Specify a fixed precision
     * @return A random double
     * @throws ChanceException
     */
    public double getDouble(Integer min, Integer max, int fixed) throws ChanceException {
        return getBigDecimal(min, max, fixed).doubleValue();
    }

    /**
     * Return a random double.
     * <pre>
     * chance.getDouble(3);
     * => 82.325
     * </pre>
     *
     * @param fixed Specify a fixed precision
     * @return A random double
     * @throws ChanceException
     */
    public double getDouble(int fixed) throws ChanceException {
        return getDouble(null, null, fixed);
    }

    /**
     * Return a random double with fixed size
     * {@value #DECIMAL_SIZE_DEFAULT_VALUE}.
     * <pre>
     * chance.getDouble();
     * => -5.33
     * </pre>
     *
     * @return A random double
     * @throws ChanceException
     */
    public double getDouble() throws ChanceException {
        return getDouble(DECIMAL_SIZE_DEFAULT_VALUE);
    }

    /**
     * Return a random double.
     * <pre>
     * chance.getDoublePositive(3);
     * => 82.325
     * </pre>
     *
     * @param fixed Specify a fixed precision
     * @return A random double
     * @throws ChanceException
     */
    public double getDoublePositive(int fixed) throws ChanceException {
        int limit = (int) Math.pow(10, fixed + FORCE_INCREASE_FIXED);
        return getDouble(0, (int) Integer.MAX_VALUE / limit, fixed);
    }

    /**
     * Return a random double with fixed size
     * {@value #DECIMAL_SIZE_DEFAULT_VALUE}.
     * <pre>
     * chance.getDoublePositive();
     * => 82.32
     * </pre>
     *
     * @return A random double
     * @throws ChanceException
     */
    public double getDoublePositive() throws ChanceException {
        return getDoublePositive(DECIMAL_SIZE_DEFAULT_VALUE);
    }

    /**
     * Return an array of random character.
     *
     * @param options Can specify a character pool, only alpha, only symbols,
     * and casing (lower or upper)
     * @param length size of the array
     * @return
     * @throws ChanceException
     */
    private char[] characters(TextOptions options, int length) throws ChanceException {
        Character character = new Character(options);
        final String text = character.getTextPool();
        String result = "";
        for (int i = 0; i < length; i++) {
            int natural = natural(text.length() - 1);
            result += text.charAt(natural);
        }
        return result.toCharArray();
    }

    /**
     * Return a random character.
     *
     * @param options Can specify a character pool, only alpha, only symbols,
     * and casing (lower or upper)
     * @return a single random character
     * @throws ChanceException
     */
    public char character(TextOptions options) throws ChanceException {
        char[] characters = characters(options, 1);
        return characters[0];
    }

    /**
     * Return a random string.
     *
     * @param options Can specify a character pool, only alpha, only symbols,
     * and casing (lower or upper)
     * @param length Specify a length
     * @return A string of especified length
     * @throws ChanceException
     */
    public String string(TextOptions options, int length) throws ChanceException {
        if (length <= 0) {
            throw new ChanceException("Length cannot be less or equal than zero.");
        }
        char[] characters = characters(options, length);
        return String.valueOf(characters);
    }

    /**
     * Return a random string.
     *
     * @param options Can specify a character pool, only alpha, only symbols,
     * and casing (lower or upper)
     * @return A string of random length
     * @throws ChanceException
     */
    public String string(TextOptions options) throws ChanceException {
        int length = natural(5, 20);
        char[] characters = characters(options, length);
        return String.valueOf(characters);
    }

    /**
     * Return a semi-speakable syllable, 2 or 3 letters.
     *
     * @return
     */
    public String syllable() {
        String result = "";
        TextOptions options = new TextOptions();
        options.setCasing(TextOptions.Casing.lower);
        options.setPoolType(TextOptions.PoolType.custom);
        try {
            int length = natural(MIN_CHAR_FOR_SYLLABLE, MAX_CHAR_FOR_SYLLABLE);
            char chr = 0;
            String pool;
            for (int i = 0; i < length; i++) {
                if (i == 0) {
                    pool = Character.CONSONANTS_AND_VOWELS;
                } else if (Character.CONSONANTS_GROUP.indexOf(chr) == -1) {
                    pool = Character.CONSONANTS_GROUP;
                } else {
                    pool = Character.VOWELS;
                }
                options.setPool(pool);
                chr = character(options);
                result += chr;
            }
        } catch (ChanceException e) {
            // it's never throw
        }
        return result;
    }

    /**
     * Return a semi-pronounceable random (nonsense) array of words.
     *
     * @param length
     * @return
     */
    private String[] words(int length) {
        String[] words = new String[length];
        for (int i = 0; i < length; i++) {
            words[i] = word();
        }
        return words;
    }

    /**
     * Return a semi-pronounceable random (nonsense) word.
     *
     * @param numSyllables
     * @param capitalize
     * @return
     */
    public String word(int numSyllables, boolean capitalize) {
        String result = "";
        for (int i = 0; i < numSyllables; i++) {
            result += syllable();
        }
        return (capitalize ? StringUtils.capitalize(result) : result);
    }

    /**
     * Return a semi-pronounceable random (nonsense) word.
     *
     * @param numSyllables
     * @return
     */
    public String word(int numSyllables) {
        return word(numSyllables, false);
    }

    /**
     * Return a semi-pronounceable random (nonsense) word.
     *
     * @return
     */
    public String word() {
        String result = "";
        try {
            int natural = natural(MIN_SYLLABLES_FOR_WORD, MAX_SYLLABLES_FOR_WORD);
            result = word(natural);
        } catch (Exception e) {
            // it's never throw
        }
        return result;
    }

    /**
     * Return a random array of sentences populated by semi-pronounceable random
     * (nonsense) words.
     *
     * @param length
     * @return
     * @throws ChanceException
     */
    private String[] sentences(int length) throws ChanceException {
        String[] sentences = new String[length];
        for (int i = 0; i < length; i++) {
            sentences[i] = sentence();
        }
        return sentences;
    }

    /**
     * Return a random sentence populated by semi-pronounceable random
     * (nonsense) words.
     *
     * @param numWords
     * @return
     * @throws ChanceException
     */
    public String sentence(int numWords) throws ChanceException {
        String[] words = words(numWords);
        String join = StringUtils.join(words, " ");
        String result = StringUtils.capitalize(join);
        TextOptions options = new TextOptions();
        options.setPoolType(TextOptions.PoolType.custom);
        options.setPool(Character.PUNCTUATION);
        return result + character(options);
    }

    /**
     * Return a random sentence populated by semi-pronounceable random
     * (nonsense) words.
     *
     * @return
     * @throws ChanceException
     */
    public String sentence() throws ChanceException {
        int numWords = natural(MIN_WORDS_FOR_SENTENCE, MAX_WORDS_FOR_SENTENCE);
        return sentence(numWords);
    }

    /**
     * Return a random paragraph generated from sentences populated by
     * semi-pronounceable random (nonsense) words.
     *
     * @param numSentences
     * @return
     * @throws ChanceException
     */
    public String paragraph(int numSentences) throws ChanceException {
        String[] sentences = sentences(numSentences);
        return StringUtils.join(sentences, " ");
    }

    /**
     * Return a random paragraph generated from sentences populated by
     * semi-pronounceable random (nonsense) words.
     *
     * @return
     * @throws ChanceException
     */
    public String paragraph() throws ChanceException {
        int numSentences = natural(MIN_SENTENCES_FOR_PARAGRAPH, MAX_SENTENCES_FOR_PARAGRAPH);
        return paragraph(numSentences);
    }

    /**
     * Return a random valid Brazilian CPF.
     *
     * @return A random CPF
     */
    public long cpf() {
        int[] firstNineDigits = new int[9];
        StringBuilder numberRepresentation = new StringBuilder();
        try {
            int length = firstNineDigits.length;
            for (int i = 0; i < length; i++) {
                firstNineDigits[i] = natural(9);
            }

            int d1 = Cpf.calculateVerifyingDigitOne(firstNineDigits);
            int d2 = Cpf.calculateVerifyingDigitTwo(firstNineDigits, d1);

            for (int i = 0; i < length; i++) {
                numberRepresentation.append(firstNineDigits[i]);
            }
            numberRepresentation.append(d1);
            numberRepresentation.append(d2);
        } catch (ChanceException e) {
            // it's never throw
        }
        return Long.parseLong(numberRepresentation.toString());
    }

    /**
     * Return a random valid Brazilian CPF, either unmasked (00000000000) or
     * masked (000.000.000-00).
     *
     * @param options
     * @return A random valid CPF
     */
    public String cpfAsText(CpfOptions options) {
        long cpf = cpf();
        String unmasked = new DecimalFormat("00000000000").format(cpf);
        switch (options) {
            case masked:
                return Cpf.format(unmasked);
            case unmasked:
                return unmasked;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Return a random valid masked Brazilian CPF.
     *
     * @return A random valid masked CPF
     */
    public String cpfAsText() {
        return cpfAsText(CpfOptions.masked);
    }

    /**
     * Return a random valid Brazilian CNPJ.
     *
     * @return A random CNPJ
     */
    public long cnpj() {
        int[] firstEightDigits = new int[8];
        StringBuilder numberRepresentation = new StringBuilder();
        try {
            int length = firstEightDigits.length;
            for (int i = 0; i < length; i++) {
                firstEightDigits[i] = natural(9);
            }

            int d1 = Cnpj.calculateVerifyingDigitOne(firstEightDigits);
            int d2 = Cnpj.calculateVerifyingDigitTwo(firstEightDigits, d1);

            for (int i = 0; i < length; i++) {
                numberRepresentation.append(firstEightDigits[i]);
            }
            numberRepresentation.append("0001");
            numberRepresentation.append(d1);
            numberRepresentation.append(d2);
        } catch (ChanceException e) {
            // it's never throw
        }
        return Long.parseLong(numberRepresentation.toString());
    }

    /**
     * Return a random valid Brazilian CNPJ, either unmasked (00000000000000) or
     * masked (00.000.000/0000-00).
     *
     * @param options
     * @return A random CNPJ
     */
    public String cnpjAsText(CnpjOptions options) {
        long cnpj = cnpj();
        String unmasked = new DecimalFormat("00000000000000").format(cnpj);
        switch (options) {
            case unmasked:
                return unmasked;
            case masked:
                return Cnpj.format(unmasked);
            default:
                throw new AssertionError();
        }
    }

    /**
     * Return a random valid masked Brazilian CNPJ.
     *
     * @return A random CNPJ
     */
    public String cnpjAsText() {
        return cnpjAsText(CnpjOptions.masked);
    }

    /**
     * Return a random gender, either Male or Female.
     *
     * @return A random gender
     */
    public Gender gender() {
        Gender[] values = Gender.values();
        int length = values.length;
        int natural = 0;
        try {
            natural = natural(length - 1);
        } catch (ChanceException e) {
            // it's never throw
        }
        return values[natural];
    }

    /**
     * Return a random Nationality.
     *
     * @return A random nationality
     */
    public Nationality nationality() {
        Nationality[] values = Nationality.values();
        int length = values.length;
        int natural = 0;
        try {
            natural = natural(length - 1);
        } catch (ChanceException e) {
            // it's never throw
        }
        return values[natural];
    }

    /**
     * Return a random Prefix or Suffix name option.
     *
     * @return A random Prefix or Suffix option
     */
    public PrefixSuffixOptions prefixSuffixOptions() {
        PrefixSuffixOptions[] values = PrefixSuffixOptions.values();
        int length = values.length;
        int natural = 0;
        try {
            natural = natural(length - 1);
        } catch (ChanceException e) {
            // it's never throw
        }
        return values[natural];
    }

    /**
     * Return a random gender, either Male or Female as plain text.
     *
     * @return A random gender
     */
    public String genderAsText() {
        return gender().name();
    }

    /**
     * Generate a random first name, specifying a gender and a nationality.
     *
     * @param gender Either Male or Female
     * @param nationality A nationality
     * @return A random first name
     */
    public String firstName(Gender gender, Nationality nationality) {
        String[] names = FirstName.getFirstNameList(gender, nationality);
        int natural = 0;
        try {
            natural = natural(names.length - 1);
        } catch (ChanceException e) {
            // it's never throw
        }
        return names[natural];
    }

    /**
     * Generate a random first name, specifying a gender.
     *
     * @param gender Either Male or Female
     * @return A random first name
     */
    public String firstName(Gender gender) {
        Nationality nacionality = nationality();
        return firstName(gender, nacionality);
    }

    /**
     * Generate a random first name, specifying a nationality.
     *
     * @param nationality A nationality
     * @return A random first name
     */
    public String firstName(Nationality nationality) {
        Gender gender = gender();
        return firstName(gender, nationality);
    }

    /**
     * Generate a random first name.
     *
     * @return A random first name
     */
    public String firstName() {
        Gender gender = gender();
        Nationality nacionality = nationality();
        return firstName(gender, nacionality);
    }

    /**
     * Generate a random last name, specifying a nationality.
     *
     * @param nationality A nationality
     * @return A random last name
     */
    public String lastName(Nationality nationality) {
        String[] names = LastName.getLastNameList(nationality);
        int natural = 0;
        try {
            natural = natural(names.length - 1);
        } catch (ChanceException e) {
            // it's never throw
        }
        return names[natural];
    }

    /**
     * Generate a random last name.
     *
     * @return A random last name
     */
    public String lastName() {
        Nationality nacionality = nationality();
        return lastName(nacionality);
    }

    /**
     * Generate a random name prefix, specifying a gender.
     *
     * @param gender Either Male or Female
     * @param options Either full or abbreviated
     * @return A random name prefix
     */
    public String namePrefix(Gender gender, PrefixSuffixOptions options) {
        String[] list = NamePrefix.getNamePrefixList(gender, options);
        int natural = 0;
        try {
            natural = natural(list.length - 1);
        } catch (ChanceException e) {
            // it's never throw
        }
        return list[natural];
    }

    /**
     * Generate a random name prefix.
     *
     * @param options Either full or abbreviated
     * @return A random name prefix
     */
    public String namePrefix(PrefixSuffixOptions options) {
        Gender gender = gender();
        return namePrefix(gender, options);
    }

    /**
     * Generate a random name prefix.
     *
     * @return A random name prefix
     */
    public String namePrefix() {
        PrefixSuffixOptions option = prefixSuffixOptions();
        return namePrefix(option);
    }

    /**
     * Generate a random name, especifying a gender, a nationality and options.
     *
     * @param gender Either Male or Female
     * @param nationality A nationality
     * @param nameOptions Name Options
     * @param psOptions Prefix options
     * @return A random name
     */
    public String name(Gender gender, Nationality nationality,
            NameOptions nameOptions, PrefixSuffixOptions psOptions) {
        String name;
        String first = firstName(gender, nationality);
        String last = lastName(nationality);
        String middle = " ";
        if (nameOptions.isMiddle()) {
            middle = " " + firstName(gender, nationality) + " ";
        } else if (nameOptions.isMiddleInitial()) {
            TextOptions textOptions = new TextOptions();
            textOptions.setCasing(TextOptions.Casing.upper);
            textOptions.setPoolType(TextOptions.PoolType.alpha);
            try {
                middle = " " + String.valueOf(character(textOptions)) + ". ";
            } catch (ChanceException e) {
                // it's never throw
            }
        }

        name = first + middle + last;
        if (nameOptions.isPrefix()) {
            name = namePrefix(gender, psOptions) + " " + name;
        }

        return name;
    }

    /**
     * Generate a random name, especifying a nationality and options.
     *
     * @param nationality A nationality
     * @param nameOptions Name Options
     * @param psOptions Prefix options
     * @return A random name
     */
    public String name(Nationality nationality,
            NameOptions nameOptions, PrefixSuffixOptions psOptions) {
        Gender gender = gender();
        return name(gender, nationality, nameOptions, psOptions);
    }

    /**
     * Generate a random name.
     *
     * @param nameOptions Name Options
     * @param psOptions Prefix options
     * @return A random name
     */
    public String name(NameOptions nameOptions, PrefixSuffixOptions psOptions) {
        Nationality nationality = nationality();
        return name(nationality, nameOptions, psOptions);
    }

    /**
     * Generate a random name.
     *
     * @return A random name
     */
    public String name() {
        NameOptions nameOptions = new NameOptions();
        return name(nameOptions, null);
    }

    /**
     * Generate a random social security number.
     *
     * @param options size (four or nine) and dash options
     * @return a random social security number
     * @throws ChanceException
     */
    public String ssn(SsnOptions options) throws ChanceException {
        TextOptions textOptions = new TextOptions();
        textOptions.setPoolType(TextOptions.PoolType.numeric);
        String ssn;
        switch (options.getLength()) {
            case four:
                ssn = string(textOptions, 4);
                break;
            case nine:
                ssn = string(textOptions, 3) + string(textOptions, 2)
                        + string(textOptions, 4);
                break;
            default:
                throw new AssertionError();
        }
        return options.isDashes() ? Ssn.format(ssn) : ssn;
    }

    /**
     * Generate a random social security number.
     *
     * @param length either four or nine
     * @return a random social security number
     * @throws ChanceException
     */
    public long ssn(SsnOptions.Length length) throws ChanceException {
        SsnOptions options = new SsnOptions();
        options.setLength(length);
        options.setDashes(false);
        String ssn = ssn(options);
        return Long.parseLong(ssn);
    }

    /**
     * Generate a random social security number.
     *
     * @return a random social security number
     * @throws ChanceException
     */
    public String ssn() throws ChanceException {
        SsnOptions options = new SsnOptions();
        options.setLength(SsnOptions.Length.nine);
        options.setDashes(true);
        return ssn(options);
    }

    /**
     * Return a random AgeOptions
     *
     * @return a random AgeOptions
     */
    public AgeOptions ageOptions() {
        AgeOptions[] values = AgeOptions.values();
        int natural = 0;
        try {
            natural = natural(values.length - 1);
        } catch (ChanceException e) {
            // it's never throw
        }
        return values[natural];
    }

    /**
     * Generate a random age, based on AgeOptions.
     *
     * @param options Age options
     * @return A random age
     */
    public int age(AgeOptions options) {
        if (options == null) {
            options = AgeOptions.any;
        }
        int natural = 0;
        try {
            natural = natural(options.getMin(), options.getMax());
        } catch (ChanceException e) {
            // it's never throw
        }
        return natural;
    }

    /**
     * Generate a random age.
     *
     * @return A random age
     */
    public int age() {
        return age(AgeOptions.any);
    }

    /**
     * Generate a random birthday.
     *
     * @param options Age options
     * @return A random birthday
     * @throws ChanceException
     */
    private DateTime birthdayDateTime(AgeOptions options) throws ChanceException {
        int age = age(options);
        int currentYear = DateTime.now().getYear();
        return dateTimeTimestamp(currentYear - age);
    }

    /**
     * Generate a random birthday.
     * <pre>
     * chance.birthday(AgeOptions.adult);
     * => Mon Jul 11 21:25:15 BRT 1980
     * </pre>
     *
     * @param options Age options
     * @return A random birthday
     * @throws ChanceException
     */
    public Date birthday(AgeOptions options) throws ChanceException {
        return birthdayDateTime(options).toDate();
    }

    /**
     * Generate a random birthday.
     * <pre>
     * chance.birthday();
     * => Mon Jul 11 21:25:15 BRT 1980
     * </pre>
     *
     * @return A random birthday
     * @throws ChanceException
     */
    public Date birthday() throws ChanceException {
        return birthday(ageOptions());
    }

    /**
     * Generate a random birthday.
     * <pre>
     * chance.birthdayAsText(AgeOptions.adult, "dd/MM/yyyy hh:mm:ss", new Locale("pt", "BR"));
     * => 06/11/1987 10:27:26
     * </pre>
     *
     * @param options Age options
     * @param pattern Style of the date
     * @param locale Custom locale
     * @return A random birthday
     * @throws ChanceException
     */
    public String birthdayAsText(AgeOptions options, String pattern, Locale locale)
            throws ChanceException {
        DateTime birthday = birthdayDateTime(options);
        return birthday.toString(pattern, locale);
    }

    /**
     * Generate a random birthday.
     * <pre>
     * chance.birthdayAsText("dd/MM/yyyy hh:mm:ss", new Locale("pt", "BR"));
     * => 06/11/1987 10:27:26
     * </pre>
     *
     * @param pattern Style of the date
     * @param locale Custom locale
     * @return A random birthday
     * @throws ChanceException
     */
    public String birthdayAsText(String pattern, Locale locale)
            throws ChanceException {
        return birthdayAsText(ageOptions(), pattern, locale);
    }

    /**
     * Generate a random birthday.
     * <pre>
     * chance.birthdayAsText("dd/MM/yyyy hh:mm:ss");
     * => 06/11/1987 10:27:26
     * </pre>
     *
     * @param pattern Style of the date
     * @return A random birthday
     * @throws ChanceException
     */
    public String birthdayAsText(String pattern)
            throws ChanceException {
        return birthdayAsText(pattern, Locale.getDefault());
    }

    /**
     * Generate a random natural, used only internally with safe max.
     *
     * @param max Max value to choose from
     * @return
     */
    private int secureNatural(int max) {
        return secureNatural(0, max);
    }

    private int secureNatural(int min, int max) {
        int natural = 0;
        try {
            natural = natural(min, max);
        } catch (ChanceException e) {
            // it's never throw
        }
        return natural;
    }

    /**
     * Generate a random second.
     *
     * @return A random second
     */
    public int second() {
        return secureNatural(Second.MAX);
    }

    /**
     * Generate a random millisecond.
     *
     * @return A random millisecond
     */
    public int millisecond() {
        return secureNatural(Millisecond.MAX);
    }

    /**
     * Generate a random minute.
     *
     * @return A random minute
     */
    public int minute() {
        return secureNatural(Minute.MAX);
    }

    /**
     * Generate a random hour.
     *
     * @param hour 24 or 12 style
     * @return A random hour
     */
    public int hour(Hour hour) {
        if (hour == null) {
            hour = Hour.twenty_four;
        }
        return secureNatural(hour.getMin(), hour.getMax());
    }

    /**
     * Generate a random hour, with 24 style.
     *
     * @return A random hour
     */
    public int hour() {
        return hour(Hour.twenty_four);
    }

    /**
     * Generate a random month.
     * <pre>
     * chance.month();
     * => 5
     * </pre>
     *
     * @return A random month
     */
    public int month() {
        return secureNatural(Month.MIN, Month.MAX);
    }

    /**
     * Generate a random month as text, using default Locale.
     * <pre>
     * chance.month(Month.MonthOptions.numeric);
     * => 05
     * chance.month(Month.MonthOptions.shortName);
     * => Oct
     * chance.month(Month.MonthOptions.fullName);
     * => October
     * </pre>
     *
     * @param options Can be numeric short or full
     * @return A random month
     */
    public String monthAsText(Month.MonthOptions options) {
        return monthAsText(options, Locale.getDefault());
    }

    /**
     * Generate a random month as text.
     * <pre>
     * chance.month(Month.MonthOptions.fullName, new Locale("pt", "BR"));
     * => Outubro
     * </pre>
     *
     * @param options Can be numeric short or full
     * @param locale Custom locale
     * @return A random month
     */
    public String monthAsText(Month.MonthOptions options, Locale locale) {
        int month = month();
        return Month.monthName(month, options, locale);
    }

    /**
     * Generate a random month as text, using full name option.
     * <pre>
     * chance.month(new Locale("pt", "BR"));
     * => Outubro
     * </pre>
     *
     * @param locale Custom locale
     * @return A random month
     */
    public String monthAsText(Locale locale) {
        return monthAsText(Month.MonthOptions.fullName, locale);
    }

    /**
     * Generate a random year.
     * <pre>
     * chance.year(1988,  2016);
     * => 1994
     * </pre>
     *
     * @param min Minimum value to choose from
     * @param max Maximum value to choose from
     * @return A random year
     * @throws ChanceException
     */
    public int year(int min, int max) throws ChanceException {
        return natural(min, max);
    }

    /**
     * Generate a random year, within the current year and 100 years from now.
     * <pre>
     * chance.year();
     * => 2026
     * </pre>
     *
     * @return A random year
     * @throws ChanceException
     */
    public int year() throws ChanceException {
        int currentYear = DateTime.now().getYear();
        return year(currentYear, currentYear + 100);
    }

    /**
     * Generate a random datetime, between min and max.
     *
     * @param min Minimum value to choose from
     * @param max Maximum value to choose from
     * @return A random datetime
     * @throws ChanceException
     */
    private DateTime dateTime(long min, long max) throws ChanceException {
        if (min < 1) {
            throw new ChanceException("Min value must be greater than zero.");
        }
        long value = getLong(min, max);
        return new DateTime(value);
    }

    /**
     * Generate a random date, between min and max.
     *
     * @param min Minimum value to choose from
     * @param max Maximum value to choose from
     * @return A random date
     * @throws ChanceException
     */
    private Date date(long min, long max) throws ChanceException {
        return dateTime(min, max).toDate();
    }

    /**
     * Generate a random date, between min and max.
     * <pre>
     * chance.date(date1, date2);
     * => Fri Mar 18 00:00:00 BRT 1994
     * </pre>
     *
     * @param min Minimum value to choose from
     * @param max Maximum value to choose from
     * @return A random date
     * @throws ChanceException
     */
    public Date date(Date min, Date max) throws ChanceException {
        if (min == null || max == null) {
            throw new ChanceException("Min/max cannot be null.");
        }
        return date(min.getTime(), max.getTime());
    }

    /**
     * Generate a random date, between min and max.
     * <pre>
     * chance.date();
     * => Thu Aug 01 13:11:48 BRT 2318
     * </pre>
     *
     * @return A random date
     * @throws ChanceException
     * @see #timestamp()
     */
    public Date date() throws ChanceException {
        return date(1, DateTime.now().getMillis() * 10);
    }

    /**
     * Generate a random datetime, limited to a year.
     *
     * @param year Year of the date
     * @param hour Hour of the date
     * @param minute Minute of the date
     * @return A random date with time
     * @throws ChanceException
     */
    private DateTime dateTime(int year, int hour, int minute, int second)
            throws ChanceException {
        int month = month();
        // https://github.com/JodaOrg/joda-time/issues/22
        int maximumValue;
        if (month == DateTimeConstants.FEBRUARY) {
            maximumValue = 28;
        } else {
            MonthDay monthDay = new MonthDay(month, 1);
            maximumValue = monthDay.dayOfMonth().getMaximumValue();
        }

        int day = natural(1, maximumValue);
        return new DateTime(year, month, day, hour, minute, second);
    }

    /**
     * Generate a random datetime, limited to a year.
     *
     * @param year Year of the date
     * @return A random date
     * @throws ChanceException
     */
    private DateTime dateTime(int year) throws ChanceException {
        return dateTime(year, 0, 0, 0);
    }

    /**
     * Generate a random date, limited to a year.
     * <pre>
     * chance.date(1994);
     * => Fri Mar 18 00:00:00 BRT 1994
     * </pre>
     *
     * @param year Year of the date
     * @return A random date
     * @throws ChanceException
     */
    public Date date(int year) throws ChanceException {
        return dateTime(year).toDate();
    }

    /**
     * Generate a random date as text, between min and max.
     * <pre>
     * chance.date(date1, date2, "dd/MM/yyyy", new Locale("pt", "BR"));
     * => 17/07/1977
     * </pre>
     *
     * @param min Minimum value to choose from
     * @param max Maximum value to choose from
     * @param pattern Style of the date
     * @param locale Custom locale
     * @return A random date
     * @throws ChanceException
     */
    public String dateAsText(Date min, Date max, String pattern, Locale locale)
            throws ChanceException {
        DateTime dateTime = dateTime(min.getTime(), max.getTime());
        return dateTime.toString(pattern, locale);
    }

    /**
     * Generate a random date as text, between min and max using default Locale.
     * <pre>
     * chance.date(date1, date2, "dd/MM/yyyy");
     * => 17/07/1977
     * </pre>
     *
     * @param min Minimum value to choose from
     * @param max Maximum value to choose from
     * @param pattern Style of the date
     * @return A random date
     * @throws ChanceException
     */
    public String dateAsText(Date min, Date max, String pattern)
            throws ChanceException {
        return dateAsText(min, max, pattern, Locale.getDefault());
    }

    /**
     * Generate a random date, limited to a year.
     * <pre>
     * chance.date(1994, "dd/MM/yyyy", new Locale("pt", "BR"));
     * => 19/10/1994
     * </pre>
     *
     * @param year Year of the date
     * @param pattern Style of the date
     * @param locale Custom locale
     * @return A random date
     * @throws ChanceException
     */
    public String dateAsText(int year, String pattern, Locale locale)
            throws ChanceException {
        DateTime dateTime = dateTime(year);
        return dateTime.toString(pattern, locale);
    }

    /**
     * Generate a random date, limited to a year using default Locale.
     * <pre>
     * chance.date(1994, "dd/MM/yyyy");
     * => 19/10/1994
     * </pre>
     *
     * @param year Year of the date
     * @param pattern Style of the date
     * @return A random date
     * @throws ChanceException
     */
    public String dateAsText(int year, String pattern)
            throws ChanceException {
        return dateAsText(year, pattern, Locale.getDefault());
    }

    /**
     * Generate a random date with random time.
     *
     * @param year Year of the timestamp
     * @return A random date with time
     * @throws ChanceException
     */
    private DateTime dateTimeTimestamp(int year) throws ChanceException {
        return dateTime(year, hour(), minute(), second());
    }

    /**
     * Generate a random date with random time.
     * <pre>
     * chance.timestamp(1994);
     * => Mon Jul 11 21:25:06 BRT 1994
     * </pre>
     *
     * @param year Year of the timestamp
     * @return A random date with time
     * @throws ChanceException
     */
    public Date timestamp(int year) throws ChanceException {
        return dateTimeTimestamp(year).toDate();
    }

    /**
     * Generate a random date with random hour and minute.
     * <pre>
     * chance.timestamp(1994);
     * => Mon Jul 11 21:25:15 BRT 1994
     * </pre>
     *
     * @return A random date with time
     * @throws ChanceException
     * @see #date()
     */
    public Date timestamp() throws ChanceException {
        return timestamp(year());
    }

    /**
     * Generate a random date with random hour and minute.
     * <pre>
     * chance.timestamp(1994, "dd/MM/yyyy hh:mm:ss", new Locale("pt", "BR"));
     * => 06/11/1994 10:27:26
     * </pre>
     *
     * @param year Year of the timestamp
     * @param pattern Style of the date
     * @param locale Custom locale
     * @return A random date with time
     * @throws ChanceException
     */
    public String timestampAsText(int year, String pattern, Locale locale)
            throws ChanceException {
        DateTime timestamp = dateTimeTimestamp(year);
        return timestamp.toString(pattern, locale);
    }

    /**
     * Generate a random date with random hour and minute.
     * <pre>
     * chance.timestamp(1994, "dd/MM/yyyy hh:mm:ss");
     * => 06/11/1994 10:27:26
     * </pre>
     *
     * @param year Year of the timestamp
     * @param pattern Style of the date
     * @return A random date with time
     * @throws ChanceException
     */
    public String timestampAsText(int year, String pattern)
            throws ChanceException {
        return timestampAsText(year, pattern, Locale.getDefault());
    }
}
