package evgeny;

import ru.relex.intertrust.suppression.Controller;
import ru.relex.intertrust.suppression.SuppressionChecker;

import java.util.List;

/**
 * Класс запускает методы из FindDeletedClasses для объектов и отображает статистику.
 * @author Евгений Воронин
 */
public class EvgenyController implements Controller {
    public void start(String suppressionFilename, String dir, List<SuppressionChecker> listOfChekers) {
        System.out.println("Тестирую реализацию " + listOfChekers.getDeveloperName());
        long startTime = System.currentTimeMillis();
        List<String> directory = listOfChekers.dir(dir);
        List<String> suppressionFileNames = listOfChekers.parseSuppression(suppressionFilename);
        List<String> deletedFiles = listOfChekers.findDeletedFiles(directory, suppressionFileNames);
        long time = System.currentTimeMillis() - startTime;
        System.out.println("Время работы реализации " + listOfChekers.getDeveloperName() + " " + time + " миллисекунд");
        System.out.println("Следующие исключения не были найдены:");
        for (String files : deletedFiles) {
            System.out.println("<suppress files='" + files + "' />");
        }
        System.out.println("Всего исключений не найдено: " + deletedFiles.size());
    }
}
