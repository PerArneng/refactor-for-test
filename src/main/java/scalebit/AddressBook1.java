package scalebit;

import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

public class AddressBook1 {

    public void start(String addressBookFile) throws IOException {

        List<String> entries = Files.readAllLines(Paths.get(addressBookFile));
        Console console = System.console();

        while (true) {
            console.printf("Enter a Name (exit to exit): ");
            String input = console.readLine();

            if (input.equals("exit")) break;
            if (input.equals("")) {
                console.printf("Empty string is not a valid search term\n");
                continue;
            }

            List<String> found = entries.stream()
                                    .filter(entry ->
                                            entry.toLowerCase()
                                                    .contains(input.toLowerCase())
                                    ).collect(toList());

            if (found.isEmpty()) {
                console.printf("No results found!\n");
            } else {
                console.printf("%s results found!\n", found.size());
                found.stream().forEach(entry ->
                        console.printf("  %s\n", entry)
                );
            }
        }


    }

    public static void main(String[] args) throws Exception {
        AddressBook1 addressBook1 = new AddressBook1();
        addressBook1.start(Stream.of(args).findFirst().orElse("addressbook.txt"));
    }
}