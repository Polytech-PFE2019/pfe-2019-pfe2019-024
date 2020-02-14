package variable;

public class ConditionsExperimentale extends Variable {
    String type ;

    public ConditionsExperimentale(String nom, String libellé, String description, String annotation) {
        super(nom, libellé, description, annotation);
    }
    public ConditionsExperimentale(){
        super();
    }

    public ConditionsExperimentale(String name, String type) {
        super(name);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString(){
        return "nom : " + getNom() + ", type : " + type;
    }
}
