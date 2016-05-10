package scalebit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class AddressBook2 {
    
    public void start(String addressBookFile) throws IOException {
        addressBookSearch(Files.readAllLines(Paths.get(addressBookFile)),
                          System.console()::readLine,
                          System.console()::printf);
    }

    @FunctionalInterface
    interface PrintFunction {
        void printf(String main, Object... args);
    }

    static void addressBookSearch(List<String> entries,
                                   Supplier<String> userInputSupplier,
                                   PrintFunction printer) {

        while (true) {
            printer.printf("Enter a Name (exit to exit): ");
            String input = userInputSupplier.get();

            if (input.equals("exit")) break;
            if (input.equals("")) {
                printer.printf("Empty string is not a valid search term\n");
                continue;
            }

            List<String> found = entries.stream()
                    .filter(entry ->
                            entry.toLowerCase()
                                    .contains(input.toLowerCase())
                    ).collect(toList());

            if (found.isEmpty()) {
                printer.printf("No results found!\n");
            } else {
                printer.printf("%s results found!\n", found.size());
                found.stream().forEach(entry ->
                        printer.printf("  %s\n", entry)
                );
            }
        }

    }

    public static void main(String[] args) throws Exception {
        AddressBook2 addressBook1 = new AddressBook2();
        addressBook1.start(Stream.of(args).findFirst().orElse("addressbook.txt"));
    }
}