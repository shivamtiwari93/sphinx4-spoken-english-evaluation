/*
 * Author: shen zhun
 * Date: 6/8/2013
 */

package sphinx4.spoken.english.evaluation;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

/**
 * A simple Sphinx4see demo showing a simple speech application built using Sphinx-4. This application uses the Sphinx-4
 * endpointer, which automatically segments incoming audio into utterances and silences.
 */
public class Sphinx4see {

    public static void main(String[] args) {
        ConfigurationManager cm;

        if (args.length > 0) {
            cm = new ConfigurationManager(args[0]);
        } else {
            cm = new ConfigurationManager(Sphinx4see.class.getResource("sphinx4see.config.xml"));
        }
        
        // allocate the recognizer
	System.out.println("Loading...");
        Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();

        // start the microphone or exit if the programm if this is not possible
        Microphone microphone = (Microphone) cm.lookup("microphone");
        if (!microphone.startRecording()) {
            System.out.println("Cannot start microphone.");
            recognizer.deallocate();
            System.exit(1);
        }
        
	printInstructions():

        // loop the recognition until the programm exits.
        while (true) {
            System.out.println("Start speaking. Press Ctrl-C to quit.\n");

            Result result = recognizer.recognize();

            if (result != null) {
                String resultText = result.getBestFinalResultNoFiller();
                System.out.println("You said: " + resultText + '\n');
            } else {
                System.out.println("I can't hear what you said.\n");
            }
        }
    }
    

    /** Prints out what to say for this demo. */
    private static void printInstructions() {
	    System.out.println("Sample sentences:\n" +
                    "the green one right in the middle\n" +
		    "the purple one on the lower right side\n" +
		    "the closest purple one on the far left side\n" +
		    "the only one left on the left\n\n" +
		    "Refer to the file hellogram.test for a complete list.\n");
   }
}
}
