/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Kevin Driessen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package drink.beer.misc.event.imp;

/**
 * @author DarkMagician6
 * @since 08-03-2013
 */

public final class Priority {

    public static final byte HIGHEST = 0;
    public static final byte HIGH = 1;
    public static final byte MEDIUM = 2;
    public static final byte LOW = 3;
    public static final byte LOWEST = 4;

    public static final byte[] STANDARD_VALUES = new byte[]{
            HIGHEST,
            HIGH,
            MEDIUM,
            LOW,
            LOWEST
    };
}