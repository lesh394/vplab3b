package mk.ukim.finki.wp.lab.model.exceptions;

public class ArtistDoesNotExistException extends Exception {
    public ArtistDoesNotExistException(Long id) {
        super("Artist with id " + id + " doesn't exist.");
    }
}
