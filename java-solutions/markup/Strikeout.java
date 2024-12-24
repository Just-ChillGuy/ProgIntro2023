package markup;

import java.util.List;

public class Strikeout extends Markup {


	public Strikeout(List<Markdown> list) {
		super(list, "~", "[s]", "[/s]");
	}
}
