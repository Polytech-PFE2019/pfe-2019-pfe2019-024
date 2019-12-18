package structure;
import niveau.*;
import others.ProfilDiagnostic;

import java.util.ArrayList;

public abstract class StructureExperiment extends Structure {

    private NiveauCovariable [] niveauCovariables ;
    private ProfilDiagnostic [] ListeprofilDiagnostics ;
    private ArrayList<Niveau> niveauxExperience ;
    private Niveau reference;

    public StructureExperiment(String nom, int nombreNiveauExp) {
        super(nom, nombreNiveauExp);
        this.niveauxExperience = new ArrayList<Niveau>();
        for(int i = 1; i <= nombreNiveauExp; i++){
            niveauxExperience.add(new Niveau(i));
        }
    }

    public StructureExperiment(String nom, int nombreNiveauExp, Niveau reference) {
        super(nom, nombreNiveauExp);
        this.niveauxExperience = new ArrayList<Niveau>();
        for(int i = 1; i <= nombreNiveauExp; i++){
            niveauxExperience.add(new Niveau(i));
        }
        this.reference = reference;
    }

    public ArrayList<Niveau> getNiveau() {
        return niveauxExperience;
    }

    public Niveau getNiveau(int idNiveauExp) {
        return niveauxExperience.get(idNiveauExp);
    }

    public void setNiveau(ArrayList<Niveau> niveaux) {
        this.niveauxExperience = niveaux;
    }
}
