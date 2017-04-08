package api;

public class RestService {

    public static final String URL = "http://localhost:8080/Booking.1.0.0-SNAPSHOT/api" + Uris.VERSION;

    public void populate() {
        new RestBuilder<>(RestService.URL).path(Uris.ADMIN).post().build();
    }

    public void deleteAll() {
        new RestBuilder<>(RestService.URL).path(Uris.ADMIN).delete().build();
    }

}
