public class Passagier extends Persoon {
    double bagageGewicht;

    public Passagier(String naam, int leeftijd, String adres, double bagageGewicht) {
        super(naam, leeftijd, adres);
        this.bagageGewicht = bagageGewicht;
    }
}

