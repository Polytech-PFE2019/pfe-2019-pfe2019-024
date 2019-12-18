package structure.factory;

import niveau.Niveau;
import structure.*;

public class StructureFactory {


    public Structure createStructure(String nom,int nbNiveau,String structType) {
        return  new Stock(nom,nbNiveau);
    }

    public Structure createStructure(String nom,int nbNiveau,String structType, Niveau reference) {
        Structure structure = null;

        switch (structType) {
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
