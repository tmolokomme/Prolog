package com.wealth.testing.ejb;

import javax.naming.Context;
import javax.naming.InitialContext;

public class EJBUnitTestHelper {
    
    public static Object setupEJB(String jndiName, Class ejbClass) throws Exception {
        Object ejbInstance = ejbClass.newInstance();
        Context ctx = new InitialContext();
        ctx.bind(jndiName, ejbInstance);
        return ejbInstance;
    }

    public static void destroyEJB(String jndiName) throws Exception {        
        Context ctx = new InitialContext();
        ctx.unbind(jndiName);
    }
}
