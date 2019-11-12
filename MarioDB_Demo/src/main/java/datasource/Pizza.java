package datasource;

/**
 *
 * @author Cecilie
 */
public class Pizza {

    public int nummer;
    public String navn;
    public String fyld;
    public int pris;

        public Pizza(int nummer, String navn, String fyld, int pris) {
        this.nummer = nummer;
        this.navn = navn;
        this.fyld = fyld;
        this.pris = pris;

    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }
    
    public int getNummer() {
        return nummer;
    }

    public String getNavn() {
        return navn;
    }

    public String getFyld() {
        return fyld;
    }

    public int getPris() {
        return pris;
    }

    @Override
    public String toString() {
//        String str = "Pizza{" + "nummer=" + nummer + ", navn=" + navn + ", fyld=";
//        for (String temp : fyld) {
//            str += temp + " ";
//        }
//        str += "pris=" + pris + ", nyhed=" + nyhed + '}';

        return nummer + " " + navn + " " + fyld + " " + pris;
    }
}
