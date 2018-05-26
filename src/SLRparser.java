
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/*
by DevMedo
Contact Info:
Name :  Mohammed Ibrahim Shalabi
Email : idevmedo@gmail.com / Moham.Shalabi@gmail.com
 */
public class SLRparser {

    static String rawInput;
    static int number_Of_Tokens = 0;

    public static void errorMessage() {
        System.out.println("Syntax Error");
        System.exit(0);
    }

    public static int getIndex(String token) throws IOException {

        /*
        To get the column number of each Terminal/non-Terminal Note:actions : Terminals , gotos: non-Terminal 
        are in the parse table as well , check the Parse Table
         */
        int indexA = -1;
        switch (token) {
            case "id":
                indexA = 0;
                break;
            case "+":
                indexA = 1;
                break;
            case "*":
                indexA = 2;
                break;
            case "(":
                indexA = 3;
                break;
            case ")":
                indexA = 4;
                break;
            case "$":
                indexA = 5;
                break;
            case "E":
                indexA = 6;
                break;
            case "T":
                indexA = 7;
                break;
            case "F":
                indexA = 8;
                break;
            default:
                errorMessage();
        }

        return indexA;
    }

    public static String[][] ParseTable() {
        //Check the attached Parse Table Picture to understand this

        String[][] parseTable = new String[12][9];
        //id
        parseTable[0][0] = "s5";
        parseTable[4][0] = "s5";
        parseTable[6][0] = "s5";
        parseTable[7][0] = "s5";

        //+
        parseTable[1][1] = "s6";
        parseTable[2][1] = "r2";
        parseTable[3][1] = "r4";
        parseTable[5][1] = "r6";
        parseTable[8][1] = "s6";
        parseTable[9][1] = "r1";
        parseTable[10][1] = "r3";
        parseTable[11][1] = "r5";

        //*
        parseTable[2][2] = "s7";
        parseTable[3][2] = "r4";
        parseTable[5][2] = "r6";
        parseTable[9][2] = "s7";
        parseTable[10][2] = "r3";
        parseTable[11][2] = "r5";

        //(
        parseTable[0][3] = "s4";
        parseTable[4][3] = "s4";
        parseTable[6][3] = "s4";
        parseTable[7][3] = "s4";

        //)
        parseTable[2][4] = "r2";
        parseTable[3][4] = "r4";
        parseTable[5][4] = "r6";
        parseTable[8][4] = "s11";
        parseTable[9][4] = "r1";
        parseTable[10][4] = "r3";
        parseTable[11][4] = "r5";

        //$
        parseTable[1][5] = "acc";
        parseTable[2][5] = "r2";
        parseTable[3][5] = "r4";
        parseTable[5][5] = "r6";
        parseTable[9][5] = "r1";
        parseTable[10][5] = "r3";
        parseTable[11][5] = "r5";

        //E
        parseTable[0][6] = "1";
        parseTable[4][6] = "8";

        //T
        parseTable[0][7] = "2";
        parseTable[4][7] = "2";
        parseTable[6][7] = "9";

        //F
        parseTable[0][8] = "3";
        parseTable[4][8] = "3";
        parseTable[6][8] = "3";
        parseTable[7][8] = "10";

        return parseTable;
    }

    public static String rawInput_to_inputString(String rawInput) {
        /*
        
            What does this method do ?
            Okay , it takes the input in this form for an example
            and convert it to this form            
                       rawInput -> InputString
                    (id+id)*id$ -> ( id + id ) * id $
        
            Why ? 
        
            Because we are using the StringTokenizer which takes a Token of a String until it finds a 'space'
            ST will take ( and then we can iterate it to take the next Tokens id and then the + , id , ) .. 
            and so on til the end of the string
        
         */

        String inputString = "";
        while (rawInput.charAt(0) != '$') {
            switch (rawInput.charAt(0)) {
                case 'i':
                    rawInput = rawInput.substring(2);
                    inputString = inputString.concat("id ");
                    break;
                case '+':
                    rawInput = rawInput.substring(1);
                    inputString = inputString.concat("+ ");
                    break;
                case '*':
                    rawInput = rawInput.substring(1);
                    inputString = inputString.concat("* ");
                    break;
                case '(':
                    rawInput = rawInput.substring(1);
                    inputString = inputString.concat("( ");
                    break;
                case ')':
                    rawInput = rawInput.substring(1);
                    inputString = inputString.concat(") ");
                    break;
                case 'E':
                    rawInput = rawInput.substring(1);
                    inputString = inputString.concat("E ");
                    break;
                case 'T':
                    rawInput = rawInput.substring(1);
                    inputString = inputString.concat("T ");
                    break;
                case 'F':
                    rawInput = rawInput.substring(1);
                    inputString = inputString.concat("F ");
                    break;
                default:
                    errorMessage();
            }
        }
        inputString = inputString.concat("$");
        return inputString;
    }

