package evgeny;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.relex.intertrust.suppression.SuppressionChecker;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Евгений Воронин
 */
public class FindDeletedClasses implements SuppressionChecker {
    //    static {
//        ru.relex.intertrust.suppression.Registrator.register(new FindDeletedClasses());
//    }
    public List<String> parseSuppression(String fullFileName) {
        List<String> suppressionList = new ArrayList<String>();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fullFileName);

            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("suppress");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (eElement.getAttribute("files").matches(".*?(java)$")) {
                        suppressionList.add(eElement.getAttribute("files"));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return suppressionList;
    }

    public List<String> dir(String path) {
        File directory = new File(path);
        ArrayList<String> classes = new ArrayList<String>();

        if (path.endsWith(".txt")) {
            String line;
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
                while ((line = bufferedReader.readLine()) != null){
                    if (!line.matches(".*?target.*?")){
                        classes.add(line);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try {
                File[] files = directory.listFiles();
                for (int i = 0; i < files.length; i++) {
                    String fileName = files[i].getName();
                    String className = null;

                    if (fileName.endsWith(".java")) {
                        if (!files[i].getAbsolutePath().matches(".*?target.*?")){
                            className = files[i].getAbsolutePath();
                        }
                    }

                    if (className != null) {
                        classes.add(className);
                    }

                    File subdir = new File(directory, fileName);
                    if (subdir.isDirectory()) {
                        classes.addAll(dir(subdir.getAbsolutePath()));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return classes;
    }

    public List<String> findDeletedFiles(List<String> suppressedFileNames, List<String> dir) {
        for (Iterator<String> classPathIt = dir.iterator(); classPathIt.hasNext();) {
            String classPath = classPathIt.next();
            for (Iterator<String> regExpIt = suppressedFileNames.iterator(); regExpIt.hasNext();) {
                String regExp = regExpIt.next();
                if (classPath.matches(".*?"+regExp)) {
                    classPathIt.remove();
                    regExpIt.remove();
                    break;
                }
            }
        }
        return suppressedFileNames;
    }

    public String getDeveloperName() {
        return "Евгений Воронин";
    }
}
