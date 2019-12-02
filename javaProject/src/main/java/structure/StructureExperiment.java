package structure;
import niveau.*;
import others.ProfilDiagnostic;

public abstract class StructureExperiment extends Structure {

    private NiveauCovariable[] niveauCovariables;
    private ProfilDiagnostic[] ListeprofilDiagnostics;

    public StructureExperiment(String nom, int nombreNiveau) {
        super(nom, nombreNiveau);
    }
}
