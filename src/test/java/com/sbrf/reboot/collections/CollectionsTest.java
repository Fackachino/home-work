package com.sbrf.reboot.collections;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionsTest {


    /*
     * Задача.
     * Имеется список лучших студентов вуза.
     *
     * 1. Иванов
     * 2. Петров
     * 3. Сидоров
     *
     * В новом семестре по результатам подсчетов оценок в рейтинг на 1 место добавился новый студент - Козлов.
     * Теперь в рейтинге участвуют 4 студента.
     * (Предполагаем что в рейтинг можно попасть только получив достаточное количество балов, что бы занять 1 место).
     *
     * Вопрос.
     * Какую коллекцию из реализаций интерфейса Collection вы предпочтете для текущего хранения и использования списка студентов?
     *
     * Проинициализируйте students, добавьте в нее 4 фамилии студентов что бы тест завершился успешно.
     */
    @Test
    public void addStudentToRating() {
        List<String> students = new LinkedList<>();
        students.add("Иванов");
        students.add("Петров");
        students.add("Сидоров");
        students.add(0,"Козлов");

        assertEquals(4, students.size());
    }

    /*
     * Задача.
     * Вы коллекционируете уникальные монеты.
     * У вас имеется специальный бокс с секциями, куда вы складываете монеты в хаотичном порядке.
     *
     * Вопрос.
     * Какую коллекцию из реализаций интерфейса Collection вы предпочтете использовать для хранения монет в боксе.
     *
     * Проинициализируйте moneyBox, добавьте в нее 10 монет что бы тест завершился успешно.
     */

    @Test
    public void addMoneyToBox() {
        Set<Integer> moneyBox = new HashSet<>();
        while(moneyBox.size() != 10){
            int coin = new Random().nextInt(100);
            moneyBox.add(coin);
        }

        assertEquals(10, moneyBox.size());
    }

    /*
     * Задача.
     * Имеется книжная полка.
     * Периодически вы берете книгу для чтения, затем кладете ее на свое место.
     *
     * Вопрос.
     * Какую коллекцию из реализаций интерфейса Collection вы предпочтете использовать для хранения книг.
     *
     * Проинициализируйте bookshelf, добавьте в нее 3 книги что бы тест завершился успешно.
     */
    @Test
    public void addBookToShelf() {
        class Book {
        }

        List<Book> bookshelf = new ArrayList<>();
        for(int i = 0; i < 3; i++) bookshelf.add(new Book());

        assertEquals(3, bookshelf.size());
    }

    /*
     * Задача на 5+.
     * У вас есть несколько тетрадей.
     * Вы складываете их на столе в стопку.
     *
     * Вопрос.
     * Какую коллекцию из реализаций интерфейса Collection вы предпочтете использовать для хранения тетрадей в стопке.
     *
     * Проинициализируйте noteStack, добавьте 5 тетрадей, чтобы тест завершился успешно.
     */
    @Test
    public void addNoteToStack() {
        class Note {
        }

        List<Note> stack = new Stack<>();
        for(int i = 0; i < 5; i++) stack.add(new Note());

        assertEquals(5, stack.size());
    }


}
