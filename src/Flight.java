import java.util.ArrayList;
import java.util.List;

public class Flight {
    String vluchtCode;
    String bestemming;
    int economyStoelen;
    int businessStoelen;
    List<Passagier> passagiers;

    public Flight(String vluchtCode, String bestemming, int economyStoelen, int businessStoelen) {
        this.vluchtCode = vluchtCode;
        this.bestemming = bestemming;
        this.economyStoelen = economyStoelen;
        this.businessStoelen = businessStoelen;
        this.passagiers = new ArrayList<>();
    }
}
