package markup;

public class Text implements Markdown {

	private final String txt;

	public Text(String txt) {
		this.txt = txt;
	}

	@Override
	public void toMarkdown(StringBuilder buildString) {
		buildString.append(txt);
	}

	@Override
	public void toBBCode(StringBuilder buildString) {
		buildString.append(txt);
	}
}
