package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all persons, CCAs and reminders in ePoch to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons, CCAs and reminders";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.resetAllFilteredLists();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
