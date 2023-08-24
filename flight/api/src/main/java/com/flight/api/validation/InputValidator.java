package com.flight.api.validation;

import com.flight.api.exception.SortInputDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * This class has validation method used for validating sort Inputs
 */
@Component
@Slf4j
public class InputValidator {

    @Autowired
    MessageSource messageSource;

    /**
     * This method validates sort input and throws exception if validation fails.
     *
     * @param sortBy
     * @param sortType
     * @throws SortInputDataException
     */
    public Boolean validateSortFields(String sortBy, String sortType) throws SortInputDataException {
        log.info("For Class InputValidator, Method named- validateSortFields Starts with parameters: {}", sortBy, sortType);

        Boolean status;
        if (
                ((!sortBy.equalsIgnoreCase("price")) &&
                        (!sortBy.equalsIgnoreCase("duration"))
                ) ||
                        ((!sortType.equalsIgnoreCase("asc")) &&
                                (!sortType.equalsIgnoreCase("desc"))
                        )
        ) {
            throw new SortInputDataException(messageSource.
                    getMessage("api.error.correct_sort_input.not.found", null, Locale.ENGLISH));
        } else {
            status = true;
        }
        log.info("For Class InputValidator, Method named- validateSortFields Ends with Validation Status returned: {}", status);
        return status;
    }
}

