package com.sbrf.reboot.streams;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamTest {

    /*
     * Отсортируйте коллекцию integers по возрастанию. Используйте Stream.
     */
    @Test
    public void sortedListStream() {
        List<Integer> integers = Arrays.asList(6, 9, 8, 3);

        List<Integer> expectedIntegers = Arrays.asList(3, 6, 8, 9);

        List<Integer> actualIntegers = integers.stream()
                .sorted()
                .collect(Collectors.toList());

        assertEquals(expectedIntegers, actualIntegers);
    }

    /*
     * Отфильтруйте коллекцию integers.
     * В коллекции должны остаться только те числа, которые делятся без остатка на 2.  Используйте Stream.
     */
    @Test
    public void filteredListStream() {
        List<Integer> integers = Arrays.asList(6, 9, 8, 3);

        List<Integer> expectedIntegers = Arrays.asList(6, 8);

        List<Integer> actualIntegers = integers.stream()
                .filter(integer -> integer % 2 == 0)
                .sorted()
                .collect(Collectors.toList());

        assertEquals(expectedIntegers, actualIntegers);

    }

    /*
     * Отфильтруйте и отсортируйте коллекцию books.
     * Получите коллекцию, в которой будут только книги от автора "Maria", отсортированные по цене.
     * Используйте Stream.
     */
    @AllArgsConstructor
    @EqualsAndHashCode
    class Book {
        private String name;
        private String author;
        private BigDecimal price;
    }

    @Test
    public void sortedAndFilteredBooks() {
        List<Book> books = Arrays.asList(
                new Book("Trees", "Maria", new BigDecimal(900)),
                new Book("Animals", "Tom", new BigDecimal(500)),
                new Book("Cars", "John", new BigDecimal(200)),
                new Book("Birds", "Maria", new BigDecimal(100)),
                new Book("Flowers", "Tom", new BigDecimal(700))

        );
        List<Book> expectedBooks = Arrays.asList(
                new Book("Birds", "Maria", new BigDecimal(100)),
                new Book("Trees", "Maria", new BigDecimal(900))

        );

        List<Book> actualBooks = books.stream()
                .filter(book -> book.author.equals("Maria"))
                .sorted(Comparator.comparing(book -> book.price))
                .collect(Collectors.toList());

        assertEquals(expectedBooks, actualBooks);

    }

    /*
     * Получите измененную коллекцию contracts.
     * Получите коллекцию, в которой будет тот же набор строк, только у каждой строки появится префикс "M-".
     * Используйте Stream.
     */
    @Test
    public void modifiedList() {
        List<String> contracts = Arrays.asList("NCC-1-CH", "NCC-2-US", "NCC-3-NH");

        List<String> expectedContracts = Arrays.asList("M-NCC-1-CH", "M-NCC-2-US", "M-NCC-3-NH");

        List<String> actualContracts = contracts.stream()
                .map(string -> string = "M-" + string)
                .collect(Collectors.toList());

        assertEquals(expectedContracts, actualContracts);

    }

    /*
     * Получите изменненую коллекцию books
     * Получить самую дорогую книгу от автора "Tom".
     * Использовать Stream
     */
    @Test
    public void mostExpensiveBook() {
        List<Book> books = Arrays.asList(
                new Book("Trees", "Maria", new BigDecimal(900)),
                new Book("Animals", "Tom", new BigDecimal(10000)),
                new Book("Dogs", "Tom", new BigDecimal(9032)),
                new Book("Space", "Tom", new BigDecimal(213)),
                new Book("Fox", "Tom", new BigDecimal(500)),
                new Book("Lions", "Tom", new BigDecimal(4384)),
                new Book("Cars", "John", new BigDecimal(200)),
                new Book("Birds", "Maria", new BigDecimal(100)),
                new Book("Flowers", "Tom", new BigDecimal(700))

        );

        Book expectedBook = new Book("Animals", "Tom", new BigDecimal(10000));

        Book actualBook = books.stream()
                .filter(book -> book.author.equals("Tom"))
                .max(Comparator.comparing(b -> b.price)).get();

        assertEquals(expectedBook, actualBook);
    }

    /*
     * Узнать, есть ли хоть 1 книга автора "Mark"
     */
    @Test
    public void bookExist() {
        List<Book> books = Arrays.asList(
                new Book("Trees", "Maria", new BigDecimal(900)),
                new Book("Animals", "Tom", new BigDecimal(10000)),
                new Book("Dogs", "Tom", new BigDecimal(9032)),
                new Book("Space", "Tom", new BigDecimal(213)),
                new Book("Fox", "Tom", new BigDecimal(500))

        );

        boolean expectedResult = false;

        boolean actualResult = books.stream().anyMatch(book -> book.author.equals("Mark"));

        assertEquals(expectedResult, actualResult);
    }

    /*
     * Посчитать стоимость всех книг.
     */
    @Test
    public void priceSum(){
        List<Book> books = Arrays.asList(
                new Book("Trees", "Maria", new BigDecimal(100)),
                new Book("Animals", "Tom", new BigDecimal(300)),
                new Book("Dogs", "Tom", new BigDecimal(400)),
                new Book("Space", "Tom", new BigDecimal(600))
        );

        BigDecimal expectedSum = new BigDecimal(1400);

        BigDecimal actualSum = new BigDecimal(books.stream().mapToInt(book -> book.price.intValue()).sum());

        assertEquals(expectedSum, actualSum);

    }

}
