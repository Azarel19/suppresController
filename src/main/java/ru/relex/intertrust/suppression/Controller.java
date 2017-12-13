package ru.relex.intertrust.suppression;

import java.util.List;

public interface Controller {
    void start(String suppressionFilename,String dir,List<SuppressionChecker> listOfChekers);


    /*
		старт запускает методы из FindDeletedClasses для объектов и отображает статистику в угодном вам виде
		(время работы, результат работы)
     */
}
