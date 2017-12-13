package ru.relex.intertrust.suppression;

import java.util.ArrayList;
import java.util.List;

/**
 * Утилитный класс для хранения всех реализаций интерфейсов SuppressionChecker и Controller
 */
public class Registrator {

    /**
     * Приватный пустой конструктор необходим для утилитного класса
     */
    private Registrator() { }

    /**
     * Список всех реализаций интерфейса SuppressionChecker
     */
    private static List<SuppressionChecker> checkers = new ArrayList<SuppressionChecker>();

    /**
     * Список всех реализаций интерфейса Controller
     */
    private static List<Controller> controllers = new ArrayList<Controller>();

    /**
     * Функция, возвращающая список реализаций интерфейса SuppressionChecker для чтения
     * @return Список реализаций интерфейса
     */
    public static List<SuppressionChecker> getCheckers() {
        return checkers;
    }

    /**
     * Функция, возвращающая список реализаций интерфейса Controller для чтения
     * @return Список реализаций интерфейса
     */
    public static List<Controller> getControllers() {
        return controllers;
    }

    /**
     * Функция регистрации конкретной реализации интерфейса SuppressionChecker
     * @param checker Реализация интерфейса, которую требуется зарегистрировать
     */
    public static void register(SuppressionChecker checker) {
        checkers.add(checker);
    }

    /**
     * Функция регистрации конкретной реализации интерфейса Controller
     * @param controller Реализация интерфейса, которую требуется зарегистрировать
     */
    public static void register(Controller controller) {
        controllers.add(controller);
    }

    //public static String getDeveloperName(interfaces.SuppressionChecker name ){};
    //return developerName;};
}
