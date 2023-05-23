package decoding;


public class CalculatorFunctions {


    public static void main(String[] args) {

       /* String test = null;
        try {
            test = convertDecimalToBinaryTwoComp(-16, 0);
        } catch (LowPreferredBitExecption e) {
            throw new RuntimeException(e);
        }
        System.out.println(test);*/
    }


    public String convertDecimalToHexadecimal(int decimal) {

        StringBuilder hexadecimal = new StringBuilder(272);
        int remainder = decimal;

        do {
            hexadecimal.insert(0, Integer.toHexString(remainder % 16).toUpperCase());
            remainder = remainder / 16;
        }
        while (remainder >= 16);
        hexadecimal.insert(0, Integer.toHexString(remainder % 16).toUpperCase());

        return hexadecimal.toString();
    }


    public String hexadecimalAddHexadecimalWithDecimal(String hexadecimal, int decimal) {
        int first = convertHexadecimalToDecimal(hexadecimal);
        return convertDecimalToHexadecimal(first + decimal);
    }

    public String hexadecimalAddTwoHexadecimals(String hexadecimal1, String hexadecimal2, int increment) {
        int first = convertHexadecimalToDecimal(hexadecimal1);
        int second = convertHexadecimalToDecimal(hexadecimal2);

        return convertDecimalToHexadecimal(first + second + increment);
    }

    public String hexadecimalAddTwoHexadecimals(String hexadecimal1, String hexadecimal2) {
        return hexadecimalAddTwoHexadecimals(hexadecimal1, hexadecimal2, 0);
    }


    public int convertHexadecimalToDecimal(String hexadecimal, int increment) {

        int result = 0;

        char[] values = hexadecimal.toCharArray();
        int currentValue;
        int digit = 0;
        for (int i = values.length - 1; i >= 0; i--, digit++) {
            currentValue = Character.digit(values[i], 16);
            int test = (int) Math.pow(16, digit);

            result += currentValue * test;
        }

        return result + increment;

    }

    public int convertBinaryToDecimalTwoComp(String text) throws IllegalArgumentException {
        StringBuilder stringBinary = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (character == '0' || character == '1' || character == ' ') {
                if (character != ' ') {
                    stringBinary.append(character);
                }
            } else {
                throw new IllegalArgumentException();
            }
        }

        //stringBinary.toString().replaceAll("\\s+", "");
        // String trimedText = text.replaceAll("\\s+","");

/*
        int addZerosTest = (stringBinary.length() - (stringBinary.length() % 4) + 4) % stringBinary.length();
        int addZeros = addZerosTest == 4 ? 0 : addZerosTest;
        for (int i = 0; i < addZeros; i++) {
            stringBinary.insert(0, '0');
        }
*/


        char[] values = stringBinary.toString().toCharArray();

        int result = 0, digit = 0;
        char currentValue;
        for (int i = values.length - 1; i >= 0; i--, digit++) {
            currentValue = values[i];
            if (currentValue == '1' && i == 0) {
                result -= (int) Math.pow(2, digit);
            } else if (currentValue == '1') {
                result += (int) Math.pow(2, digit);
            }
        }


        // long testq = Long.parseLong(stringBinary.toString(), 2);
        // Integer.parseInt(stringBinary.toString(), 2);
        return result;
    }


    public int convertHexadecimalToDecimal(String hexadecimal) {
        return convertHexadecimalToDecimal(hexadecimal, 0);
    }

    public  String convertDecimalToBinaryTwoComp(int decimal, int preferredBits) throws LowPreferredBitExecption {

        int absValue = Math.abs(decimal);
        int numberOfBits = (int) (Math.log(absValue) / Math.log(2)) + 2;
        StringBuilder binaryString = new StringBuilder();


        if (preferredBits != 0) {
            if (preferredBits < numberOfBits) {
                throw new LowPreferredBitExecption(preferredBits, numberOfBits);
            }
        }
        int remainder = absValue;
        while (remainder > 0) {
            if (remainder % 2 == 0) {
                binaryString.insert(0, 0);
            } else {
                binaryString.insert(0, 1);
            }
            remainder = remainder / 2;
        }
        binaryString.insert(0, 0);

        while (binaryString.toString().length() < preferredBits) {
            binaryString.insert(0, 0);
        }

        if (decimal < 0) {
            char[] binArr = binaryString.toString().toCharArray();
            binaryString = new StringBuilder(binArr.length);
            boolean readyToFlip = false;

            for (int i = binArr.length - 1; i >= 0; i--) {
                if (readyToFlip) {
                    binArr[i] = binArr[i] == '0' ? '1' : '0';
                } else if (binArr[i] == '1') {
                    readyToFlip = true;
                }
                binaryString.insert(0, binArr[i]);
            }

            // Format
            formatBinary(binaryString);
            return binaryString.toString();
        }

        // Format
        formatBinary(binaryString);

        String toReturn = binaryString.toString();
        return toReturn.length() != 0 ? toReturn : "0";
    }

   /* public  String convertDecimalToBinaryTwoComp(int decimal) throws LowPreferredBitExecption {
        return convertDecimalToBinaryTwoComp(decimal, 0);
    }*/

    public String convertHexadecimalToBinaryTwoComp(String hexadecimal, int preferredBits) throws LowPreferredBitExecption {
        int decimal = convertHexadecimalToDecimal(hexadecimal);
        return convertDecimalToBinaryTwoComp(decimal, preferredBits);
    }

   /* public String convertHexadecimalToBinaryTwoComp(String hexadecimal) throws LowPreferredBitExecption {
        int decimal = convertHexadecimalToDecimal(hexadecimal);
        return convertDecimalToBinaryTwoComp(decimal, 0);
    }*/
    public String convertBinaryTwoCompToHexadecimal(String binary) {
        int decimal = convertBinaryToDecimalTwoComp(binary);
        return convertDecimalToHexadecimal(decimal);
    }
    
    public String addBinaryTwoComp (String firstBinary, String secondBinary, int preferredBits) throws LowPreferredBitExecption {
        int firstDecimal = convertBinaryToDecimalTwoComp(firstBinary);
        int secondDecimal = convertBinaryToDecimalTwoComp(secondBinary);
        
        
        return convertDecimalToBinaryTwoComp(firstDecimal + secondDecimal, preferredBits);
    }

    private void formatBinary(StringBuilder stringBuilder) {
        for (int i = stringBuilder.length() - 4; i > 0; i -= 4) {
            stringBuilder.insert(i, " ");
        }
    }
}