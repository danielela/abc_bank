package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement());
    }

    @Test
    public void testOneAccount(){
        Customer daniel = new Customer("Daiel").openAccount(new Account(Account.SAVINGS));
        assertEquals(1, daniel.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer daniel = new Customer("Daniel")
                .openAccount(new Account(Account.SAVINGS));
        daniel.openAccount(new Account(Account.CHECKING));
        //again try to open checking account it should not be added and total account should be 2
        daniel.openAccount(new Account(Account.CHECKING));
        assertEquals(2, daniel.getNumberOfAccounts());
    }

    @Test
    public void testThreeAcounts() {
        Customer daniel = new Customer("Daniel")
                .openAccount(new Account(Account.SAVINGS));
        daniel.openAccount(new Account(Account.CHECKING));
        assertEquals(3, daniel.getNumberOfAccounts());
    }
    
    @Test
    public void testFundTransfer() {
    	 Account checkingAccount = new Account(Account.CHECKING);
	        Account savingsAccount = new Account(Account.SAVINGS);
	        Account maxSavingsAccount = new Account(Account.MAXI_SAVINGS);
	        
        Customer daniel = new Customer("Daniel")
                .openAccount(savingsAccount);
        daniel.openAccount(checkingAccount);
        daniel.openAccount(maxSavingsAccount);
        Bank bank = new Bank();
        bank.addCustomer(daniel);
        checkingAccount.deposit(100.0);
        checkingAccount.deposit(200.0);
        checkingAccount.deposit(400.0);
        checkingAccount.deposit(700.0);
        checkingAccount.deposit(1000.0);
        
        checkingAccount.withdraw(300.00);
        
        savingsAccount.deposit(1000.00);
        savingsAccount.deposit(1500.00);
        
        maxSavingsAccount.deposit(3000.00);
        
        maxSavingsAccount.deposit(2000.00);
       
        bank.totalInterestPaid();
        
        
        assertEquals(5.3020000000000005, transferFundWithinAcc(Account.CHECKING, Account.SAVINGS, 200.00),DOUBLE_DELTA);
    }
    
    
    
    
}
