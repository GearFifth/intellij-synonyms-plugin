package intellijsynonymsplugin.dialogs;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBScrollPane;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SynonymsDialog extends DialogWrapper {

    private final List<String> synonyms;
    private final String word;

    public SynonymsDialog(String word, List<String> synonyms) {
        super(true);
        this.synonyms = synonyms;
        this.word = word;
        setTitle("Synonyms Dialog");
        init();
    }


    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel(new BorderLayout());

        JTextArea label = new JTextArea(getSynonymsText());
        label.setEditable(false);
        label.setLineWrap(true);
        label.setWrapStyleWord(true);

        JBScrollPane scrollPane = new JBScrollPane(label);
        scrollPane.setPreferredSize(new Dimension(getLongestRowWidth(label), 200));

        dialogPanel.add(scrollPane, BorderLayout.CENTER);

        return dialogPanel;
    }

    private String getSynonymsText() {
        StringBuilder builder = new StringBuilder("Synonyms for " + this.word + ":\n");
        if(synonyms.isEmpty()){
            builder.append("There are no synonyms for the selected word");
        }
        for (String synonym : synonyms) {
            builder.append("- ").append(synonym).append("\n");
        }
        return builder.toString();
    }



    private int getLongestRowWidth(JTextArea textArea) {
        int maxWidth = 0;
        FontMetrics fontMetrics = textArea.getFontMetrics(textArea.getFont());

        for (String line : textArea.getText().split("\n")) {
            int width = fontMetrics.stringWidth(line);
            maxWidth = Math.max(maxWidth, width);
        }

        return maxWidth + textArea.getInsets().left + textArea.getInsets().right + 20;
    }
}
