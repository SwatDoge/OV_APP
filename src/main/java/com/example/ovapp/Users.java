package com.example.ovapp;

import com.example.ovapp.models.user.User;
import com.example.ovapp.tools.TripDetails;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Users {
    private final int MIN_USERNAME_LENGTH = 2;
    private final int MAX_USERNAME_LENGTH = 30;
    private final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_ROUTES_HISTORY = 100;


    private static Users singleton;
    private List<User> userList;
    public User currentUser;

    //Get singleton instance
    static public Users getInstance() {
        if (singleton == null) {
            singleton = new Users();
        }

        return singleton;
    }



    public void addTripDetailsToCurrentUser(TripDetails tripDetails) {
        if (currentUser != null) {
            List<TripDetails> userTripHistory = currentUser.getTripDetails();

            // Controleer of het maximum aantal routes nog niet is bereikt
            if (userTripHistory.size() < MAX_ROUTES_HISTORY) {
                userTripHistory.add(tripDetails);
                saveUsers(); // Save the updated user list with the new trip details
            } else {
                throw new RuntimeException("Maximaal aantal opgeslagen routes bereikt.");
            }
        } else {
            throw new RuntimeException("No user is currently logged in.");
        }
    }

    //Load up user list from .json file on initialisation.
    Users() {
        try {
            String jsonContent = String.join("", Files.readAllLines(Paths.get("src/main/resources/json/users.json")));
            System.out.println("JSON Content: " + jsonContent); // Voeg deze regel toe

            userList = new ArrayList<>(Arrays.asList(new Gson().fromJson(jsonContent, User[].class)));
        } catch (Exception e) {
            e.printStackTrace(); // Voeg deze regel toe om de fout af te drukken
            throw new RuntimeException("Uw gebruikersdatabase is corrupt, neem contact op met een developer");
        }
    }

    //CRUD users
    private User getUser(String username, String password) {
        return userList
                .stream()
                .filter(user -> user.username.equalsIgnoreCase(username) && user.password.equals(password))
                .findFirst()
                .orElse(null);
    }

    public void createUser(String username, String password) {
        if (isUsernameDuplicate(username)) {
            throw new RuntimeException("Deze gebruikersnaam word al gebruikt.");
        } else if (username.length() < MIN_USERNAME_LENGTH) {
            throw new RuntimeException("Gebruikersnaam is te kort. (Minstens " + MIN_USERNAME_LENGTH + " karakters)");
        } else if (username.length() > MAX_USERNAME_LENGTH) {
            throw new RuntimeException("Gebruikersnaam is te lang. (Maximaal " + MAX_USERNAME_LENGTH + " karakters)");
        } else if (password.length() < MIN_PASSWORD_LENGTH) {
            throw new RuntimeException("Wachtwoord is te kort. (Minstens " + MIN_PASSWORD_LENGTH + " karakters)");
        } else if (username.contains(" ")) {
            throw new RuntimeException("Je mag geen spatie gebruiken in je gebruikersnaam.");
        }

        userList.add(new User(username, password));

        saveUsers();
    }

    public void deleteCurrentUser() {
        userList.remove(currentUser);
        currentUser = null;

        saveUsers();
    }

    // Validation
    private boolean isUsernameDuplicate(String username) {
        return userList.stream().anyMatch(user -> user.username.equalsIgnoreCase(username));
    }

    public void saveUsers() {
        String userJson = new Gson().toJson(userList);
        System.out.println(userJson);
        try {
            Path path = Paths.get("src/main/resources/json/users.json");
            Files.writeString(path, userJson);
        } catch (IOException e) {
            throw new RuntimeException("Uw gebruikersdatabase is corrupt, neem contact op met een developer");
        }
    }

    // Login logic
    public void logIntoUser(String username, String password) {
        User user = getUser(username, password);

        if (user == null) {
            throw new RuntimeException("Gebruikersnaam of wachtwoord klopt niet.");
        }

        currentUser = user;
    }

    public boolean isSomeUserLoggedIn() {
        return currentUser != null;
    }

    public void logoutCurrentUser() {
        if (currentUser != null) {
            // Voer hier andere acties uit bij het uitloggen
            System.out.println("Uitloggen van gebruiker: " + currentUser.getUsername());

            // Stel currentUser in op null
            currentUser = null;

            // Sla de gebruikerslijst onmiddellijk op
            saveUsers();
        }
    }
}