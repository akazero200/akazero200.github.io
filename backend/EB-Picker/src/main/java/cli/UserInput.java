package cli;

public class UserInput {
	private String command;
	private String[] parameters;
	private String data;

	public UserInput(String command, String[] parameters, String data) {
		this.command = command;
		this.parameters = parameters;
		this.data = data;
	}

	public String getCommand() {
		return command;
	}

	public String[] getParameters() {
		return parameters;
	}

	public String getData() {
		return data;
	}
}
