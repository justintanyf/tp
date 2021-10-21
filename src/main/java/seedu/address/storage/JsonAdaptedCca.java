package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.cca.Cca;
import seedu.address.model.cca.CcaName;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;

/**
 * Jackson-friendly version of {@link Cca}.
 */
public class JsonAdaptedCca {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Cca's %s field is missing!";

    private final String name;
    private final Set<JsonAdaptedPerson> personArrayList = new HashSet<>();
    private final Set<JsonAdaptedReminder> reminders = new HashSet<>();

    /**
     * Constructs a {@code JsonAdaptedCca} with the given cca details.
     */
    @JsonCreator
    public JsonAdaptedCca(@JsonProperty("name") String name,
                          @JsonProperty("members")Set<JsonAdaptedPerson> personArrayList,
                          @JsonProperty("reminders")Set<JsonAdaptedReminder> reminders) {
        this.name = name;
        if (personArrayList != null) {
            this.personArrayList.addAll(personArrayList);
        }
        if (reminders != null) {
            this.reminders.addAll(reminders);
        }
    }

    /**
     * Converts a given {@code Cca} into this class for Jackson use.
     */
    public JsonAdaptedCca(Cca source) {
        name = source.getName().fullName;
        personArrayList.addAll(source.getPersonArrayList().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toSet()));
        reminders.addAll(source.getReminders().stream()
                .map(JsonAdaptedReminder::new)
                .collect(Collectors.toSet()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Cca} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Cca toModelType() throws IllegalValueException {
        final List<Person> personList = new ArrayList<>();
        final List<Reminder> reminderList = new ArrayList<>();
        for (JsonAdaptedPerson person : personArrayList) {
            personList.add(person.toModelType());
        }
        for (JsonAdaptedReminder reminder: reminders) {
            reminderList.add(reminder.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, CcaName.class.getSimpleName()));
        }
        if (!CcaName.isValidName(name)) {
            throw new IllegalValueException(CcaName.MESSAGE_CONSTRAINTS);
        }
        final CcaName modelName = new CcaName(name);
        final Set<Person> personArrayList = new HashSet<>(personList);
        final Set<Reminder> reminders = new HashSet<>(reminderList);

        return new Cca(modelName, personArrayList, reminders);
    }

}
