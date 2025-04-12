package bet.astral.tuples.generate;

import bet.astral.tuples.generate.tuples.Pair;
import bet.astral.tuples.generate.tuples.Triple;
import bet.astral.tuples.generate.value.Tuple;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Generator {
	private final File file;
	private final String packageName;
	private final File filePackage;
	private final List<Tuple> generatable = new ArrayList<>();
	private final FileGenerator fileGenerator = new FileGenerator(this);
	public Generator(File file, String packageName) {
		this.file = file;
		this.packageName = packageName;
		filePackage = new File(file, packageName.replace(".", "/"));
	}

	public void clearDataDirectory(){
		file.delete();
		file.mkdirs();
		filePackage.mkdirs();
	}

	public void generate(){
		for (Tuple tuple : generatable){
			generate(tuple, false, false);
			generate(tuple, true, false);
			generate(tuple, false, true);
			generate(tuple, true, true);
		}
	}

	private void generate(Tuple tuple, boolean impl, boolean mutable){
		String fileName = (impl ? "impl." : "") + (mutable ? "mutable.Mutable" : impl ? "immutable." : "") + fileGenerator.properCase(tuple.getName()) + (impl ? "Impl" : "");
		String packageName = (this.packageName + "."+fileName).replace(".", "/") + ".java";
		File file = new File(this.file, packageName);
		if (!file.exists()){
			if (!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(fileGenerator.generateClass(tuple, impl, mutable));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void add(Tuple tuple){
		generatable.add(tuple);
	}


	public static void main(String[] args){
		String path = args[0];
		Generator generator = new Generator(new File(path), "bet.astral.tuples.generated");
		generator.clearDataDirectory();

		List<Triple<String, Character, String>> generate = new ArrayList<>();

		generate.add(new Triple<>("Unit", 'A', "first"));
		generate.add(new Triple<>("Pair", 'B', "second"));
		generate.add(new Triple<>("Triplet", 'C', "third"));
		generate.add(new Triple<>("Quartet", 'D', "fourth"));
		generate.add(new Triple<>("Quintet", 'E', "fifth"));
		generate.add(new Triple<>("Sextet", 'F', "sixth"));
		generate.add(new Triple<>("Septet", 'G', "seventh"));
		generate.add(new Triple<>("Octet", 'H', "eighth"));
		generate.add(new Triple<>("Nonet", 'I', "ninth"));
		generate.add(new Triple<>("Decet", 'J', "tenth"));
		/*
		generate.add(new Triple<>("Undocet", 'K', "eleventh"));
		generate.add(new Triple<>("Duodecet", 'L', "twelfth"));
		generate.add(new Triple<>("Trecedet", 'M', "thirteenth"));
		generate.add(new Triple<>("Quattuordecet", 'N', "fourteenth"));
		generate.add(new Triple<>("Quindecet", 'O', "fifteenth"));
		generate.add(new Triple<>("Sexdecet", 'P', "sixteenth"));
		generate.add(new Triple<>("Septendecet", 'Q', "seventeenth"));
		generate.add(new Triple<>("Octodecet", 'R', "eighteenth"));
		generate.add(new Triple<>("Nonadecet", 'S', "nineteenth"));
		generate.add(new Triple<>("Vigintet", 'T', "twentieth"));
		 */

		Tuple before = null;
		for (Triple<String, Character, String> triple : generate){
			if (before==null){
				before = new Tuple(triple.getFirst(), new Pair<>(triple.getSecond(), triple.getThird()));
				generator.add(before);
			} else {
				generator.add(before = new Tuple(before, triple.getFirst(), new Pair<>(triple.getSecond(), triple.getThird())));
			}
		}

		generator.generate();
	}


	public File getFile() {
		return file;
	}

	public String getPackageName() {
		return packageName;
	}

	public List<Tuple> getGeneratable() {
		return generatable;
	}
}
