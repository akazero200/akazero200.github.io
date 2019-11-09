package logic;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Set;
import java.util.TreeSet;


public class Controller {
	private Connector con;
	private Shuffler shuffler;

	private  JSONArray pickedFile;
	private JSONArray selectedFile;

	private Logger log = LoggerFactory.getLogger(Controller.class);

	public Controller() {
		con = new Connector();
	}

	public void run(){
		try {
			pickedFile = con.getPickedFile();
			selectedFile = con.getSelectedFile();

		} catch (IOException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}

		shuffler = new Shuffler(buildPersonList(selectedFile));
		try {
			printPersons(shuffler.shuffle());
			printPersons(shuffler.shuffle());
			printPersons(shuffler.shuffle());
			printPersons(shuffler.shuffle());
			printPersons(shuffler.shuffle());
			printPersons(shuffler.shuffle());
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

	private TreeSet<Person> buildPersonList(JSONArray selected){
		log.debug("Building Person List");
		log.debug("selected file: {}",selected.toString());

		final String[] namelist = {"lukas", "tabea", "sam", "kathi(blond)", "lisbet", "mo", "marcel", "jo", "ferdi", "lyd", "clemens", "luki", "kathi(brünett)"};
		TreeSet<Person> persons = new TreeSet<>();

		for(int i = 0; i < 13; i++){
			ArrayList<Integer> refuses = new ArrayList<>();

			JSONObject refusedObject = selected.getJSONObject(i);
			JSONArray jsonRefuses = refusedObject.getJSONArray("ref");

			log.debug("jsonRefuses of {} are {}", i+1, jsonRefuses.toString());

			for(Object refuse:jsonRefuses){
				String strNr = (String)refuse;
				if(!strNr.isEmpty()){
					refuses.add(Integer.parseInt(strNr));
				}
			}
			persons.add(new Person(i+1, namelist[i], refuses));
		}
		return persons;
	}

	private void printPersons(Set<Person> persons){
		Base64.Decoder decoder = Base64.getDecoder();
		StringBuilder sb = new StringBuilder("--------Persons--------\n");

		for (Person p:persons){
			sb.append(p.getNr())
					.append(" - ")
					.append(p.getName())
					.append(" - refused:")
					.append(p.getRefuses().toString())
					.append(" - picked: ");


			String pickedNR = new String(decoder.decode(p.getPickedNr()));
			sb.append(pickedNR).append('\n');
		}
		System.out.println(sb.toString());
	}


}
