import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenerateXmlFeatureModel {

    private ArrayList<String> fileNameArray = new ArrayList<>();
    private FileWriter fr;
    private File file;
    private BufferedWriter writer;
    private List<String> filesPath;
    private ArrayList<String> packageNames;
    private HashMap<String,ArrayList<String>> classNamesWithVariables;
    private ArrayList<String> alreadyAdded;

    public GenerateXmlFeatureModel() throws IOException {
        packageNames = new ArrayList<>();
        alreadyAdded = new ArrayList<>();
        classNamesWithVariables = new HashMap<>();
        File file = new File("Manager/src/main/resources/myModel.xml");
        if (file.exists() && file.isFile())
        {
            file.delete();
        }
        file.createNewFile();
        file = new File("Manager/src/main/resources/myModel.xml");
        fr =new FileWriter("Manager/src/main/resources/myModel.xml",true);
        writer = new BufferedWriter(fr);
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<featureModel>\n" +
                "\t<properties>\n" +
                "\t\t<graphics key=\"showhiddenfeatures\" value=\"true\"/>\n" +
                "\t\t<graphics key=\"legendautolayout\" value=\"true\"/>\n" +
                "\t\t<graphics key=\"showshortnames\" value=\"false\"/>\n" +
                "\t\t<graphics key=\"layout\" value=\"horizontal\"/>\n" +
                "\t\t<graphics key=\"showcollapsedconstraints\" value=\"true\"/>\n" +
                "\t\t<graphics key=\"legendhidden\" value=\"false\"/>\n" +
                "\t\t<graphics key=\"layoutalgorithm\" value=\"1\"/>\n" +
                "\t</properties> \n"+
                "\t<struct> \n");
        this.findAllFiles();
        this.generateClassAndVariableArray();
        this.generateLinks();
        this.endFile();
        System.out.println(classNamesWithVariables);
    }

    private void addAndFeature(String name,int tabIndex) throws IOException {
        for(int i = 0; i < tabIndex ; i++){
            writer.write("\t");
        }
        writer.write("<and name=\""+name+"\">\n");
    }

    private void addFeature(String name,int tabIndex) throws IOException {
        for(int i = 0; i < tabIndex ; i++){
            writer.write("\t");
        }
        writer.write("<feature name=\""+name+"\"/>\n");
    }

    private void generateLinks() throws IOException {
        String root = this.getRoot();
        this.recurseLoop(root,2);
        writer.write("\t</struct>\n");
        //classNamesWithVariables.forEach((key,value) ->  findOccurenceOfClassName(key));
    }

    private void recurseLoop(String root,int tabIndex) throws IOException {
        if(!alreadyAdded.contains(root)) {
            alreadyAdded.add(root);
            if (classNamesWithVariables.get(root).size() == 0) {
                this.addFeature(root, tabIndex);
            } else {
                addAndFeature(root, tabIndex);
                for (String variable : classNamesWithVariables.get(root)) {
                    this.recurseLoop(variable, tabIndex + 1);
                }
                for (int i = 0; i < tabIndex; i++) {
                    writer.write("\t");
                }
                writer.write("</and>\n");
            }
        }
    }
    private String getRoot(){
        for (Map.Entry<String, ArrayList<String>> entry : classNamesWithVariables.entrySet()) {
            if(isTheirOccurenceOfClassInVarriableArray(entry.getKey())){
               return entry.getKey();
            };
        }
        return null;
    }
    private boolean isTheirOccurenceOfClassInVarriableArray(String className){
        for (Map.Entry<String, ArrayList<String>> entry : classNamesWithVariables.entrySet()) {
            if(entry.getValue().contains(className)){
                return false;
            }
        }
        return true;
      //classNamesWithVariables.forEach((key,value) ->  System.out.println(key + " = " + value));
    }
    private void findAllFiles(){
        try (Stream<Path> walk = Files.walk(Paths.get(System.getProperty("user.dir")+"\\Manager\\src\\main\\java"))) {
            filesPath = walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(".java")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateClassAndVariableArray() throws IOException {
        String tmp = "";
        String packagePath = "";
        String packageName = "";

        //on retire les package et fichier inutile
        for (int i = 0; i < filesPath.size(); i++) {
            packagePath = filesPath.get(i).substring(0, filesPath.get(i).lastIndexOf("\\"));
            if (packagePath.substring(packagePath.lastIndexOf("\\") + 1).equals("java") ||
                    packagePath.substring(packagePath.lastIndexOf("\\") + 1).equals("businessLevel") ||
                    packagePath.substring(packagePath.lastIndexOf("\\") + 1).equals("factory")) {
                filesPath.remove(i);
                i--;
            }
        }
        for (String s : filesPath) {
            fileNameArray.add(getClassName(s));
        }
        for (String s : filesPath) {
            classNamesWithVariables.put(getClassName(s), new ArrayList<>());
        }
        this.addVariablesInClass();
    }

    private String getPackagePath(String path){
        return path.substring(0, path.lastIndexOf("\\"));
    }

    private String getPackageName(String path){
        return getPackagePath(path).substring(getPackagePath(path).lastIndexOf("\\") + 1);
    }

    private String getClassName(String path){
        return (path.substring(path.lastIndexOf("\\") + 1)).substring(0,(path.substring(path.lastIndexOf("\\") + 1)).indexOf("."));
    }

    public void addVariablesInClass() throws IOException {
        Boolean[][] matriceOccurence = new Boolean[fileNameArray.size()][fileNameArray.size()];
        for (int i = 0; i < fileNameArray.size(); i++) {
            Arrays.fill(matriceOccurence[i],false);
        }
        BufferedReader tmpReader;
        ArrayList<String> varName = new ArrayList<>();
        int i = 0;
        for (String s : filesPath) {

            tmpReader = new BufferedReader(new FileReader(s));
            Scanner sc = new Scanner(tmpReader);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if(!line.contains("import") && !line.contains("package")){
                    for (int j = 0; j < fileNameArray.size(); j++) {
                        //si on a pas déjà vue cette variable
                        //et que la ligne contient la variable
                        //et que le nom de la class n'est pas égale au nom de la variable
                        if (!matriceOccurence[i][j] && !matriceOccurence[j][i] && line.contains(" " + fileNameArray.get(j) + " ") && !getClassName(s).equals(fileNameArray.get(j))) {
                            if(!line.contains("extends")) {
                                classNamesWithVariables.get(getClassName(s)).add(fileNameArray.get(j));
                                varName.add(fileNameArray.get(j));
                                matriceOccurence[i][j] = true;
                            }else{
                                matriceOccurence[i][j] = true;
                                classNamesWithVariables.get(fileNameArray.get(j)).add(getClassName(s));
                            }
                        }
                    }
                }
            }
            i++;
        }

    }



    private void endFile() throws IOException {
        writer.append("\n" +
                "</featureModel>");
        writer.close();
    }
}