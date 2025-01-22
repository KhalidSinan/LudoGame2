import java.util.HashMap;
import java.util.Map;

public class Dice {
    Map<Integer, Double> prob = new HashMap<>();
    int[] throwHistory = new int[3];
    int throwCount = 0;

    int rollDice() {
        throwCount++;
        probCalc();
        int diceNumber = cumulativeProbability();

        if (throwCount == 1) {
            throwHistory[0] = diceNumber;
        } else if (throwCount == 2) {
            throwHistory[1] = diceNumber;
        } else if (throwCount == 3) {
            throwHistory[2] = diceNumber;
        }
        clearThrowHistory();
        return diceNumber;
    }

    void clearThrowHistory() {
        throwHistory = new int[3];
        throwCount = 0;
    }

    void probCalc() {

        for (int i = 1; i <= 6; i++) {
            prob.put(i, 1.0 / 6.0);

        }
        if (throwCount > 1 && throwHistory[0] == 6) {

            double conditionalProbability = 0.5 * prob.get(6);
            prob.put(6, conditionalProbability);

            double remainingProbability = (1.0 - conditionalProbability);
            double adjustment = remainingProbability / 5.0;
            for (int i = 1; i <= 6; i++) {
                if (i != 6) {
                    prob.put(i, prob.get(i) + adjustment);
                }
            }
        }


        if (throwCount > 2 && throwHistory[0] == 6 && throwHistory[1] == 6) {
            double conditionalProbability = 0.25 * prob.get(6);
            prob.put(6, conditionalProbability);

            double remainingProbability = (1.0 - conditionalProbability);
            double adjustment = remainingProbability / 5.0;
            for (int i = 1; i <= 6; i++) {
                if (i != 6) {
                    prob.put(i, prob.get(i) + adjustment);
                }
            }
        }


    }


    int cumulativeProbability() {
        double randomValue = Math.random();
        double cumulativeProbability = 0.0;

        for (int i = 1; i <= 6; i++) {
            cumulativeProbability += prob.get(i);
            if (randomValue <= cumulativeProbability) {
                return i;
            }
        }


        return 6;
    }

}


