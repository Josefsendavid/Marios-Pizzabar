package datasource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MySystem {

    private Menukort menukort;
    Menukort menukort2;
    MySystem system;

    public MySystem(){
    }
    
    public MySystem(Menukort menukort) {
        this.menukort = menukort;
    }

    public void runPizza() throws IOException {

        Scanner scan = new Scanner(System.in);
        int input = 0;
        while (input < 6) {
            System.out.println("1 = Menukort");
            System.out.println("2 = Opret bestilling");
            System.out.println("3 = Ryd bestillinger");
            System.out.println("4 = Se bestilling");
            System.out.println("5 = Se populære pizza");
            System.out.println("6 = Exit");
            System.out.println("Vælg 1 - 6: ");
            input = scan.nextInt();
            // 1 = vismenukort, 2 = opret bestilling, 3 = Ryd bestillinger, 4 = se bestilling, 5 = Se populære, 6 = afslut loop
            if (input == 1) {
                visMenukort();
                System.out.println();
            } else if (input == 2) {
                System.out.println("Gå til menu indtast 0");
                System.out.println(menukort.vælgPizza());
                System.out.println();
            } else if (input == 3) {
                fjernBestilling();
                System.out.println();
            } else if (input == 4) {
                seBestilling();
                System.out.println();
            } else if (input == 5) {
                pizzaPopularitet();
                System.out.println();
            }
        }
    }

    public void visMenukort() {
        ArrayList<Pizza> pizzaListe = menukort.getMenukort();
        for (Pizza pizza : pizzaListe) {
            System.out.println(pizza);
        }
    }

    public void fjernBestilling() throws IOException {
        menukort.rydBestillinger();

    }

    public void seBestilling() throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("bestillinger.txt"))) {
            String valgtePizzaer;
            while ((valgtePizzaer = br.readLine()) != null) {
                System.out.println(valgtePizzaer);
            }
        }
    }

    public List<String> læsPopularitet() {
        ArrayList<String> liste = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("pizzapopularitet.txt"))) {
            String valgtePizzaer;
            while ((valgtePizzaer = br.readLine()) != null) {
                liste.add(valgtePizzaer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return liste;
    }

    public String pizzaPopularitet() throws FileNotFoundException, IOException {
        List<String> list = læsPopularitet();

        String pizza = "";
        int count = 0;

        for (int i = 0; i < list.size(); i++) {
            int number = 0;
            for (int j = i; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {
                    number++;
                }
            }
            if (number > count) {
                count = number;
                pizza = list.get(i);
                System.out.println(pizza + " " + count);
            }
        }
        return pizza;
    }

    public Menukort getMenukort() {
        return menukort;
    }

    public void init() throws FileNotFoundException, IOException, SQLException {
        menukort2 = new Menukort();
        system = new MySystem(menukort2);
        menukort2.opretMenukort();
        system.runPizza();
    }

    public void opretBestillingsListe() throws IOException {
        system.runPizza();
    }
}
