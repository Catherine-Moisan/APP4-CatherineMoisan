package app;

import electronique.Composant;

import java.io.File;
import java.util.Scanner;

public class CircuitApp {

    private static final String DOSSIER_JSON = "json/";

    static void main() {
        Scanner sc = new Scanner(System.in);
        CircuitBuilder builder = new CircuitBuilder();

        boolean condition = true;

        while (condition){
            File dossier = new File(DOSSIER_JSON);
            File[] fichiers = dossier.listFiles((dir, name) -> name.endsWith(".json"));

            if (fichiers == null || fichiers.length == 0){
                System.out.println("aucun fichier JSON trouvé dans le dossier");
            }

            System.out.println("\n Fichiers disponibles :");
            for (int i = 0; i < fichiers.length; i++){
                System.out.println("[" + (i+1) + "]" + fichiers[i].getName());
            }

            int choix = -1;
            while (choix < 1 || choix > fichiers.length){
                System.out.println("\n Entrez le numéro du fichier : ");
                if (sc.hasNextInt()){
                    choix = sc.nextInt();
                } else {
                    System.out.println("Veuiller choisir un nombre valide");
                    sc.next();
                }
            }

            File fichierChoisi = fichiers[choix -1];

            try {
                Composant circuit = builder.construireCircuit(fichierChoisi.getPath());
                double resistance = circuit.calculerResistance();

                System.out.println("La résistance équivalente calculée est " + resistance);
            } catch (Exception e) {
                System.out.println("Erreur lors du traitement : " + e.getMessage());
            }


            System.out.println("\n [1] pour tester un autrte fichier");
            System.out.println("\n [2] pour fermer le programme");

            String continuer = "";
             if (continuer.equalsIgnoreCase("1")){
                System.out.println("Entrez le numéro du fichier");
                choix = sc.nextInt();
             }

             if (continuer.equalsIgnoreCase("2")){
                 System.out.println("Fermeture du programme");
             }

        }


    }
}