    public static String rule(String action) {
        /*
        These are the rules we have 
        the Rs in the Parse Table
         */
        String r = "";

        switch (action) {
            case "r1":
                r = "E → E+T";
                break;
            case "r2":
                r = "E → T";
                break;
            case "r3":
                r = "T → T*F";
                break;
            case "r4":
                r = "T → F";
                break;
            case "r5":
                r = "F → (E)";
                break;
            case "r6":
                r = "F → id";
                break;

        }
        return r;
    }

    public static String remainingInput(String inputString, int number_Of_Tokens, int tokenCount) {
        /*
        Returns the remaining input after Shifting the next Token from the input to the stack (push it to the stack with the number after the s)
        And updates the input column in the output (check output)
         */
        StringTokenizer st = new StringTokenizer(inputString);
        String remaining = "";

        for (int j = 0; j < number_Of_Tokens; j++) {
            st.nextToken();
        }

        for (int j = number_Of_Tokens; j < tokenCount; j++) {
            remaining = remaining.concat(st.nextToken());
        }

        return remaining;
    }

    public static void main(String[] args) throws IOException {

        String[][] parseTable = ParseTable();
        File f = new File("input.txt");// input file name
        File ou = new File("output.txt");
        PrintWriter pw = new PrintWriter(ou);

        Scanner input = new Scanner(f);
        while (input.hasNext()) {
            rawInput = input.next();
        }

        String inputString = rawInput_to_inputString(rawInput);
        StringTokenizer st = new StringTokenizer(inputString);
        System.out.println("Input String : " + rawInput);
        System.out.println("\nStack\t\t\t\t\t    Input\tAction");
        pw.println("Input String : " + rawInput);
        pw.println("\nStack\t\t\t\t\t    Input\tAction");

        Stack s = new Stack();
        s.push("0");
        // Initialize the Stack with 0
        int tokenCount = st.countTokens();
        String str = st.nextToken();
        do {

            int index = getIndex(str);

            String action = parseTable[Integer.parseInt((String) s.peek())][index];
            /*
            Look at the last Element in the stack , and the first Token in the input
            these two defines the index of a cell in the Parse Table which holds the action
            
            for an example :-
            
            
            +---------------------------------------------+
            |S                                            |
            |   stack   input                             |   
            |   [0]     (                                 |  
            |                                             |           
            |Action:-                                     |
            |   [0][(]                                    |
            | ->[0][3] : S4                               |
            | if we look in the Parse Table we'll find S4 |
            | S4 is the Action : Shift by   S4            |
            +---------------------------------------------+
            
            
            +---------------------------------------------+
            |R                                            |
            |                                             |
            |   stack              input                  |
            |   [0, (, 4, id, 5]   +id)*id$               |
            |                                             |
            |   Action:-                                  |
            |    [5][+]                                   |
            |  ->[5][1] : R6                              |
            |R6 is the Action : Reduce by  F → id ( r6 ) |
            +---------------------------------------------+
            
             */
            switch (action.charAt(0)) {
                case 's':
                    /*
                        if we got S 
                        we'll shift the next Token : 
                        1- Push it in the stack
                        2- Push the number of Shifting (s4 -> 4 , s5 -> 5..)
                        3- Update the remaining input
                    
                        for an example
                        stack   input           action
                        [0]     (id+id)*id$     Shift by   s4
     after changes: ->  [0,(,4]  id+id)*id$ 
                            
                    
                    
                     */
                    String number = parseTable[Integer.parseInt((String) s.peek())][index].substring(1);

                    String ri = remainingInput(inputString, number_Of_Tokens, tokenCount);
                    number_Of_Tokens++;
                    System.out.printf("%-30s %20s %s %s\n", s.toString(), ri, "Shift by  ", action);
                    pw.printf("%-30s %20s %s %s\n", s.toString(), ri, "Shift by  ", action);
                    s.push(str);
                    s.push(number);
                    str = st.nextToken();
                    break;
                case 'r':
                    /* if we got R , we'll do changes in the stack. popping and pushing.

                            stack             input                  
                           [0, (, 4, id, 5]   +id)*id$               

                           Action:-                                  
                            [5][+]                                   
                          ->[5][1] : R6                              
                        R6 is the Action : Reduce by  F → id ( r6 ) 
                        Number of POPS from the stack = (Number of (Terminal or non-Terminal) on the RHS) * 2
                        pops=1*2 -> 2
                    
                        1- Pop two times :Now our stack is like this
                           [0, (, 4]
                        2- Now check the last Element in the stack with the LHS of the Rule which is F in the parse table
                           [4][F]
                         ->[4][8]: 3
                        3- Push F , Push 3
                    
                        Stack            input
   after changes ->    [0, (, 4, F, 3]  +id)*id$
                 
                    
                     */
                    
                    //number(S4) -> 4 | number(R6) -> 6 and so on .. 
                    number = parseTable[Integer.parseInt((String) s.peek())][index].substring(1);

                    String rule = rule(action);
                    String rin = remainingInput(inputString, number_Of_Tokens, tokenCount);
                    System.out.printf("%-30s %20s %s %s %s %s %s\n", s.toString(), rin, "Reduce by  ", rule, "(", action, ")");
                    pw.printf("%-30s %20s %s %s %s %s %s\n", s.toString(), rin, "Reduce by  ", rule, "(", action, ")");

                    /*
                           How many times do we pop from the stack ?
                    
                            r1 = E → E+T (Type 1)
                            r2 = E → T   (Type 2)
                            r3 = T → T*F (Type 1)
                            r4 = T → F   (Type 2)
                            r5 = F → (E) (Type 1)
                            r6 = F → id  (Type 2)

                            As you can see , The rules we have are only two types

                            Type 1 : Three (Terminal or non-Terminal) on the RHS of the rule
                            Type 2 : One (Terminal or non-Terminal) on the RHS of the rule

                            Number of POPS from the stack = (Number of (Terminal or non-Terminal) on the RHS) * 2
                            therefore :-
                            Type 1 : 6 pops
                            Type 2 : 2 pops

                            and as we can see the number of rules that are from type 2 are 
                            2 , 4 , and 6 
                            aka Even numbers
                            so if the rule number is EVEN (number%2==0)
                            number of pops = 2

                            Bingo !
                     */
                    int popTimes = 6;
                    if (Integer.parseInt(number) % 2 == 0) {
                        popTimes = 2;
                    }
                    for (int i = 0; i < popTimes; i++) {
                        s.pop();
                    }

                    String ls = "";
                    switch (Integer.parseInt(number)) {
                        case 1:
                            ls = "E";
                            break;
                        case 2:
                            ls = "E";
                            break;
                        case 3:
                            ls = "T";
                            break;
                        case 4:
                            ls = "T";
                            break;
                        case 5:
                            ls = "F";
                            break;
                        case 6:
                            ls = "F";
                            break;
                    }
                    // ls stands for Left Side of the Rule, LHS the Non-Terminal
                    int indexA = -1;
                    switch (ls.charAt(0)) {
                        case 'E':
                            indexA = 6;
                            break;
                        case 'T':
                            indexA = 7;
                            break;
                        case 'F':
                            indexA = 8;
                            break;
                    }
                    /*
                        ls -> F
                        num([4][F]) -> [4][F] -> 3
                        push F , Push 3
                    */
                    int num = Integer.parseInt(parseTable[Integer.parseInt((String) s.peek())][indexA]);
                    s.push(ls + "");
                    s.push(num + "");

                    break;
                case 'a':
                    // this is the end of parsing, when it gets to [1][$] Accept : Successfully
                    pw.printf("%-49s %s\n", s.toString(), "$ ACCEPT");
                    System.out.printf("%-49s %s\n", s.toString(), "$ ACCEPT");
                    pw.flush();
                    pw.close();
                    System.exit(0);
            }
        } while (true);
    }
}
