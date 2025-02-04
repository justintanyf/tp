package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.cca.Cca;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    /**
     * Adds a new {@code Cca} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withCca(Cca cca) {
        addressBook.addCca(cca);
        return this;
    }

    /**
     * Adds a new {@code Reminder} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withReminder(Reminder reminder) {
        addressBook.addReminder(reminder);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
