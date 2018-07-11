
package com.wealth.testing.ejb;

import com.wealth.testing.hibernate.HibernateEnabledTestCase;

public class EJBEnabledTestCase extends HibernateEnabledTestCase {
    
    private Object ejbInstance = null;
    private String ejbJNDIName = null;
    private Class ejbClass = null;

    public EJBEnabledTestCase(String name, String ejbJNDIName, Class ejbClass) {
        super(name);
        this.ejbJNDIName = ejbJNDIName;
        this.ejbClass = ejbClass;
    }

    protected void setUp() throws Exception {
        super.setUp();
        this.ejbInstance = EJBUnitTestHelper.setupEJB(this.ejbJNDIName, this.ejbClass);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        EJBUnitTestHelper.destroyEJB(this.ejbJNDIName);
        this.ejbInstance = null;
    }
}