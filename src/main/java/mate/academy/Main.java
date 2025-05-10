package mate.academy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import mate.academy.lib.Injector;
import mate.academy.model.CinemaHall;
import mate.academy.model.Movie;
import mate.academy.model.MovieSession;
import mate.academy.service.CinemaHallService;
import mate.academy.service.MovieService;
import mate.academy.service.MovieSessionService;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.academy");

    public static void main(String[] args) {

        final MovieService movieService = (MovieService)injector.getInstance(MovieService.class);

        Movie movie = new Movie("Shrek");
        movie.setDescription("story about shrek");
        movieService.add(movie);
        Movie secondMovie = new Movie("Star Wars");
        secondMovie.setDescription("star wars description");
        movieService.add(secondMovie);
        Movie getMovie = movieService.get(movie.getId());
        Movie getSecondMovie = movieService.get(secondMovie.getId());
        System.out.println("first movie" + getMovie);
        System.out.println("second movie" + getSecondMovie);
        movieService.getAll().forEach(System.out::println);

        System.out.println("-----------------------");

        final CinemaHallService cinemaHallService = (CinemaHallService)injector
                .getInstance(CinemaHallService.class);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("small hall");
        CinemaHall cinemaHall2 = new CinemaHall();
        cinemaHall2.setCapacity(200);
        cinemaHall2.setDescription("big hall");
        cinemaHallService.add(cinemaHall);
        cinemaHallService.add(cinemaHall2);
        CinemaHall getHall = cinemaHallService.get(cinemaHall.getId());
        CinemaHall getHall2 = cinemaHallService.get(cinemaHall2.getId());
        System.out.println("first hall " + getHall);
        System.out.println("second hall " + getHall2);
        cinemaHallService.getAll().forEach(System.out::println);

        System.out.println("-----------------------");

        final MovieSessionService movieSessionService = (MovieSessionService)injector
                .getInstance(MovieSessionService.class);
        MovieSession firstMovieSession = new MovieSession();
        firstMovieSession.setMovie(movie);
        firstMovieSession.setCinemaHall(cinemaHall);
        firstMovieSession.setShowTime(LocalDateTime.of(2025, 5, 5, 12, 20));
        MovieSession secondMovieSession = new MovieSession();
        secondMovieSession.setMovie(secondMovie);
        secondMovieSession.setCinemaHall(cinemaHall2);
        secondMovieSession.setShowTime(LocalDateTime.of(2025, 6, 2, 22, 30));
        movieSessionService.add(firstMovieSession);
        movieSessionService.add(secondMovieSession);
        MovieSession getFirstSession = movieSessionService.get(firstMovieSession.getId());
        MovieSession getSecondSession = movieSessionService.get(secondMovieSession.getId());
        System.out.println("first session " + firstMovieSession);
        System.out.println("second session " + secondMovieSession);
        movieSessionService.findAvailableSessions(movie.getId(), LocalDate.of(2025, 5, 5))
                .forEach(System.out::println);
        movieSessionService.findAvailableSessions(secondMovie.getId(), LocalDate.of(2025, 5, 2))
                .forEach(System.out::println);

    }
}
