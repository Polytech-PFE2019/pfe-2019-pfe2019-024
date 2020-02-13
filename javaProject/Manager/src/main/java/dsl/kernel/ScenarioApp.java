package dsl.kernel;

import dsl.kernel.generator.Visitor;
import structure.Structure;

import java.util.ArrayList;
import java.util.List;

public class ScenarioApp {

    private Structure structure;
    private String name;

    public ScenarioApp(){
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
