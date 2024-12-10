package mk.ukim.finki.wp.lab.model.exceptions;

public class AlbumDoesNotExistException extends Exception{
    public AlbumDoesNotExistException(Long id) {
        super("Album with id "+ id+" doesn't exist.");
    }
}
