// IAccount Interface
public interface IAccount {
    void Deposit(double amount);
    double Withdraw(double amount);
    double GetCurrentBalance();
    int GetAccountNumber();
}

// Abstract Account Base Class
abstract class Account implements IAccount {
    protected int accountNumber;
    protected double balance;

    public Account(int accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    @Override
    public int GetAccountNumber() {
        return accountNumber;
    }

    @Override
    public double GetCurrentBalance() {
        return balance;
    }

    @Override
    public void Deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}

// StandardAccount Class
class StandardAccount extends Account {
    private double creditLimit;

    public StandardAccount(int accountNumber, double creditLimit) {
        super(accountNumber);
        this.creditLimit = creditLimit < 0 ? creditLimit : 0;
    }

    @Override
    public double Withdraw(double amount) {
        double maxWithdrawal = balance - creditLimit;
        double amountToWithdraw = Math.min(amount, maxWithdrawal);
        balance -= amountToWithdraw;
        return amountToWithdraw;
    }
}

// BasicAccount Class
class BasicAccount extends Account {
    private double withdrawalLimit;

    public BasicAccount(int accountNumber, double withdrawalLimit) {
        super(accountNumber);
        this.withdrawalLimit = Math.max(0, withdrawalLimit);
    }

    @Override
    public double Withdraw(double amount) {
        double amountToWithdraw = Math.min(amount, Math.min(balance, withdrawalLimit));
        balance -= amountToWithdraw;
        return amountToWithdraw;
    }
}

// PremiumAccount Class
class PremiumAccount extends Account {
    public PremiumAccount(int accountNumber) {
        super(accountNumber);
    }

    @Override
    public double Withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return amount;
        }
        double amountToWithdraw = balance;
        balance = 0;
        return amountToWithdraw;
    }
}

// Bank Class
import java.util.ArrayList;
import java.util.List;

class Bank {
    private List<Account> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    public void OpenAccount(Account account) {
        accounts.add(account);
    }

    public void CloseAccount(int accountNumber) {
        Account account = accounts.stream()
                                  .filter(a -> a.GetAccountNumber() == accountNumber)
                                  .findFirst()
                                  .orElse(null);

        if (account != null) {
            if (account.GetCurrentBalance() >= 0) {
                accounts.remove(account);
            } else {
                System.out.println("Cannot close account due to negative balance.");
            }
        }
    }

    public List<Account> GetAllAccounts() {
        return new ArrayList<>(accounts);
    }

    public List<Account> GetAllAccountsInDebt() {
        List<Account> inDebt = new ArrayList<>();
        for (Account account : accounts) {
            if (account.GetCurrentBalance() < 0) {
                inDebt.add(account);
            }
        }
        return inDebt;
    }

    public List<Account> GetAllAccountsWithBalance(double minBalance) {
        List<Account> result = new ArrayList<>();
        for (Account account : accounts) {
            if (account.GetCurrentBalance() > minBalance) {
                result.add(account);
            }
        }
        return result;
    }
}