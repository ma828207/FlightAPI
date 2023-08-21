package com.flight.api.validation;

import com.flight.api.exception.SortInputDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * This class has validation method used for validating sort Inputs
 */
@Component
public class ValidateData {

    @Autowired
    MessageSource messageSource;

    /* This method validates sort input and throws exception if validation fails */
    public void validateSortFields(String sortBy, String sortDir) throws SortInputDataException {

        if (
                ((!sortBy.equalsIgnoreCase("price")) &&
                        (!sortBy.equalsIgnoreCase("duration"))
                ) ||
                        ((!sortDir.equalsIgnoreCase("asc")) &&
                                (!sortDir.equalsIgnoreCase("desc"))
                        )
        ) {
            throw new SortInputDataException(messageSource.
                    getMessage("api.error.correct_sort_input.not.found", null, Locale.ENGLISH));
        }
    }
}

