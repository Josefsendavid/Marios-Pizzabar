package datasource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Cecilie
 */
public class Menukort {

    private ArrayList<Pizza> menukort = new ArrayList<Pizza>();
    File alleBestillinger = new File("bestillinger.txt");
    File popularitet = new File("pizzapopularitet.txt");

    public void opretMenukort() throws SQLException {
        //File fil = new File("menukort.txt");
        Scanner sc = new Scanner(System.in);

        PizzaMapper pm = new PizzaMapper();
        menukort = pm.getPizzas();
        
        
        

    }

    public String vælgPizza() throws IOException {
        Scanner sc = new Scanner(System.in);
        String pizzaNavn = "";

        System.out.print("Pizza: ");
        int valgtPizzaNummer = sc.nextInt();
        try {
            while (valgtPizzaNummer != 0) {
                if (valgtPizzaNummer > 0 && valgtPizzaNummer <= menukort.size()) {
                    pizzaNavn = menukort.get(valgtPizzaNummer - 1).navn;
                    System.out.println(pizzaNavn);
                    gemBestilling(pizzaNavn);

                }
                System.out.print("Pizza: ");
                valgtPizzaNummer = sc.nextInt();
            }
        } catch (Exception e) {
            System.out.print(e);
        }

        return "";
    }

    public void gemBestilling(String pizzaNavn) throws IOException {

        ArrayList<String> gemBestillinger = new ArrayList<>();

        BufferedWriter bw = new BufferedWriter(new FileWriter(alleBestillinger, true));
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(popularitet, true));
        bw.write(pizzaNavn);
        bw.newLine();
        bw.write(LocalTime.now().toString());
        bw1.write(pizzaNavn);
        bw.newLine();
        bw1.newLine();
        bw.close();
        bw1.close();

    }

    public void rydBestillinger() throws IOException {
        PrintWriter pw = new PrintWriter("bestillinger.txt");
        pw.close();
    }

    ArrayList<Pizza> getMenukort() {
        return menukort;
    }

}
