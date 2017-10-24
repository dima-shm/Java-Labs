package shm.dim.lab_5.calc_calories;

import shm.dim.lab_5.person.Person;

public class CalculatorCalories {

    private static double arm;
    private static double bmr;

    private static double calcBMR(String sex, int weight, int height, int age) {
        if(sex.equals("ж"))
            return (655.0955 + ( 9.5634 * weight) + ( 1.8496 * height) - (4.6756 * age));
        else
            return (66.4730 + ( 13.7516 * weight) + (5.0033 * height - (6.7550 * age)));
    }

    private static double getArm(String activityLevel) {
        switch (activityLevel) {
            case "Низкая":
                return 1.2;
            case "Невысокая":
                return 1.375;
            case "Умеренная":
                return 1.55;
            case "Высокая":
                return 1.725;
            case "Экстремальная":
                return 1.9;
            default:
                return 1.2;
        }
    }

    public static double calcCalories(Person person) {
        bmr = calcBMR(person.getSex(), person.getWeight(), person.getHeight(), person.getAge());
        arm = getArm(person.getActivityLevel());;
        return Math.round(bmr * arm);
    }
}