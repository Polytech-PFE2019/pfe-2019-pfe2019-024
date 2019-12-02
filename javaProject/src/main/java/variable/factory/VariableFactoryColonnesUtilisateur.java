package variable.factory;
import variable.ColonneUtilisateur;
import variable.Variable;

public class VariableFactoryColonnesUtilisateur extends VariableFactory {


    protected ColonneUtilisateur createVariable(String nom, String type) {
        return new ColonneUtilisateur(nom, type);
    }

    protected ColonneUtilisateur createVariable(String nom) {
        return null;
    }


}
