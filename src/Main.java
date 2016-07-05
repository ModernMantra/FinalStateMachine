
import java.io.*;

public class Main {

    public static void main(String [] args) throws IOException {
        DFA automataTest = new DFA();
        char alphabet[] = new char [] {'0','1'};
        int finalStates[] = new int [] {2,5};
        // Number_of_states -- Alphabet -- Starting_State -- Final_State -- TransitionTableFilePath -- String_To_Be_Tested
        automataTest.setFormalDefinition(5,alphabet,2,finalStates,"/Users/kerimnjuhovic/Desktop/automata_description.txt","0101001101");
    }
}
