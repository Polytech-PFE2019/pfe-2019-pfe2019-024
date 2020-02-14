package dsl.kernel.generator;

import dsl.kernel.ScenarioApp;
import niveau.Niveau;
import niveau.Presentation;
import niveau.tools.Etiquette;
import niveau.tools.Rapport;
import structure.Structure;
import variable.ColonneUtilisateur;
import variable.Compteur;
import variable.ConditionsExperimentale;
import variable.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Generator extends Visitor<StringBuilder>{


    private StringBuilder builder;
    private int tab = 2;

    public Generator(){

        this.builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<featureModel>\n" +
                "\t<properties>\n" +
                "\t\t<graphics key=\"showhiddenfeatures\" value=\"true\"/>\n" +
                "\t\t<graphics key=\"legendautolayout\" value=\"true\"/>\n" +
                "\t\t<graphics key=\"showshortnames\" value=\"false\"/>\n" +
                "\t\t<graphics key=\"layout\" value=\"horizontal\"/>\n" +
                "\t\t<graphics key=\"showcollapsedconstraints\" value=\"true\"/>\n" +
                "\t\t<graphics key=\"legendhidden\" value=\"false\"/>\n" +
                "\t\t<graphics key=\"layoutalgorithm\" value=\"1\"/>\n" +
                "\t</properties> \n" );
    }

    @Override
    public void visit(Structure structure,Class< ? extends Structure> kind) {

        builder.append(tabgenerator()+"<and abstract=\"true\" mandatory=\"true\" name =\"Structure\">\n");
        tab++;

        builder.append(tabgenerator()+ "<and abstract=\"true\" mandatory=\"true\" name =\"typeStructure\">\n");
        tab++;
        builder.append(tabgenerator()+ "<feature mandatory=\"true\" name =\""
                + kind.toString().substring(kind.toString().indexOf(".")+1)
                +"\"/>\n");
        builder.append(tabgenerator()+"</and>\n");
        tab--;
        for(int i = 0; i < structure.getNiveaux().size();i++){
            System.out.println(structure.getNiveaux().get(i).toString());
            visit(structure.getNiveaux().get(i),i+1);
        }
        builder.append("\n\t\t</and> \n");
    }

    public void typeVariableGenerator(List<Variable> variables,int i){
        List<Variable> colonneUtilisateurs = new ArrayList<Variable>();
        List compteur = new ArrayList<Variable>();
        List<Variable> conditionExperimentale = new ArrayList<Variable>();

        for(Variable var :variables){
            if( var instanceof ColonneUtilisateur){
                colonneUtilisateurs.add(var);
            }
            if( var instanceof Compteur){
                compteur.add(var);
            }
            if( var instanceof ConditionsExperimentale){
                System.out.println("*************************** "+var.toString() );
                conditionExperimentale.add(var);
            }
        }
        if(colonneUtilisateurs.size() > 0) {
            System.out.println(colonneUtilisateurs);
            builder.append(tabgenerator() + "<and mandatory=\"true\" abstract=\"true\" name =\"ColonneUtilisateur" + i + "\">\n");
            tab++;
            for (int j = 0; j < colonneUtilisateurs.size(); j++) {
                visit(colonneUtilisateurs.get(j), colonneUtilisateurs.get(j).getClass(), i+"");
            }
            tab--;
            builder.append(tabgenerator() + "</and>\n");
        }
        if(compteur.size() > 0) {

            builder.append(tabgenerator() + "<and mandatory=\"true\" abstract=\"true\" name =\"Compteur" + i + "\">\n");
            tab++;
            for (int j = 0; j < compteur.size(); j++) {
                visit(variables.get(j), variables.get(j).getClass(), i+"");
            }
            tab--;
            builder.append(tabgenerator() + "</and>\n");
        }
        if(conditionExperimentale.size() > 0) {
            System.out.println();
            builder.append(tabgenerator() + "<and mandatory=\"true\" abstract=\"true\" name =\"ConditionsExperimentale" + i + "\">\n");
            tab++;

            for (int j = 0; j < conditionExperimentale.size(); j++) {
                visit(conditionExperimentale.get(j), conditionExperimentale.get(j).getClass(), i+"");
            }
            tab--;
            builder.append(tabgenerator() + "</and>\n");
        }
    }

    @Override
    public void visit(Niveau niveau,int i) {
        builder.append(tabgenerator()+"<and  mandatory=\"true\" name =\"Niveau"+i+"\">\n");
        tab++;
        builder.append(tabgenerator()+"<and  mandatory=\"true\" name =\"Variablesniveau"+i+"\">\n");
        tab++;
        typeVariableGenerator(niveau.getVariables(),i);
        tab--;
        builder.append(tabgenerator()+"</and>\n");

        builder.append(tabgenerator()+"<and  mandatory=\"true\" name =\"Presentations"+i+"\">\n");
        tab++;
        for(int j = 0; j < niveau.getPresentations().size();j++){
            visit(niveau.getPresentations().get(j), i);
        }
        tab--;
        builder.append(tabgenerator()+"</and>\n");
        for(int j = 0; j < niveau.getRapports().size();j++){
            visit(niveau.getRapports().get(j));
        }
        for(int j = 0; j < niveau.getEtiquettes().size();j++){
            visit(niveau.getEtiquettes().get(j));
        }
        tab--;
        builder.append(tabgenerator()+"</and>\n");
    }

    private String tabgenerator(){
        String res = "";
        for(int i= 0;i < tab; i++){
            res += "\t";
        }
        return res;
    }
    @Override
    public void visit(Presentation presentation,int i) {
        builder.append(tabgenerator()+"<and  mandatory=\"true\" name =\"P"+i+presentation.getNom()+"\">\n");
        tab++;
        typeVariableGenerator(presentation.getVariables(),i);
        for(int j = 0; j < presentation.getVariables().size();j++){
            visit(presentation.getVariables().get(j),presentation.getVariables().get(j).getClass(),"p");
        }

        tab--;
        builder.append(tabgenerator()+"</and>\n");
    }

    @Override
    public void visit(Variable variable, Class< ? extends Variable> kind,String from) {
        System.out.println("*****************************"+variable.toString());
        builder.append(tabgenerator()+"<feature  mandatory=\"true\" name =\""+variable.getNom()+from+"\"/>\n");
    }

    @Override
    public void visit(Rapport rapport) {
        builder.append(tabgenerator()+"<feature  mandatory=\"true\" name =\"Rapport\"/>\n");
    }

    @Override
    public void visit(Etiquette Etiquette) {
        builder.append(tabgenerator()+"<feature  mandatory=\"true\" name =\"Etiquette\"/>\n");
    }



    @Override
    public void visit(ScenarioApp scenarioApp) {
        builder.append("\t<struct>\n");
        scenarioApp.getStructure().accept(this,scenarioApp.getStructure().getClass());
        builder.append("\t</struct>");
        builder.append("\n" + "</featureModel>");
    }

    public String getGeneratedCode(){
        return builder.toString();
    }
}
