package mk.ukim.finki.wp.lab.repository.inmemory;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SongRepository {

    public List<Song> findAll(){
        return DataHolder.songs;
    }

    public Optional<Song> findByTrackId(String trackId){
        return DataHolder.songs.stream().filter(song -> song.getTrackId().equals(trackId)).findFirst();
    }

    public Optional<Song> findById(Long id){
        return DataHolder.songs.stream().filter(song -> song.getId().equals(id)).findFirst();
    }

    public Artist addArtistToSong(Artist artist, Song song){
        List<Artist> artists = new ArrayList<>(song.getPerformers());
        artists.add(artist);
        song.setPerformers(artists);
        return artist;
    }

    public void deleteById(Long id){
        DataHolder.songs = DataHolder.songs.stream().filter(s -> !Objects.equals(s.getId(), id)).collect(Collectors.toList());
        System.out.println(DataHolder.songs);
    }

    public void addSong(String title, String trackId, String genre, int releaseYear, Album album){
        DataHolder.songs.add(new Song(title, trackId, genre, releaseYear, new ArrayList<>(), album));
    }
}
