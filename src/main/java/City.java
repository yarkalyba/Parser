/**
 * Created by Victor on 03.10.2018.
 */
import lombok.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


@Getter
@Setter
@ToString
public class City {
    private String name;
    private String url;
    private String administrativeArea;
    private int numberOfCitizens;
    private String yearOfFound;
    private Coordinates coordinates; // Set this
    private double area;

    private static final int INFO_SIZE = 6;




    public static City parse(Element city) throws IOException {
        Elements info = city.select("td");
        if (info.size() == INFO_SIZE) {
            Element anchor = info.get(1).select("a").first();
            City myCity = new City();
            myCity.setName(anchor.attr("title"));
            myCity.setUrl(String.format("https://uk.wikipedia.org%s", anchor.attr("href")));
            anchor = info.get(2).select("a").first();
            myCity.setAdministrativeArea(anchor.attr("title"));
            anchor = info.get(3);
            myCity.setNumberOfCitizens(Integer.parseInt(anchor.text().split("!")[0].replaceAll("\\s","").replaceAll("&nbsp;","").replaceAll(String.valueOf((char) 160), "")));
            anchor = info.get(4).select("a").first();
            myCity.setYearOfFound(anchor.attr("title"));
            anchor = info.get(5);
            myCity.setArea(Double.parseDouble(anchor.text()));
            Document doc = Jsoup.connect(myCity.url).timeout(0).get();
            Element cords = doc.select("span.geo").first();
            if (cords!=null){
                myCity.setCoordinates(new Coordinates(cords.text().split("; ")[0],cords.text().split("; ")[1]));
            }
            else {
                myCity.setCoordinates(null);
            }
            return myCity;
        }
        return null;
    }

}