package ru.relex.intertrust.suppression;

import java.util.List;

/**
 * Интерфейс для поиска удаленных файлов в файле suppressions.xml
 */
public interface SuppressionChecker
{

    /**
     * Функция получения имен java-классов, которые указаны в файле suppressions.xml
     * @param fullFileName Полное имя к файлу suppressions.xml
     * @return Список имен java-классов, указанных в файле
     */
    List<String> parseSuppression(String fullFileName);

    /**
     * Функция получения списка всех java-классов, находящихся в заданной директории
     * @param path Путь, с которого будет начинаться поиск java-классов
     * @return Список полных имен java-классов, найденных в заданной директории
     */
    List<String> dir(String path);

    /**
     * Функция поиска файлов, которые указаны в файле suppressions.xml,
     * но которые были удалены из проекта
     * @param suppressedFileNames Список имен java-классов из файла suppressions.xml
     * @param dir Список полных имен java-классов проекта, для которого выполняется проверка
     * @return Список имен java-классов, которые были удалены из проекта
     */
    List<String> findDeletedFiles(List<String> suppressedFileNames, List<String> dir);

    String getDeveloperName();//остается здесь. Выводит имя автора.
}
