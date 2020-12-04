package com.example.websocket;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MdToHtml {

    public void test(String filepath) throws IOException {

        Parser parser = Parser.builder().build();
        byte[] encoded = Files.readAllBytes(Paths.get(filepath));
        String s = new String(encoded, StandardCharsets.UTF_8);
//        Node document = parser.parse("### This is *Sparta*");
        Node document = parser.parse(s);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String markdownHtml = renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
        System.out.println(markdownHtml);
    }
}
