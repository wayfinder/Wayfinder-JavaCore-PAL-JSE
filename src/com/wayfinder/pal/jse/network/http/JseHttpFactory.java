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

import java.net.URISyntaxException;

import com.wayfinder.pal.network.http.HttpEntityEnclosingRequest;
import com.wayfinder.pal.network.http.HttpFactory;
import com.wayfinder.pal.network.http.HttpHost;
import com.wayfinder.pal.network.http.HttpRequest;

public final class JseHttpFactory implements HttpFactory {
    
    private final HttpConfiguration m_netConfig;
    
    JseHttpFactory() {
        m_netConfig = new HttpConfiguration();
    }
    
    
    /**
     * Returns the {@link HttpConfiguration} used for configuring the http
     * connections used through the Core
     * 
     * @return
     */
    public HttpConfiguration getNetConfig() {
        return m_netConfig;
    }
    
    
    //-------------------------------------------------------------------------
    // HttpFactory ifc
    

    /* (non-Javadoc)
     * @see com.wayfinder.pal.network.http.HttpFactory#newHttpGetRequest(java.lang.String)
     */
    @Override
    public HttpRequest newHttpGetRequest(String uri) {
        return new JseHttpRequest(m_netConfig, uri);
    }
    
    
    /* (non-Javadoc)
     * @see com.wayfinder.pal.network.http.HttpFactory#newHttpPostRequest(java.lang.String)
     */
    @Override
    public HttpEntityEnclosingRequest newHttpPostRequest(String uri) {
        return new JseHttpRequest(m_netConfig, uri);
    }
    

    /* (non-Javadoc)
     * @see com.wayfinder.pal.network.http.HttpFactory#newHttpHost(java.lang.String, int)
     */
    @Override
    public HttpHost newHttpHost(String hostname, int port) {
        try {
            return new JseHttpHost(hostname, port);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e.getReason());
        }
    }

}
