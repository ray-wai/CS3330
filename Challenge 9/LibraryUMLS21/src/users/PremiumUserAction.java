/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import java.util.ArrayList;

/**
 *
 * @author Junlin Wang, TA at cs3330@missouri.edu
 */

public interface PremiumUserAction {
    void borrowLaptop(String laptopId);
    String activityNotice(String noticeInfo);
    UserType applySpecialUserId(String specialUserId);
    Double rentConferenceRoom(String confRoomId, Double price, Double moneyPaid);
}