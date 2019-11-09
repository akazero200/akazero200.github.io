package logic;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;
import java.util.Base64;

public class Connector {
	private OkHttpClient client = new OkHttpClient();
	private Base64.Decoder decoder = Base64.getDecoder();
	private final String selectedFileInfoURL = "https://api.github.com/repos/akazero200/EB-19/contents/selected.json";

	private String selectedFileURL = "";


	public Connector() {
	}

	/**
	 * returns the "selected" array from the selected.json
	 *
	 */
	public JSONArray getSelectedFile() throws IOException{
		if(selectedFileURL.isEmpty()){
			selectedFileURL = getFileURL(selectedFileInfoURL);
		}

		JSONObject response = new JSONObject(getFromUrl(selectedFileURL));
		String responseContent = response.getString("content").replaceAll("\n","");
		JSONObject selectedFile = new JSONObject(new String(decoder.decode(responseContent)));

		return selectedFile.getJSONArray("selection");
	}


	//----------------HELPER METHODS------------------
	private String getFileURL(String fileInfoUrl) throws IOException{
		JSONObject selectedFileInfo = new JSONObject(getFromUrl(fileInfoUrl));
		return selectedFileInfo.getString("git_url");
	}


	//---------------HTTP REQUESTS--------------------
	private String getFromUrl(String url) throws IOException {
		Request request = new Request.Builder()
				.url(url)
				.addHeader("Accept", "application/json")
				.build();
		try(Response response = client.newCall(request).execute()){
			if(response.code()!=200){
				throw new IOException("Server returned: "+response.code());
			}
			return response.body().string();
		}
	}

}
