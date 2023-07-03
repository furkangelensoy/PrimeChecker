import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        String numberLength = Integer.toString(number);
        System.out.println(primeCounter(probablyNumber(number, numberLength)));

    }

    public static int[] probablyNumber(int number, String numberLength) {
        int tempnumber = number;
        int remainder;
        int[] digits = new int[numberLength.length()];
        int[] repeatNumber = new int[numberLength.length()];
        int sorting = 0;
        while (tempnumber > 0) {
            remainder = tempnumber % 10;
            tempnumber /= 10;
            digits[sorting] = remainder;
            repeatNumber[sorting] = remainder;
            sorting++;
        }

        int[] repeatCounter = new int[sorting];


        for (int i = 0; i < repeatNumber.length; i++) {
            int repeatCount = 1;
            for (int j = i + 1; j < repeatNumber.length; j++) {
                if (repeatNumber[i] == repeatNumber[j]) {
                    repeatCount++;
                }
            }
            repeatCounter[i] = repeatCount;
        }
        int repeatFactorial = 1;
        for (int i = 0; i < repeatCounter.length; i++) {
            repeatFactorial *= repeatCounter[i];
        }


        Random random = new Random();

        int loop = 1;
        for (int j = 1; j <= numberLength.length(); j++) {
            loop *= j;
        }
        int probablyNumberCount = loop / repeatFactorial;
        int[] probablyNumbers = new int[probablyNumberCount];
        int count = 0;

        while (count < probablyNumberCount) {
            for (int i = 0; i < digits.length; i++) {
                int randomIndex = random.nextInt(digits.length);
                int temp = digits[randomIndex];
                digits[randomIndex] = digits[i];
                digits[i] = temp;
            }

            int newNumber = 0;
            for (int i = 0; i < digits.length; i++) {
                newNumber = newNumber + (int) Math.pow(10, i) * digits[i];
            }

            count++;
            int notEqual = 0;
            probablyNumbers[count - 1] = newNumber;
            for (int i = 0; i < probablyNumbers.length; i++) {
                if (probablyNumbers[count - 1] != probablyNumbers[i]) {
                    notEqual++;

                }
            }
            if (notEqual != probablyNumberCount - 1) {
                count--;
            }

        }

        return probablyNumbers;

    }

    public static int primeCounter(int[] probablyNumbers) {

        int primeCounter = 0;
        for (int i = 0; i < probablyNumbers.length; i++) {
            int divideCount = 0;
            for (int j = 2; j < probablyNumbers[i]; j++) {
                if (divideCount > 0) {
                    continue;
                }
                if (probablyNumbers[i] % j == 0) {
                    divideCount++;
                } else {
                    if (j == probablyNumbers[i] - 1 && divideCount == 0) {
                        primeCounter++;
                    }
                }
            }
            if (primeCounter > 0) {
                return 1;
            }
        }
        return 0;

    }



}