package Junit;

import org.Book;
import org.Library;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    }

    @ParameterizedTest()
    @CsvSource(value ={"1980, 2000, 2", "1930, 2000, 3", "1998, 2020, 0"} )
    void searchBooksByPublicationYear(String year1, String year2, int foundBooks){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        Year fromY =Year.parse(year1,formatter);
        Year toY = Year.parse(year2,formatter);
        LocalDate from = fromY.atMonthDay(MonthDay.of(1,1));
        LocalDate to = toY.atMonthDay(MonthDay.of(12,31));
        List<Book> result = library.findBooks(from, to);
        assertThat(result.size()== foundBooks);
    }
}
