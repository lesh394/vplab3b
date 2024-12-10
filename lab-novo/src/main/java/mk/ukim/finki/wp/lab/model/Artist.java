package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
    @OneToMany
    private List<Song> songs;

    public Artist(String firstName, String lastName, String bio, List<Song> songs) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.songs = songs;
    }
}
