package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionTests {

    @Test
    void arrayTests() {

        //тест для создания массива строк и проверки, что в нем действительно находятся те строки? которые мы в него поместили
        var array = new String[]{"a", "b", "c"};

        Assertions.assertEquals(3,array.length);
        array[0]="a";
        Assertions.assertEquals("a", array[0]);

        array[0] = "d";
        Assertions.assertEquals("d", array[0]);
    }

    @Test
    void listTests(){
       // var list= List.of("a", "b", "c");  // при таком способе записи НЕЛЬЗЯ менять элементы списка
        var list=new ArrayList<>( List.of("a", "b", "c"));
        //list.add("a");
        //list.add("b");
       // list.add("c");
        Assertions.assertEquals(3,list.size());
        Assertions.assertEquals("a", list.get(0));

        list.set(0,"d");  //меняем 0 значение списка
        Assertions.assertEquals("d",list.get(0));


    }
}
