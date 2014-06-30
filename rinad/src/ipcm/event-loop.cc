/*
 * Event loop over librina
 *
 *    Vincenzo Maffione <v.maffione@nextworks.it>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

#include <iostream>
#include <map>

#include <librina/common.h>
#include "event-loop.h"

void
EventLoop::register_event(rina::IPCEventType type, EventHandler handler)
{
        handlers[type] = handler;
}

void
EventLoop::run()
{
        for (;;) {
                rina::IPCEvent *event = rina::ipcEventProducer->eventWait();
                rina::IPCEventType ty;

                if (!event) {
                        std::cerr << "Null event received" << std::endl;
                        break;
                }

                ty = event->getType();
                if (handlers.count(ty) && handlers[ty]) {
                        handlers[ty](event, data_model);
                }
        }
}
