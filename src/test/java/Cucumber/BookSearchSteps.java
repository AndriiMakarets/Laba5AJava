package Cucumber;

import static org.junit.Assert.assertEquals;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.Book;
import org.Library;
import org.junit.Assert;

public class BookSearchSteps {
    Library library = new Library();
    List<Book> result = new ArrayList<>();

    @Given("a book with the title {string}, written by {string}, published in {string}")
    public void addNewBook(String title, String author, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        LocalDate published = LocalDate.parse(date, formatter);
        Book book = new Book(title, author, published);
        library.addBook(book);
    }

    @When("the customer searches for books published between {string} and {string}")
    public void setSearchParameters(String year1, String year2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        Year fromY =Year.parse(year1,formatter);
        Year toY = Year.parse(year2,formatter);
        LocalDate from = fromY.atMonthDay(MonthDay.of(1,1));
        LocalDate to = toY.atMonthDay(MonthDay.of(12,31));
        result = library.findBooks(from, to);
    }

    @When("the customer took the book with the title {string}, written by {string}, published in {string}")
    public void theCustomerTookTheBook(String title, String author, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        LocalDate published = LocalDate.parse(date, formatter);
        Book book = new Book(title, author, published);
        System.out.println(book);
        System.out.println(library);
        library.removeBook(book);
        result = library.getStore();
    }

    @When("the customer searches for books by {string}")
    public void theCustomerSearchesForBooksByAuthor(String author) {
        result = library.findBooksByAuthor(author);
    }

    @When("the customer searches for book {string}")
    public void theCustomerSearchesForBookByTitle(String title) {
        result = library.findBooks(title);
    }

    @Then("{int} books should have been found")
    public void verifyAmountOfBooksFound(int booksFound) {
        assertEquals(result.size(), booksFound);
    }

    @Then("Book {int} should have the title {string}")
    public void verifyBookAtPosition(int position, String title) {
        Assert.assertEquals(result.get(position - 1).getTitle(), title);
    }



}