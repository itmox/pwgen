package pwgen;

public class CheckPassword {

	private String password;
	private boolean md5Cracked = true;
	private boolean sha1Cracked = true;
	public CheckPassword(String password){
		this.password = password;
		startCheck();
	}
	private void startCheck() {
		MD5Checker md5checker = new MD5Checker(password);
		 SHA1Checker sha1Checker = new SHA1Checker(password);
		 md5Cracked = md5checker.isPasswordCracked();
		 sha1Cracked = sha1Checker.isPasswordCracked();
		 
		
	}
	
	public boolean isPasswordSafe(){
		if(md5Cracked || sha1Cracked){
			return false; 
		 }
		return true;
	}
}
