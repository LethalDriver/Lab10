package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Gson gson = new Gson();
        Scanner scanner = new Scanner(System.in);
        String input;
        String name;
        String genus;
        String type;
        double min;
        double max;
        List<Fruit> result;
        while (true){
            System.out.println("What do you wanna do? \nAvailable commands: \n" +
                    "name - search a fruit by its name\ngenus - search fruits by its genus \n" +
                    "nutritions - search fruits by nutrition \nq - quit the program");
            input = scanner.nextLine();
            switch (input){
                case "name":
                    System.out.println("Please provide the name starting with capital letter");
                    name = scanner.nextLine();
                    System.out.println(searchByName(name, gson).toString());
                    break;
                case "genus":
                    System.out.println("Please provide the genus starting with capital letter");
                    genus = scanner.nextLine();
                    result = searchByGenus(genus, gson);
                    for (Fruit fruit:
                            result) {
                        System.out.println(fruit.toString());
                    }
                    break;
                case "nutritions":
                    System.out.println("Please provide the type of nutrition: ");
                    type = scanner.nextLine();
                    System.out.println("Provide the minimum value of the nutrient: ");
                    min = Double.parseDouble(scanner.nextLine());
                    System.out.println("Provide the maximum value of the nutrient: ");
                    max = Double.parseDouble(scanner.nextLine());
                    result = searchByNutrition(type, min, max, gson);
                    for (Fruit fruit:
                            result) {
                        System.out.println(fruit.toString());
                    }
                    break;
                case "q":
                    return;
            }
        }

    }



    public static Fruit searchByName(String name, Gson gson){
        String url="https://www.fruityvice.com/api/fruit/" + name;
        StringBuffer response = fetchFruits(url);
        Fruit fruit = gson.fromJson(response.toString(), Fruit.class);
        return fruit;
    }


    public static StringBuffer fetchFruits(String url){
        StringBuffer response = new StringBuffer();
        try{
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            System.out.println("Response: " + responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputLine;
            while ((inputLine=in.readLine()) != null){
                response.append(inputLine);
            }
            in.close();
        }
        catch (MalformedURLException ex){
            System.out.println("Bad url");
        }
        catch (IOException e) {
            System.out.println("Connection failed");
        }
        return response;
    }
    public static List<Fruit> searchByGenus(String genus, Gson gson){

        String url="https://www.fruityvice.com/api/fruit/genus/" + genus;
        StringBuffer response = fetchFruits(url);


        List<Fruit> fruits = Arrays.asList(gson.fromJson(response.toString(), Fruit[].class));
        return fruits;
    }

    public static List<Fruit> searchByNutrition(String type, double min, double max, Gson gson){

        String url="https://www.fruityvice.com/api/fruit/" + type + "?min=" + min +"&max=" + max;
        StringBuffer response = fetchFruits(url);

        List<Fruit> fruits = Arrays.asList(gson.fromJson(response.toString(), Fruit[].class));
        return fruits;
    }

}