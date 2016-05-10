package scalebit;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

public class AddressBook2Test {

    @Test
    public void addressBookSearchFoundOne() throws Exception {

        List<String> output = new ArrayList<>();
        String person = "Fromagio Banolo";

        AddressBook2.addressBookSearch(
                Arrays.asList(person),
                userInput("Fromagio", "exit"),
                (main, args) -> output.add(String.format(main, args))
        );

        Assert.assertTrue(findInList(output, person));
    }

    @Test
    public void addressBookSearchFoundNone() throws Exception {

        List<String> output = new ArrayList<>();

        AddressBook2.addressBookSearch(
                Arrays.asList("-"),
                userInput("Lotta", "exit"),
                (main, args) -> output.add(String.format(main, args))
        );

        Assert.assertTrue(findInList(output, "No results"));
    }

    @Test
    public void addressBookSearchEmptySearchString() throws Exception {

        List<String> output = new ArrayList<>();

        AddressBook2.addressBookSearch(
                Arrays.asList("-"),
                userInput("", "exit"),
                (main, args) -> output.add(String.format(main, args))
        );

        Assert.assertTrue(findInList(output, "Empty"));
    }

    private static Supplier<String> userInput(String... input) {
        Iterator<String> userInput = Arrays.asList(input).iterator();
        return () -> userInput.next();
    }

    private static boolean findInList(List<String> haystack, String needle) {
        return haystack.stream()
                .filter(line -> line.contains(needle))
                .findFirst().map(line -> true).orElse(false);
    }

}