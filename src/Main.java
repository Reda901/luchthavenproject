import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Passagier> passagiers = new ArrayList<>();
    static List<Flight> vluchten = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Voeg Passagier Toe");
            System.out.println("2. Voeg Vlucht Toe");
            System.out.println("3. Board Passagier");
            System.out.println("4. Print Vluchten");
            System.out.println("5. Stop");
            System.out.print("Kies een optie: ");

            int keuze;
            try {
                keuze = sc.nextInt();
                sc.nextLine(); // Consumeer de newline na nextInt()
            } catch (Exception e) {
                sc.nextLine(); // Consumeer de foutieve invoer
                System.out.println("Foutieve invoer. Voer een geldig getal in.");
                continue;
            }

            switch (keuze) {
                case 1 -> voegPassagierToe(sc);
                case 2 -> voegVluchtToe(sc);
                case 3 -> boardPassagier(sc);
                case 4 -> printVluchten();
                case 5 -> {
                    System.out.println("Afsluiten...");
                    return;
                }
                default -> System.out.println("Ongeldige keuze. Probeer het opnieuw.");
            }
        }
    }

    static void voegPassagierToe(Scanner sc) {
        System.out.print("Naam: ");
        String naam = sc.nextLine().trim();

        if (naam.isEmpty() || bevatCijfers(naam)) {
            System.out.println("Foutieve invoer. Naam mag niet leeg zijn of cijfers bevatten.");
            return;
        }

        System.out.print("Leeftijd: ");
        int leeftijd;
        try {
            leeftijd = sc.nextInt();
            sc.nextLine(); // Consumeer de newline na nextInt()
            if (leeftijd < 0) {
                System.out.println("Foutieve invoer. Leeftijd kan niet negatief zijn.");
                return;
            }
        } catch (Exception e) {
            sc.nextLine(); // Consumeer de foutieve invoer
            System.out.println("Foutieve invoer. Voer een geldig getal in voor de leeftijd.");
            return;
        }

        System.out.print("Adres (bijvoorbeeld: Straatnaam 123): ");
        String adres = sc.nextLine().trim();

        if (adres.isEmpty()) {
            System.out.println("Foutieve invoer. Adres mag niet leeg zijn.");
            return;
        }

        System.out.print("Bagagegewicht: ");
        double gewicht;
        try {
            gewicht = sc.nextDouble();
            sc.nextLine(); // Consumeer de newline na nextDouble()
            if (gewicht < 0) {
                System.out.println("Foutieve invoer. Gewicht kan niet negatief zijn.");
                return;
            }
        } catch (Exception e) {
            sc.nextLine(); // Consumeer de foutieve invoer
            System.out.println("Foutieve invoer. Voer een geldig getal in voor het bagagegewicht.");
            return;
        }

        if (gewicht <= 20) {
            passagiers.add(new Passagier(naam, leeftijd, adres, gewicht));
            System.out.println("Passagier toegevoegd.");
        } else {
            System.out.println("Bagage te zwaar. Passagier niet toegevoegd.");
        }
    }

    static boolean bevatCijfers(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    static void voegVluchtToe(Scanner sc) {
        System.out.print("Code: ");
        String code = sc.nextLine().trim();

        System.out.print("Bestemming: ");
        String bestemming = sc.nextLine().trim();

        System.out.print("Economy Stoelen: ");
        int economyStoelen;
        try {
            economyStoelen = sc.nextInt();
            sc.nextLine(); // Consumeer de newline na nextInt()
        } catch (Exception e) {
            sc.nextLine(); // Consumeer de foutieve invoer
            System.out.println("Foutieve invoer. Voer een geldig getal in voor de economy stoelen.");
            return;
        }

        System.out.print("Business Stoelen: ");
        int businessStoelen;
        try {
            businessStoelen = sc.nextInt();
            sc.nextLine(); // Consumeer de newline na nextInt()
        } catch (Exception e) {
            sc.nextLine(); // Consumeer de foutieve invoer
            System.out.println("Foutieve invoer. Voer een geldig getal in voor de business stoelen.");
            return;
        }

        vluchten.add(new Flight(code, bestemming, economyStoelen, businessStoelen));
        System.out.println("Vlucht toegevoegd.");
    }

    static void boardPassagier(Scanner sc) {
        System.out.print("Passagier Naam: ");
        String naam = sc.nextLine().trim();
        System.out.print("Vlucht Code: ");
        String code = sc.nextLine().trim();

        Passagier passagier = passagiers.stream()
                .filter(p -> p.naam.equalsIgnoreCase(naam))
                .findFirst()
                .orElse(null);

        Flight vlucht = vluchten.stream()
                .filter(f -> f.vluchtCode.equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);

        if (passagier != null && vlucht != null) {
            if (vlucht.economyStoelen > 0 || vlucht.businessStoelen > 0) {
                vlucht.passagiers.add(passagier);

                if (vlucht.economyStoelen > 0) {
                    vlucht.economyStoelen--;
                } else {
                    vlucht.businessStoelen--;
                }

                System.out.println("Passagier succesvol geboard.");
            } else {
                System.out.println("Geen stoelen beschikbaar.");
            }
        } else {
            System.out.println("Ongeldige passagier of vlucht.");
        }
    }

    static void printVluchten() {
        for (Flight vlucht : vluchten) {
            System.out.println("Vlucht: " + vlucht.vluchtCode + ", Bestemming: " + vlucht.bestemming);
            if (vlucht.passagiers.isEmpty()) {
                System.out.println("- Geen passagiers.");
            } else {
                for (Passagier p : vlucht.passagiers) { // Correcte syntax
                    System.out.println("- " + p.naam);
                }
            }
        }
    }
}
