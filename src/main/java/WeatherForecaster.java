import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

/**
 * Created by Victor on 04.10.2018.
 */
public class WeatherForecaster {


    //TODO Apply forecast logic for city
    public String forecast(City city) throws UnirestException {
        System.out.printf("City: %s\nInfo about the city: %s\nAdministrative Area: %s\nNumber of citizens: %d\nYear of found: %s\nCoordinates: %s\nArea: %f\n", city.getName(), city.getUrl(), city.getAdministrativeArea(), city.getNumberOfCitizens(), city.getYearOfFound(),city.getCoordinates(),city.getArea());
        if (city.getCoordinates() == null){
            System.out.println("Sorry we didn`t support weather for this city");
            return null;
        }
        JsonNode request = Unirest.post("http://api.apixu.com/v1/current.json?key=f6da3a783d34446f8f4120423180410&q=" + city.getCoordinates().getLatitude() + "%20" + city.getCoordinates().getLongtitude())
                .asJson().getBody();
        JSONObject request_obj = request.getObject();
        System.out.printf("Current weather:\n" +
                "Last updated on %s\n" +
                "Temperature in celsius: %s\n" +
                "Condition: %s\n" +
                "Humidity: %s\n", request_obj.getJSONObject("current").getString("last_updated"),
                request_obj.getJSONObject("current").getDouble("temp_c"),
                request_obj.getJSONObject("current").getJSONObject("condition").getString("text"),
                request_obj.getJSONObject("current").getDouble("humidity")
                );
        System.out.println("Raw json response:"+request.toString());
        return null;

    }

}
