import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Map.Entry;

public class Main {
    Double temp;

    static List<Integer> citiesNumbers = new ArrayList<>(){
        {
            add(2023469);
            add(2988507);
            add(3178287);
            add(2950159);
            add(524901);
            add(551487);
            add(5368361);
            add(2643743);
            add(756135);
            add(2673730);
        }
    };
    public static void main(String[] args) throws IOException {
        Map<String,Integer> result = new HashMap<>();
        Gson gson = new Gson();
        for(int x : citiesNumbers){
            String url = String.format("https://api.openweathermap.org/data/2.5/weather?id=%s&appid=7a07b6be57973acce7796cb38409ebcf",x);
            URL weatherUrl = new URL(url);
            InputStream stream = (InputStream) weatherUrl.getContent();
            Weather weather = gson.fromJson(new InputStreamReader(stream),Weather.class);
            result.put(weather.name, (int) (weather.main.temp-273));
        }
        Map<String, Integer> sortedResult =
                result.entrySet().stream()
                        .sorted(Entry.comparingByValue(Comparator.reverseOrder()))
                        .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));
        for (Map.Entry<String, Integer> set :
                sortedResult.entrySet()) {
            System.out.println(set.getKey() + " = "
                    + set.getValue());
        }
    }
}