package pwgen;

import java.util.Random;

import com.sun.javafx.runtime.SystemProperties;

public class main {
	
	static String unsafePw;
	static String genWay = "";
	
	public static void main(String args[]){
		if(args.length == 0){
			System.out.println("You must at least define a weak password to generate a strong one");
		}
		
		if(args.length == 1){
			if(((String)args[0]).equals("help")){
				System.out.println("At the moment only one parameter is allowed."
						+ System.getProperty("line.separator")
						
						+ "the command is as follows: pwgen \"yourUnsafePassword\""
						+ System.getProperty("line.separator")
						+ System.getProperty("line.separator")
						+"Important Notes:"
						+ System.getProperty("line.separator")
						+ "The Generation of a more strong password works as follows:"
						+ System.getProperty("line.separator")
						+ "1. Some letters are replaced with numbers. The numbers chosen to replace the original letters are static. The leetspeak pattern is used to replace the letters"
						+ "which makes it easy to read the original word after the replacement."
						+ System.getProperty("line.separator")
						+ "2. A randomly chosen special character is put infront of the password and the same character is placed behind the password."
						+ System.getProperty("line.separator")
						+ "3. Arandomly chosen number (one or two digits) are placed infront of the first special character and behind the last on"
						+ System.getProperty("line.separator")
						+ "Example: Hello -> 33$H4110$33");
				System.exit(-1);
					
			}
			else if(((String)args[0]).equals("")){
				System.out.println("You must at least define a weak password to generate a strong one");
			}
			else{
			unsafePw = args[0];
			}
		}
		
		//if(args.length == 2){
		//	unsafePw = args[0];
		//	genWay = args[1];
		//}
		
		else{
			System.out.println("Something went wrong. Type \"pwgen help\" to get more information/help");
			System.exit(-1);
		}
		boolean isSafe = false;
		//the for loop is used to prevent DoS-Attacks. If 10 transformed passwords are not safe enough the password generation is aborted.
	for(int i= 0; i<10; i++){
	 String saferPw = generatePassword(unsafePw);
	 CheckPassword check = new CheckPassword(saferPw);
	 isSafe = check.isPasswordSafe();
	 if(isSafe){
		 System.out.println(saferPw);
		 break;
	 }
	}
	if(!isSafe){
		System.out.println("The weak password can not be converted to an stronger one. The checks against online databases show that the generated passwords are still not strong enough"
				+ " please choose another \"weak\" password and try again.");
	}
	}

	private static String generatePassword(String usPw) {
		//String alphabet = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.,:;-_#'+?=)(/&%$\"![]";
		String numbers = "1234567890";
		String specialChars[] = new String[] {".",",", ":",";", "-", "_", "#", "'", "+",
				"?", "=", ")", "(", "/", "&", "%", "$", "\"", "!", "[", "]"};
		String[] toReplace = new String[]{"i","I", "a", "A", "b", "B", "G", "g", "e", "E", "o", "O", "s", "S","t", "T", "l", "L"};
		int[] replaced = new int[]{1, 1, 4, 4, 8, 8, 9, 9, 3, 3, 0, 0, 5, 5, 7, 7, 1, 1};
		for(int i = 0; i<toReplace.length; i++){
			usPw = usPw.replace(toReplace[i], String.valueOf(replaced[i]));
		}
		Random r = new Random();
		int ran = r.nextInt(specialChars.length);
		usPw = specialChars[ran] + usPw + specialChars[ran];
		 ran = r.nextInt(numbers.charAt(r.nextInt(numbers.length())));
		 usPw = ran + usPw + ran;
		System.out.println(usPw);

		return usPw;
		}
	
	
	
}
