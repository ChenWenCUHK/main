package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns the user prefs' archive book file path.
     */
    Path getArchiveBookFilePath();

    /**
     * Sets the user prefs' archive book file path.
     */
    void setArchiveBookFilePath(Path archiveBookFilePath);

    /**
     * Replaces archive book data with the data in {@code archiveBook}.
     */
    void setArchiveBook(ReadOnlyAddressBook archiveBook);

    /** Returns the ArchiveBook */
    ReadOnlyAddressBook getArchiveBook();

    /**
     * Returns the user prefs' pin book file path.
     */
    Path getPinBookFilePath();

    /**
     * Sets the user prefs' pin book file path.
     */
    void setPinBookFilePath(Path pinBookFilePath);

    /**
     * Replaces pin book data with the data in {@code pinBook}.
     */
    void setPinBook(ReadOnlyPinBook pinBook);

    /** Returns the PinBook */
    ReadOnlyPinBook getPinBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a person with the same identity as {@code editedPerson} exists in the address book.
     */
    boolean hasEditedPerson(Person editedPerson);

    /**
     * Returns true if a person with the same identity as {@code toAdd} exists in the address book.
     */
    boolean hasSameIdentityField(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Archives the given person.
     * The person must exist in the address book.
     */
    void archivePerson(Person target);

    /**
     * Unarchives the given person.
     * The person must exist in the archive book.
     */
    void unarchivePerson(Person target);

    /**
     * Pins the given person.
     * The person must exist in the address book.
     */
    void pinPerson(Person target);

    /**
     * Unpins the given person.
     * The person must exist in the pin book.
     */
    void unpinPerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /** Returns an unmodifiable view of the filtered archived list */
    ObservableList<Person> getFilteredArchivedPersonList();

    /**
     * Updates the filter of the filtered archived list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredArchivedPersonList(Predicate<Person> predicate);

    /** Returns an unmodifiable view of the filtered pinned list */
    ObservableList<Person> getFilteredPinnedPersonList();

    /**
     * Updates the filter of the filtered pinned list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPinnedPersonList(Predicate<Person> predicate);

    /**
     * Returns true if the model has previous address book states to restore.
     */
    boolean canUndoAddressBook();

    /**
     * Returns true if the model has undone address book states to restore.
     */
    boolean canRedoAddressBook();

    /**
     * Restores the model's address book to its previous state.
     */
    void undoAddressBook();

    /**
     * Restores the model's address book to its previously undone state.
     */
    void redoAddressBook();

    /**
     * Saves the current address book state for undo/redo.
     */
    void commitAddressBook();

    /**
     * Restores the model's archive book to its previous state.
     */
    void undoArchiveBook();

    /**
     * Restores the model's archive book to its previously undone state.
     */
    void redoArchiveBook();

    /**
     * Saves the current archive book state for undo/redo.
     */
    void commitArchiveBook();

    /**
     * Restores the model's pin book to its previous state.
     */
    void undoPinBook();

    /**
     * Restores the model's pin book to its previously undone state.
     */
    void redoPinBook();

    /**
     * Saves the current pin book state for undo/redo.
     */
    void commitPinBook();

    /**
     * Selected person in the filtered person list.
     * null if no person is selected.
     */
    ReadOnlyProperty<Person> selectedPersonProperty();

    /**
     * Returns the selected person in the filtered person list.
     * null if no person is selected.
     */
    Person getSelectedPerson();

    /**
     * Sets the selected archived person in the filtered archived person list.
     */
    void setSelectedPerson(Person person);

    /**
     * Selected person in the filtered pin list.
     * null if no person is selected.
     */
    ReadOnlyProperty<Person> selectedPinPersonProperty();

    /**
     * Returns the selected person in the filtered pin list.
     * null if no person is selected.
     */
    Person getSelectedPinPerson();

    /**
     * Sets the selected person in the filtered pin list.
     */
    void setSelectedPinPerson(Person person);

    /**
     * Selected archived person in the filtered archived person list.
     * null if no archived person is selected.
     */
    ReadOnlyProperty<Person> selectedArchivedPersonProperty();

    /**
     * Returns the selected archived person in the filtered archived person list.
     * null if no archived person is selected.
     */
    Person getSelectedArchivedPerson();

    /**
     * Sets the selected archived person in the filtered archived person list.
     */
    void setSelectedArchivedPerson(Person person);

}
