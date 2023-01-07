package org.wahlzeit.annotations;

import java.lang.annotation.Repeatable;

@Repeatable(PatternInstanceArray.class)
public @interface PatternInstance {
    String patternName();
    String[] participants();
}
