package mk.ukim.finki.emt_l.web.restC;


import mk.ukim.finki.emt_l.model.Author;
import mk.ukim.finki.emt_l.model.Book;
import mk.ukim.finki.emt_l.model.Country;
import mk.ukim.finki.emt_l.model.dto.BookDTO;
import mk.ukim.finki.emt_l.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt_l.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt_l.service.impl.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@CrossOrigin
public class BookRestController {

    private  final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/listBooks")
    //izlistaj gi site knigi
    public List<Book> listAll(){
        return this.bookService.listAll();
    }
    //samo tie so id

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        return  this.bookService.findById(id).map(book -> ResponseEntity.ok().body(book)).orElseGet(()->ResponseEntity.notFound().build());
    }

    //create
    @PostMapping("/add")
    public ResponseEntity<Book> create(@RequestBody BookDTO bookDTO){
        Book novaB=this.bookService.addBook(bookDTO);
        if(novaB==null){
            return ResponseEntity.badRequest().build();
        }else {
            return ResponseEntity.ok().body(novaB);
        }
    }

    //edit
    @PutMapping("/edit/{id}")
    public  ResponseEntity<Book> update(@PathVariable Long id,@RequestBody BookDTO bookDTO){
        //najdi koja kniga sakash da ja update
        Book foundB=this.bookService.editBook(id, bookDTO);
        if(foundB==null){
            //ne e pronajdena, ne postoi
            return  ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok().body(foundB);
        }
    }
    //delete

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id) {
        //najdi sto ke brises so id
        Book najdiB = this.bookService.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if (najdiB == null) {
            return ResponseEntity.notFound().build();
        } else {
            //ako e najdena brisi
            this.bookService.deleteBook(id);
            return ResponseEntity.ok().build();
        }
    }
    @PutMapping("/markTaken/{id}")
    public ResponseEntity<Book> markBookAsTaken(@PathVariable Long id) {
        Book najdiB = this.bookService.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if (najdiB == null) {
            return ResponseEntity.notFound().build();
        } else {
            bookService.markBookAsTaken(id);
            return ResponseEntity.ok(najdiB);
        }
    }
}
