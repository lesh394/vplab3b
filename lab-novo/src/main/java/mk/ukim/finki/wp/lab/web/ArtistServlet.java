package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.exceptions.ArtistDoesNotExistException;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "ArtistServlet", urlPatterns = "/artist")
public class ArtistServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final ArtistService artistService;
    private final SongService songService;

    public ArtistServlet(SpringTemplateEngine springTemplateEngine, ArtistService artistService, SongService songService, SongService songService1) {
        this.springTemplateEngine = springTemplateEngine;
        this.artistService = artistService;
        this.songService = songService1;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("artists", artistService.listArtists());
        context.setVariable("trackId", req.getSession().getAttribute("selectedTrackId"));

        springTemplateEngine.process("artistsList.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String artistId = req.getParameter("artistId");
        String trackId = (String) req.getSession().getAttribute("selectedTrackId");
        System.out.println(trackId);

        if (artistId != null){
            try {
                songService.addArtistToSong(artistService.findById(Long.valueOf(artistId)), songService.findByTrackId(trackId));
            } catch (ArtistDoesNotExistException e) {
                throw new RuntimeException(e);
            }
//            artistService.addSongToArtist(artistService.findById(Long.valueOf(artistId)), songService.findByTrackId(trackId));

            resp.sendRedirect("/songDetails");
        }
        else{
            resp.sendRedirect("/artist");
        }
    }
}
