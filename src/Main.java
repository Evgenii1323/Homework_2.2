import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        Stream<Person> stream1 = persons.stream();
        Stream<Person> stream2 = stream1.filter(x -> x.getAge() < 18);
        long count = stream2.count();
        System.out.println("Количество несовершеннолетних " + count);

        Stream<Person> stream3 = persons.stream();
        Stream<Person> stream4 = stream3.filter(x -> x.getAge() >= 18 && x.getAge() <= 27);
        Stream<Person> stream5 = stream4.filter(x -> x.getSex() == Sex.MAN);
        Stream<String> stream6 = stream5.map(value -> value.getFamily());
        List<String> list = stream6.collect(Collectors.toList());
        System.out.println(list);

        Stream<Person> stream7 = persons.stream();
        Stream<Person> stream8 = stream7.filter(x -> x.getAge() >= 18 && x.getAge() <= 65);
        Stream<Person> stream9 = stream8.filter(x -> x.getSex() == Sex.MAN);
        Stream<Person> stream10 = stream9.filter(x -> x.getEducation() == Education.HIGHER);
        Stream<Person> stream11 = persons.stream();
        Stream<Person> stream12 = stream11.filter(x -> x.getAge() >= 18 && x.getAge() <= 60);
        Stream<Person> stream13 = stream12.filter(x -> x.getSex() == Sex.WOMAN);
        Stream<Person> stream14 = stream13.filter(x -> x.getEducation() == Education.HIGHER);
        Stream<Person> stream15 = Stream.concat(stream10, stream14);
        Stream<Person> stream16 = stream15.sorted(Comparator.comparing(Person::getFamily));
        Stream<String> stream17 = stream16.map(value -> value.getFamily());
        List<String> list1 = stream17.collect(Collectors.toList());
        System.out.println(list1);
    }
}