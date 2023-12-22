package com.example.ovapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Request {

    public static void sendApiRequest(String fromStation, String toStation, String transportType, boolean searchForArrival, String time, String date) {
        String url = "https://reisinfo.ns-mlab.nl/api/v3/trips";

        Map<String, String> params = Map.of(
                "fromStation", fromStation,
                "toStation", toStation,
                "dateTime",  date + "T" + time,
                "lang", "nl",
                "product", "OVCHIPKAART_ENKELE_REIS",
                "travelClass", "2",
                "searchForArrival", String.valueOf(searchForArrival),
                "disabledTransportModalities", transportType,
                "firstMileModality", "PUBLIC_TRANSPORT",
                "lastMileModality", "PUBLIC_TRANSPORT"
        );

        try {
            String apiUrl = url + buildQueryString(params);
            System.out.println("API URL: " + apiUrl);

            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                processJsonResponse(response.toString());
            } else {
                System.out.println("Fout " + responseCode);
                System.out.println(readErrorResponse(connection));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String buildQueryString(Map<String, String> params) {
        StringBuilder queryString = new StringBuilder("?");

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String value = URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8);

            if (!value.isEmpty()) {
                queryString
                        .append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8))
                        .append("=")
                        .append(value)
                        .append("&");
            }
        }

        return queryString.toString();
    }

    private static void processJsonResponse(String jsonResponse) {
        JsonParser jsonParser = new JsonParser();
        JsonObject json = jsonParser.parse(jsonResponse).getAsJsonObject();
        JsonArray trips = json.getAsJsonArray("trips");

        for (int i = 0; i < Math.min(trips.size(), 6); i++) {
            JsonObject routeInfo = trips.get(i).getAsJsonObject();

            JsonObject origin = routeInfo.getAsJsonArray("legs").get(0).getAsJsonObject().getAsJsonObject("origin");
            String originName = origin.get("name").getAsString();
            String plannedDepartureTime = origin.get("plannedDateTime").getAsString().substring(11, 16);

            JsonObject destination = routeInfo.getAsJsonArray("legs").get(0).getAsJsonObject().getAsJsonObject("destination");
            String destinationName = destination.get("name").getAsString();
            String plannedArrivalTime = destination.get("plannedDateTime").getAsString().substring(11, 16);

            int plannedDuration = routeInfo.get("plannedDurationInMinutes").getAsInt();
            int idx = routeInfo.get("idx").getAsInt();
            int transfers = routeInfo.get("transfers").getAsInt();


            System.out.println("Route: " + idx);
            System.out.println("==========================================");
            System.out.println("Van: " + originName);
            System.out.println("Naar: " + destinationName);
            System.out.println("Overstappen: " + transfers);
            System.out.println("Vertrektijd: " + plannedDepartureTime);
            System.out.println("Aankomst-tijd: " + plannedArrivalTime);
            System.out.println("Geplande duur: " + plannedDuration + " minuten");
            System.out.println("==========================================");

            JsonArray stops = routeInfo.getAsJsonArray("legs").get(0).getAsJsonObject().getAsJsonArray("stops");
            for (int j = 0; j < stops.size(); j++) {
                JsonObject stop = stops.get(j).getAsJsonObject();
                String stopName = stop.get("name").getAsString();

                JsonElement plannedArrivalTimeElement = stop.get("plannedArrivalDateTime");
                String plannedArrivalTimeStop = (plannedArrivalTimeElement != null && !plannedArrivalTimeElement.isJsonNull()) ? plannedArrivalTimeElement.getAsString().substring(11, 16) : "";

                JsonElement plannedDepartureTimeElement = stop.get("plannedDepartureDateTime");
                String plannedDepartureTimeStop = (plannedDepartureTimeElement != null && !plannedDepartureTimeElement.isJsonNull()) ? plannedDepartureTimeElement.getAsString().substring(11, 16) : "";

                if (!plannedArrivalTimeStop.isEmpty()) {
                    System.out.println("Stop: " + stopName + ", Aankomsttijd: " + plannedArrivalTimeStop);
                } else if (!plannedDepartureTimeStop.isEmpty()) {
                    System.out.println("Stop: " + stopName + ", Vertrektijd: " + plannedDepartureTimeStop);
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    private static String readErrorResponse(HttpURLConnection connection) throws IOException {
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        StringBuilder errorResponse = new StringBuilder();
        String errorLine;

        while ((errorLine = errorReader.readLine()) != null) {
            errorResponse.append(errorLine);
        }

        errorReader.close();
        return errorResponse.toString();
    }
}
