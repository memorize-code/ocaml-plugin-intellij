package com.or.lang.core.psi;

import com.intellij.psi.PsiQualifiedNamedElement;
import org.jetbrains.annotations.Nullable;

/**
 * A {@link PsiQualifiedNamedElement} with extra access to the element path as array of strings.
 */
public interface PsiQualifiedPathElement extends PsiQualifiedNamedElement {
    String @Nullable [] getPath();
}
