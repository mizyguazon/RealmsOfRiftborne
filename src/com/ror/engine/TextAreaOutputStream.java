package com.ror.engine;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;
import java.util.function.Consumer;

public class TextAreaOutputStream extends OutputStream {
    private final JTextArea outputArea;
    private final Function<String, String> textFilter;
    private final Consumer<String> textObserver;
    private final StringBuilder buffer = new StringBuilder();

    public TextAreaOutputStream(JTextArea outputArea) {
        this(outputArea, null, null);
    }

    public TextAreaOutputStream(JTextArea outputArea, Consumer<String> textObserver) {
        this(outputArea, null, textObserver);
    }

    public TextAreaOutputStream(
        JTextArea outputArea,
        Function<String, String> textFilter,
        Consumer<String> textObserver
    ) {
        this.outputArea = outputArea;
        this.textFilter = textFilter;
        this.textObserver = textObserver;
    }

    @Override
    public void write(int b) {
        write(new byte[]{(byte) b}, 0, 1);
    }

    @Override
    public void write(byte[] b, int off, int len) {
        String text = new String(b, off, len, StandardCharsets.UTF_8);

        synchronized (buffer) {
            buffer.append(text);
        }

        SwingUtilities.invokeLater(this::flushBufferToTextArea);
    }

    private void flushBufferToTextArea() {
        String text;

        synchronized (buffer) {
            if (buffer.length() == 0) {
                return;
            }

            text = buffer.toString();
            buffer.setLength(0);
        }

        String filteredText = text;

        if (textFilter != null) {
            filteredText = textFilter.apply(text);
        }

        if (filteredText != null && !filteredText.isEmpty()) {
            outputArea.append(filteredText);
            outputArea.setCaretPosition(outputArea.getDocument().getLength());
        }

        if (textObserver != null) {
            textObserver.accept(text);
        }
    }
}
