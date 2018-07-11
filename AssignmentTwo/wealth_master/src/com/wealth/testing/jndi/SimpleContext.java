package com.wealth.testing.jndi;

import java.util.Hashtable;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

public class SimpleContext implements Context {

    protected final static NameParser myParser = new SimpleParser();

    private Hashtable table = new Hashtable();

    public Object lookup(Name name) throws NamingException {
        throw new UnsupportedOperationException("Method lookup(Name name) not yet implemented!");
    }

    public Object lookup(String name) throws NamingException {
        //System.out.println("LOOKUP :: table=[" + table + "]");
        return this.table.get(name);
    }

    public void bind(Name name, Object object) throws NamingException {
        throw new UnsupportedOperationException("Method bind(Name name, Object object) not yet implemented!");
    }

    public void bind(String name, Object object) throws NamingException {
        this.table.put(name, object);
        //System.out.println("BIND :: table=[" + table + "]");
    }

    public void rebind(Name name, Object object) throws NamingException {
        // throw new UnsupportedOperationException("Method rebind(Name name,
        // Object object) not yet implemented!");
        //System.out.println("Something called rebind(Name name, Object object)!");
    }

    public void rebind(String name, Object object) throws NamingException {
        throw new UnsupportedOperationException("Method rebind(String name, Object object) not yet implemented!");
    }

    public void unbind(Name name) throws NamingException {
        throw new UnsupportedOperationException("Method unbind(Name name) not yet implemented!");
    }

    public void unbind(String name) throws NamingException {
        this.table.remove(name);
    }

    public void rename(Name oldName, Name newName) throws NamingException {
        throw new UnsupportedOperationException("Method rename(Name oldName, Name newName) not yet implemented!");
    }

    public void rename(String oldName, String newName) throws NamingException {
        throw new UnsupportedOperationException("Method rename(String oldName, String newName) not yet implemented!");
    }

    public NamingEnumeration list(Name name) throws NamingException {
        throw new UnsupportedOperationException("Method list(Name name) not yet implemented!");
    }

    public NamingEnumeration list(String name) throws NamingException {
        throw new UnsupportedOperationException("Method list(String name) not yet implemented!");
    }

    public NamingEnumeration listBindings(Name name) throws NamingException {
        throw new UnsupportedOperationException("Method listBindings(Name name) not yet implemented!");
    }

    public NamingEnumeration listBindings(String name) throws NamingException {
        throw new UnsupportedOperationException("Method listBindings(String name) not yet implemented!");
    }

    public void destroySubcontext(Name name) throws NamingException {
        throw new UnsupportedOperationException("Method destroySubcontext(Name name) not yet implemented!");
    }

    public void destroySubcontext(String name) throws NamingException {
        throw new UnsupportedOperationException("Method destroySubcontext(String name) not yet implemented!");
    }

    public Context createSubcontext(Name name) throws NamingException {
        throw new UnsupportedOperationException("Method createSubcontext(Name name) not yet implemented!");
    }

    public Context createSubcontext(String name) throws NamingException {
        throw new UnsupportedOperationException("Method createSubcontext(String name) not yet implemented!");
    }

    public Object lookupLink(Name name) throws NamingException {
        throw new UnsupportedOperationException("Method lookupLink(Name name) not yet implemented!");
    }

    public Object lookupLink(String name) throws NamingException {
        throw new UnsupportedOperationException("Method lookupLink(String name) not yet implemented!");
    }

    public NameParser getNameParser(Name name) throws NamingException {
        // throw new UnsupportedOperationException("Method getNameParser(Name
        // name) not yet implemented!");
        // Do lookup to verify that the name exists
        Object obj = lookup(name);
        if (obj instanceof Context) {
            ((Context)obj).close();
        }
        return myParser;
    }

    public NameParser getNameParser(String name) throws NamingException {
        // throw new UnsupportedOperationException("Method getNameParser(String
        // name) not yet implemented!");
        // Do lookup to verify that the name exists
        Object obj = lookup(name);
        if (obj instanceof Context) {
            ((Context)obj).close();
        }
        return myParser;
    }

    public Name composeName(Name name, Name prefix) throws NamingException {
        throw new UnsupportedOperationException("Method composeName(Name name, Name prefix) not yet implemented!");
    }

    public String composeName(String name, String prefix) throws NamingException {
        throw new UnsupportedOperationException("Method composeName(String name, String prefix) not yet implemented!");
    }

    public Object addToEnvironment(String propName, Object propVal) throws NamingException {
        throw new UnsupportedOperationException("Method addToEnvironment(String propName, Object propVal) not yet implemented!");
    }

    public Object removeFromEnvironment(String propName) throws NamingException {
        throw new UnsupportedOperationException("Method removeFromEnvironment(String propName) not yet implemented!");
    }

    public Hashtable getEnvironment() throws NamingException {
        throw new UnsupportedOperationException("Method getEnvironment() not yet implemented!");
    }

    public void close() throws NamingException {
        throw new UnsupportedOperationException("Method close() not yet implemented!");
    }

    public String getNameInNamespace() throws NamingException {
        throw new UnsupportedOperationException("Method getNameInNamespace() not yet implemented!");
    }
}