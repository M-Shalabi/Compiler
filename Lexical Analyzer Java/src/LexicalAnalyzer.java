
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LexicalAnalyzer_15 {

    /*
    [Name: Mohammed Ibrahim Shalabi       ID:1535530]
    [Name: Hamad Hassan Al Naime          ID:1538330]
    [Name: Abu Taleb Al-Hassan Zarban     ID:1538038]
    [Name: Rayan Yaseen Naytah            ID:1538384]
     */
    static char lookahead;
    static String reserveWords[] = {"abstract", "assert", "boolean", "break", "byte", "case",
        "catch", "char", "class", "const", "continue", "default",
        "double", "do", "else", "enum", "extends", "false",
        "final", "finally", "float", "for", "goto", "if",
        "implements", "import", "instanceof", "int", "interface", "long",
        "native", "new", "null", "package", "private", "protected",
        "public", "return", "short", "static", "strictfp", "super",
        "switch", "synchronized", "this", "throw", "throws", "transient",
        "true", "try", "void", "volatile", "while"};

    static void print(char lexeme[], String spaces, String name) {
        int l = 0;
        while (lexeme[l] != '\0') {
            System.out.print(lexeme[l]);
            l++;
        }
        l = 0;

        System.out.println(spaces + name);

    }

    static void tokenizer(File f) throws FileNotFoundException, IOException {
        // Write statement(s) here to store all reserved words of C/Java into an array
        // Create an array named “lexeme” to store lexemes
        char lexeme[] = new char[30];
        int state = 0;		// integer variable representing states
        char specifiers[] = {'n', 'a', 't', 'r', 'v', 'o', 'f', '?', '"', 'b', 39, 92};
        // specifiers  39 represents ' and 92 represents \ 
        int i = 0, j = 0, k = 0;
        boolean flag = false;

        //Read one character from the input file and store it in lookahead  variable
        if (!f.exists()) {
            System.out.println("File Does Not Exist");
            System.exit(0);
        }

        FileReader fr = new FileReader(f);
        BufferedReader buffer = new BufferedReader(fr);
        int c = 0;

        c = buffer.read();
        lookahead = (char) c;
        System.out.println("Lexemes\tTokens");
        while (c != -1) {
            switch (state) {
                case 0:				// case for white spaces
                    if (lookahead == '\r' || lookahead == '\t' || lookahead == ' ' || lookahead == '\n' || lookahead == '\r') {
                        state = 0;
                        //Write statement to read one character from the input file
                        c = buffer.read();
                        lookahead = (char) c;
                    } else if (lookahead == '_' || Character.isLetter(lookahead) || lookahead == '$') // underscore and Letter for Identifiers
                    {
                        state = 1;
                        lexeme[i++] = lookahead;
                    } else if (Character.isDigit(lookahead)) {
                        state = 3;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == ',') {
                        state = 5;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == ';') {
                        state = 6;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == ':') {
                        state = 7;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '?') {
                        state = 8;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '{') {
                        state = 9;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '}') {
                        state = 10;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '(') {
                        state = 11;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == ')') {
                        state = 12;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '=') {
                        state = 13;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '+') {
                        state = 15;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '-') {
                        state = 18;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '*') {
                        state = 21;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '/') {
                        state = 23;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '%') {
                        state = 25;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '<') {
                        state = 27;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '>') {
                        state = 30;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '[') {
                        state = 33;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == ']') {
                        state = 34;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '!') {
                        state = 35;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '&') {
                        state = 37;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '|') {
                        state = 40;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '^') {
                        state = 43;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '~') {
                        state = 45;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '.') {
                        state = 47;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '"') {
                        state = 48;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '\'') {
                        state = 49;
                        lexeme[i++] = lookahead;

                    } else if (lookahead == '/'){
                        state = 53;
                        lexeme[i++] = lookahead;
                        
                    } else {
                        error(c, buffer);
                        state = 0;
                    }

                    break;
                case 1:
                    //Read the next character from the input file
                    c = buffer.read();
                    lookahead = (char) c;

                    if (lookahead == '_' || Character.isLetter(lookahead) || Character.isDigit(lookahead)) {
                        state = 1;
                        lexeme[i++] = lookahead;
                    } else {
                        state = 2;
                    }

                    break;
                case 2:
                    state = 0;
                    //The following code will unget the last character read from the input file
                    lexeme[i] = '\0'; //Storing null character at the end
                    for (j = 0; j < reserveWords.length; j++) {

                        String mlexeme = "";

                        int l = 0;
                        while (lexeme[l] != '\0') {
                            mlexeme = mlexeme.concat(lexeme[l] + "");
                            l++;
                        }
                        l = 0;

                        if (reserveWords[j].equals(mlexeme)) {
                            flag = true;
                            break;
                        }
                    }
                    //The following code is used to write the lexeme and its token in the output file
                    if (flag) //If it is reserved word
                    {
                        //Write statements here to print the lexeme and the reserved word
                        int l = 0;
                        while (lexeme[l] != '\0') {
                            System.out.print(lexeme[l]);
                            l++;
                        }
                        l = 0;
                        System.out.print("\t");
                        while (lexeme[l] != '\0') {
                            System.out.print(lexeme[l]);
                            l++;
                        }
                        flag = false;
                        System.out.println("");
                    } else //If it is identifier
                    {
                        //Write statements here to print the lexeme
                        //and its corresponding token “ID”	
                        print(lexeme, "\t", "ID");
                    }
                    i = 0;
                    break;

                case 3:
                    //Write statement to read one character from the   
                    //input file and store it in lookahead variable
                    c = buffer.read();
                    lookahead = (char) c;

                    if (Character.isDigit(lookahead)) {
                        state = 3;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '.') {

                        state = 51;
                        lexeme[i++] = lookahead;
                    } else {
                        state = 4;
                    }
                    break;
                case 4:
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "\t", "INTEGER");
                    i = 0;
                    break;
                //The following cases are used to recognize delimeters or punctuation marks

                case 5:
                    state = 0;
                    lexeme[i] = '\0';
                    //Write statements here to print the lexeme                                                
                    //and its corresponding token “COMMA”	

                    i = 0;
                    print(lexeme, "\t", "COMMA");
                    //Write statement here to read one character from
                    //the input file and store it in lookahead variable
                    c = buffer.read();
                    lookahead = (char) c;
                    break;

                case 6:
                    state = 0;
                    lexeme[i] = '\0';
                    //Write statements here to print the lexeme                                                
                    //and its corresponding token “SEMI_COMMA”
                    print(lexeme, "\t", "SEMI_COLON");
                    i = 0;
                    //Write statement here to read one character from   
                    //the input file and store it in lookahead variable
                    c = buffer.read();
                    lookahead = (char) c;
                    break;

                case 7:
                    state = 0;
                    lexeme[i] = '\0';
                    //Write statements here to print the lexeme
                    //and its corresponding token “COLON”
                    print(lexeme, "\t", "COLON");

                    i = 0;
                    //Write statement here to read one character from
                    //the input file and store it in lookahead variable	
                    c = buffer.read();
                    lookahead = (char) c;
                    break;
                case 8:
                    state = 0;
                    lexeme[i] = '\0';
                    //Write statements here to print the lexeme                                                
                    //and its corresponding token
                    //“QUESTION_MARK”					                                              
                    i = 0;
                    //Write statement here to read one character from  
                    //the input file and store it in lookahead variable
                    print(lexeme, "\t", "QUESTION_MARK");

                    c = buffer.read();
                    lookahead = (char) c;
                    break;

                case 9:
                    state = 0;
                    lexeme[i] = '\0';
                    //Write statements here to print the lexeme    
                    //and its corresponding token             
                    //“LEFT_CURLY”		
                    print(lexeme, "\t", "LEFT_CURLY");
                    i = 0;
                    //Write statement here to read one character from  
                    //the input file and store it in lookahead variable
                    c = buffer.read();
                    lookahead = (char) c;
                    break;
                case 10:
                    state = 0;
                    lexeme[i] = '\0';
                    //Write statements here to print the lexeme
                    //and its corresponding token                                               
                    //“RIGHT_CURLY”	
                    print(lexeme, "\t", "RIGHT_CURLY");

                    i = 0;
                    //Write statement here to read one character from   
                    //the input file and store it in lookahead variable
                    c = buffer.read();
                    lookahead = (char) c;
                    break;

                // Started here case 11 
                case 11:
                    state = 0;
                    lexeme[i] = '\0';
                    // Left LEFT_PARENTHESIS

                    print(lexeme, "\t", "LEFT_PARENTHESIS");

                    i = 0;
                    //Write statement here to read one character from   
                    //the input file and store it in lookahead variable
                    c = buffer.read();
                    lookahead = (char) c;
                    break;

                case 12:
                    state = 0;
                    lexeme[i] = '\0';
                    // Left LEFT_PARENTHESIS

                    print(lexeme, "\t", "RIGHT_PARENTHESIS");

                    i = 0;
                    //Write statement here to read one character from   
                    //the input file and store it in lookahead variable
                    c = buffer.read();
                    lookahead = (char) c;
                    break;

                case 13:

                    c = buffer.read();
                    lookahead = (char) c;

                    if (lookahead == '=') {

                        state = 14;
                        lexeme[i++] = lookahead;

                    } else {
                        state = 0;
                        lexeme[i] = '\0';
                        // Left LEFT_PARENTHESIS

                        print(lexeme, "\t", "ASSIGNMENT_OPERATOR");

                        i = 0;
                        //Write statement here to read one character from   
                        //the input file and store it in lookahead variable
                        //**Commented this** K9             c = buffer.read();
                        //**Commented this** K9             lookahead = (char) c;
                        break;

                    }
                case 14:
                    state = 0;
                    lexeme[i] = '\0';
                    // Equal
                    print(lexeme, "\t", "EQUAL");
                    i = 0;
                    c = buffer.read();
                    lookahead = (char) c;
                    break;
                case 15:
                    c = buffer.read();
                    lookahead = (char) c;

                    if (lookahead == '+') {
                        state = 16;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '=') {
                        state = 17;
                        lexeme[i++] = lookahead;
                    } else {
                        state = 0;
                        lexeme[i] = '\0';
                        // plus
                        print(lexeme, "\t", "Arithmetic_Plus_Operator");
                        i = 0;
                        c = buffer.read();
                        lookahead = (char) c;
                        break;

                    }
                case 16:
                    state = 0;
                    lexeme[i] = '\0';
                    // plus
                    print(lexeme, "\t", "INCREAMENT");
                    i = 0;
                    c = buffer.read();
                    lookahead = (char) c;
                    break;
                case 17:
                    state = 0;
                    lexeme[i] = '\0';
                    // plus
                    print(lexeme, "\t", "Plus_Assign");
                    i = 0;
                    c = buffer.read();
                    lookahead = (char) c;
                    break;

                case 18:
                    c = buffer.read();
                    lookahead = (char) c;

                    if (lookahead == '-') {
                        state = 19;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '=') {
                        state = 20;
                        lexeme[i++] = lookahead;
                    } else {
                        state = 0;
                        lexeme[i] = '\0';
                        // plus
                        print(lexeme, "\t", "Arithmetic_Minus_Operator");
                        i = 0;
                        c = buffer.read();
                        lookahead = (char) c;
                        break;

                    }
                case 19:
                    state = 0;
                    lexeme[i] = '\0';
                    // plus
                    print(lexeme, "\t", "DECREAMENT");
                    i = 0;
                    c = buffer.read();
                    lookahead = (char) c;
                    break;
                case 20:
                    state = 0;
                    lexeme[i] = '\0';
                    // plus
                    print(lexeme, "\t", "Minus-Assign");
                    i = 0;
                    c = buffer.read();
                    lookahead = (char) c;
                    break;
                case 21:
                    c = buffer.read();
                    lookahead = (char) c;

                    if (lookahead == '=') {
                        state = 22;
                        lexeme[i++] = lookahead;
                    } else {
                        state = 0;
                        lexeme[i] = '\0';
                        // plus
                        print(lexeme, "\t", "Arithmetic_Multiply_Operator");
                        i = 0;
                        c = buffer.read();
                        lookahead = (char) c;
                        break;
                    }
                case 22:
                    state = 0;
                    lexeme[i] = '\0';
                    // plus
                    print(lexeme, "\t", "Multiply-Assign");
                    i = 0;
                    c = buffer.read();
                    lookahead = (char) c;
                    break;

                case 23:
                    c = buffer.read();
                    lookahead = (char) c;

                    if (lookahead == '=') {
                        state = 24;
                        lexeme[i++] = lookahead;
                    }else if(lookahead =='/'){
                    state=53;
                    break;
                    }else if(lookahead=='*'){
                        state=54;
                        break;
                            } else {
                        state = 0;
                        lexeme[i] = '\0';
                        print(lexeme, "\t", "Devide_Operator");
                        i = 0;
                        break;
                    }
                    break;
                case 24:
                    state = 0;
                    lexeme[i] = '\0';
                    // plus
                    print(lexeme, "\t", "Devide-Assign");
                    i = 0;
                    c = buffer.read();
                    lookahead = (char) c;
                    break;

                case 25:
                    c = buffer.read();
                    lookahead = (char) c;

                    if (lookahead == '=') {
                        state = 26;
                        lexeme[i++] = lookahead;
                    } else {
                        state = 0;
                        lexeme[i] = '\0';
                        // plus
                        print(lexeme, "\t", "Moduluse_Operator");
                        i = 0;
                        c = buffer.read();
                        lookahead = (char) c;
                        break;
                    }
                case 26:
                    state = 0;
                    lexeme[i] = '\0';
                    // plus
                    print(lexeme, "\t", "Moduluse-Assign");
                    i = 0;
                    c = buffer.read();
                    lookahead = (char) c;
                    break;

                case 27:
                    c = buffer.read();
                    lookahead = (char) c;

                    if (lookahead == '<') {
                        state = 28;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '=') {
                        state = 29;
                        lexeme[i++] = lookahead;
                    } else {
                        state = 0;
                        lexeme[i] = '\0';
                        // plus
                        print(lexeme, "\t", "Less_Than_Operator");
                        i = 0;
                        c = buffer.read();
                        lookahead = (char) c;
                        break;
                    }

                case 28:
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "\t", "shift_left_Operator");
                    i = 0;
                    c = buffer.read();
                    lookahead = (char) c;
                    break;

                case 29:
                    state = 0;
                    lexeme[i] = '\0';
                    // plus
                    print(lexeme, "\t", "Less_Equal_Operator");
                    i = 0;
                    c = buffer.read();
                    lookahead = (char) c;
                    break;

                case 30:
                    c = buffer.read();
                    lookahead = (char) c;

                    if (lookahead == '>') {
                        state = 31;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '=') {
                        state = 32;
                        lexeme[i++] = lookahead;
                    } else {
                        state = 0;
                        lexeme[i] = '\0';
                        // plus
                        print(lexeme, "\t", "Greater_Than_Operator");
                        i = 0;
                        c = buffer.read();
                        lookahead = (char) c;
                        break;
                    }
                case 31:
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "\t", "shift_Right_Operator");
                    i = 0;
                    c = buffer.read();
                    lookahead = (char) c;
                    break;
                case 32:
                    state = 0;
                    lexeme[i] = '\0';
                    // plus
                    print(lexeme, "\t", "Greater_Equal_Operator");
                    i = 0;
                    c = buffer.read();
                    lookahead = (char) c;
                    break;
                case 33:
                    state = 0;
                    lexeme[i] = '\0';
                    // plus
                    print(lexeme, "\t", "Left_Bracket");
                    i = 0;
                    c = buffer.read();
                    lookahead = (char) c;
                    break;

                case 34:
                    state = 0;
                    lexeme[i] = '\0';
                    // plus
                    print(lexeme, "\t", "Right_Bracket");
                    i = 0;
                    c = buffer.read();
                    lookahead = (char) c;
                    break;
                case 35:
                    c = buffer.read();
                    lookahead = (char) c;

                    if (lookahead == '=') {
                        state = 36;
                        lexeme[i++] = lookahead;
                    } else {
                        state = 0;
                        lexeme[i] = '\0';
                        print(lexeme, "\t", "Not_Operator");
                        i = 0;
                        c = buffer.read();
                        lookahead = (char) c;
                        break;
                    }
                case 36:
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "\t", "NotEqual_Operator");
                    i = 0;
                    c = buffer.read();
                    lookahead = (char) c;
                    break;
                case 37:
                    c = buffer.read();
                    lookahead = (char) c;

                    if (lookahead == '&') {
                        state = 38;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '=') {
                        state = 39;
                        lexeme[i++] = lookahead;
                    } else {
                        state = 0;
                        lexeme[i] = '\0';
                        print(lexeme, "\t", "Bitwise_And_Operator");
                        i = 0;
                        c = buffer.read();
                        lookahead = (char) c;
                        break;
                    }

                case 38:
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "\t", "Logical_AND_Operator");
                    i = 0;
                    c = buffer.read();
                    lookahead = (char) c;
                    break;

                case 39:
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "\t", "Bitwise_AND_Assign");
                    i = 0;
                    c = buffer.read();
                    lookahead = (char) c;
                    break;
                case 40:
                    c = buffer.read();
                    lookahead = (char) c;

                    if (lookahead == '|') {
                        state = 41;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '=') {
                        state = 42;
                        lexeme[i++] = lookahead;
                    } else {
                        state = 0;
                        lexeme[i] = '\0';
                        print(lexeme, "\t", "Bitwise_OR_Operator");
                        i = 0;
                        c = buffer.read();
                        lookahead = (char) c;
                        break;
                    }

                case 41:
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "\t", "Logical_OR_Operator");
                    i = 0;
                    c = buffer.read();
                    lookahead = (char) c;
                    break;

                case 42:
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "\t", "Bitwise_OR_Assign");
                    i = 0;
                    c = buffer.read();
                    lookahead = (char) c;
                    break;

                case 43:
                    c = buffer.read();
                    lookahead = (char) c;

                    if (lookahead == '=') {
                        state = 44;
                        lexeme[i++] = lookahead;
                    } else {

                        state = 0;
                        lexeme[i] = '\0';
                        print(lexeme, "\t", "XOR_Operator");
                        i = 0;
                        c = buffer.read();
                        lookahead = (char) c;
                        break;
                    }
                case 44:
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "\t", "XOR_Assign");
                    i = 0;
                    c = buffer.read();
                    lookahead = (char) c;
                    break;
                case 45:
                    c = buffer.read();
                    lookahead = (char) c;

                    if (lookahead == '=') {
                        state = 44;
                        lexeme[i++] = lookahead;
                    } else {
                        state = 0;
                        lexeme[i] = '\0';
                        print(lexeme, "\t", "NOR_Operator");
                        i = 0;
                        c = buffer.read();
                        lookahead = (char) c;
                        break;
                    }
                case 46:
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "\t", "NOR_Assign");
                    i = 0;
                    c = buffer.read();
                    lookahead = (char) c;
                    break;
                case 47:
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "\t", "Dot");
                    i = 0;
                    c = buffer.read();
                    lookahead = (char) c;
                    break;
                case 48:
                    c = buffer.read();
                    lookahead = (char) c;

                    if (lookahead == '"') {
                        lexeme[i++] = lookahead;
                        state = 0;
                        lexeme[i] = '\0';
                        print(lexeme, "\t", "String_Literal");
                        i = 0;
                        c = buffer.read();
                        lookahead = (char) c;
                        break;
                    } else {
                        state = 48;
                        lexeme[i++] = lookahead;
                        break;
                    }

                case 49:
                    c = buffer.read();
                    lookahead = (char) c;
                    lexeme[i++] = lookahead;
                    state = 50;
                    break;
                case 50:
                    c = buffer.read();
                    lookahead = (char) c;

                    if (lookahead == '\'') {
                        lexeme[i++] = lookahead;
                        state = 0;
                        lexeme[i] = '\0';
                        print(lexeme, "\t", "CHARACTER");
                        i = 0;
                        c = buffer.read();
                        lookahead = (char) c;
                        break;
                    } else {
                        System.out.println("ERROR CAN'T HAVE MORE THAN ONE CHAR INSIDE SINGLE QOUTES \' \' ");
                        System.exit(0);
                    }

                case 51:
                    c = buffer.read();
                    lookahead = (char) c;
                    if (Character.isDigit(lookahead)) {
                        state = 51;
                        lexeme[i++] = lookahead;
                    } else {
                        state = 52;
                        break;
                    }
                    break;

                case 52:
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "\t", "DOUBLE");
                    i = 0;
                    break;

                case 53:
                    buffer.readLine();
                    state = 0;
                   
                        c = buffer.read();
                        lookahead = (char) c;
                    
                case 54:
                    c = buffer.read();
                    lookahead = (char) c;
                    state=54;
                    if(lookahead=='*'){
                    c = buffer.read();
                    lookahead = (char) c;
                    if(lookahead=='/'){
                        state = 0;
                        c = buffer.read();
                        lookahead = (char) c;
                        i = 0;
                    }
                    }
                   // break;
                    
                // i'll explain here very briefly, after reading + we will go to that state
                //read the next char with that code buffer
                // and check if the current char is one of the next cases
                // if its + means its ++ if its = its += else it's + Arthamatic plus
                //+(+,=, ) =(=, ) -(-,=, ) *(=, ) /(=, ) %(=, ) < (<,=, ) >(>,=, ) !(=, ) 
                //&(&,=, ) |(|,=, ) ^(=, ) ~(=, ) . : ? [ ] " (=='\'')
                // and and assign or assign  xor asign xor nor assign nor dot colon Q-Mark
                //            Left bracket right bracked  if " -> " state=0 print lexeme "String litral" lexeme=""; else { read next car state=same state }
                // if ' -> ' print lexeme Character else Error
            }
        }
    }

    static void error(int c, BufferedReader buffer) throws IOException {
        System.out.println("UNRECOGNIZED_TOKEN");
        c = buffer.read();
        lookahead = (char) c;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // input file name"    v    "
        File f = new File("input.txt");

        tokenizer(f);
        System.out.println("Tokens have been identified successfully....!!!");

    }
}
