package org.hbrs.se1.ws24.exercises.uebung9;

public class TextDocument extends CoreDocument {
    public enum Encoding {
        UTF8("UTF-8"), UTF16("UTF-16"), UTF32("UTF-32");

        private final String value;

        Encoding(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private String content;
    private Encoding encoding;

    public TextDocument(String content, Encoding encoding) {
        this.content = content;
        this.encoding = encoding;
    }

    @Override
    public int getSizeInBytes() {
        return content.getBytes(java.nio.charset.StandardCharsets.UTF_8).length;
    }
}