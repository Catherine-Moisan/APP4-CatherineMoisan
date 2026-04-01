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

    public Composant construireCircuit (String cheminFichier) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File(cheminFichier));
        return lireComposant(root);

    }

    private Composant lireComposant(JsonNode noeudCircuit) {

        String type = noeudCircuit.get("type").asText();

        if (type.equals("resistance")){
            return new Resistance(noeudCircuit.get("valeur").asDouble());
        }

        if (type.equals("serie")){
            List<Composant> listeSerie = new ArrayList<>();

            for (JsonNode composantNoeud : noeudCircuit.get("composants")){
                listeSerie.add(lireComposant(composantNoeud));
            }
            return new CircuitSerie(listeSerie);
        }

        if (type.equals("parallele")){
            List<Composant> listeParallele = new ArrayList<>();

            for (JsonNode composantNoeud : noeudCircuit.get("composant")){
                listeParallele.add(lireComposant(composantNoeud));
            }
            return new CircuitParallele(listeParallele);
        }

        else {
            throw new InvalidParameterException("Le type est inconnu");
        }

    }
}
