package com.sbrf.reboot.concurrentmodify;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemoveElementWithoutErrorsTest {

    @Test
    public void successConcurrentModificationException() {

        List<Integer> list = new ArrayList() {{
            add(1);
            add(2);
            add(3);
        }};

        assertThrows(ConcurrentModificationException.class, () -> {
            
            for (Integer integer : list) {
                list.remove(1);
            }
            
        });

    }

    @Test
    public void successRemoveElementWithoutErrors() {
        List<Integer> list = new ArrayList() {{
            add(1);
            add(2);
            add(3);
        }};

        assertDoesNotThrow(() -> {
            List<Integer> removeList = new ArrayList<>();

            for (Integer integer : list) {
                removeList.add(1);
            }
            list.removeAll(removeList);

        });

    }

    @Test
    public void successRemoveElementWithoutErrors2() {
        List<Integer> list = new ArrayList() {{
            add(1);
            add(2);
            add(3);
        }};

        assertDoesNotThrow(() -> {
        Iterator<Integer> integerIterator = list.iterator();
        int i = 0;
        while(integerIterator.hasNext()){
            integerIterator.next();
            if(i == 1){
                integerIterator.remove();
            }
            i++;
        }
        });

    }

}
