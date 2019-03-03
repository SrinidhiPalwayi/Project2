// package shared;
import shared.Trainer;
import java.util.ArrayList;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * A fixed iteration trainer
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class FixedIterationTrainerMod {
    
    /**
     * The inner trainer
     */
    private Trainer trainer;
    
    /**
     * The number of iterations to train
     */
    private int iterations;

    private StringBuffer tempResults;
    
    /**
     * Make a new fixed iterations trainer
     * @param t the trainer
     * @param iter the number of iterations
     */
    public FixedIterationTrainerMod(Trainer t, int iter) {
        trainer = t;
        iterations = iter;
    }

    /**
     * @see shared.Trainer#train()
     */
    public double train(double start) {
        double sum = 0;
        double end = 0;
        double trainingTime = 0;

        ArrayList<String> results = new ArrayList<>();
        results.add(trainer.toString() +" iteration number, training time, fitness,");



        for (int i = 0; i < iterations; i++) {
            double fitness = trainer.train();
            if (i % 100 == 0) {
                StringBuffer tempResults=  new StringBuffer();
                end = System.nanoTime();
                trainingTime = end - start;
                trainingTime = end - start;
                trainingTime /= Math.pow(10,9);
                tempResults.append(i);
                tempResults.append(",");
                tempResults.append(trainingTime);
                tempResults.append(",");
                tempResults.append(fitness);
                System.out.println(trainingTime + " : " + fitness);
                results.add(tempResults.toString());
            }
            sum += fitness;
        }
        try{
            PrintWriter writer = new PrintWriter(trainer.toString()+"results.csv", "UTF-8");
            for (String current: results) {
                writer.write((current + "\n").toCharArray());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sum / iterations;
    }
    

}
