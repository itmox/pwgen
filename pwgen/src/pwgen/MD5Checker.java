package pwgen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.md5crackerResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class MD5Checker {

	/*
	 * 
	 */
	private String[] md5tables = new String[]{ 
			"http://md5cracker.org/api/api.cracker.php?r=4795&database=md5cracker.org&hash=",
			"http://md5cracker.org/api/api.cracker.php?r=7466&database=tmto&hash=",
			"http://md5cracker.org/api/api.cracker.php?r=60&database=md5.net&hash=",
			"http://md5cracker.org/api/api.cracker.php?r=2255&database=md5online.net&hash=",
			"http://md5cracker.org/api/api.cracker.php?r=9405&database=md5.my-addr.com&hash=",
			"http://md5cracker.org/api/api.cracker.php?r=3849&database=md5decryption.com&hash=",
			"http://md5cracker.org/api/api.cracker.php?r=2976&database=authsecu&hash=",
			"http://md5cracker.org/api/api.cracker.php?r=1765&database=netmd5crack&hash=",
			"http://md5cracker.org/api/api.cracker.php?r=2478&database=md5pass&hash=",
			"http://md5cracker.org/api/api.cracker.php?r=6567&database=i337.net&hash=",
			"http://md5cracker.org/api/api.cracker.php?r=4932&database=md5crack&hash="};
	private String saferPw;
	private String md5Hash;
	public MD5Checker(String saferPw) {
		this.saferPw = saferPw;
		try {
			md5Hash = HashGeneratorUtils.generateMD5(saferPw);
		} catch (Exception e) {
			System.out.print("unable to generate the MD5 Hash of the password");
			e.printStackTrace();
		}
	}

	public boolean isPasswordCracked(){
		try {
			if(isPasswordInMD5DBs(saferPw)){
				return true;
			}
		} catch (IOException e) {
			System.out.println("Something went wrong during the HashTable lookup. Are you connected to the Internet?"
					+ "please write a mail to timo@thraem.com with an description of the problem. It will be fixed as soon as possible");
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings({ "deprecation", "resource" })
	private boolean isPasswordInMD5DBs(String saferPw2) throws ClientProtocolException, IOException {
		for(int i = 0; i<md5tables.length; i++){
		HttpClient client = new DefaultHttpClient();
		  HttpGet request = new HttpGet(md5tables[i].concat(md5Hash));
		  HttpResponse response = client.execute(request);
		  BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
		  StringBuilder builder = new StringBuilder();
		  for (String line = null; (line = rd.readLine()) != null;) {
			    builder.append(line).append("\n");
			}
		  client.getConnectionManager().shutdown();
		  md5crackerResponse objResponse = new ObjectMapper().readValue(builder.toString(), md5crackerResponse.class);
		  if(objResponse.getResult()!= null){
			  if(objResponse.getResult().equals(saferPw)){
				  return true;
			  }
		  }
		}
		return false;
	}

}
