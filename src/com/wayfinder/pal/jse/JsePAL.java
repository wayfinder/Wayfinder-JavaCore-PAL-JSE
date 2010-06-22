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
package com.wayfinder.pal.jse;

import com.wayfinder.pal.PAL;
import com.wayfinder.pal.concurrency.ConcurrencyLayer;
import com.wayfinder.pal.debug.LogHandler;
import com.wayfinder.pal.hardwareinfo.HardwareInfo;
import com.wayfinder.pal.jse.concurrency.JseConcurrencyLayer;
import com.wayfinder.pal.jse.debug.JseLogHandler;
import com.wayfinder.pal.jse.hardwareinfo.JseHardwareInfo;
import com.wayfinder.pal.jse.network.JseNetworkLayer;
import com.wayfinder.pal.jse.persistence.JsePersistenceLayer;
import com.wayfinder.pal.jse.positioning.JsePositioningLayer;
import com.wayfinder.pal.jse.softwareinfo.JseSoftwareInfo;
import com.wayfinder.pal.jse.sound.JseSoundLayer;
import com.wayfinder.pal.jse.util.JseUtilFactory;
import com.wayfinder.pal.softwareinfo.SoftwareInfo;
import com.wayfinder.pal.util.UtilFactory;

public class JsePAL extends PAL {
    
    private JsePAL(LogHandler handler, 
            ConcurrencyLayer cLayer, 
            JseNetworkLayer netLayer,
            HardwareInfo hardInfo,
            SoftwareInfo softInfo,
            JsePersistenceLayer perLayer,
            UtilFactory uFactory,
            JseSoundLayer soundLayer,
            JsePositioningLayer posLayer) {
        super(handler, cLayer, netLayer, hardInfo, softInfo, perLayer,uFactory, soundLayer, posLayer);
    }
    

    @Override
    public void requestGC() {
        // System.gc();
    }
    
    
    @Override
    public JseNetworkLayer getNetworkLayer() {
        return (JseNetworkLayer) super.getNetworkLayer();
    }
    
    
    
    public static JsePAL createStandardEditionPAL() {
        return new JsePAL(new JseLogHandler(), 
               new JseConcurrencyLayer(),
               new JseNetworkLayer(),
               new JseHardwareInfo(),
               new JseSoftwareInfo(),
               new JsePersistenceLayer(),
               new JseUtilFactory(),
               new JseSoundLayer(),
               new JsePositioningLayer());
    }
    
}
