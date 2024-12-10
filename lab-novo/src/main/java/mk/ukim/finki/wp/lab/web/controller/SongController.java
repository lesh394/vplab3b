package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.exceptions.AlbumDoesNotExistException;
import mk.ukim.finki.wp.lab.model.exceptions.SongDoesNotExistException;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final AlbumService albumService;

    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, @RequestParam(defaultValue = "-1") Long album, Model model){
        if (error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("albums", albumService.findAll());

        if (album == -1) {
            model.addAttribute("songs", songService.listSongs());
        } else {
            model.addAttribute("songs", songService.findByAlbumId(album));
        }
        return "listSongs";
    }

    @RequestMapping("/add")
    public String saveSong(@RequestParam String title,
                           @RequestParam String trackId,
                           @RequestParam String genre,
                           @RequestParam int releaseYear,
                           @RequestParam Long albumId) throws AlbumDoesNotExistException {
        songService.addSong(title, trackId, genre, releaseYear, albumId);

        return "redirect:/songs";
    }

    @RequestMapping("/edit/{songId}")
    public String editSong(@PathVariable Long songId,
                           @RequestParam String title,
                           @RequestParam String trackId,
                           @RequestParam String genre,
                           @RequestParam int releaseYear,
                           @RequestParam Long albumId) throws AlbumDoesNotExistException, SongDoesNotExistException {

        songService.editSong(songId, title, trackId, genre, releaseYear, albumId);

        return "redirect:/songs";
    }

    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id){
        this.songService.deleteById(id);

        return "redirect:/songs";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditSongForm(Model model, @PathVariable Long id) {
        try {
            model.addAttribute("song", songService.findById(id));
            model.addAttribute("albums", albumService.findAll());
            return "add-song";
        }
        catch (Exception e){
            return "redirect:/songs";
        }
    }

    @GetMapping("/add-form")
    public String getAddSongPage(Model model){
        model.addAttribute("albums", albumService.findAll());
        return "add-song";
    }

    @PostMapping
    public String showArtists(@RequestParam(required = false) Long selectedSong, HttpServletRequest req){
        if (selectedSong != null){
            req.getSession().setAttribute("songId", selectedSong);
            return "redirect:/artist";
        }
        else{
            return "redirect:/songs";
        }
    }
}
