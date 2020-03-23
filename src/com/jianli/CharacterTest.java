package com.jianli;

public class CharacterTest {
    public static void main(String[] args) {
        char a = 'x';
        Character b = new Character('a');

        Character c = 'b';
        System.out.println("She said \"Hello\" to me");

        System.out.println(Character.isLetter(b));
        System.out.println(Character.isDigit(b));
        System.out.println(Character.isWhitespace(b));
        System.out.println(Character.isUpperCase(b));
        System.out.println(Character.isLowerCase(b));
        System.out.println(Character.toLowerCase(b));
        System.out.println(Character.toUpperCase(b));
        System.out.println(b.toString());
    }
}
