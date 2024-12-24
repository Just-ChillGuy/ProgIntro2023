package markup;

import java.util.List;

public class Emphasis extends Markup {


	public Emphasis(List<Markdown> list) {
		super(list, "*", "[i]", "[/i]");
	}
}
