package Twfahp;

/**
 * Parser of arguments passed in at console to assign
 * values to Plate initialized values top, bottom, left,
 * right, and dimensions.
 * 
 * 
 *
 */
public class Arguments {
	public int d = 0;
	public Float top;
	public Float bottom;
	public Float left;
	public Float right;
	public String[] args;
	/**
	 * Constructor for parsing args
	 * @param args Array of String arguments
	 */
	public Arguments(final String[] arguments) {
		args = arguments;
		validateArgs();
		parseArgs();
	}

	/**
	 * Searches for dimensions, top, bottom, left, and right values.
	 * @param args String o
	 */
	private void parseArgs() {
		for(int i = 0; i< args.length; i+=2){
			switch(args[i]){
			case "-d":
				d = Integer.parseInt(args[i+1]);
				break;
			case "-l":
				left = Float.parseFloat(args[i+1]);
				break;
			case "-r":
				right = Float.parseFloat(args[i+1]);
				break;
			case "-t":
				top = Float.parseFloat(args[i+1]);
				break;
			case "-b":
				bottom = Float.parseFloat(args[i+1]);
				break;
			default:
				break;
			}
		}
	}
	/**
	 * Only one format is accepted as input for string arguments
	 * and it requires a total of 10 items in the array of strings
	 * @param args Array of String arguments.
	 * @throws IllegalArgumentException
	 */
	public void validateArgs() throws IllegalArgumentException{
		if (args.length == 0 || args.length < 10){
			System.out.println("Input should be as follows:");
			System.out.println("java	<packageName>.Demo -d	# -l	#	-r	#	-t	#	-b	#");
			throw new IllegalArgumentException("user needs more than inputs provided");
		}
	}
}


