package com.flight.api.validation;

import com.flight.api.exception.SortInputDataException;
import com.flight.api.util.SortField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

import static com.flight.api.util.SortField.DESCENDING;

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

        Boolean status = false;
        if (
                ( (!sortBy.equalsIgnoreCase(SortField.PRICE.getValue()))&&
                        (!sortBy.equalsIgnoreCase(SortField.DURATION.getValue())
                        ) ||
                        ((!sortType.equalsIgnoreCase(SortField.ASCENDING.getValue())) &&
                                (!sortType.equalsIgnoreCase(SortField.DESCENDING.getValue()))
                        ))

        ) {
            log.info("For Class InputValidator, Method named- validateSortFields, Validation fails with status returned as: {}", status);
            throw new SortInputDataException(messageSource.
                    getMessage("api.error.correct_sort_input.not.found", null, Locale.ENGLISH));
        } else {
            status= true;
            log.info("For Class InputValidator, Method named- validateSortFields Ends with Success Validation Status as: {}", status);
            return status;
        }

    }
}

