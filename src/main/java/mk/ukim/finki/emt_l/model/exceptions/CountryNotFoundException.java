package mk.ukim.finki.emt_l.model.exceptions;

public class CountryNotFoundException extends  RuntimeException {

    public  CountryNotFoundException(Long id){
        super(String.format("Country with id: %d is not found", id));
    }

}
