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

package drink.beer.misc.event;

import drink.beer.Client;
import drink.beer.misc.event.imp.Event;
import drink.beer.misc.event.imp.Priority;
import drink.beer.misc.plugin.PluginEntryPoint;

import java.io.Serial;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author DarkMagician6
 * @since 02-02-2014
 */

public final class EventHandler {

    private final HashMap<Class<? extends Event>, List<MethodData>> REGISTRY_MAP = new HashMap<>();

    public EventHandler() {
        Client.INSTANCE.LOGGER.info("EventHandler");
    }

    @PluginEntryPoint
    public void register(Object object) {
        for (final Method method : object.getClass().getDeclaredMethods()) {
            if (!isMethodBad(method)) {
                register(method, object);
            }
        }
    }

    @PluginEntryPoint
    public void unregister(Object object) {
        for (final List<MethodData> dataList : this.REGISTRY_MAP.values()) {
            dataList.removeIf(data -> data.getSource().equals(object));
        }

        cleanMap(true);
    }

    @SuppressWarnings("UnusedReturnValue")
    @PluginEntryPoint
    public Event call(final Event event) {
        List<MethodData> dataList = this.REGISTRY_MAP.get(event.getClass());

        if (dataList != null) {
            for (final MethodData data : dataList) {
                invoke(data, event);
            }
        }

        return event;
    }

    @SuppressWarnings("unchecked")
    private void register(Method method, Object object) {
        Class<? extends Event> indexClass = (Class<? extends Event>) method.getParameterTypes()[0];

        final MethodData data = new MethodData(object, method, method.getAnnotation(EventTarget.class).value());

        if (!data.getTarget().canAccess(object)) {
            data.getTarget().setAccessible(true);
        }

        if (this.REGISTRY_MAP.containsKey(indexClass)) {
            if (!this.REGISTRY_MAP.get(indexClass).contains(data)) {
                this.REGISTRY_MAP.get(indexClass).add(data);
                sortListValue(indexClass);
            }
        } else {
            this.REGISTRY_MAP.put(indexClass, new CopyOnWriteArrayList<>() {
                @Serial
                private static final long serialVersionUID = 666L;

                {
                    add(data);
                }
            });
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void cleanMap(boolean onlyEmptyEntries) {
        Iterator<Map.Entry<Class<? extends Event>, List<MethodData>>> mapIterator = this.REGISTRY_MAP.entrySet().iterator();

        while (mapIterator.hasNext()) {
            if (!onlyEmptyEntries || mapIterator.next().getValue().isEmpty()) {
                mapIterator.remove();
            }
        }
    }

    private void invoke(MethodData data, Event argument) {
        try {
            data.getTarget().invoke(data.getSource(), argument);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ignored) {
            Client.INSTANCE.LOGGER.warn("Failed to invoke event!");
        }
    }

    private void sortListValue(Class<? extends Event> indexClass) {
        List<MethodData> sortedList = new CopyOnWriteArrayList<>();

        for (final byte priority : Priority.STANDARD_VALUES) {
            for (final MethodData data : this.REGISTRY_MAP.get(indexClass)) {
                if (data.getPriority() == priority) {
                    sortedList.add(data);
                }
            }
        }

        this.REGISTRY_MAP.put(indexClass, sortedList);
    }

    private boolean isMethodBad(Method method) {
        return method.getParameterTypes().length != 1 || !method.isAnnotationPresent(EventTarget.class);
    }

    private static final class MethodData {

        private final Object source;
        private final Method target;
        private final byte priority;

        public MethodData(Object source, Method target, byte priority) {
            this.source = source;
            this.target = target;
            this.priority = priority;
        }

        public Object getSource() {
            return this.source;
        }

        public Method getTarget() {
            return this.target;
        }

        public byte getPriority() {
            return this.priority;
        }
    }
}
