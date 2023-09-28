package com.NPU.深入浅出java多线程.chapter2_1;

import java.security.*;

/**
 * @author hjn
 * @date 2021.5.8
 * @modify 2021.5.8
 */
public abstract class SafeRunnable implements Runnable {

    public abstract void protectedRun();

    @Override
    public final void run() {
        CodeSource nullSource = new CodeSource(null, (CodeSigner[]) null);
        PermissionCollection noPerms = new Permissions();
        ProtectionDomain domain = new ProtectionDomain(nullSource, noPerms);
        AccessControlContext safeContext = new AccessControlContext(
                new ProtectionDomain[] { domain });

        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                protectedRun();
                return null;
            }
        }, safeContext);
    }

    public static void main(String[] args) {
        // Turn on the security management
        SecurityManager sm = new SecurityManager();
        System.setSecurityManager(sm);

        new Thread(new SafeRunnable() {
            @Override
            public void protectedRun() {
                // friendly operation
                System.out.println("hello");
            }
        }).start();

        new Thread(new SafeRunnable() {
            @Override
            public void protectedRun() {
                // malicious operation
                System.exit(0);
            }
        }).start();
    }
}
