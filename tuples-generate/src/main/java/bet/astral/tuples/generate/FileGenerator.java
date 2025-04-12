package bet.astral.tuples.generate;

import bet.astral.tuples.generate.tuples.Pair;
import bet.astral.tuples.generate.value.Tuple;

public class FileGenerator {
	private final Generator generator;
	private Tuple tuple;
	private String currentIndentation = "";
	private boolean impl;
	private boolean mutable;
	private boolean locked = false;

	public FileGenerator(Generator generator) {
		this.generator = generator;
	}

	public String generateCopyRight(){
		return "";
	}
	public String generatePackage(){
		return "package " + generator.getPackageName() + (!impl && mutable ? ".mutable" : impl ? ".impl."+(mutable ? "mutable" : "immutable") : "") + ";\n\n";
	}
	public String generateTupleImport() {
		StringBuilder builder = new StringBuilder();
		if (!impl) {
			builder.append("import ").append(generator.getPackageName()).append(".impl.immutable.").append(properCase(tuple.getName())).append("Impl;").append("\n");
			builder.append("import ").append(generator.getPackageName()).append(".impl.mutable.Mutable").append(properCase(tuple.getName())).append("Impl;").append("\n");
			builder.append("import ").append(generator.getPackageName()).append(".mutable.Mutable").append(properCase(tuple.getName())).append(";").append("\n");
		}
		return builder.toString();
	}
	public String generateImports(){
		StringBuilder builder = new StringBuilder();
		if (!impl && !mutable) {
			builder.append(generateTupleImport());
			builder.append("import org.jetbrains.annotations.Contract;").append("\n").append(currentIndentation);
			builder.append("import org.jetbrains.annotations.NotNull;").append("\n").append(currentIndentation);
			builder.append("\n").append(currentIndentation);
			builder.append("import java.util.Iterator;").append("\n").append(currentIndentation);
			builder.append("import java.util.List;").append("\n").append(currentIndentation);
			builder.append("\n");
		} else if (mutable) {
			if (impl) {
				builder.append("import ").append(generator.getPackageName()).append(".").append(properCase(tuple.getName())).append(";").append("\n");
				builder.append("import ").append(generator.getPackageName()).append(".mutable.Mutable").append(properCase(tuple.getName())).append(";").append("\n");
			} else {
				builder.append("import ").append(generator.getPackageName()).append(".").append(properCase(tuple.getName())).append(";").append("\n");
			}
 		} else {
			builder.append("import ").append(generator.getPackageName()).append(".").append(properCase(tuple.getName())).append(";").append("\n");
		}
		builder.append("\n");
		return builder.toString();
	}

	public String generateHeader() {
		StringBuilder builder = new StringBuilder();
		builder.append("public ").append(impl ? "class" : "interface");
		builder.append(" ");
		builder.append(mutable ? "Mutable" : "").append(properCase(tuple.getName())).append(impl ? "Impl" : "");
		builder.append(generateTypes(tuple.getValueNames().length));
		if (impl) {
			builder.append(" implements ").append(mutable ? "Mutable" : "").append(properCase(tuple.getName()));
			builder.append(generateTypes(tuple.getValueNames().length));
		} else if (tuple.getBefore() != null) {
			if (mutable) {
				builder.append(" extends ").append("Mutable").append(properCase(tuple.getBefore()));
				builder.append(generateTypes(tuple.getValueNames().length-1));
				builder.append(", ").append(properCase(tuple.getName()));
				builder.append(generateTypes(tuple.getValueNames().length));
			} else {
				builder.append(" extends ").append(mutable ? "Mutable" : "").append(properCase(tuple.getBefore()));
				builder.append(generateTypes(tuple.getValueNames().length - 1));
			}
		} else if (tuple.getBefore() == null) {
			if (mutable) {
				builder.append(" extends ").append(properCase(tuple.getName())).append(generateTypes(tuple.getValueNames().length));
			} else {
				builder.append(" extends Iterable<Object>, Cloneable");
			}
		}
		builder.append(" {").append("\n");
		return builder.toString();
	}

	public String generateTypes(int amount){
		StringBuilder builder = new StringBuilder();
		builder.append("<");
		int i = 0;
		for (Pair<Character, String> pair : tuple.getValueNames()) {
			if (i==amount){
				break;
			}
			if (builder.length() > 1) {
				builder.append(", ");
			}
			builder.append(pair.getFirst().toString().toUpperCase());
			i++;
		}
		builder.append(">");
		return builder.toString();
	}

