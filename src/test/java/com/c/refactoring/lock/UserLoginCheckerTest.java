package com.c.refactoring.lock;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginCheckerTest {
    UserLoginChecker userLoginChecker = new UserLoginChecker();

    @Test
    void testisUserAllowedToLogin_DifferentUserTriesImmediatelyAfter() {
        Object[] access = new Object[]{"TEST_USER_ID_1", new Date()};
        Lock lock = userLoginChecker.isUserAllowedToLogin(true, new User(
                "TEST_USER_ID_2"), Arrays.asList(new Object[][]{access}));
        assertTrue(lock.isReadAccess());
        assertNotNull(lock.getLockReason());
    }

    @Test
    void testisUserAllowedToLogin_SameUserReturnsToFirstScreen() {
        Object[] access = new Object[]{"TEST_USER_ID", new Date()};
        Lock lock = userLoginChecker.isUserAllowedToLogin(true, new User(
                "TEST_USER_ID"), Arrays.asList(new Object[][]{access}));
        assertFalse(lock.isReadAccess());
        assertNull(lock.getLockReason());
    }

    @Test
    void testisUserAllowedToLogin_SameUserReturnsToSecondScreen() {
        Object[] access = new Object[]{"TEST_USER_ID", new Date()};
        Lock lock = userLoginChecker.isUserAllowedToLogin(false, new User(
                "TEST_USER_ID"), Arrays.asList(new Object[][]{access}));
        assertFalse(lock.isReadAccess());
        assertNull(lock.getLockReason());
    }

    @Test
    void testisUserAllowedToLogin_User2TriesToLoginToFirstScreen3hoursAfterUser1() {
        Object[] access = new Object[]{"TEST_USER_ID_1", threeHoursBefore()};
        Lock lock = userLoginChecker.isUserAllowedToLogin(true, new User(
                "TEST_USER_ID_2"), Arrays.asList(new Object[][]{access}));
        assertFalse(lock.isReadAccess());
        assertNull(lock.getLockReason());
    }

    @Test
    void testisUserAllowedToLogin_User2TriesToLoginToSecondScreen3hoursAfterUser1() {
        Object[] access = new Object[]{"TEST_USER_ID_1", threeHoursBefore()};
        Lock lock = userLoginChecker.isUserAllowedToLogin(false, new User(
                "TEST_USER_ID_2"), Arrays.asList(new Object[][]{access}));
        assertTrue(lock.isReadAccess());
        assertNotNull(lock.getLockReason());
    }

    private Date threeHoursBefore() {
        Date now = new Date();
        return new Date(now.getTime() - 3 * 60 * 60 * 1000);
    }

}
