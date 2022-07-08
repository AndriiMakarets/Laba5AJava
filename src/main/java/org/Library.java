package org;

import org.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Library {

    public List<Book> getStore() {
        return store;
    }

    private final List<Book> store = new ArrayList<>();

    public void addBook(final Book book) {
        store.add(book);
    }

    public List<Book> findBooks(final LocalDate from, final LocalDate to) {
        return store.stream().filter(book -> from.isBefore(book.getPublished())
                && to.isAfter(book.getPublished())).sorted(Comparator.comparing(Book::getPublished).reversed())
                .collect(Collectors.toList());
    }

    public List<Book> findBooks(String title) {
        return store.stream().filter(book -> title.equals(book.getTitle())).collect(Collectors.toList());
    }

    public List<Book> findBooksByAuthor(String author) {
        return store.stream().filter(book -> author.equals(book.getAuthor()))
                .sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
    }

    public void removeBook(final Book book){
        for(int i = 0; i< store.size(); i++){
            Book thisBook = store.get(i);
            if(thisBook.getAuthor().equals(book.getAuthor()) && thisBook.getTitle().equals(book.getTitle()) &&
                    thisBook.getPublished().toString().equals(book.getPublished().toString())){
                store.remove(i);
                break;
            }
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (Book book: store) {
            result += book.toString();
        }
        return result;
    }
}