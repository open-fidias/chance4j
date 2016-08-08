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
package br.com.fidias.chance4j.person.name.br;

import br.com.fidias.chance4j.person.Gender;
import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author atila
 */
public class BrazilianFirstName {
    
    private final static Map<Gender, String[]> NAMES = new EnumMap(Gender.class);
    
    public static String[] getNames(Gender gender) {
        if (NAMES.isEmpty()) {
            NAMES.put(Gender.Female, FEMALE_NAMES);
            NAMES.put(Gender.Male, MALE_NAMES);
        }
        return NAMES.get(gender);
    }
    
    private final static String[] FEMALE_NAMES = new String[] {
        "Agatha",
        "Alana",
        "Alice",
        "Alícia",
        "Amanda",
        "Ana",
        "Bárbara",
        "Beatriz",
        "Bianca",
        "Brenda",
        "Bruna",
        "Camila",
        "Carolina",
        "Catarina",
        "Cecília",
        "Clara",
        "Clarice",
        "Débora",
        "Eduarda",
        "Elisa",
        "Eloá",
        "Emanuelly",
        "Emilly",
        "Esther",
        "Fernanda",
        "Gabriela",
        "Giovanna",
        "Helena",
        "Heloísa",
        "Isabel",
        "Isabella",
        "Isabelly",
        "Isadora",
        "Ísis",
        "Joana",
        "Júlia",
        "Juliana",
        "Laís",
        "Lara",
        "Larissa",
        "Laura",
        "Lavínia",
        "Letícia",
        "Lívia",
        "Liz",
        "Lorena",
        "Luana",
        "Luiza",
        "Maitê",
        "Manuela",
        "Marcela",
        "Maria",
        "Mariana",
        "Marina",
        "Melissa",
        "Milena",
        "Natália",
        "Nicole",
        "Olívia",
        "Paola",
        "Pietra",
        "Rafaela",
        "Raquel",
        "Rebeca",
        "Sarah",
        "Sophia",
        "Valentina",
        "Vitória",
        "Yasmin",
    };
    
    private final static String[] MALE_NAMES = new String[] {
        "André",
        "Antônio",
        "Arthur",
        "Augusto",
        "Benjamin",
        "Bernardo",
        "Breno",
        "Brian",
        "Bruno",
        "Caio",
        "Cauã",
        "Daniel",
        "Danilo",
        "Davi",
        "Diego",
        "Diogo",
        "Eduardo",
        "Emanuel",
        "Felipe",
        "Fernando",
        "Francisco",
        "Gabriel",
        "Guilherme",
        "Gustavo",
        "Heitor",
        "Henrique",
        "Hugo",
        "Iago",
        "Ian",
        "Isaac",
        "Iuri",
        "João",
        "Joaquim",
        "Juan",
        "Kauê",
        "Leonardo",
        "Levi",
        "Lorenzo",
        "Luan",
        "Lucas",
        "Luiz",
        "Marcelo",
        "Mateus",
        "Miguel",
        "Murilo",
        "Nicolas",
        "Otávio",
        "Pedro",
        "Rafael",
        "Raul",
        "Renan",
        "Ricardo",
        "Rodrigo",
        "Samuel",
        "Tales",
        "Téo",
        "Tiago",
        "Tomas",
        "Vicente",
        "Vinicius",
        "Vitor",
    };
}