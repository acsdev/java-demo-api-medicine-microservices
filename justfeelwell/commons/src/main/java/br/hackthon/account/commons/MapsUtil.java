package br.hackthon.account.commons;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Optional;

public class MapsUtil {

    private static MapsUtil ourInstance = new MapsUtil();

    private MapsUtil() {
    }

    private static final Logger LOG = LoggerFactory.getLogger(MapsUtil.class);

    public Optional<GeoPointDTO> getCoordinates(String address) {

        try {
            final String GET  = "GET";
            final String UTF8 = "UTF-8";

            StringBuilder builder = new StringBuilder();
            builder.append("https://maps.googleapis.com/maps/api/geocode/json");
            builder.append("?address=").append( URLEncoder.encode(address, UTF8) );
            builder.append("&sensor=false");
            builder.append("&key=").append("AIzaSyC00OuQ5Dri6SMB9AosTNE6565Vas-JFMY");
            builder.append("&_=").append(System.nanoTime());

            String json = HTTPCall.doGetAndReturnJson(builder.toString());
            if ( json.toString().contains("ZERO_RESULTS") || json.toString().contains("OVER_QUERY_LIMIT") ) {

                builder = new StringBuilder();
                builder.append("http://www.datasciencetoolkit.org/maps/api/geocode/json");
                builder.append("?address=").append( URLEncoder.encode(address, UTF8) );

                builder.append("&_=").append(System.nanoTime());

                json = HTTPCall.doGetAndReturnJson(builder.toString());
            }

            JsonElement element = new GsonBuilder().create().fromJson(json, JsonElement.class);

            JsonArray results    = element.getAsJsonObject().getAsJsonArray("results");
            JsonElement geometry = results.get(0).getAsJsonObject().get("geometry");
            JsonObject location  = geometry.getAsJsonObject().getAsJsonObject("location");
            //
            String latitude  = location.get("lat").getAsString();
            String longitude = location.get("lng").getAsString();

            return Optional.of(new GeoPointDTO( new BigDecimal(latitude), new BigDecimal(longitude)));
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return Optional.empty();
    }

    public static MapsUtil getInstance() {
        return ourInstance;
    }
}
