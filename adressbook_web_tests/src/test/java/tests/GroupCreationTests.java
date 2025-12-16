package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GroupCreationTests extends TestBase {

    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>();
        /*for (var name : List.of("", "group name")) {
            for (var header : List.of("", "group header")) {
                for (var footer : List.of("", "group footer")) {
                    result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }*/
        var xml = "";
        try (var reader = new FileReader("groups.xml");
             var breader = new BufferedReader(reader)
        ) {
            var line = breader.readLine();
            while (line != null) {
                xml = xml + line;
                line = breader.readLine();
            }
        }
        // var json = Files.readString(Paths.get("groups.json"));
        var mapper = new XmlMapper();
        var value = mapper.readValue(xml, new TypeReference<List<GroupData>>() {
        });
        result.addAll(value);
        return result;
    }

    public static Stream<GroupData> RandomGroups() {
        Supplier<GroupData> randomGroup = () ->
                new GroupData()
                        .withName(CommonFunctions.randomStringFunctionalStyle(10))
                        .withHeader(CommonFunctions.randomStringFunctionalStyle(20))
                        .withFooter(CommonFunctions.randomStringFunctionalStyle(30));
        return Stream.generate(randomGroup).limit(1);
    }

    @ParameterizedTest
    @MethodSource("RandomGroups")
    public void CanCreateGroup(GroupData group) {
        var oldGroups = app.jbdc().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.jbdc().getGroupList();
//        Comparator<GroupData> compareById = (o1, o2) -> {
//            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));//сравниваем айдишники последователно
//        };
//        newGroups.sort(compareById);
        var maxId = newGroups.get(newGroups.size() - 1).id();


        //строим список групп? которые не встречались в старом. Берем новый спискок групп,и строим поток и используем функцию фильтр.
        // Сюда мы положим функцию которая используетс для фильтрафии
        //функция которая берет элеент потока и возвращает тру или фолс
        //фунйки проверяет что элмемнт не содержится в старом списке

        var extraGroups=newGroups.stream().filter(g->!oldGroups.contains(g)).toList();
        var newId=extraGroups.get(0).id();

        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newId));
//        expectedList.sort(compareById);
        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
    }

    public static List<GroupData> negativeGroupProvider() {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData()
                        .withName("name'")
                        .withHeader("")
                        .withFooter("")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void CanNotCreateGroup(GroupData group) {
        var oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.hbm().getGroupList();
        Assertions.assertEquals(newGroups, oldGroups);
    }

}