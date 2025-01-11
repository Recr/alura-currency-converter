import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        boolean running = true;
        do {
            System.out.println("Currency Converter\n");
            System.out.println("1 - BRL to USD");
            System.out.println("2 - BRL to ARS");
            System.out.println("3 - BRL to JPY");
            System.out.println("4 - BRL to KRW");
            System.out.println("5 - BRL to MXN");
            System.out.println("6 - BRL to EUR");
            System.out.println("0 - Exit\n");
            System.out.print("Choose an option: ");

            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();
            String amountInput = "";
            if (!option.equals("0")) {
                System.out.print("\nType the amount: ");
                amountInput = scanner.nextLine();
            }
            switch (option) {
                case "1":
                    request("USD/" + amountInput);
                    break;
                case "2":
                    request("ARS/" + amountInput);
                    break;
                case "3":
                    request("JPY/" + amountInput);
                    break;
                case "4":
                    request("KRW/" + amountInput);
                    break;
                case "5":
                    request("MXN/" + amountInput);
                    break;
                case "6":
                    request("EUR/" + amountInput);
                    break;
                case "0":
                    System.out.print("\nExiting...");
                    running = false;
                    break;
                default:
                    System.out.print("\nIncorrect option\n\n");
                    break;
            }

        } while (running);

    }

    public static void request(String parameters) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String uri = "https://v6.exchangerate-api.com/v6/"
                + getApiKey()
                + "/pair/BRL/";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + parameters))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        deserialize(response.body());
    }

    public static void deserialize (String body) {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        ConversionDto conversion = gson.fromJson(body, ConversionDto.class);
        System.out.println(new Conversion(conversion));
    }

    public static String getApiKey() {
        try {
            File env = new File(".env");
            Scanner scanner = new Scanner(env);
            String apikey = scanner.nextLine();
            scanner.close();
            return apikey;
        } catch (Exception e) {
            System.err.println("A error occurred: couldn't read the .env file");
        }
        return "{Error while reading api key}";
    }
}