package bet.astral.tuples.generate.value;

import bet.astral.tuples.generate.tuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tuple {
	private final Pair<Character, String>[] valueNames;
	private final String before;
	private final String name;

	@SafeVarargs
	public Tuple(String name, Pair<Character, String>... valueNames) {
		this.valueNames = valueNames;
		this.before = null;
		this.name = name;
	}
	public Tuple(Tuple before, String name, Pair<Character, String>... valueNames) {
		List<Pair<Character, String>> values = new ArrayList<>(Arrays.asList(before.valueNames));
		values.addAll(Arrays.asList(valueNames));
		//noinspection unchecked
		this.valueNames = values.toArray(Pair[]::new);
		this.before = before.name;
		this.name = name;
	}

	public Pair<Character, String>[] getValueNames() {
		return valueNames;
	}

	public String getName() {
		return name;
	}

	public String getBefore() {
		return before;
	}
}
