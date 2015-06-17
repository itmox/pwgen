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

public class SHA1Checker {

	
	private String saferPw;
	private String sha1Hash;
	private String[] sha1Tables = new String[] {"https://hashtoolkit.com/reverse-hash/?hash="};
	public SHA1Checker(String saferPw) {
		this.saferPw = saferPw;
		try {
			sha1Hash = HashGeneratorUtils.generateSHA1(saferPw);
		} catch (Exception e) {
			System.out.print("unable to generate the SHA1 Hash of the password");
			e.printStackTrace();
		}

	}
	public boolean isPasswordCracked(){
		try {
			if(isPasswordInSHA1DBs(saferPw)){
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	@SuppressWarnings({ "deprecation", "resource" })
	private boolean isPasswordInSHA1DBs(String saferPw)throws ClientProtocolException, IOException {
			for(int i = 0; i<sha1Tables.length; i++){
			HttpClient client = new DefaultHttpClient();
			  HttpGet request = new HttpGet(sha1Tables[i].concat(sha1Hash));
			  HttpResponse response = client.execute(request);
			  BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
			  StringBuilder builder = new StringBuilder();
			  for (String line = null; (line = rd.readLine()) != null;) {
				    builder.append(line).append("\n");
				}
			  client.getConnectionManager().shutdown();
			  if(builder.toString().contains(saferPw)){
				  return true;
			  }
			}
			return false;
		}
	}
