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

import java.net.URI;
import java.net.URISyntaxException;

import com.wayfinder.pal.network.http.HttpHost;

final class JseHttpHost implements HttpHost {
    
    private final URI m_uri;
    
    JseHttpHost(String hostName, int port) throws URISyntaxException {
        m_uri = new URI(DEFAULT_SCHEME_NAME, null, hostName, port, null, null, null);
    }
    
    
    /* (non-Javadoc)
     * @see com.wayfinder.pal.network.http.HttpHost#getHostName()
     */
    @Override
    public String getHostName() {
        return m_uri.getHost();
    }

    
    /* (non-Javadoc)
     * @see com.wayfinder.pal.network.http.HttpHost#getPort()
     */
    @Override
    public int getPort() {
        return m_uri.getPort();
    }

    
    /* (non-Javadoc)
     * @see com.wayfinder.pal.network.http.HttpHost#getSchemeName()
     */
    @Override
    public String getSchemeName() {
        return m_uri.getScheme();
    }

    
    /* (non-Javadoc)
     * @see com.wayfinder.pal.network.http.HttpHost#toURI()
     */
    @Override
    public String toURI() {
        return m_uri.toString();
    }
    
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return m_uri.toString();
    }
    
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof JseHttpHost) {
            return m_uri.equals(((JseHttpHost) obj).m_uri);
        }
        return false;
    }
    
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return m_uri.hashCode();
    }

}
