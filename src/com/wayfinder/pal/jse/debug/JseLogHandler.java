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

import java.util.logging.Level;
import java.util.logging.Logger;

import com.wayfinder.pal.debug.LogHandler;
import com.wayfinder.pal.debug.LogMessage;

public class JseLogHandler implements LogHandler {
    
    public final static String LOGGER_STR = "com.wayfinder.pal.jse";
    private final Logger m_logger;
    
    public JseLogHandler() {
        m_logger = Logger.getLogger(LOGGER_STR);
        m_logger.addHandler(new SplitStreamHandler());
        m_logger.setUseParentHandlers(false);
        m_logger.setLevel(Level.FINEST);
    }
    
    
    @Override
    public void writeMessageToPlatformLog(LogMessage message) {
        switch(message.getType()) {
        case LogMessage.TYPE_MESSAGE:
            m_logger.log(getLevelFor(message.getLevel()), message.getMethodName() + "::" + message.getMessage());
            break;
            
        case LogMessage.TYPE_EXCEPTION:
            m_logger.log(getLevelFor(message.getLevel()), message.getMethodName(), message.getThrowable());
            break;
        }
    }

    private static Level getLevelFor(com.wayfinder.pal.debug.Level loglevel) {
        switch(loglevel.getIntValue()) {
        case com.wayfinder.pal.debug.Level.VALUE_FATAL:
            return FATAL;
            
        case com.wayfinder.pal.debug.Level.VALUE_ERROR:
            return ERROR;

        case com.wayfinder.pal.debug.Level.VALUE_WARN:
            return Level.WARNING;
            
        case com.wayfinder.pal.debug.Level.VALUE_INFO:
            return Level.INFO;

        case com.wayfinder.pal.debug.Level.VALUE_DEBUG:
            return DEBUG;

        case com.wayfinder.pal.debug.Level.VALUE_TRACE:
            return TRACE;

        default:
            return Level.ALL;
        }
    }
    
    
    /**
     * OFF is a special level that can be used to turn off logging.
     * This level is initialized to <CODE>Integer.MAX_VALUE</CODE>.
     */
    static final Level OFF = Level.OFF;

    /**
     * SEVERE is a message level indicating a serious failure.
     * <p>
     * In general SEVERE messages should describe events that are
     * of considerable importance and which will prevent normal
     * program execution.   They should be reasonably intelligible
     * to end users and to system administrators.
     * This level is initialized to <CODE>1000</CODE>.
     */
    private static final Level FATAL = new WFLevel("FATAL", Level.SEVERE.intValue() + 100);

    
    
    private static final Level ERROR = new WFLevel("ERROR", Level.SEVERE.intValue());
    
    
  
    private static final Level DEBUG = new WFLevel("DEBUG", Level.FINE.intValue());

    
    private static final Level TRACE = new WFLevel("TRACE", Level.FINEST.intValue());
    
    
    private static class WFLevel extends Level {
        private static final long serialVersionUID = 7497192561680153005L;

        WFLevel(String name, int value) {
            super(name, value);
        }
        
        public String getLocalizedName() {
            return getName();
        }
    }


	@Override
	public void startFileLogging() {
		// 
		
	}
}
