package decoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Calculator {

    static CalculatorFunctions calculatorFunctions = new CalculatorFunctions();
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {


        String line;

        while (true) {
            System.out.println("Which calculation: (0 to exit)");
            System.out.println("1 = Add hexadecimal with decimal");
            System.out.println("2 = Add hexadecimal with hexadecimal");
            System.out.println("3 = Add binary with binary (Two complements)");
            System.out.println("4 = Decimal to hexadecimal");
            System.out.println("5 = Hexadecimal to decimal");
            System.out.println("6 = Binary to decimal (Two complements)");
            System.out.println("7 = Decimal to binary (Two complements)");
            System.out.println("8 = Hexadecimal to binary (Two complements)");
            System.out.println("9 = Binary to hexadecimal (Two complements)");

            try {
                line = reader.readLine();
                int command = Integer.parseInt(line);
                if (command == 0) {
                    return;
                } else if (command < 0 || 9 < command) {
                    throw new IllegalArgumentException();
                }
                commands(command);
            } catch (Exception e) {
                System.out.println("Wrong input");
            }
        }

    }

    public static void commands(int command) throws IOException {
        String line;
        switch (command) {
            case 1 -> {
                while (true) {
                    System.out.println("Enter hexadecimal: (Empty line to exit)");
                    String hexadecimal;
                    line = reader.readLine();
                    try {
                        if (line.isEmpty()) {
                            return;
                        }
                        if (!line.matches("^[0-9A-Fa-f]+$")) {
                            throw new IllegalArgumentException();
                        }
                        hexadecimal = line;
                        System.out.println("Enter decimal");
                        line = reader.readLine();
                        int decimal = Integer.parseInt(line);
                        System.out.println();
                        String newHex = calculatorFunctions.hexadecimalAddHexadecimalWithDecimal(hexadecimal, decimal);
                        System.out.println("Hexadecimal of " + hexadecimal + " + decimal of : " + decimal + " is equal to " + newHex);
                        System.out.println("Incremented value would be " + calculatorFunctions.hexadecimalAddHexadecimalWithDecimal(newHex, 1));
                        incrementComment();
                    } catch (Exception e) {
                        System.out.println("Wrong input");
                    }
                }
            }
            case 2 -> {
                while (true) {
                    System.out.println("Enter first hexadecimal: (Empty line to exit)");
                    String hexadecimal1, hexadecimal2;
                    line = reader.readLine();
                    try {
                        if (line.isEmpty()) {
                            return;
                        }
                        if (!line.matches("^[0-9A-Fa-f]+$")) {
                            throw new IllegalArgumentException();
                        }
                        hexadecimal1 = line;
                        System.out.println("Enter second hexadecimal");
                        line = reader.readLine();
                        if (!line.matches("^[0-9A-Fa-f]+$")) {
                            throw new IllegalArgumentException();
                        }
                        hexadecimal2 = line;
                        System.out.println();
                        String newHex = calculatorFunctions.hexadecimalAddTwoHexadecimals(hexadecimal1, hexadecimal2);
                        System.out.println("Hexadecimal of " + hexadecimal1 + " + " + hexadecimal2 + " is equal to " + newHex);
                        System.out.println("Incremented value would be " + calculatorFunctions.hexadecimalAddHexadecimalWithDecimal(newHex, 1));
                        incrementComment();
                    } catch (Exception e) {
                        System.out.println("Wrong input");
                    }
                }
            }
            case 3 -> {
                while (true) {
                    System.out.println("Enter first binary number: (Empty line to exit)");
                    line = reader.readLine();
                    try {
                        if (line.isEmpty()) {
                            return;
                        }
                        if (!line.matches("^\\s*[01]+(?:\\s+[01]+)*\\s*$")) {
                            throw new IllegalArgumentException();
                        }
                        String firstBinary = line;
                        System.out.println("Enter second binary number: ");
                        line = reader.readLine();
                        if (!line.matches("^\\s*[01]+(?:\\s+[01]+)*\\s*$")) {
                            throw new IllegalArgumentException();
                        }
                        String secondBinary = line;
                        System.out.println("Enter preferred bits: (zero or empty line to get minimum bits)");
                        line = reader.readLine();
                        int preferredBits;
                        if (line.isEmpty()) {
                            preferredBits = 0;
                        } else {
                            preferredBits = Integer.parseInt(line);
                        }

                        if (line.isEmpty() || preferredBits == 0) {
                            System.out.println("Binary of " + firstBinary + " + " + secondBinary + " with minimum bits is equal to " + calculatorFunctions.addBinaryTwoComp(firstBinary, secondBinary, preferredBits));
                        } else {
                            System.out.println();
                            System.out.println("Binary of " + firstBinary + " + " + secondBinary + " with " + preferredBits + " bits is equal to " + calculatorFunctions.addBinaryTwoComp(firstBinary, secondBinary, preferredBits));
                        }
                    } catch (LowPreferredBitExecption lp) {
                        System.out.println(lp.getMessage());
                    } catch (Exception e) {
                        System.out.println("Wrong input");
                    }
                }
            }


            case 4 -> {
                while (true) {
                    int number;
                    System.out.println("Enter number: (Empty line to exit)");
                    line = reader.readLine();
                    try {
                        if (line.isEmpty()) {
                            return;
                        }
                        number = Integer.parseInt(line);
                        System.out.println();
                        System.out.println("Hexadecimal of " + number + " is equal to " + calculatorFunctions.convertDecimalToHexadecimal(number));

                        System.out.println("Incremented value would be " + calculatorFunctions.convertDecimalToHexadecimal(number + 1));
                        incrementComment();
                    } catch (Exception e) {
                        System.out.println("Wrong input");
                    }
                }
            }
            case 5 -> {
                while (true) {
                    System.out.println("Enter number: (Empty line to exit)");
                    line = reader.readLine();
                    try {
                        if (line.isEmpty()) {
                            return;
                        }
                        if (!line.matches("^[0-9A-Fa-f]+$")) {
                            throw new IllegalArgumentException();
                        }
                        System.out.println();
                        System.out.println("Decimal of " + line + " is equal to " + calculatorFunctions.convertHexadecimalToDecimal(line));
                        System.out.println("Incremented value would be " + calculatorFunctions.convertHexadecimalToDecimal(line, 1));
                        incrementComment();
                    } catch (Exception e) {
                        System.out.println("Wrong input");
                    }
                }
            }
            case 6 -> {
                while (true) {
                    System.out.println("Enter number: (Empty line to exit)");
                    line = reader.readLine();
                    try {
                        if (line.isEmpty()) {
                            return;
                        }
                        if (!line.matches("^\\s*[01]+(?:\\s+[01]+)*\\s*$")) {
                            throw new IllegalArgumentException();
                        }
                        System.out.println();
                        System.out.println("Decimal of " + line + " is equal to " + calculatorFunctions.convertBinaryToDecimalTwoComp(line));
                    } catch (Exception e) {
                        System.out.println("Wrong input");
                    }
                }
            }
            case 7 -> {
                while (true) {
                    System.out.println("Enter number: (Empty line to exit)");
                    line = reader.readLine();
                    try {
                        if (line.isEmpty()) {
                            return;
                        }
                        int decimal = Integer.parseInt(line);
                        System.out.println("Enter preferred bits: (zero or empty line to get minimum bits)");
                        line = reader.readLine();
                        int preferredBits;
                        if (line.isEmpty()) {
                            preferredBits = 0;
                        } else {
                            preferredBits = Integer.parseInt(line);
                        }

                        if (line.isEmpty() || preferredBits == 0) {
                            System.out.println("Binary of " + decimal + " with minimum bits is equal to " + calculatorFunctions.convertDecimalToBinaryTwoComp(decimal, preferredBits));
                        } else {
                            System.out.println();
                            System.out.println("Binary of " + decimal + " with " + preferredBits + " bits is equal to " + calculatorFunctions.convertDecimalToBinaryTwoComp(decimal, preferredBits));
                        }
                    } catch (LowPreferredBitExecption lp) {
                        System.out.println(lp.getMessage());
                    } catch (Exception e) {
                        System.out.println("Wrong input");
                    }
                }
            }
            case 8 -> {
                while (true) {
                    System.out.println("Enter number: (Empty line to exit)");
                    line = reader.readLine();
                    try {
                        if (line.isEmpty()) {
                            return;
                        }
                        if (!line.matches("^[0-9A-Fa-f]+$")) {
                            throw new IllegalArgumentException();
                        }
                        System.out.println("Enter preferred bits: (zero or empty line to get minimum bits)");
                        String bits = reader.readLine();
                        int preferredBits;
                        if (bits.isEmpty()) {
                            preferredBits = 0;
                        } else {
                            preferredBits = Integer.parseInt(bits);
                        }
                        if (preferredBits == 0) {
                            System.out.println("Binary of " + line + " with minimum bits is equal to " + calculatorFunctions.convertHexadecimalToBinaryTwoComp(line, preferredBits));
                        } else {
                            System.out.println();
                            System.out.println("Binary of " + line + " with " + preferredBits + " bits is equal to " + calculatorFunctions.convertHexadecimalToBinaryTwoComp(line, preferredBits));
                        }
                    } catch (LowPreferredBitExecption lp) {
                        System.out.println(lp.getMessage());
                    } catch (Exception e) {
                        System.out.println("Wrong input");
                    }
                }
            }
            case 9 -> {
                while (true) {
                    System.out.println("Enter number: (Empty line to exit)");
                    line = reader.readLine();
                    try {
                        if (line.isEmpty()) {
                            return;
                        }
                        if (!line.matches("^\\s*[01]+(?:\\s+[01]+)*\\s*$")) {
                            throw new IllegalArgumentException();
                        }
                        System.out.println();
                        System.out.println("Hexadecimal of " + line + " is equal to " + calculatorFunctions.convertBinaryTwoCompToHexadecimal(line));
                    } catch (Exception e) {
                        System.out.println("Wrong input");
                    }
                }
            }
        }
    }

    public static void incrementComment() {
        System.out.println("Incremented value applicable for: BR, LEA, LD, LDI, ST, STI. (address)");
    }
}