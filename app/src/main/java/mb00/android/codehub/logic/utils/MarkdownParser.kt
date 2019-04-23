package mb00.android.codehub.logic.utils

import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer

/**
 * Contains methods used to parse Markdown from the GitHub API
 */

object MarkdownParser {

    fun parseMarkdown(readmeMarkdown: String?): String? {
        val parser = Parser.builder().build()
        val document = parser.parse(readmeMarkdown)
        val renderer = HtmlRenderer.builder().build()
        return renderer.render(document)
    }

}
