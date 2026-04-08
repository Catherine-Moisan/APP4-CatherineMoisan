package app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import electronique.CircuitParallele;
import electronique.CircuitSerie;
import electronique.Composant;
import electronique.Resistance;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class CircuitBuilder {

    public Composant construireCircuit (String cheminFichier) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        try{
            root = mapper.readTree(new File(cheminFichier));
        } catch (Exception e){
            throw new Exception("Impossible de lire le fichier Json : " + e.getMessage());
        }

        if (!root.has("circuit")) {
            throw new IllegalArgumentException("Le fichier ne contient pas les objets nécéssaires");
        }
        JsonNode noeudCircuit = root.get("circuit");
        return lireComposant(noeudCircuit);

    }

    private Composant lireComposant(JsonNode noeudCircuit) {
        String type = noeudCircuit.get("type").asText();

        if ("resistance".equals(type)){
            return new Resistance(noeudCircuit.get("valeur").asDouble());
        }
        else if ("serie".equals(type)){
            List<Composant> listeSerie = new ArrayList<>();

            for (JsonNode composantNoeud : noeudCircuit.get("composants")){
                listeSerie.add(lireComposant(composantNoeud));
            }
            return new CircuitSerie(listeSerie);
        } else if ("parallele".equals(type)){
            List<Composant> listeParallele = new ArrayList<>();

            for (JsonNode composantNoeud : noeudCircuit.get("composants")){
                listeParallele.add(lireComposant(composantNoeud));
            }
            return new CircuitParallele(listeParallele);
        }

        else {
            throw new InvalidParameterException("Le type est inconnu");
        }

    }
}
