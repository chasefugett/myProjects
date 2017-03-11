package Tpdohp;

public class Demo {

	public static String results;
	public static Arguments diffArgs;
	public static int iterationCount;
	
	public Demo(String []args){
		diffArgs = new Arguments(args);
	}
	
    public static void main(final String args[]) {
       execute(diffArgs);
    }    

    public static void execute(Arguments diffArgs) {
    	
    	Plate oldPlate = new Plate (diffArgs);
    	Plate newPlate = new Plate(diffArgs);
    	final DiffusionSimulation simulation = new DiffusionSimulation(diffArgs, oldPlate, newPlate);
    	simulation.calculate();
    	iterationCount = simulation.iterationCount;
    	results = simulation.display();
    }
}


