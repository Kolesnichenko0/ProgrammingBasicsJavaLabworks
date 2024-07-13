package part2.labwork5.first.service;

import part2.labwork5.first.dao.DAOManager;
import part2.labwork5.first.dao.DayDAO;
import part2.labwork5.first.model.DayForDB;

import java.util.List;

/**
 * The DayService class provides services related to the DayForDB model.
 * It uses the DAOManager to interact with the database and perform CRUD operations on the 'days' table.
 * This class is part of the Service layer in the application architecture, which acts as a bridge between the Controller layer and the DAO layer.
 */
public class DayService {
    private DayDAO dayDAO;

    /**
     * Constructs a new DayService object.
     *
     * @param daoManager the DAOManager object that provides access to the DAO layer.
     */
    public DayService(DAOManager daoManager) {
        this.dayDAO = daoManager.getDayDAO();
    }

    /**
     * Retrieves all DayForDB objects from the database.
     *
     * @return a List of DayForDB objects.
     */
    public List<DayForDB> getAllDays() {
        return dayDAO.getAllDays();
    }

    /**
     * This method is used to add a day to a specific weather season.
     *
     * @param weatherSeason The season of the weather to which the day will be added.
     * @param day           The DayForDB object that represents the day to be added.
     */
    public void addDay(String weatherSeason, DayForDB day) {
        dayDAO.addDay(weatherSeason, day);
    }

    /**
     * This method is used to remove a specific day from the database.
     *
     * @param weatherSeason The season of the weather for which the day is to be removed.
     * @param date          The date of the day to be removed in the format "yyyy-MM-dd".
     */
    public void removeDay(String weatherSeason, String date) {
        dayDAO.removeDay(weatherSeason, date);
    }

    /**
     * This method is used to find days with a specific word fragment in the comment.
     *
     * @param wordFragment The word fragment to be searched for in the comment.
     * @return List of days with the specified word fragment in the comment.
     */
    public List<DayForDB> findDaysWithWordFragment(String wordFragment) {
        return dayDAO.findDaysWithWordFragment(wordFragment);
    }

    /**
     * This method is used to find days with a specific word fragment in the comment for a specific season.
     *
     * @param wordFragment  The word fragment to be searched for in the comment.
     * @param weatherSeason The season of the weather.
     * @return List of days with the specified word fragment in the comment for the specified season.
     */
    public List<DayForDB> findDaysWithWordFragment(String wordFragment, String weatherSeason) {
        return dayDAO.findDaysWithWordFragment(wordFragment, weatherSeason);
    }

    /**
     * This method is used to find days with the maximum temperature.
     *
     * @return List of days with the maximum temperature.
     */
    public List<DayForDB> findMaxTemperatureDays() {
        return dayDAO.findMaxTemperatureDays();
    }

    /**
     * This method is used to find days with the maximum temperature for a specific season.
     *
     * @param weatherSeason The season of the weather.
     * @return List of days with the maximum temperature for the specified season.
     */
    public List<DayForDB> findMaxTemperatureDays(String weatherSeason) {
        return dayDAO.findMaxTemperatureDays(weatherSeason);
    }

    /**
     * This method is used to find days with the longest comment.
     *
     * @return List of days with the longest comment.
     */
    public List<DayForDB> findLongestCommentDays() {
        return dayDAO.findLongestCommentDays();
    }

    /**
     * This method is used to find days with the longest comment for a specific season.
     *
     * @param weatherSeason The season of the weather.
     * @return List of days with the longest comment for the specified season.
     */
    public List<DayForDB> findLongestCommentDays(String weatherSeason) {
        return dayDAO.findLongestCommentDays(weatherSeason);
    }
}
