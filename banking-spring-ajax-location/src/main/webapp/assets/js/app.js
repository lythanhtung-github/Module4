class App {
    static DOMAIN_SERVER = "http://localhost:8080";
    static CUSTOMER_API = this.DOMAIN_SERVER + "/api/customers";
    static DEPOSIT_API = this.DOMAIN_SERVER + "/api/deposits";
    static WITHDRAW_API = this.DOMAIN_SERVER + "/api/withdraws";
    static TRANSFER_API = this.DOMAIN_SERVER + "/api/transfers";
    static PROVINCE_URL = "https://vapi.vnappmob.com/api/province";
}

class LocationRegion {
    constructor(id, provinceId, provinceName, districtId, districtName, wardId, wardName, address) {
        this.id = id;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.wardId = wardId;
        this.wardName = wardName;
        this.address = address;
    }
}

class Customer {
    constructor(id, fullName, email, phone, locationRegion, balance) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.locationRegion = locationRegion;
        this.balance = balance;
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