class App {
    static DOMAIN_SERVER = "http://localhost:1502";
    static CUSTOMER_API = this.DOMAIN_SERVER + "/customers";
    static DEPOSIT_API = this.DOMAIN_SERVER + "/deposits";
    static WITHDRAW_API = this.DOMAIN_SERVER + "/withdraws";
    static TRANSFER_API = this.DOMAIN_SERVER + "/transfers";
}

class Customer {
    constructor(id, fullName, email, phone, address, balance, deleted) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
        this.deleted = deleted;
    }
}

class Withdraw {
    constructor(id, customerId, transactionAmount) {
        this.id = id;
        this.customerId = customerId;
        this.transactionAmount = transactionAmount;
    }
}

class Deposit {
    constructor(id, customerId, transactionAmount) {
        this.id = id;
        this.customerId = customerId;
        this.transactionAmount = transactionAmount;
    }
}

class Transfer {
    constructor(id, senderId, transferAmount, fees, feesAmount, transactionAmount, recipientId) {
        this.id = id;
        this.senderId = senderId;
        this.transferAmount = transferAmount;
        this.fees = fees;
        this.feesAmount = feesAmount;
        this.transactionAmount = transactionAmount;
        this.recipientId = recipientId;
    }
}