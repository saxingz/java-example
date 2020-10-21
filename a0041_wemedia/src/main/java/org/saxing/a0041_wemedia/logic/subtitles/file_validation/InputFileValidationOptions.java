package org.saxing.a0041_wemedia.logic.subtitles.file_validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Collection;

@AllArgsConstructor
@Getter
@Builder
public class InputFileValidationOptions {
    private Collection<String> allowedExtensions;

    private boolean allowEmpty;

    private Long maxAllowedSize;

    private boolean loadContent;
}
