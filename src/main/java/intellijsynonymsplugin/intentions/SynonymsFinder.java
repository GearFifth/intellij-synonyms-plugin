package intellijsynonymsplugin.intentions;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.codeInspection.util.IntentionName;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import intellijsynonymsplugin.dialogs.SynonymsDialog;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SynonymsFinder implements IntentionAction {
    @Override
    public @IntentionName @NotNull String getText() {
        return "Show synonyms";
    }

    @Override
    public @NotNull @IntentionFamilyName String getFamilyName() {
        return getText();
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        PsiElement element = file.findElementAt(editor.getCaretModel().getOffset());
        return PsiTreeUtil.getParentOfType(element, PsiElement.class) != null;
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
        System.out.println("Intention Action invoked: " + getText());
        PsiElement element = file.findElementAt(editor.getCaretModel().getOffset());
        String word = element.getText();
        new SynonymsDialog(word, List.of("alternative1", "alternative2", "alternative3")).showAndGet();
    }

    @Override
    public boolean startInWriteAction() {
        return false;
    }


}
