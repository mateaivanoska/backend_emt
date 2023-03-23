package mk.ukim.finki.emt_l.web.restC;

import mk.ukim.finki.emt_l.model.Author;
import mk.ukim.finki.emt_l.model.dto.AuthorDTO;
import mk.ukim.finki.emt_l.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt_l.service.impl.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
@CrossOrigin
public class AuthorRestController {
    private  final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }
    //site avtori izlistaj
    @GetMapping("/authorsList")
    public List<Author> listAuthor(){
         return  this.authorService.listAll();
    }
    //najdi spored id

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id){
        return this.authorService.findById(id).
                map(author -> ResponseEntity.ok().body(author)).orElseGet(()->ResponseEntity.notFound().build());
    }

    //dodadi    /api/author/add
    @PostMapping("/add")
    public ResponseEntity<Author> create(@RequestBody AuthorDTO authorDTO){
        Author author=this.authorService.addAuthor(authorDTO);
        //ako ne e kreiran ->bad req
        if(author==null){
            return ResponseEntity.badRequest().build();
        }else
        //ako e dodadi go vo bodito
        {
            return ResponseEntity.ok().body(author);
        }
    }

    //update
    @PutMapping("/edit/{id}")
    public  ResponseEntity<Author> update(@PathVariable Long id,@RequestBody AuthorDTO authorDTO){
        //najdi koj avtor sakash da go update
        Author foundA=this.authorService.editAuthor(id, authorDTO);
        if(foundA==null){
            //ne e pronajden, ne postoi
            return  ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok().body(foundA);
        }
    }
    //delete  IZGLEDA DEKA ZA KNIGA NEMAM NESTO DODADENO!!!!!!!!!!!
//api/author/delete/5
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Author> delete(@PathVariable Long id){
        //najdi go avtorot
        Author foundA=this.authorService.findById(id).orElseThrow(()->new AuthorNotFoundException(id));
        if(foundA==null){
            return ResponseEntity.notFound().build();
        }else {
            //go ima izbrisi go
            this.authorService.deleteAuthor(id);
            return ResponseEntity.ok(foundA);
        }
    }

}

