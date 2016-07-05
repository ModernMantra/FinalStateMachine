# FinalStateMachine
Small project done for educational purposes. App checks whether given string is accepted by a Final State Machine (Non Deterministic Automata). Before string is checked, it must be provided essential data such as Number_of_states Alphabet, Starting_State, Final_State, Path to transition table (check sample automata_description.txt). App automatically generates graphically the Final State Machine graph using Graphviz (graphviz must be pre-installed, check www.graphviz.com)

Graph is created in such way that it is genereated specific file with .dot extension (check file FinalStateMachine.dot and Graphviz syntax on their official page) which contains instructions for Graphviz to creating graph. Afterwards, such a file is compiled over command prompt and final image is genereated (FinalStateMachine.dot.png). 

With given sample input and transition function(automata_description.txt), image is generated as follows 
![alt tag](https://raw.githubusercontent.com/ModernMantra/FinalStateMachine/master/FinalStateMachine.dot.png)
