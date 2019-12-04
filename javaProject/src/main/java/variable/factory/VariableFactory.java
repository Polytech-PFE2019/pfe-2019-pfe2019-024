package variable.factory;

import variable.Variable;

public abstract class VariableFactory {

    public Variable createInstanceOfVariable(String nom,String type) {
        return createVariable(nom, type);
    }

    protected abstract Variable createVariable(String nom, String type);

    public Variable createInstanceOfVariable(String nom) {
        return createVariable(nom);
    }

    protected abstract Variable createVariable(String nom);
}
