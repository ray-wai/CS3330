/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

/**
 *
 * @author Junlin Wang, TA at cs3330@missouri.edu
 */
public class PremiumUser extends MemberUser implements PremiumUserAction {
    
    private Integer vipLevel;
    public double vipDiscount = 0.7; 
    protected static final String CATEGORY = "PLATINUM";
    
    
    
    public PremiumUser(String username, String password, int vipLevel) {
        super(genUserId(), UserType.VIP, username, password); 
        vipDiscount = vipDiscount - 0.3 * vipLevel;
        this.vipLevel = vipLevel;
    }
    
    protected PremiumUser(String userId, UserType userType, String username, String password, int vipLevel) {
        super(userId, userType, username, password);
        vipDiscount = vipDiscount - 0.3 * vipLevel;
        this.vipLevel = vipLevel;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
    }
   
    public void increaseLevel() {
        if (vipLevel == 10) {
            System.out.println("You already reach top level!");
        }
        else {
            vipLevel++;
            vipDiscount = vipDiscount - 0.05*vipLevel;
        }
    }

    @Override
    public void borrowLaptop(String laptopId) {
        System.out.println("User " + userId + " has rent laptop:" + laptopId);
    }

    @Override
    public String activityNotice(String noticeInfo) {
        String notice = "Activity information " + noticeInfo + " has been rent to:" + emailAddress; 
        
        System.out.println(notice);
        
        return notice; 
    }

    @Override
    public UserType applySpecialUserId(String specialUserId) {
        this.userId = specialUserId;
        return this.userType; 
    }

    @Override
    public Double rentConferenceRoom(String confRoomId, Double price, Double moneyPaid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public double bookPurchase(double bookPrice, double moneyPaid) {
        System.out.println("Book Price: " + bookPrice * vipDiscount);
        
        return super.discountChange(bookPrice, moneyPaid, vipDiscount);
    }

    @Override
    public double coffeePurchase(double coffeePrice, double moneyPaid) {
        System.out.println("Food Price " + coffeePrice * vipDiscount);
        
        return super.discountChange(coffeePrice, moneyPaid, vipDiscount);
    }

    @Override
    public double sandwichPurchase(double foodPrice, double moneyPaid) {
        System.out.println("Food Price " + foodPrice * vipDiscount);
        
        return super.discountChange(foodPrice, moneyPaid, vipDiscount);
    }
    
    @Override
    public String printUserInfo(){
        return String.format("User ID: %s, Username: %s, Password: %s , Vip Level: %d, User Type: " + userType, userId, username, password,vipLevel);
    }  
}