import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    public static void main(String[] args)
    {
        //Tableau test = new Tableau("excelCourse/src/main/resources/Cross/2005-05BIBLIO_CR-L1.xls","Feuil1");
        //System.out.println(test);
        List<String> filePath = null;
        ArrayList<Tableau> tab = new ArrayList<Tableau>();
        try (Stream<Path> walk = Files.walk(Paths.get("excelCourse/src/main/resources"))) {
            filePath = walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(".xls")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String tmp = "";
        for(int i = 0 ; i < filePath.size();i++){
            tmp = filePath.get(i);
            tab.add(new Tableau(tmp));
            filePath.set(i,tmp.substring(tmp.lastIndexOf("\\")+1));
        }
        GenerateDiffFile diff = new GenerateDiffFile();
        try {
            diff.generateHeaderDiffSheet(tab,filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
