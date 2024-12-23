# BankAccountSystem
Ori Paz HW1 Mobile Apps Development 2024/5
Project Overview
The project simulates a banking system for managing different types of accounts, providing basic banking functionalities.
There are three account types in the system:
Standard Account: Includes a credit limit feature.
Basic Account: Includes a daily withdrawal limit.
Premium Account: Allows unrestricted withdrawals up to the available balance.
The system supports account management (opening, closing), depositing money, withdrawing funds, filtering accounts by balance or debt status, and viewing all accounts.
How the Project Was Built
The project was implemented in Java, following OOP principles:
The IAccount interface defines the contract between classes, including methods like Deposit, Withdraw, and GetCurrentBalance.
The abstract Account class implements the interface and provides a shared foundation for all account types. It includes shared logic like balance management and account number handling.
Each class extending Account implements unique functionalities. For instance:
The StandardAccount class implements a credit limit feature.
The BasicAccount class enforces a daily withdrawal limit.
The PremiumAccount class allows unrestricted withdrawals up to the current balance.
The Bank class manages all accounts, handles the creation and closure of accounts, and allows filtering and organizing accounts based on specified criteria.
Challenges and Solutions
Managing Account-Specific Constraints
Challenge: Designing withdrawal logic that respects the unique constraints of each account type without duplicating code unnecessarily.
Solution: Overriding the Withdraw method in each account type to tailor behavior while leveraging shared functionality in the base Account class.
Closing Accounts with Debt
Challenge: Ensuring accounts can only be closed if their balance is zero or positive, preventing errors in cases of debt.
Solution: Implementing a method that scans the list of accounts and checks the balance before proceeding with closure.
Developing Flexible Account Filtering
Challenge: Enabling flexible filtering of accounts based on various criteria, such as minimum balance or accounts in debt.
Solution: Adding dedicated methods in the Bank class that return filtered lists based on specific conditions.
