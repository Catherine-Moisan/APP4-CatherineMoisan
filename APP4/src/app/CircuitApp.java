package app;

import electronique.Composant;

import java.io.File;
import java.util.Scanner;

public class CircuitApp {

    private static final String DOSSIER_JSON = "";

    static void main() {
        Scanner sc = new Scanner(System.in);
        CircuitBuilder builder = new CircuitBuilder();

        boolean condition = true;

        while (condition) {
            File dossier = new File("U:/H2026/Programmation/APP4/APP4/src/donnees/fichiers_json/");
            File[] fichiers = dossier.listFiles((dir, name) -> name.endsWith(".json"));

            if (fichiers == null || fichiers.length == 0) {
                System.out.println("aucun fichier JSON trouvé dans le dossier");
            }

            System.out.println("\n Fichiers disponibles :");
            for (int i = 0; i < fichiers.length; i++) {
                System.out.println("[" + (i + 1) + "]" + fichiers[i].getName());
            }

            int choix = 0;
            if (choix >= 1 || choix < fichiers.length) {
                System.out.println("\n Entrez le numéro du fichier : ");
                if (sc.hasNextInt()) {
                    choix = sc.nextInt();
                } else {
                    System.out.println("Veuiller choisir un nombre valide");
                    sc.nextLine();
                }
            }


            File fichierChoisi = fichiers[choix - 1];

            try {
                Composant circuit = builder.construireCircuit(fichierChoisi.getPath());
                double resistance = circuit.calculerResistance();

                System.out.println("La résistance équivalente calculée est " + resistance);
            } catch (Exception e) {
                System.out.println("Erreur lors du traitement : " + e.getMessage());
            }

            System.out.println("\n [R] pour tester un autrte fichier");
            System.out.println("\n [Q] pour fermer le programme");

            String action = "";
            action = sc.nextLine();
            while (!action.equalsIgnoreCase("R") && !action.equalsIgnoreCase("Q")){
                System.out.println("Votre choix : ");
                action = sc.nextLine().trim();
            }
            if (action.equalsIgnoreCase("Q")) {
                condition = false;
                System.out.println("Fermeture du simulateur");
            }
        }







    }
}
