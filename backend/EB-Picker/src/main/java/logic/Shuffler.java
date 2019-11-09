package logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;


public class Shuffler {
	private TreeSet<Person> persons;
	private Base64.Encoder encoder =  Base64.getEncoder();
	private Random rand = new Random();

	private static final Logger log = LoggerFactory.getLogger(Shuffler.class);

	public Shuffler(TreeSet<Person> persons) {
		this.persons = persons;
	}

	public TreeSet<Person> shuffle(){
		log.debug("NOW Shuffling");
		int picked;
		boolean lastShuffle = false;
		TreeSet<Integer> used = new TreeSet<>(Integer::compareTo);

		resetShuffle();

		for(Person p:persons){
			log.debug("Picking for: {} with NR: {}",p.getName(), p.getNr());
			log.debug("refused nr: {}",p.getRefuses().toString());
			log.debug("already used: {}",used.toString());

			do {
				picked = rand.nextInt(13)+1;
				log.debug("Picked: {}", picked);
				//to prevent endless loop
				//only one left but cant pick it
				if(used.size()==12){
					if(lastShuffle){
						throw new RuntimeException("shuffling failed");
					} else {
						lastShuffle = true;
						for(int i=1; i<=13; i++){
							if(!used.contains(i))
								picked = i;
						}
					}
				}

			} while (picked==p.getNr()
					|| p.getRefuses().contains(picked)
					|| used.contains(picked)
					|| personPickedOther(persons, picked, p.getNr()));
			log.debug("Pick confirmed");

			used.add(picked);
			p.setPickedNr(picked, nrToBase64(picked));
		}

		return persons;
	}

	private void resetShuffle(){
		persons.forEach((person -> person.setPickedNr(-1, "")));
	}

	private String nrToBase64(int number){
		String nrAsString = ""+number;
		return encoder.encodeToString(nrAsString.getBytes());
	}

	private boolean personPickedOther(TreeSet<Person> persons, int person, int other ){
		for(Person p: persons){
			if(p.getNr()==person && p.getDecodedPick()==other)
				return true;
		}
		return false;
	}

}
