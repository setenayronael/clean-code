package com.c.refactoring.lock;

import java.util.Date;
import java.util.List;

public class UserLoginChecker {

    private static final int MAXIMUM_LOCK_TIME = 60 * 60 * 1000;

    /**
     * {@inheritDoc}.
     */
    public Lock isUserAllowedToLogin(boolean isFirstScreen, User userTryingToLogin,
                                     List<Object[]> existingLocks) {

        if (existingLocks.isEmpty() || existingLocks.get(0) == null)
            return createWriteLock();

        Object[] currentLock = existingLocks.get(0);
        String userIdWithLock = (String) currentLock[0];
        Date lockTimestamp = (Date) currentLock[1];

        if (userIdWithLock == null)
            return createWriteLock();

        long timeElapseSinceLock = new Date().getTime() - lockTimestamp.getTime();
        boolean isSameUser = userIdWithLock.equalsIgnoreCase(userTryingToLogin.getUserId());

        if (isSameUser) {
            return createWriteLock();
        }

        if (timeElapseSinceLock > MAXIMUM_LOCK_TIME && isFirstScreen) {
            return createWriteLock();
        }

        return createReadLock(userIdWithLock);

    }

    private Lock createWriteLock() {
        Lock lck = new Lock();
        lck.setRead(false);
        return lck;
    }

    private Lock createReadLock(String userId) {
        String lockMsg = Constants.LOCK_TEXT.replace("@@USER@@", userId);
        Lock lck = new Lock();
        lck.setRead(true);
        lck.setLockReason(lockMsg);
        return lck;
    }
}