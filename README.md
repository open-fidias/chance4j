# Chance4j

**Chance4j** - Random generator helper for Java.

[![Build Status](https://travis-ci.org/open-fidias/chance4j.svg?branch=master)](https://travis-ci.org/open-fidias/chance4j)
[![Javadocs](http://www.javadoc.io/badge/com.github.open-fidias/chance4j.svg)](http://www.javadoc.io/doc/com.github.open-fidias/chance4j)
[![Coverage Status](https://coveralls.io/repos/github/open-fidias/chance4j/badge.svg?branch=coveralls-plugin-setup)](https://coveralls.io/github/open-fidias/chance4j?branch=coveralls-plugin-setup)

Homepage: <https://github.com/open-fidias/chance4j>

Chance4j is a minimalist generator of random strings, numbers, etc. to
help reduce some monotony particularly while writing automated tests or
anywhere else you need anything random.

Based on the <http://chancejs.com> by [Victor Quinn](https://github.com/victorquinn) and [contributors](https://github.com/chancejs/chancejs/graphs/contributors).

The aim is to have the same api from chancejs.

## Installation

Add the dependency in your `pom.xml` file:

~~~xml
<dependency>
    <groupId>com.github.open-fidias</groupId>
    <artifactId>chance4j</artifactId>
    <version>1.0.2</version>
</dependency>
~~~

Or manually download the jar file from <https://github.com/open-fidias/chance4j/releases>.

## Usage

All you need to do is create an instance of `Chance` and call the methods. Example:

~~~java
Chance chance = new Chance();

// basics
int value1 = chance.integer();
long value2 = chance.getLong();
int natural = chance.natural();
boolean choice = chance.bool();

// text api
String word = chance.word();
String sentence = chance.sentence();
String paragraph = chance.paragraph();

// person api
int age = chance.age();
String birthday = chance.birthdayAsText("yyyy-MM-dd hh:mm:ss");
String cpf = chance.cpfAsText();
String name = chance.name();
~~~

The complete API can be found at <http://www.javadoc.io/doc/com.github.open-fidias/chance4j>.
All methods are documented and have examples right in the javadoc.

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :smile:

## LICENSE

Copyright (C) 2016  Átila Camurça <camurca.home@gmail.com>
Fidias Free and Open Source Team <fidiascom@gmail.com>

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
