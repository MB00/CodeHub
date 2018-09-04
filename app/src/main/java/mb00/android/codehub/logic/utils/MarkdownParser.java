package mb00.android.codehub.logic.utils;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

/**
 * Contains methods used to parse Markdown from the GitHub API
 */

public class MarkdownParser {

    public static String parseMarkdown(String readmeMarkdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(readmeMarkdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String readmeParsed = renderer.render(document);
        return readmeParsed;
    }

}
