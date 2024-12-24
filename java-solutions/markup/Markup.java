package markup;

import java.util.List;

public abstract class Markup implements Markdown {

	private final List<Markdown> list;
	private final String mdTag;
	private final String bbTagBegin, bbTagEnd;

	public Markup(List<Markdown> list, String mdTag, String bbTagBegin, String bbTagEnd) {
		this.list = list;
		this.mdTag = mdTag;
		this.bbTagBegin = bbTagBegin;
		this.bbTagEnd = bbTagEnd;
	}

	@Override
	public void toMarkdown(StringBuilder buildString) {
		buildString.append(mdTag);
		for (Markdown markdown : list) {
			markdown.toMarkdown(buildString);
		}
		buildString.append(mdTag);
	}

	@Override
	public void toBBCode(StringBuilder buildString) {
		buildString.append(bbTagBegin);
		for (Markdown markdown : list) {
			markdown.toBBCode(buildString);
		}
		buildString.append(bbTagEnd);

	}
}
