package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.ArtistRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DataHolder {
    private final SongRepositoryJpa songRepository;
    private final AlbumRepositoryJpa albumRepository;
    private final ArtistRepositoryJpa artistRepository;

    public static List<Artist> artists = new ArrayList<>();
    public static List<Song> songs = new ArrayList<>();
    public static List<Album> albums = new ArrayList<>();

    @Autowired
    public DataHolder(SongRepositoryJpa songRepository, AlbumRepositoryJpa albumRepository, ArtistRepositoryJpa artistRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }


    @PostConstruct
    public void init(){
        artists.add(new Artist("Axl", "Rose", "Lead singer of Guns N' Roses", new ArrayList<>()));
        artists.add(new Artist("Jon", "Bon Jovi", "Singer and frontman of Bon Jovi", new ArrayList<>()));
        artists.add(new Artist("Freddie", "Mercury", "Legendary Queen vocalist", new ArrayList<>()));
        artists.add(new Artist("Elvis", "Presley", "The King of Rock and Roll", new ArrayList<>()));
        artists.add(new Artist("David", "Bowie", "Famous for reinventing himself", new ArrayList<>()));

        Album album1 = new Album("Thriller", "Pop", "1982");
        Album album2 = new Album("Back in Black", "Rock", "1980");
        Album album3 = new Album("The Dark Side of the Moon", "Progressive Rock", "1973");
        Album album4 = new Album("Abbey Road", "Rock", "1969");
        Album album5 = new Album("Rumours", "Rock", "1977");
        albums.add(album1);
        albums.add(album2);
        albums.add(album3);
        albums.add(album4);
        albums.add(album5);

        songs.add(new Song("Sweet Child O' Mine", "T" + String.valueOf((long) (Math.random() * 1000)), "Rock", 1987, new ArrayList<>(), album1));
        songs.add(new Song("Livin' on a Prayer", "T" + String.valueOf((long) (Math.random() * 1000)), "Rock", 1986, new ArrayList<>(), album2));
        songs.add(new Song("Bohemian Rhapsody", "T" + String.valueOf((long) (Math.random() * 1000)), "Rock", 1975, new ArrayList<>(), album3));
        songs.add(new Song("Jailhouse Rock", "T" + String.valueOf((long) (Math.random() * 1000)), "Rock", 1957, new ArrayList<>(), album4));
        songs.add(new Song("Space Oddity", "T" + String.valueOf((long) (Math.random() * 1000)), "Rock", 1969, new ArrayList<>(), album5));

        artistRepository.saveAll(artists);
        albumRepository.saveAll(albums);
        songRepository.saveAll(songs);
    }
}
