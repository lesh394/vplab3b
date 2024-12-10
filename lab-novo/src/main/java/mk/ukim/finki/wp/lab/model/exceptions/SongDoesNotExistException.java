package mk.ukim.finki.wp.lab.model.exceptions;

public class SongDoesNotExistException extends Exception{
    public SongDoesNotExistException(Long songId) {
        super("Song with id "+ songId+" doesn't exist.");
    }
}
