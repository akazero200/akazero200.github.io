package logic;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class Person implements Comparable {
	private int nr;
	private String name;
	private List<Integer> refuses;
	private String pickedNr;
	private int decodedPick;

	public Person(int nr, String name, List<Integer> refuses) {
		this.nr = nr;
		this.name = name;
		this.refuses = refuses;
		pickedNr = "";
		decodedPick = -1;
	}

	public void setPickedNr(int picked, String encodedPick) {
		this.decodedPick = picked;
		this.pickedNr = encodedPick;
	}

	public int getNr() {
		return nr;
	}

	public int getDecodedPick() {
		return decodedPick;
	}

	public String getName() {
		return name;
	}

	public List<Integer> getRefuses() {
		return refuses;
	}

	public String getPickedNr() {
		return pickedNr;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return nr == person.nr;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nr);
	}

	@Override
	public int compareTo(@NotNull Object o) {
		if(o instanceof Person){
			Person other = (Person)o;
			return getNr()-other.getNr();
		}

		return 1;
	}
}
