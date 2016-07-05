import java.io.*;
import java.util.*;

/**
 * Program takes input through console which contains
 * description of Deterministic Finite Automata.
 * Input string is taken from user in order to
 * determine whether string is accepted or not
 */
public class DFA {

    private char[] alphabet;
    private int id;
    private int start_state;
    private int [] accept;
    private int number_of_states;
    private String graphOfAutomata = "";


    public DFA() {
        number_of_states = -1;
        start_state = -1;
    }

    /**
     * Reads file form given path. File must contain description of language in given order
     * number_of_states, alphabet, transition 'table', start_and_final state
     */
    void setFormalDefinition(int numberOfStates, char [] alphabetArray, int startingState,int [] finalStates, String filePath, String stringToBeTested) throws IOException {

        try {
            //Initialise file reader
            FileReader file_reader = new FileReader(filePath);
            BufferedReader buffered_reader = new BufferedReader(file_reader);
            //Stores ArrayLists that contain all possible transitions when reading a symbol from a string
            HashMap map = new HashMap();

            graphOfAutomata += "digraph automata { ratio=\"fill\";\n size=\"8.3,11.7!\"; graph [rankdir=LR]; node [shape = circle];";
            graphOfAutomata += startingState + "[ style = filled, color = lightgrey ];";

            for (int i = 0; i < finalStates.length; i++) {
                graphOfAutomata += finalStates[i] + "[ shape = doublecircle ]";
            }

            number_of_states = numberOfStates;
            alphabet = alphabetArray;

            // reading transition 'table' line by line
            int key = -1;

            //Read in transitions and create DFANode for each state and possible transitions to be made when a symbol is read
            for (int i = 0; i < number_of_states; i++) {
                //Arraylist to store DFANodes for each possible transition for a state
                ArrayList<Nodes> nodeArr = new ArrayList<Nodes>();
                for (int j = 0; j < alphabet.length; j++) {
                    String transition;
                    String line = buffered_reader.readLine();
                    String[] temp = line.split(" ");
                    Nodes tempNode = new Nodes();
                    key = Integer.parseInt(temp[0]);
                    graphOfAutomata += temp[0] + "->";
                    tempNode.setID(key);
                    temp[1] = temp[1].replace(" ", "");
                    tempNode.setAlphabet(temp[1]);
                    transition = String.valueOf(temp[1]);
                    tempNode.setDest(Integer.parseInt(temp[2]));
                    graphOfAutomata += temp[2] + "[ label = \" "+transition+"\" ];";
                    nodeArr.add(j, tempNode); //Store DFANode in state arrayList
                }
                map.put(key, nodeArr); //Store the arraylist for the current state to hashmap map
            }
            graphOfAutomata += "}";
            //get start state
            start_state = startingState;
            ArrayList<Nodes> temp = (ArrayList<Nodes>) map.get(start_state);
            Iterator<Nodes> it = temp.iterator();
            while (it.hasNext()) {
                Nodes t = it.next();
                t.setStart(true);
            }

            //get accept states
            for (int i = 0; i < finalStates.length - 1; i++) {
                int acc = finalStates[i];
                ArrayList<Nodes> arr = (ArrayList<Nodes>) map.get(acc);
                it = arr.iterator();
                while (it.hasNext()) {
                    it.next().setAccept(true);
                }
            }
            createGraph(graphOfAutomata);
            // CHECK IF STRING IS ACCEPTED
                ArrayList<Nodes> state = (ArrayList<Nodes>) map.get(start_state); //Creates ArrayList that indicates current state

                for (int i = 0; i < stringToBeTested.length(); i++) { //Iterates through string
                    it = state.iterator();
                    boolean found = false;
                    while (!found && it.hasNext()) {
                        Nodes tempNode = it.next();
                        if (tempNode.getAlphabet().equals(stringToBeTested.substring(i, i + 1))) { //If current symbol in string matches transition symbol
                            state = (ArrayList<Nodes>) map.get(tempNode.getDest()); //Change to next state indicated
                            found = true;
                        }
                    }
                }
                System.out.println(state.get(0).getAccept());

        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    void createGraph(String text) throws IOException{
        BufferedWriter output = null;
        String fileName = "FinalStateMachine.dot";
        try {
            File file = new File(fileName);
            output = new BufferedWriter(new FileWriter(file));
            output.write(text);
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( output != null ) {
                output.close();
            }
        }
        String filePath = "/Users/kerimnjuhovic/IdeaProjects/FinalStateMachine/";
        String CMD_COMMAND = "dot -Tpng -O "+filePath+""+fileName;
        Runtime.getRuntime().exec(CMD_COMMAND);
    }

}
