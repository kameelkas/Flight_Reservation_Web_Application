class Customer{
    private int customerID;
    private String name;
    private String emailAddr;
    private String phoneNum;

    public Customer(int cID, String name, String email, String phone){
        this.customerID = cID;
        this.name = name;
        this. emailAddr = email;
        this.phoneNum = phone;
    }

    public int getCustomerID(){
        return this.customerID;
    }

    public String getCustomerName(){
        return this.name;
    }

}