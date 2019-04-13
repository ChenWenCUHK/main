package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RENTALPRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SELLINGPRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE_BUYER = "Parameters for buyer: " + PREFIX_CUSTOMER + "CUSTOMER_TYPE "
            + PREFIX_NAME + "NAME " + PREFIX_PHONE + "PHONE " + PREFIX_EMAIL + "EMAIL " + PREFIX_REMARK + "REMARK \n"
            + "Example for buyer: " + COMMAND_WORD + " " + PREFIX_CUSTOMER + "buyer " + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 " + PREFIX_EMAIL + "johnd@example.com " + PREFIX_REMARK + "I am a buyer\n";

    public static final String MESSAGE_USAGE_SELLER = "Parameters for seller: " + PREFIX_CUSTOMER + "CUSTOMER_TYPE "
            + PREFIX_NAME + "NAME " + PREFIX_PHONE + "PHONE " + PREFIX_EMAIL + "EMAIL " + PREFIX_REMARK + "REMARK "
            + PREFIX_ADDRESS + "ADDRESS " + PREFIX_SELLINGPRICE + "SELLING_PRICE " + "[" + PREFIX_TAG + "TAG" + "]...\n"
            + "Example for seller: " + COMMAND_WORD + " " + PREFIX_CUSTOMER + "seller " + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 " + PREFIX_EMAIL + "johnd@example.com " + PREFIX_REMARK + "I am a seller "
            + PREFIX_ADDRESS + "Clementi Rd, S123456 " + PREFIX_SELLINGPRICE + "500000 " + PREFIX_TAG + "MRT\n";

    public static final String MESSAGE_USAGE_LANDLORD = "Parameters for landlord: " + PREFIX_CUSTOMER + "CUSTOMER_TYPE "
            + PREFIX_NAME + "NAME " + PREFIX_PHONE + "PHONE " + PREFIX_EMAIL + "EMAIL " + PREFIX_REMARK + "REMARK "
            + PREFIX_ADDRESS + "ADDRESS " + PREFIX_RENTALPRICE + "RENTAL_PRICE " + "[" + PREFIX_TAG + "TAG" + "]...\n"
            + "Example for landlord: " + COMMAND_WORD + " " + PREFIX_CUSTOMER + "landlord " + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 " + PREFIX_EMAIL + "johnd@example.com " + PREFIX_REMARK + "I am a landlord "
            + PREFIX_ADDRESS + "Clementi Rd, S123456 " + PREFIX_RENTALPRICE + "2000 " + PREFIX_TAG + "MRT \n";

    public static final String MESSAGE_USAGE_TENANT = "Parameters for tenant: " + PREFIX_CUSTOMER + "CUSTOMER_TYPE "
            + PREFIX_NAME + "NAME " + PREFIX_PHONE + "PHONE " + PREFIX_EMAIL + "EMAIL " + PREFIX_REMARK + "REMARK \n"
            + "Example for tenant: " + COMMAND_WORD + " " + PREFIX_CUSTOMER + "tenant " + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 " + PREFIX_EMAIL + "johnd@example.com " + PREFIX_REMARK + "I am a tenant\n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. \n"
            + MESSAGE_USAGE_BUYER + MESSAGE_USAGE_SELLER + MESSAGE_USAGE_LANDLORD + MESSAGE_USAGE_TENANT;

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_IDENTITY_FIELD = "Customer with one or more duplicate identity "
            + "field found in addressbook";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";


    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        } else if (model.hasSameIdentityField(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_IDENTITY_FIELD);
        }

        model.addPerson(toAdd);
        model.commitAddressBook();
        model.commitArchiveBook();
        model.commitPinBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean requiresMainList() {
        return true;
    }

    @Override
    public boolean requiresArchiveList() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
