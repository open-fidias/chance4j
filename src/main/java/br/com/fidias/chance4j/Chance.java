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
import br.com.fidias.chance4j.time.Second;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomDataGenerator;

/**
 *
 * @author Átila Camurça camurca.home@gmail.com
 */
public class Chance {

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
        if (max <= 0) {
            throw new ChanceException("Max must be greater than zero.");
        }
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

    private float floating(Float min, Float max, int fixed) {
        throw new UnsupportedOperationException();
    }

    /**
     * Return a random floating point number.
     *
     * @param min Minimum value to choose from
     * @param max Maximum value to choose from
     * @param fixed Specify a fixed precision
     * @return A single floating point number
     * @throws ChanceException Min cannot be greater than Max.
     */
    public float floating(int min, int max, int fixed) throws ChanceException {
        int num;
        int localFixed = (int) Math.pow(10, fixed);
        int localMax;
        if (max <= -1) {
            localMax = (int) (Integer.MAX_VALUE / localFixed);
        } else {
            localMax = max;
        }
        int localMin;
        if (min <= -1) {
            localMin = -localMax;
        } else {
            localMin = min;
        }
        num = integer(localMin * localFixed, localMax * localFixed);
        float f = num * 1.0f / localFixed * 1.0f;
        BigDecimal result = new BigDecimal(f).setScale(fixed, RoundingMode.HALF_UP);
        return result.floatValue();
    }

    /**
     * Return a random floating point number.
     *
     * @return A single floating point number
     * @throws ChanceException
     */
    public float floating() throws ChanceException {
        return floating(-1, -1, 4);
    }

    /**
     * Return a random floating point number.
     *
     * @param fixed Specify a fixed precision
     * @return A single floating point number
     * @throws ChanceException
     */
    public float floating(int fixed) throws ChanceException {
        return floating(-1, -1, fixed);
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
     * Generate a random second.
     *
     * @return A random second
     */
    public int second() {
        int natural = 0;
        try {
            natural = natural(Second.MIN, Second.MAX);
        } catch (ChanceException e) {
            // it's never throw
        }
        return natural;
    }
}
