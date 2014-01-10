/*
 * RMT (Relaying and Multiplexing Task)
 *
 *    Francesco Salvestrini <f.salvestrini@nextworks.it>
 *    Miquel Tarzan         <miquel.tarzan@i2cat.net>
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

#ifndef RINA_RMT_H
#define RINA_RMT_H

#include "common.h"
#include "du.h"
#include "efcp.h"
#include "ipcp-factories.h"

struct rmt;

/*
 * NOTEs:
 *
 * QoS-id - An identifier unambiguous within this DIF that identifies a
 * QoS-hypercube. As QoS-cubes are created they are sequentially enumerated.
 * QoS-id is an element of Data Transfer PCI that may be used by the RMT to
 * classify PDUs.
 *
 * RMT - This task is an element of the data transfer function of a DIF.
 * Logically, it sits between the EFCP and SDU Protection.  RMT performs the
 * real time scheduling of sending PDUs on the appropriate (N-1)-ports of the
 * (N-1)-DIFs available to the RMT.
 */

/* NOTE: There's one RMT for each IPC Process */

struct rmt * rmt_create(struct ipcp_instance *  parent,
                        struct kfa *            kfa,
                        struct efcp_container * efcpc);
int          rmt_destroy(struct rmt * instance);

int          rmt_address_set(struct rmt * instance,
                             address_t    address);

int          rmt_queue_send_add(struct rmt * instance,
                                port_id_t    id);
int          rmt_queue_send_delete(struct rmt * instance,
                                   port_id_t    id);

int          rmt_queue_recv_add(struct rmt * instance,
                                port_id_t    id);
int          rmt_queue_recv_delete(struct rmt * instance,
                                   port_id_t    id);

/* FIXME: Please check the following API */
int          rmt_send(struct rmt * instance,
                      address_t    address,
                      cep_id_t     connection_id,
                      struct pdu * pdu);

int          rmt_send_port_id(struct rmt *  instance,
                              port_id_t     id,
                              struct pdu *  pdu);

/* FIXME: Please check the following API */
int          rmt_receive(struct rmt * instance,
                         struct sdu * sdu,
                         port_id_t    from);

/*PFT proxy API*/
int          rmt_pdu_fte_add(struct rmt *       instance,
                             struct list_head * pft_entries);
int          rmt_pdu_fte_remove(struct rmt *       instance,
                                struct list_head * pft_entries);

#endif
