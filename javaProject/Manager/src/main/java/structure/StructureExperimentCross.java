package structure;

import profilDeCroisement.*;

public abstract class StructureExperimentCross extends StructureExperiment {

    private ProfilDeCroisement [] profilDeCroisements ;
    private ProfilMatriceDeCroisement [] profilMatriceDeCroisements ;

    public StructureExperimentCross(String nom, int nombreNiveau) {
        super(nom, nombreNiveau);
    }
}
