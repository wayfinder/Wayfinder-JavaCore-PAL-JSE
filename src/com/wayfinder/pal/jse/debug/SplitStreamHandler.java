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
package com.wayfinder.pal.jse.debug;

import java.io.OutputStream;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class SplitStreamHandler extends Handler {

    private final Handler m_infoHandler;
    private final Handler m_errorHandler;
    
    
    public SplitStreamHandler(OutputStream infoStream, boolean closeInfo, OutputStream errorStream, boolean closeError) {
        m_infoHandler = new InternalConsoleHandler(infoStream, closeInfo, Level.FINEST);
        m_errorHandler = new InternalConsoleHandler(errorStream, closeError, Level.WARNING);
    }
    
    
    public SplitStreamHandler() {
        this(System.out, false, System.err, false);
    }
    
    
    
    @Override
    public void close() throws SecurityException {
        m_infoHandler.close();
        m_errorHandler.close();
    }
    

    @Override
    public void flush() {
        m_infoHandler.flush();
        m_errorHandler.flush();
    }
    

    @Override
    public void publish(LogRecord record) {
        if(record.getLevel().intValue() < m_errorHandler.getLevel().intValue()) {
            m_infoHandler.publish(record);
        } else {
            m_errorHandler.publish(record);
        }
    }

}
