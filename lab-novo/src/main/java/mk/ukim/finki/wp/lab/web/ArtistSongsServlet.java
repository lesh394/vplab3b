package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.ArtistService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="ArtistSongsServlet", urlPatterns = "/artistSongs")
public class ArtistSongsServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final ArtistService artistService;


    public ArtistSongsServlet(SpringTemplateEngine springTemplateEngine, ArtistService artistService) {
        this.springTemplateEngine = springTemplateEngine;
        this.artistService = artistService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("artists", artistService.listArtists());

        springTemplateEngine.process("artistSongs.html", context, resp.getWriter());
    }
}
