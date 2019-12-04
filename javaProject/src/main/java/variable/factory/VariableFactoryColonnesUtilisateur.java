package variable.factory;
import variable.ColonneUtilisateur;
import variable.Variable;

public class VariableFactoryColonnesUtilisateur extends VariableFactory {


    public ColonneUtilisateur createVariable(String nom, String type) {
        return new ColonneUtilisateur(nom, type);
    }

    public ColonneUtilisateur createVariable(String nom) {
        return null;
    }


}
