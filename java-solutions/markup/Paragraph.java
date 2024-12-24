package markup;

import java.util.List;

public class Paragraph implements Markdown {
	private final List<Markdown> str;

	public Paragraph(List<Markdown> list) {
		this.str = list;
	}

	@Override
	public void toMarkdown(StringBuilder buildString) {
		for (Markdown toMarkDown : str) {
			toMarkDown.toMarkdown(buildString);
		}
	}

	@Override
	public void toBBCode(StringBuilder buildString) {
		for (Markdown toMarkDown : str) {
			toMarkDown.toBBCode(buildString);
		}
	}
}
