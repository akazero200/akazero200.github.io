package cli.commands;

public class Command {
	private final String name;
	private final String[] syntax;
	private final String[] parameters;
	private final String description;
	private final boolean requiresData;

	public Command(String name, String[] syntax, String[] parameters, String description, boolean requiresData) {
		this.name = name;
		this.syntax = syntax;
		this.parameters = parameters;
		this.description = description;
		this.requiresData = requiresData;
	}

	public String getName() {
		return name;
	}

	public String[] getSyntax() {
		return syntax;
	}

	public String[] getParameters() {
		return parameters;
	}

	public String getDescription() {
		return description;
	}

	public boolean isRequiresData() {
		return requiresData;
	}
}
