package structure;
import dsl.kernel.generator.Visitor;
import niveau.*;
import securite.*;
import observationMultiple.*;

import java.util.ArrayList;

public abstract class Structure {

    private GroupeUtilisateur [] utilisateurs ;
    private ArrayList < Niveau > niveaux ;
    private TypeObservationMultiple typeObservationMultiple ;
    private InformationObservationMultiple InformationObservationMultiple ;
    private String nom;
    private int nombreNiveau;

    public Structure(String nom, int nombreNiveau){
        this.nom = nom;
        this.nombreNiveau = nombreNiveau;
        this.niveaux = new ArrayList<Niveau>();
        for(int i = 1; i <= nombreNiveau; i++){
            niveaux.add(new Niveau(i));
        }
    }

    public void accept(Visitor visitor, Class< ? extends Structure> kind) {
        visitor.visit(this, kind);
    }

    public GroupeUtilisateur[] getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(GroupeUtilisateur[] utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public ArrayList<Niveau> getNiveaux() {
        return niveaux;
    }

    public Niveau getNiveau(int idNiveau) {
        return niveaux.get(idNiveau);
    }

    public void setNiveaux(ArrayList<Niveau> niveaux) {
        this.niveaux = niveaux;
    }



}
