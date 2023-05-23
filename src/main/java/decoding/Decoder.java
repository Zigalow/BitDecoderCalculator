package decoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Decoder {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        System.out.printf("Enter 1 to execute the program: \n0 - LC3 \n");

        String input = reader.readLine();
        switch (input) {
            case "0" -> {
                System.out.println("Bin to LC3:");
                LC3simple();
            }
            default -> System.out.println("Program not executed.");
        }
    }
    public static void LC3simple() throws IOException {
        String line;
        boolean processedInput = false;
        while ((line = reader.readLine()) != null) {
            line = line.trim(); // trim the input string
            if (line.isEmpty()) {
                continue; // skip empty lines
            }
            if (line.matches("[01]{16}")) {
                try {
                    LC3Instruction instruction = LC3Instruction.decode(line);
                    String opcode = instruction.opcode.toString();
                    String dr = instruction.dr == 40000 ? "" : "R" + instruction.dr;
                    String sr1 = instruction.sr1 == 40000 ? "" : ", R" + instruction.sr1;
                    String sr2 = instruction.sr2 == 40000 ? "" : ", R" + instruction.sr2;
                    String imm5value = instruction.imm5value == 40000 ? "" : ", #" + instruction.imm5value;
                    String offset = instruction.offset == 40000 ? "" : ", #" + instruction.offset;
                    String baseR = instruction.baseR == 40000 ? "" : ", R" + instruction.baseR;
                    String trapvect8 = instruction.trapvect8 == 40000 ? "" : ", #" + instruction.trapvect8;
                    String trapMessage = instruction.trapMessage == null ? "" : ", \"" + instruction.trapMessage + "\"";
                    if (opcode.equals("BR")) {
                        // Handle BR instruction
                        boolean n = line.charAt(4) == '1';
                        boolean z = line.charAt(5) == '1';
                        boolean p = line.charAt(6) == '1';
                        if (n) {
                            dr += "n";
                        } if (z){
                            sr1 += "z";
                        } if (p){
                            sr2 += "p";
                        }
                        baseR = "";

                    }
                    else if (opcode.equals("TRAP")) {
                        opcode = "";
                        trapvect8 = "";
                        if(trapMessage.contains(",")){
                            trapMessage = trapMessage.replace(",", "");
                            trapMessage = trapMessage.replace(" ", "");
                        }

                    }
                    System.out.println(opcode + " " + dr + sr1 + sr2 + imm5value + offset + baseR + trapvect8 + trapMessage);
                    processedInput = true;
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid binary string: " + line);
                }
            } else {
                System.err.println("Invalid binary string: " + line);
            }
        }
        if (!processedInput) {
            System.out.println(); // print an empty line if no input was processed
        }
    }
}