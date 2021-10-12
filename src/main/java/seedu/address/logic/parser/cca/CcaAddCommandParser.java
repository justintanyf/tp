package seedu.address.logic.parser.cca;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.cca.CcaAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.cca.Cca;
import seedu.address.model.cca.CcaName;

public class CcaAddCommandParser implements Parser<CcaAddCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the CcaAddCommand
     * and returns an CcaAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CcaAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CcaAddCommand.MESSAGE_USAGE));
        }

        CcaName ccaName = ParserUtil.parseCcaName(argMultimap.getValue(PREFIX_NAME).get());

        // Create a new CCA here
        Cca cca = new Cca(ccaName);

        return new CcaAddCommand(cca);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}