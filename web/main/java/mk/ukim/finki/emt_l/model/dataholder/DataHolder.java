package mk.ukim.finki.emt_l.model.dataholder;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt_l.model.Author;
import mk.ukim.finki.emt_l.model.Book;
import mk.ukim.finki.emt_l.model.Country;
import mk.ukim.finki.emt_l.model.enumm.Category;
import mk.ukim.finki.emt_l.repository.AuthorRepository;
import mk.ukim.finki.emt_l.repository.BookRepository;
import mk.ukim.finki.emt_l.repository.CountryRepository;
import org.hibernate.sql.ast.tree.cte.CteColumn;
import org.springframework.stereotype.Component;

@Component
public class DataHolder {
        private final AuthorRepository authorRepository;
        private final CountryRepository countryRepository;
        private final BookRepository bookRepository;

            public DataHolder(AuthorRepository authorRepository, CountryRepository countryRepository, BookRepository bookRepository) {
            this.authorRepository = authorRepository;
            this.countryRepository = countryRepository;
            this.bookRepository = bookRepository;
        }
        @PostConstruct
        public void initData(){

                //Countries
            Country macedonia = new Country("Macedonia", "Europe");
            Country czech = new Country("Czech", "Europe");
            Country russia = new Country("Russia", "Europe");
            Country germany = new Country("Germany", "Europe");
            Country uk = new Country("UK", "Europe");
            Country france = new Country("France", "Europe");
            Country poland = new Country("Poland", "Europe");
            Country ju = new Country("Jugoslavija", "Europe");

            countryRepository.save(macedonia);
            countryRepository.save(czech);
            countryRepository.save(germany);
            countryRepository.save(russia);
            countryRepository.save(uk);
            countryRepository.save(france);
            countryRepository.save(poland);
            countryRepository.save(ju);

            //Authors
            Author goranStefanovski=new Author("Goran","Stefanovski",macedonia);
            Author francKafka=new Author("Franc","Kafka",czech);
            Author leoTolstoy=new Author("Leo","Tolstoy",russia);
            Author fyodorDostoevsky=new Author("Fyodor","Dostoevsjy",russia);
            Author tomasMann=new Author("Tomas","Mann",germany);
            Author georgeOrwell=new Author("George","Orwell",uk);
            Author monteKristo=new Author("Monte","Kristo",france);
            Author vladimirNabokov=new Author("Vladimir","Nabokov",russia);
            Author mihailBulgakov=new Author("Mihail","Bulgakov",russia);
            Author ivoAndrikj=new Author("Ivo","Andrikj",ju);
            Author mesaSelimovikj=new Author("Mesa","Selimovikj",ju);
            authorRepository.save(goranStefanovski);
            authorRepository.save(francKafka);
            authorRepository.save(leoTolstoy);
            authorRepository.save(fyodorDostoevsky);
            authorRepository.save(tomasMann);
            authorRepository.save(georgeOrwell);
            authorRepository.save(monteKristo);
            authorRepository.save(vladimirNabokov);
            authorRepository.save(mihailBulgakov);
            authorRepository.save(ivoAndrikj);
            authorRepository.save(mesaSelimovikj);

            //Books
            Book divoMeso=new Book("DivoMeso",Category.NOVEL,goranStefanovski,4);
            Book process=new Book("Process", Category.CLASSICS,francKafka,22);
            Book vojnaIMir=new Book("VojnaIMir",Category.CLASSICS,leoTolstoy,5);
            Book idiot=new Book("Idiot", Category.CLASSICS,fyodorDostoevsky,7);
            Book volsebenBreg=new Book("VolsebenBreg",Category.FANTASY,tomasMann,9);
            Book m1984=new Book("m1984",Category.BIOGRAPHY,georgeOrwell,12);
            Book aleksandraDuma=new Book("AleksandraDuma",Category.CLASSICS,monteKristo,33);
            Book lolita=new Book("Lolita",Category.DRAMA,vladimirNabokov,24);
            Book masterotIMargarita=new Book("MaterotIMargarita",Category.CLASSICS,mihailBulgakov,15);
            Book mostotNaDrina=new Book("MostotNaDrina",Category.CLASSICS,ivoAndrikj,27);
            Book smrtIdervis=new Book("SmrtIDervis",Category.CLASSICS,mesaSelimovikj,44);
            bookRepository.save(divoMeso);
            bookRepository.save(process);
            bookRepository.save(vojnaIMir);
            bookRepository.save(idiot);
            bookRepository.save(volsebenBreg);
            bookRepository.save(m1984);
            bookRepository.save(aleksandraDuma);
            bookRepository.save(lolita);
            bookRepository.save(masterotIMargarita);
            bookRepository.save(mostotNaDrina);
            bookRepository.save(smrtIdervis);




        }
}
