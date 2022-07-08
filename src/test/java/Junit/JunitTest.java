package Junit;

import org.Book;
import org.Library;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JunitTest {
   static Library library = new Library();

    @BeforeAll
    static void  setLibrary(){
        String date = "21-09-1937";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        LocalDate published = LocalDate.parse(date, formatter);
        library.addBook(new Book("The Hobbit", "J. R. R. Tolkien", published));

        date = "01-04-1980";
        published = LocalDate.parse(date, formatter);
        library.addBook(new Book("A Confederacy of Dunces", "John Kennedy Toole", published));

        date = "01-08-1996";
        published = LocalDate.parse(date, formatter);
        library.addBook(new Book("A Game of Thrones (A Song of Ice and Fire)", "George R.R. Martin", published));

        date = "29-07-1954";
        published = LocalDate.parse(date, formatter);
        library.addBook(new Book("The Lord of the Rings", "J. R. R. Tolkien", published));
    }


    @Tag("search")
    @ParameterizedTest()
    @CsvSource(value ={"1980, 2000, 2", "1930, 2000, 4", "1998, 2020, 0", "-1998, 2020, -10"} )
    void searchBooksByPublicationYear(String year1, String year2, int foundBooks){
        Assumptions.assumeTrue(Integer.parseInt(year1)>0&&Integer.parseInt(year2)<2023&& foundBooks>=0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        Year fromY =Year.parse(year1,formatter);
        Year toY = Year.parse(year2,formatter);
        LocalDate from = fromY.atMonthDay(MonthDay.of(1,1));
        LocalDate to = toY.atMonthDay(MonthDay.of(12,31));
        List<Book> result = library.findBooks(from, to);
        assertThat(result.size()== foundBooks);
    }

    @Tag("search")
    @ParameterizedTest()
    @MethodSource("generateData")
    void searchBooksByAuthor(String author, List<Book> books){
        List<Book> foundBooks = library.findBooksByAuthor(author);
        assertThat(books).isEqualToComparingFieldByFieldRecursively(foundBooks);
    }

    @org.jetbrains.annotations.NotNull
    private static Stream<Arguments> generateData() {
        String date = "21-09-1937";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        LocalDate published = LocalDate.parse(date, formatter);
        date = "29-07-1954";
        LocalDate published1 = LocalDate.parse(date, formatter);
        date = "01-08-1996";
        LocalDate published2 = LocalDate.parse(date, formatter);
        return Stream.of(
                Arguments.of("J. R. R. Tolkien", Arrays.asList(
                        new Book("The Lord of the Rings", "J. R. R. Tolkien", published1),
                        new Book("The Hobbit", "J. R. R. Tolkien",published))),
                Arguments.of("George R.R. Martin", Arrays.asList(
                        new Book("A Game of Thrones (A Song of Ice and Fire)", "George R.R. Martin",published2))),
                Arguments.of("Sir Arthur Ignatius Conan Doyle", Collections.emptyList())
        );
    }

    @Tag("take")
    @Test
    public void takeBook(){
        System.out.println( library.getStore());
        String date = "21-09-1937";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        LocalDate published = LocalDate.parse(date, formatter);
        Book book = new Book("The Hobbit", "J. R. R. Tolkien", published);
        library.removeBook(book);

        List<Book> books = new ArrayList<>();


        date = "01-04-1980";
        published = LocalDate.parse(date, formatter);
        books.add(new Book("A Confederacy of Dunces", "John Kennedy Toole", published));

        date = "01-08-1996";
        published = LocalDate.parse(date, formatter);
        books.add(new Book("A Game of Thrones (A Song of Ice and Fire)", "George R.R. Martin", published));

        date = "29-07-1954";
        published = LocalDate.parse(date, formatter);
        books.add(new Book("The Lord of the Rings", "J. R. R. Tolkien", published));

       System.out.println( library.getStore());

        assertThat(library.getStore()).isEqualToComparingFieldByFieldRecursively(books);
    }

}
