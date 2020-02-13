package dsl.kernel.generator;

import dsl.kernel.ScenarioApp;
import niveau.Niveau;
import niveau.Presentation;
import niveau.tools.Etiquette;
import niveau.tools.Rapport;
import structure.Structure;
import variable.Variable;

public abstract class Visitor<T> {
    public abstract void visit(Structure structure, Class< ? extends Structure> kind);
    public abstract void visit(Niveau niveau,int i);
    public abstract void visit(Presentation presentation, int i);
    public abstract void visit(Variable variable, Class< ? extends Variable> kind,String from);
    public abstract void visit(ScenarioApp scenarioApp);
    public abstract void visit(Etiquette etiquette);
    public abstract void visit(Rapport rapport);
}
