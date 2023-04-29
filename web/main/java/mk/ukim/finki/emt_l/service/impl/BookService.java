package mk.ukim.finki.emt_l.service.impl;


import mk.ukim.finki.emt_l.model.Author;
import mk.ukim.finki.emt_l.model.Book;
import mk.ukim.finki.emt_l.model.dto.BookDTO;
import mk.ukim.finki.emt_l.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt_l.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt_l.repository.BookRepository;
import mk.ukim.finki.emt_l.service.BookServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements BookServiceInterface {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    //izlestaj
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    //najdi po id
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    //zacuvaj saveHere
    public Book saveHere(BookDTO book, Book novaK) {
        novaK.setName(book.getName());
        novaK.setCategory(book.getCategory());
        Author a = authorService.findById(book.getAuthor()).orElseThrow(() -> new AuthorNotFoundException(book.getAuthor()));
        novaK.setAuthor(a);
        novaK.setAvailableCopies(book.getAvailableCopies());
        return bookRepository.save(novaK);
    }

    //dodadi kniga
    public Book addBook(BookDTO book) {
        Book novaK = new Book();
        return this.saveHere(book, novaK);
    }
    //editiraj

    public Book editBook(Long id, BookDTO book) {
        //najdi ja knigata spored id
        Book foundK = this.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return this.saveHere(book, foundK);

    }

    //izbrisi
    public void deleteBook(Long id) {
        this.bookRepository.deleteById(id);
    }

    //namali broj na knigi spored kniga najdena spored id
    public void markBookAsTaken(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
    }
}


