package structure.factory;

import structure.*;

public class StructureFactory {


    public Structure getStructure(String nom,int nbNiveau,String structType) {
        Structure structure = null;

        switch (structType) {
            case "Stock":
                structure = new Stock(nom,nbNiveau);
                break;
            case "Planning":
                structure = new Experimentation(nom,nbNiveau);
                break;
            case "Phyto":
                structure = new Experimentation(nom,nbNiveau);
                break;
            case "Experimentation":
                structure = new Experimentation(nom,nbNiveau);
                break;
            case "Selection":
                structure = new Selection(nom,nbNiveau);
                break;
            default:
                throw new IllegalArgumentException("Type de structure inconnu");
        }

        return structure;
    }
}
