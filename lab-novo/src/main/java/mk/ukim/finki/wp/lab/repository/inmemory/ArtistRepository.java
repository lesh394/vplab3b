package mk.ukim.finki.wp.lab.repository.inmemory;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepository {

    public List<Artist> findAll(){
        return DataHolder.artists;
    }

    public Optional<Artist> findById(Long id){
        return DataHolder.artists.stream().filter(artist -> artist.getId().equals(id)).findFirst();
    }

//    public Song addSongToArtist(Artist artist, Song song){
//        List<Song> songs = new ArrayList<>(artist.getSongs());
//        songs.add(song);
//        artist.setSongs(songs);
//        return song;
//    }
}
