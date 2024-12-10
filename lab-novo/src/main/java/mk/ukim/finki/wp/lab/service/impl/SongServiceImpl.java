package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.AlbumDoesNotExistException;
import mk.ukim.finki.wp.lab.model.exceptions.SongDoesNotExistException;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepositoryJpa;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepositoryJpa songRepository;
    private final AlbumRepositoryJpa albumRepository;

    public SongServiceImpl(SongRepositoryJpa songRepository, AlbumRepositoryJpa albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Song addArtistToSong(Artist artist, Song song) {
        List<Artist> artists = song.getPerformers();
        artists.add(artist);
        song.setPerformers(artists);
        return songRepository.save(song);
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findById(Long.valueOf(trackId)).orElseThrow();
    }

    @Override
    public Song findById(Long id) throws SongDoesNotExistException {
        return songRepository.findById(id).orElseThrow(() -> new SongDoesNotExistException(id));
    }

    @Override
    public void deleteById(Long id){
        songRepository.deleteById(id);
    }

    @Override
    public void addSong(String title, String trackId, String genre, int releaseYear, Long albumId) throws AlbumDoesNotExistException {
        Album album = albumRepository.findById(albumId).orElseThrow(() -> new AlbumDoesNotExistException(albumId));
        Song song = new Song(title, trackId, genre, releaseYear, new ArrayList<>(), album);
        songRepository.save(song);
    }

    @Override
    public void editSong(Long songId, String title, String trackId, String genre, int releaseYear, Long albumId) throws SongDoesNotExistException, AlbumDoesNotExistException {
        Album album = albumRepository.findById(albumId).orElseThrow(() -> new AlbumDoesNotExistException(albumId));
        Song song = songRepository.findById(songId).orElseThrow(() -> new SongDoesNotExistException(songId));
        song.setTitle(title);
        song.setTrackId(trackId);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(album);
        songRepository.save(song);
    }

    @Override
    public List<Song> findByAlbumId(Long albumId) {
        return songRepository.findAllByAlbum_Id(albumId);
    }
}
