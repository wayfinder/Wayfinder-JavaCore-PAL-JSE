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
package com.wayfinder.pal.jse.graphics;

import java.awt.Font;
import java.awt.font.FontRenderContext;

import com.wayfinder.pal.graphics.WFFont;

public class J2seFont implements WFFont {
	
	Font font;
	
	public J2seFont(int aWFSize, int aWFStyle) {				
		int style = convertToJ2seStyle(aWFStyle);
		int size = convertToPixelSize(aWFSize);
		font = new Font("Arial", style, size);		
	}
	
	Font getJ2SEFont() {
		return font;
	}

	@Override
	public int getFontHeight() {
		return font.getSize();
	}

	@Override
	public int getStringWidth(String str) {
		return (int)font.getStringBounds(str, new FontRenderContext(font.getTransform(), false, false)).getWidth();		
	}

	@Override
	public int getStyle() {
		return 0;
	}
	
	private static int convertToPixelSize(int aWFSize) {
        switch(aWFSize) {
	        case WFFont.SIZE_SMALL:       return 12;
	        case WFFont.SIZE_MEDIUM:      return 18;
	        case WFFont.SIZE_LARGE:       return 21;
	        case WFFont.SIZE_VERY_LARGE:  return 40;
        }
        throw new IllegalArgumentException("WFont size " + aWFSize + " is not a proper size");
    }
	
	private static int convertToJ2seStyle(int aWFStyle) {
		final int seStyle;
        if(aWFStyle != WFFont.STYLE_PLAIN) {
            final boolean isBold = (aWFStyle & WFFont.STYLE_BOLD) == WFFont.STYLE_BOLD;
            final boolean isItalic = (aWFStyle & WFFont.STYLE_ITALIC) == WFFont.STYLE_ITALIC;
//            final boolean isUnderlined = (aWFStyle & WFFont.STYLE_UNDERLINE) == WFFont.STYLE_UNDERLINE;
            if(isBold && isItalic) {
                seStyle = Font.BOLD;
            } else if(isBold) {
                seStyle = Font.BOLD;
            } else if(isItalic) {
                seStyle = Font.ITALIC;
            } else {
                seStyle = Font.PLAIN;
            }            
        } else {
            seStyle = Font.PLAIN;
        }
        return seStyle;
	}

}
