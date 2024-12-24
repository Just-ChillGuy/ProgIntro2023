package markup;

import java.util.List;

public class Strong extends Markup {

	public Strong(List<Markdown> list) {
		super(list, "__", "[b]", "[/b]");
	}
}
