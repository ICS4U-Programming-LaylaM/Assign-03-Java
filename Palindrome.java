import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
* This program takes a number and checks if it is a
*  palindrome or not. If not, it adds the reverse of the
*  number to itself and checks again.
*
* @author Layla Michel
* @version 1.0
* @since 2022-04-11
*/

class Palindrome {
    /**
    * Default empty constructor.
    */
    Palindrome() { }

    /**
    * Creating function to find the palindrome depth of
    * each number in the input.txt.
    *
    * @param number as long
    * @param depthInt as int
    *
    * @return returnArray as long array
    */
    public long[] depth(long number, int depthInt) {
        final String numberStr = Long.toString(number);
        String newString = "";

        // Create a reverse string of the numbers as strings
        for (int index = 0; index < numberStr.length(); index++) {
            final char chars = numberStr.charAt(index);
            newString = chars + newString;
        }

        final long reverseNum = Long.parseLong(newString);

        // Check if the number as a string is a palindrome
        if (newString.equals(numberStr)) {
            // Create array with the depth and palindrome number
            final long[] returnArray = {0, 1};
            returnArray[0] = Long.valueOf(depthInt);
            returnArray[1] = reverseNum;

            return returnArray;
        } else {
            // Call the function again if the number is not
            // a palindrome
            return depth(number + reverseNum, depthInt + 1);
        }

    }

    /**
    * Creating main function.
    *
    * @param args nothing passed in
    * @throws IOException if no file is passed in
    */
    public static void main(String[] args)
            throws IOException {
        // Declaring variables
        final String[] stringArray;
        final String[] depthArr;
        int counter;
        long[] depthArray = {0, 1};
        final int baseDepth = 0;
        String string;

        // Create a palindrome object
        final Palindrome palindrome = new Palindrome();

        final List<String> listOfStrings =
            new ArrayList<String>();

        BufferedReader bf = null;
        try {
            // Check if there are some arguments
            if (null != args[0]
                // Length > 4 because a.txt will be shortest filename
                && args[0].length() > 4
                // Check if arguments have the correct file extension
                && args[0].endsWith(".txt")) {
                bf = new BufferedReader(new FileReader(args[0]));
            }

            String line = bf.readLine();
            // Add file elements to list
            while (line != null) {
                listOfStrings.add(line);
                line = bf.readLine();
            }

            // Create array of strings of the size of
            // the list
            stringArray = listOfStrings.toArray(new String[0]);

            final List<String> depthString =
                new ArrayList<String>();

            // Check each number from the input.txt
            for (counter = 0; counter < stringArray.length; counter++) {
                depthArray = palindrome.depth(Long.parseLong(
                    stringArray[counter]), baseDepth);
                // Create string that displays the depth
                // of each number and their palindrome
                string = "The number " + stringArray[counter]
                     + " is a depth " + depthArray[0]
                     + " palindrome. Palindrome: "
                     + depthArray[1];

                // Add to a new array
                depthString.add(string);
            }

            depthArr = depthString.toArray(new String[0]);

            // Build a string containing the elements
            // of the array
            final StringBuilder builder = new StringBuilder();
            for (counter = 0; counter < depthArr.length; counter++) {
                builder.append(depthArr[counter]);
                builder.append("\n");
            }

            // Create new file called "output.txt"
            final BufferedWriter writer = new BufferedWriter(new
                    FileWriter("/home/runner/Assign-03-Java/output.txt"));
            writer.write(builder.toString());
            writer.close();
            System.out.println("Results added to 'output.txt'");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Text file needed.");
        }
    }
}
