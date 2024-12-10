package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.AlbumDoesNotExistException;
import mk.ukim.finki.wp.lab.model.exceptions.SongDoesNotExistException;

import java.util.List;

public interface SongService {
    List<Song> listSongs();
    Song addArtistToSong(Artist artist, Song song);
    Song findByTrackId(String trackId);
    Song findById(Long id) throws SongDoesNotExistException;
    void deleteById(Long id);
    void addSong(String title, String trackId, String genre, int releaseYear, Long albumId) throws AlbumDoesNotExistException;
    void editSong(Long songId, String title, String trackId, String genre, int releaseYear, Long albumId) throws SongDoesNotExistException, AlbumDoesNotExistException;

    List<Song> findByAlbumId(Long albumId);
}