	public String generateContract(Pair<Character, String>[] types){
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < types.length; i++){
			if (!builder.isEmpty()){
				builder.append(", ");
			}
			builder.append("_");
		}
		builder.insert(0, "@Contract(value = \"");
		builder.append(" -> new\", pure = true)");
		return builder.toString();
	}

	public String generateStaticMethods(){
		StringBuilder builder = new StringBuilder();
		builder.append(generateStaticMethod(false, false));
		builder.append(generateStaticMethod(true, false));
		builder.append(generateStaticMethod(false, true));
		builder.append(generateStaticMethod(true, true));
		return builder.toString();
	}

	public String generateStaticMethod(boolean mutable, boolean copyMethod){
		setIndentation(1);
		StringBuilder builder = new StringBuilder();
		builder.append(currentIndentation);
		builder.append(!copyMethod ?  generateContract(tuple.getValueNames()) : "@Contract(value = \"_ -> new\", pure = true)").append("\n").append(currentIndentation);
		builder.append("static ").append(generateTypes(tuple.getValueNames().length)).append(" ");
		builder.append("@NotNull").append(" ");
		builder.append(mutable ? "Mutable" : "").append(properCase(tuple.getName()));
		builder.append(generateTypes(tuple.getValueNames().length)).append(" ");
		builder.append(mutable ? "mutable" : "immutable").append("(");
		builder.append(copyMethod ? "@NotNull " : "");
		builder.append(!copyMethod ? generateMethodVariables() : properCase(tuple.getName())+generateTypes(tuple.getValueNames().length)+ " "+ tuple.getName().toLowerCase()).append(") {").append("\n");
		setIndentation(2);
		builder.append(currentIndentation);
		builder.append("return new ").append(mutable ? "Mutable" : "").append(properCase(tuple.getName())).append("Impl<>");
		builder.append("(").append(copyMethod ? tuple.getName().toLowerCase() : generateMethodVariableInserts()).append(");");
		builder.append("\n");
		setIndentation(1);
		builder.append(currentIndentation);
		builder.append("}");
		builder.append("\n");
		return builder.toString();
	}

	public String generateMethodVariables(){
		StringBuilder builder = new StringBuilder();
		for (Pair<Character, String> pair : tuple.getValueNames()){
			if (!builder.isEmpty()){
				builder.append(", ");
			}
			builder.append(pair.getFirst().toString().toUpperCase()).append(" ").append(pair.getSecond().toLowerCase());
		}
		return builder.toString();
	}

	public String generateMethodVariableInserts(){
		StringBuilder builder = new StringBuilder();
		for (Pair<Character, String> pair : tuple.getValueNames()){
			if (!builder.isEmpty()){
				builder.append(", ");
			}
			builder.append(pair.getSecond().toLowerCase());
		}
		return builder.toString();
	}

	public String generateGetter(){
		setIndentation(1);
		return currentIndentation+tuple.getValueNames()[tuple.getValueNames().length-1].getFirst()+ " get"+properCase(tuple.getValueNames()[tuple.getValueNames().length-1].getSecond())+"();\n";
	}

	public String generateIterator(){
		setIndentation(1);
		StringBuilder builder = new StringBuilder();
		builder.append(currentIndentation);
		builder.append("@NotNull").append("\n");
		builder.append(currentIndentation);
		builder.append("@Override").append("\n");
		builder.append(currentIndentation);
		builder.append("default Iterator<Object> iterator() {").append("\n");
		setIndentation(2);
		if (tuple.getBefore()==null){
			builder.append(currentIndentation);
			builder.append("//noinspection unchecked\n");
		}
		builder.append(currentIndentation);
		builder.append("return").append(" ").append(tuple.getBefore() != null ? "" : "(Iterator<Object>)").append(" List.of(");
		boolean isEmpty = true;
		for (Pair<Character, String> pair : tuple.getValueNames()){
			if (!isEmpty){
				builder.append(", ");
			}
			isEmpty = false;
			builder.append("get").append(properCase(pair.getSecond())).append("()");
		}
		builder.append(").iterator();").append("\n");
		setIndentation(1);
		builder.append(currentIndentation);
		builder.append("}").append("\n");
		return builder.toString();
	}

	public String generateClone(boolean mutable){
		StringBuilder builder = new StringBuilder();
		setIndentation(1);
		builder.append(currentIndentation);
		builder.append("default ").append(mutable ? "Mutable" : "");
		builder.append(properCase(tuple.getName()));
		builder.append(generateTypes(tuple.getValueNames().length));
		builder.append(" cloneAs");
		builder.append(mutable ? "Mutable" : "Immutable");
		builder.append("() {").append("\n");
		setIndentation(2);
		builder.append(currentIndentation);
		builder.append("return ").append(mutable ? "mutable" : "immutable");
		builder.append("(this);").append("\n");
		setIndentation(1);
		builder.append(currentIndentation);
		builder.append("}").append("\n");
		return builder.toString();
	}

	public String generateFields(){
		StringBuilder builder = new StringBuilder();
		for (Pair<Character, String> pair : tuple.getValueNames()) {
			setIndentation(1);
			builder.append(currentIndentation);
			builder.append("private ").append(mutable ? "" : "final ")
					.append(pair.getFirst().toString().toUpperCase())
					.append(" ").append(pair.getSecond().toLowerCase()).append(";").append("\n");
		}
		builder.append("\n");
		return builder.toString();
	}

	public String generateConstructor(){
		StringBuilder builder = new StringBuilder();
		setIndentation(1);
		builder.append(currentIndentation);
		builder.append("public ");
		builder.append(mutable ? "Mutable" : "").append(properCase(tuple.getName())).append("Impl");
		builder.append("(").append(generateMethodVariables()).append(") {");
		builder.append("\n");
		for (Pair<Character, String> pair : tuple.getValueNames()){
			builder.append(currentIndentation);
			builder.append("this.").append(pair.getSecond().toLowerCase()).append(" = ").append(pair.getSecond().toLowerCase()).append(";").append("\n");
		}
		setIndentation(1);
		builder.append(currentIndentation);
		builder.append("}").append("\n");
		builder.append(currentIndentation);
		builder.append("public ");
		builder.append(mutable ? "Mutable" : "").append(properCase(tuple.getName())).append("Impl");
		builder.append("(").append(properCase(tuple.getName())).append(generateTypes(tuple.getValueNames().length)).append(" ").append(tuple.getName().toLowerCase()).append(") {");
		builder.append("\n");
		setIndentation(2);
		for (Pair<Character, String> pair : tuple.getValueNames()){
			builder.append(currentIndentation);
			builder.append("this.").append(pair.getSecond().toLowerCase()).append(" = ").append(tuple.getName().toLowerCase()).append(".")
					.append("get").append( properCase(pair.getSecond().toLowerCase())).append("();").append("\n");
		}
		setIndentation(1);
		builder.append(currentIndentation);
		builder.append("}").append("\n");

		return builder.toString();
	}

	public String generateSetters(){
		StringBuilder builder = new StringBuilder();
		for (Pair<Character, String> pair : tuple.getValueNames()) {
			setIndentation(1);
			builder.append(currentIndentation);
			if (impl) {
				builder.append("public Mutable").append(properCase(tuple.getName())).append(generateTypes(tuple.getValueNames().length)).append(" set").append(properCase(pair.getSecond()))
						.append("(").append(pair.getFirst().toString().toUpperCase()).append(" ").append(pair.getSecond().toLowerCase()).append(") {").append("\n");
				setIndentation(2);
				builder.append(currentIndentation);
				builder.append("this.").append(pair.getSecond().toLowerCase()).append(" = ").append(pair.getSecond().toLowerCase()).append(";").append("\n");
				builder.append(currentIndentation);
				builder.append("return this;").append("\n");
				setIndentation(1);
				builder.append(currentIndentation);
				builder.append("}").append("\n");
			} else {
				builder.append("Mutable").append(properCase(tuple.getName())).append(generateTypes(tuple.getValueNames().length)).append(" set").append(properCase(pair.getSecond()))
						.append("(").append(pair.getFirst().toString().toUpperCase()).append(" ").append(pair.getSecond().toLowerCase()).append(");").append("\n");
			}
		}
		return builder.toString();
	}

	public String generateGetters(){
		StringBuilder builder = new StringBuilder();
		for (Pair<Character, String> pair : tuple.getValueNames()) {
			setIndentation(1);
			builder.append(currentIndentation);
			builder.append("public ").append(pair.getFirst().toString().toUpperCase());
			builder.append(" get").append(properCase(properCase(pair.getSecond()))).append("()").append(" {").append("\n");
			setIndentation(2);
			builder.append(currentIndentation);
			builder.append("return ").append("this.").append(pair.getSecond().toLowerCase()).append(";").append("\n");
			setIndentation(1);
			builder.append(currentIndentation);
			builder.append("}");
			builder.append("\n");
		}
		return builder.toString();
	}

	public String generateClass(Tuple tuple, boolean impl, boolean mutable){
		if (locked){
			throw new RuntimeException("File generator is currently locked!");
		}
		locked = true;
		this.tuple = tuple;
		this.impl = impl;
		this.mutable = mutable;
		currentIndentation = "";
		StringBuilder builder = new StringBuilder();
		builder.append(generateCopyRight());
		builder.append(generatePackage());
		builder.append(generateImports());
		builder.append(generateHeader());

		if (impl){
			builder.append(generateFields());
			builder.append(generateConstructor());
			builder.append(generateGetters());
		}
		if (!impl && !mutable){
			builder.append(generateStaticMethods()).append("\n");
			builder.append(generateGetter()).append("\n");
			builder.append(generateIterator());
			builder.append(generateClone(true));
			builder.append(generateClone(false));
		} else if (mutable){
			builder.append(generateSetters());
		}
		setIndentation(0);
		builder.append("}");
		locked = false;
		return builder.toString();
	}

	private void setIndentation(int amount){
		String indentation = "\t";
		this.currentIndentation = indentation.repeat(Math.max(0, amount));
	}

	public String properCase(String value){
		return value.replaceFirst(value.substring(0, 1), value.substring(0, 1).toUpperCase());
	}
}









