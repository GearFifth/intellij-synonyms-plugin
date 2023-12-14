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
import intellijsynonymsplugin.repositories.ThesaurusRepository;
import intellijsynonymsplugin.services.ThesaurusService;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

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
        ThesaurusService thesaurusService = new ThesaurusService(new ThesaurusRepository());
        try {
            PsiElement element = file.findElementAt(editor.getCaretModel().getOffset());
            String identifier = element != null ? element.getText() : null;
            new SynonymsDialog(identifier,thesaurusService.getSynonyms(identifier)).showAndGet();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean startInWriteAction() {
        return false;
    }


}
