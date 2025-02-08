package com.jaga.windows;

import com.jaga.core.config.ConfigLogger;
import com.jaga.game.Game;
import com.jaga.game.commands.CommandHub;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.reader.impl.LineReaderImpl;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.*;

public class TerminalGame extends JFrame {
    private static RSyntaxTextArea textArea;
    private JTextField inputField;
    private Terminal terminal;
    private PrintWriter terminalWriter;
    private LineReader lineReader;

    public TerminalGame() {
        initWindow();
    }

    private void initWindow() {
        textArea = new RSyntaxTextArea();
        textArea.setEditable(false);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
        textArea.setCodeFoldingEnabled(false);

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        RTextScrollPane scrollPane = new RTextScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        inputField = new JTextField();

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = inputField.getText();
                inputField.setText("");
                processCommand(command);
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputField, BorderLayout.SOUTH);

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        try {
            initializeTerminal();
            redirectLoggerOutput();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeTerminal() throws IOException {
        terminal = TerminalBuilder.builder()
                .system(true)
                .encoding("UTF-8")
                .build();

        terminalWriter = new PrintWriter(terminal.writer(), true);

        lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();

        LineReaderImpl reader = (LineReaderImpl) lineReader;
        reader.setVariable(LineReader.SECONDARY_PROMPT_PATTERN, "");

        reader.setOpt(LineReader.Option.DISABLE_EVENT_EXPANSION);
        reader.setOpt(LineReader.Option.INSERT_TAB);
        reader.setOpt(LineReader.Option.AUTO_FRESH_LINE);

        reader.setOpt(LineReader.Option.CASE_INSENSITIVE);
        reader.setOpt(LineReader.Option.USE_FORWARD_SLASH);

        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    String line = lineReader.readLine(">> ");
                    // Обработка введённой команды
                    processCommand(line);
                }
            } catch (UserInterruptException e) {
                // Обработка прерывания пользователем (например, нажатием Ctrl+C)

                System.exit(0);
            }
        });

        thread.setDaemon(true);
        thread.start();
    }

    private void processCommand(String command) {
        appendText(">> " + command, true);

        new CommandHub(command);
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    public static void appendText(String text, boolean user) {
        textArea.setEditable(true);

        if (user) {
            SyntaxScheme scheme = textArea.getSyntaxScheme();
            textArea.append(text + "\n");
        } else {
            StringBuilder formattedText = new StringBuilder();
            String[] parts = text.split("\u001B\\[");
            for (String part : parts) {
                if (part.isEmpty()) {
                    continue;
                }

                String[] subParts = part.split("m", 2);
                if (subParts.length >= 2) {
                    String ansiCode = "\u001B[" + subParts[0] + "m";
                    String content = subParts[1];
                    formattedText.append(content);
                } else {
                    formattedText.append(part);
                }
            }
            textArea.append(formattedText.toString());
        }


        textArea.setEditable(false);
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    public void redirectLoggerOutput() {
        Logger rootLogger = LogManager.getLogManager().getLogger(Game.class.getName());
        Handler[] handlers = rootLogger.getHandlers();
        for (Handler handler : handlers) {
            rootLogger.removeHandler(handler);
        }

        Handler handler = new Handler() {
            @Override
            public void publish(LogRecord record) {
                if (record.getLevel().intValue() >= Level.INFO.intValue()) {
                    String message = ConfigLogger.getSimpleFormatter().format(record);
                    appendText(message, false);
                }
            }

            @Override
            public void flush() {
            }

            @Override
            public void close() throws SecurityException {
            }
        };

        rootLogger.addHandler(handler);
        rootLogger.setLevel(Level.ALL);
    }

    public void open() {
        setVisible(true);
    }

    public void close() {
        setVisible(false);
    }
}
