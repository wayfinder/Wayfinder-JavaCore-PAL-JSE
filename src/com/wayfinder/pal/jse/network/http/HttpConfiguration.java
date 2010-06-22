/*******************************************************************************
 * Copyright (c) 1999-2010, Vodafone Group Services
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions 
 * are met:
 * 
 *     * Redistributions of source code must retain the above copyright 
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above 
 *       copyright notice, this list of conditions and the following 
 *       disclaimer in the documentation and/or other materials provided 
 *       with the distribution.
 *     * Neither the name of Vodafone Group Services nor the names of its 
 *       contributors may be used to endorse or promote products derived 
 *       from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING 
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY 
 * OF SUCH DAMAGE.
 ******************************************************************************/
package com.wayfinder.pal.jse.network.http;

import java.net.Proxy;


/**
 * Configuration settings for the network handling on Java Standard Edition
 * 
 */
public final class HttpConfiguration {
    
    private Proxy m_proxy;
    private boolean m_keepAlive;
    
    HttpConfiguration() {
        m_proxy = Proxy.NO_PROXY;
        m_keepAlive = true;
    }
    
    
    /**
     * Sets the {@link Proxy} object used for all http connections made by the
     * Core
     * 
     * @param proxy The {@link Proxy} object to use or null if no proxy should
     * be used
     */
    public synchronized void setProxy(Proxy proxy) {
        if(proxy == null) {
            proxy = Proxy.NO_PROXY;
        }
        m_proxy = proxy;
    }
    
    
    /**
     * Returns the proxy object used for all http connections by the core
     * 
     * @return A {@link Proxy} object, will never return <code>null</code>
     */
    synchronized Proxy getProxy() {
        return m_proxy;
    }
    
    
    /**
     * Setting for if the implementation should allow the underlying sockets of
     * the http connections to stay alive for reuse later.
     * <p>
     * This setting is <code>true</code> by default
     * 
     * @param keepAlive true if underlying connections should be kept alive
     */
    public synchronized void setKeepAlive(boolean keepAlive) {
        m_keepAlive = keepAlive;
    }
    
    
    /**
     * Checks to see if underlying sockets should be kept alive
     * 
     * @return <code>true</code> if they should
     */
    public synchronized boolean isKeepAlive() {
        return m_keepAlive;
    }
    
    

}
